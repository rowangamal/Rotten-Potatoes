<template>
  <navBar />
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
              {{ title }}
              <i v-if="notAtList()" @click="addToList" class="fa-regular fa-bookmark"></i>
              <i v-else @click="removeFromList" class="fa-solid fa-bookmark"></i>
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
            @keyup.enter="addComment"
          />
        </div>
      </div>
    </section>
  </div>
</template>

<script>
// import carousel from "./carousel.vue";
import carouselActors from "@/components/carouselActors.vue";
import navBar from "@/components/navBar.vue";
import $store from "../store/index.js";

export default {
  props: ["id"],
  components: {
    carouselActors,
    navBar,
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
      date: "",
      img: "",
      comments: [
        { comment: "this movie is cool", user: "pola" },
        { comment: "what the hell have I just watched", user: "hany" },
      ],
      genres: [],
      urlll: "",
      updated: false,
      commentUpdate:true
    };
  },
  mounted() {
    console.log(this.id);
    this.loadData();
    this.actorFetch();
    this.loadComments();
  },
  
  methods: {
    addComment(){
      if(!$store.state.currUser!==null){
        console.log($store.state.currMov.id);
        console.log(this.id);
      let comment={
        id:this.id,
        rate:this.rate,//nb3t el rate hena 3la asas el star
        comment:this.text,
        username:$store.state.currUser.userName,
      }
      fetch(`http://localhost:8080/addComment`,
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(comment),
        }
      )
        .then((response) => {
          if (response.ok) {
            return response.json();
          } else {
            throw new Error(`HTTP error! Status: ${response.status}`);
          }
        })
        .then((userData) => {
          console.log(userData);
        })
        .catch((error) => {
          console.error("Error during Change:", error);
        });
        this.text=""
        setTimeout(() => {
          this.loadComments();
        },500)
        
      }
    },
    loadComments(){
      fetch(`http://localhost:8080/Comments/${this.id}`)
      .then((response) => {
        if (response.ok) {
          return response.json();
        } else {
          throw new Error(`HTTP error! Status: ${response.status}`);
        }
      })
      .then((comments) => {
        // Handle the comments data
        console.log('User comments:', comments);
      })
      .catch((error) => {
        console.error('Error fetching user comments:', error);
      });
    },
    async playNow() {
      let newtit = this.title.replaceAll(" ", "%20");
      console.log(newtit);
      const url = `https://movie-tv-music-search-and-download.p.rapidapi.com/search?keywords=${newtit}&quantity=40&page=1`;
      const options = {
        method: "GET",
        headers: {
          "X-RapidAPI-Key":
            "fe9219690cmshc641c468ac1874dp156f63jsna03d6a220172",
          //backUP:fe9219690cmshc641c468ac1874dp156f63jsna03d6a220172
          //72b0a49e61msh60849d7630f09f4p1f7053jsn06c00cf2eaaf
          "X-RapidAPI-Host":
            "movie-tv-music-search-and-download.p.rapidapi.com",
        },
      };

      try {
        const response = await fetch(url, options);
        const result = await response.json();
        this.urlll = result.result[0].torrent;
        console.log(result.result);
      } catch (error) {
        console.error(error);
      }

      window.open(this.urlll, "_blank");
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
          $store.commit("setCurrMov", response.cast);
          this.actors = $store.state.currMov;
        })
        .catch((err) => console.error(err));
      this.updated = false;
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
    notAtList(){
      if($store.state.currUser===null){
        return true;
      }
      else{
        let user = $store.state.currUser;
        let movie = {
          id: this.id,
          title: this.title,
          rate: this.rate,
          date: this.date,
          img: this.img,
        };
        let x = user.watchlist.filter((m) => m.id === movie.id);
        if(x.length===0){
          return true;
        }
        else{
          return false;
        }
      }
    },
    addToList() {
      if ($store.state.currUser === null) {
        this.$router.push({ name: "loginForm" });
      } else {
        let user = $store.state.currUser;
        let movie = {
          id: this.id,
          title: this.title,
          rate: this.rate,
          date: this.date,
          img: this.img,
        };
        user.watchlist = user.watchlist.filter((m) => m.id !== movie.id);
        user.watchlist.push(movie);
        $store.commit("setCurrUser", user);
        localStorage.setItem("userData", JSON.stringify(user));
        console.log($store.state.currUser);
        //fetch hena to update the user in backend
        fetch(`http://localhost:8080/updateUser/${$store.state.currUser.email}`,
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify($store.state.currUser),
        }
      )
        .then((response) => {
          if (response.ok) {
            return response.json();
          } else {
            throw new Error(`HTTP error! Status: ${response.status}`);
          }
        })
        .then((userData) => {
          
        })
        .catch((error) => {
          console.error("Error during Change:", error);
        });
      }
    },
    removeFromList() {
      let user = $store.state.currUser;
      let movie = {
        id: this.id,
        title: this.title,
        rate: this.rate,
        date: this.date,
        img: this.img,
      };
      user.watchlist = user.watchlist.filter((m) => m.id !== movie.id);
      $store.commit("setCurrUser", user);
      localStorage.setItem("userData", JSON.stringify(user));
      console.log($store.state.currUser);
      //fetch hena to update the user in backend
      fetch(`http://localhost:8080/updateUser/${$store.state.currUser.email}`,
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify($store.state.currUser),
        }
      )
        .then((response) => {
          if (response.ok) {
            return response.json();
          } else {
            throw new Error(`HTTP error! Status: ${response.status}`);
          }
        })
        .then((userData) => {
          
        })
        .catch((error) => {
          console.error("Error during Change:", error);
        });
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
          this.date = response.release_date;
          this.img=`https://image.tmdb.org/t/p/original`+response.poster_path;
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
.fa-solid{
    color: #ef9e3f;
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