<template>
  <div class="all">
    <section
      v-bind:style="{ 'background-image': 'url(' + backDrop + ')' }"
      class="cont"
    >
      <div @click="closeVideo" v-show="video" class="vid">
        <iframe v :src="video" frameborder="0"></iframe>
      </div>
      <div
        @click="closeVideo"
        class="d-flex flex-column justify-content-center"
      >
        <div class="overlay"></div>
        <div class="movieDetails d-flex justify-content-start">
          <div class="col-6 description flex-fill">
            <h1 class="title">
              {{ title }} <i class="fa-regular fa-bookmark"></i>
            </h1>
            <div class="info d-flex justify-content-start gap-4">
              <p class="rate">
                <i class="fa-solid fa-star imark"></i>{{ rate }}
              </p>
              <p v-for="genre in genres" :key="genre.id" class="data">
                {{ genre.name }}
              </p>
            </div>
            <p class="moviedescription">
              {{ description }}
            </p>
            <button @click="playNow" type="button" class="btn trailer">
              Download Now <i class="fa-solid fa-play"></i>
            </button>
            <button @click="inVid" type="button" class="btn trailer">
              Trailer
            </button>
          </div>
          <div class="col-6">
            <div class="div">
              <h1>Actors</h1>
              <carouselActors :actor="this.actors" />
            </div>
          </div>
        </div>
      </div>
    </section>
    <section
      v-bind:style="{ 'background-image': 'url(' + backDrop + ')' }"
      class="cont"
    >
      <div class="overlay"></div>
      <div class="overlay1"></div>

      <div class="container d-flex flex-column justify-content-end">
        <div
          v-if="isloggedin"
          class="comments d-flex flex-column align-content-start"
        >
          <div class="commentdiv d-flex align-items-baseline">
            <h1 class="commenthead">comment and review</h1>
            <div class="yourrate">
              <i
                class="fa-solid fa-star"
                @mouseover="mouseOver(1)"
                @mousedown="mousedown(1)"
                @mouseleave="mouseleave"
                :style="{ color: usersRate > 0 ? '#EF9E3F' : 'white' }"
              ></i>
              <i
                class="fa-solid fa-star"
                @mouseover="mouseOver(2)"
                @mousedown="mousedown(2)"
                @mouseleave="mouseleave"
                :style="{ color: usersRate > 1 ? '#EF9E3F' : 'white' }"
              ></i>
              <i
                class="fa-solid fa-star"
                @mouseover="mouseOver(3)"
                @mousedown="mousedown(3)"
                @mouseleave="mouseleave"
                :style="{ color: usersRate > 2 ? '#EF9E3F' : 'white' }"
              ></i>
              <i
                class="fa-solid fa-star"
                @mouseover="mouseOver(4)"
                @mousedown="mousedown(4)"
                @mouseleave="mouseleave"
                :style="{ color: usersRate > 3 ? '#EF9E3F' : 'white' }"
              ></i>
              <i
                class="fa-solid fa-star"
                @mouseover="mouseOver(5)"
                @mousedown="mousedown(5)"
                @mouseleave="mouseleave"
                :style="{ color: usersRate > 4 ? '#EF9E3F' : 'white' }"
              ></i>
            </div>
          </div>
          <input
            v-model="text"
            type="text"
            class="form-control thecomment"
            placeholder="comment"
          />
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import carousel from "./carousel.vue";
import carouselActors from "./carouselActors.vue";
import { storeID } from "./id.js";
export default {
  props: ["index", "id"],
  components: {
    carousel,
    carouselActors,
  },
  data() {
    return {
      actors: [],
      video: "",
      cat: "",
      text: "",
      isloggedin: true,
      image: "",
      backDrop: "",
      rate: null,
      usersRate: 0,
      clicked: false,
      title: "",
      description: "",
      comments: [
        { comment: "this movie is cool", user: "pola" },
        { comment: "what the hell have I just watched", user: "hany" },
      ],
      genres: [],
      urlll:"",
      updated:false
    };
  },
  updated() {
    if(!this.updated){
      this.loadData();
      this.actorFetch();
      console.clear();
    }
  },
  methods: {
    async playNow() {
      let newtit=this.title.replaceAll(" ","%20");
      console.log(newtit);
      const url = `https://movie-tv-music-search-and-download.p.rapidapi.com/search?keywords=${newtit}&quantity=40&page=1`;
      const options = {
      method: 'GET',
      headers: {
        'X-RapidAPI-Key': '72b0a49e61msh60849d7630f09f4p1f7053jsn06c00cf2eaaf',
        //backUP:fe9219690cmshc641c468ac1874dp156f63jsna03d6a220172
        'X-RapidAPI-Host': 'movie-tv-music-search-and-download.p.rapidapi.com'
      }
    };

    try {
      const response = await fetch(url, options);
      const result = await response.json();
      this.urlll=result.result[0].torrent;
      console.log(result.result);
    } catch (error) {
      console.error(error);
    }


      window.open(this.urlll, '_blank');
    },
    inVid() {
      const options = {
        method: "GET",
        headers: {
          accept: "application/json",
          Authorization:
            "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwNjQ3YWZiMWU3ZjAwODk2M2M3MTU4MjM5N2VlNjVjMSIsInN1YiI6IjY1NTAxZDM3OTY1M2Y2MDExYmZkYzkzYyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.7PEbDmBQNqrY3JJ7bRgmJV8S8tPVS8ziZ4TkWSJ2IqU",
        },
      };

      fetch(
        `https://api.themoviedb.org/3/movie/${this.id}/videos?language=en-US`,
        options
      )
        .then((response) => response.json())
        .then((response) => {
          for (let i = 0; i < response.results.length; i++) {
            if (response.results[i].type === "Trailer") {
              this.video =
                "https://www.youtube.com/embed/" + response.results[i].key;
            }
          }
        })
        .catch((err) => console.error(err));
    },
    actorFetch() {
      const options = {
        method: "GET",
        headers: {
          accept: "application/json",
          Authorization:
            "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwNjQ3YWZiMWU3ZjAwODk2M2M3MTU4MjM5N2VlNjVjMSIsInN1YiI6IjY1NTAxZDM3OTY1M2Y2MDExYmZkYzkzYyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.7PEbDmBQNqrY3JJ7bRgmJV8S8tPVS8ziZ4TkWSJ2IqU",
        },
      };

      fetch(
        `https://api.themoviedb.org/3/movie/${this.id}/credits?language=en-US`,
        options
      )
        .then((response) => response.json())
        .then((response) => {
          storeID.currMov = response.cast;
          this.actors = storeID.currMov;
        })
        .catch((err) => console.error(err));
        this.updated=false;
    },
    mouseOver(index) {
      if (!this.clicked) {
        this.usersRate = index;
      }
    },
    mouseleave() {
      if (!this.clicked) {
        this.usersRate = 0;
      }
    },
    mousedown(index) {
      this.usersRate = index;
      this.clicked = true;
    },
    closeVideo() {
      this.video = "";
    },
    loadData() {
      let movId = this.id;

      const options = {
        method: "GET",
        headers: {
          accept: "application/json",
          Authorization:
            "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwNjQ3YWZiMWU3ZjAwODk2M2M3MTU4MjM5N2VlNjVjMSIsInN1YiI6IjY1NTAxZDM3OTY1M2Y2MDExYmZkYzkzYyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.7PEbDmBQNqrY3JJ7bRgmJV8S8tPVS8ziZ4TkWSJ2IqU",
        },
      };

      fetch(
        `https://api.themoviedb.org/3/movie/${this.id}?language=en-US`,
        options
      )
        .then((response) => response.json())
        .then((response) => {
          this.title = response.title;
          this.description = response.overview;
          this.backDrop =
            "https://image.tmdb.org/t/p/original" + response.backdrop_path;
          this.rate = response.vote_average;
          this.genres = response.genres;
        })
        .catch((err) => console.error(err));
    },
  },
};
</script>

