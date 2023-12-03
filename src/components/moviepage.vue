<template>
  <div class="all">
    <section class="cont">
      <div class="d-flex flex-column justify-content-end mov">
        <div class="overlay"></div>
        <div class="movieDetails d-flex justify-content-between">
          <div class="description flex-fill">
            <h1 class="title">
              {{ title }} <i class="fa-regular fa-bookmark"></i>
            </h1>
            <div class="info d-flex justify-content-around">
              <p class="rate">
                <i class="fa-solid fa-star imark"></i>{{ rate }}
              </p>
              <p class="data">some data</p>
            </div>
            <p class="moviedescription">
              {{ description }}
            </p>
            <button type="button" class="btn trailer">
              Play Now <i class="fa-solid fa-play"></i>
            </button>
            <button type="button" class="btn trailer">Trailer</button>
          </div>
          <div class="artists flex-fill">
            <h1 class="actors">Actors</h1>
            <carousel :category="cat" />
          </div>
        </div>
      </div>
    </section>
    <section class="cont">
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
export default {
  props: ["index"],
  components: {
    carousel,
  },
  data() {
    return {
      isloggedin: true,
      image: "./az5ucwh1vcg71.jpg",
      rate: 8,
      usersRate: 0,
      clicked: false,
      title: "Dune",
      description:
        "A noble family becomes embroiled in a war for control over the galaxy's most valuable asset while its heir becomes troubled by visions of a dark future.",
      comments: [
        { comment: "this movie is cool", user: "pola" },
        { comment: "what the hell have I just watched", user: "hany" },
      ],
    };
  },
  methods: {
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
  },
};
</script>

<style scoped>
.actors {
  padding-top: 60px;
}
.movieDetails {
  padding: 50px;
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
  background: url("./az5ucwh1vcg71.jpg");
  background-size: cover;
  background-repeat: no-repeat;
  background-position: center;
  min-height: 600px;
  margin: 0;
  bottom: 0px;
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
.mov {
  position: absolute;
  bottom: 0px;
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
.fa-bookmark:hover{
  color: #ef9e3f;
  cursor: pointer;
}
h1 {
  margin-left: 18px;
  text-transform: capitalize;
}
.info {
  margin-left: -140px;
}
</style>