<style scoped>
.actors {
  padding-top: 60px;
}
.movieDetails {
  position: relative;
  padding: 50px;
  top: 110px;
}
.description {
  max-width: 50%;
  text-align: left;
}
.trailer {
  margin: 5px;
  margin-left: 20px;
  width: 200px;
  height: 56px;
  flex-shrink: 0;
  background: #ef9e3f;
  color: white;
  opacity: 1;
}

.cont {
  position: relative;
  color: #fff;
  background-size: cover;
  background-repeat: no-repeat;
  background-position: center;
  min-height: 800px;
  margin: 0;
  z-index: 1;
}
.title {
  font-size: 60px;
  font-style: normal;
  font-weight: 400;
  line-height: normal;
  text-transform: capitalize;
}
.rate {
  color: #fff;
  font-family: "Quicksand";
  font-size: 20px;
  font-style: normal;
  font-weight: 400;
  line-height: normal;
}
.data {
  color: #fff;
  font-family: "Quicksand";
  font-size: 20px;
  font-style: normal;
  font-weight: 400;
  line-height: normal;
}
.moviedescription {
  color: #fff;
  font-family: "Quicksand";
  font-size: 20px;
  font-style: normal;
  font-weight: 400;
  line-height: normal;
  padding: 20px;
}
.trailer:hover {
  background: #fff;
  color: #ef9e3f;
}
.actors {
  font-size: 32px;
  font-style: normal;
  font-weight: 400;
  line-height: normal;
  position: relative;
}
.actorss {
  margin-bottom: 15px;
}
.comments {
  padding: 80px;
}
.commenthead {
  text-align: start;
}

input[type="text"] {
  height: 100px;
  width: 200px;
  transition: width 0.4s ease-in-out;
  font-size: 35px;
  color: rgb(183, 183, 183);
  padding: 16px 32px;
}

input[type="text"]:focus {
  width: 60%;
  min-height: 80px;
  font-size: 35px;
  padding: 16px 32px;
  text-decoration: none;
  cursor: text;
  color: black;
}
.yourrate {
  font-size: 40px;
  padding-left: 40px;
}
.ibtn:hover {
  color: #ef9e3f;
}
.prevcomments {
  margin: 4px;
  background: white;
  color: black;
  border-radius: 5px;
  max-width: 1000px;
}
.imark {
  color: #ef9e3f;
}
.all {
  margin-top: 70px;
}
.overlay {
  position: absolute;
  inset: 0;
  width: 100%;
  height: 100%;
  z-index: -1;
  background: linear-gradient(to bottom, rgba(255, 204, 0, 0), #000000);
}
.overlay1 {
  position: absolute;
  inset: 0;
  width: 100%;
  height: 100%;
  z-index: -1;
  opacity: 0.8;
  background: #000000;
}
.fa-bookmark {
  font-size: 36px;
}
.fa-bookmark:hover {
  color: #ef9e3f;
  cursor: pointer;
}
h1 {
  margin-left: 18px;
  text-transform: capitalize;
}
.info {
  text-align: left;
  position: relative;
  left: 20px;
}
.artists {
  margin-top: 80px;
  margin-left: 100px;
}
.vid {
  position: absolute;
  top: 40px;
  left: 200px;
  min-height: 60%;
  min-width: 70%;
  height: 90%;
  z-index: 3;
}
.vid iframe {
  outline: black solid 5px;
  border-radius: 10px;
  width: 70%;
  height: 70%;
}
</style>