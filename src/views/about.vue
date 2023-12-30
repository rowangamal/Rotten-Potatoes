<template>
  <navBar />
  <div class="all">
    <div v-if="movs.length === 0" class="contain">
      <h1>About Rotten Potatoes</h1>
      <div>
        Welcome to Rotten Potatoes, Where Movie Reviews Simmer and Sizzle!
      </div>
      <h4>Our Story</h4>
      <div class="points">
        At Rotten Potatoes, we believe in the power of a good film to transport
        you to different worlds, evoke emotions, and spark conversations.
        Whether you're a seasoned cinephile or just looking for your next movie
        night pick, Rotten Potatoes is your go-to destination for insightful and
        entertaining movie reviews. What sets Rotten Potatoes apart is our
        commitment to delivering reviews that are as diverse as the films
        themselves.
      </div>
      <h4>Our Mission</h4>
      <div class="points">
        We're here to help you decide what to watch and to help you discover new
        movies and TV shows you'll love. We provide several ways to interact
        with our reviews, including a rating system and comments.
      </div>
      <h4>Contact Us/Support</h4>
      <div class="points">
        If you have any questions or concerns,
        <a href="mailto:rottenpotatoes@gmail.com"
          >contact us through this link</a
        >
      </div>
    </div>
    <div class="body" v-else>
      <section>
        <div class="row justify-content-center">
          <movieCard
            class="col-2"
            v-for="movie in movs"
            :key="movie"
            :img="movie.img"
            :rate="movie.rate"
            :title="movie.title"
            :date="movie.date"
            :id="movie.id"
          />
        </div>
      </section>
    </div>
  </div>
</template>

<script>
import navBar from "@/components/navBar.vue";
import $store from "../store/index.js";
import movieCard from "@/components/movieCard.vue";

export default {
  name: "AboutView",
  components: {
    navBar,
    movieCard,
  },
  watch: {
    "$store.state.searchMovs": function (newMovs) {
      this.movs = newMovs;
    },
  },
  data() {
    return {
      movs: [],
    };
  },
};
</script>

<style scoped>
.contain {
  /* margin-top: 100px;
    margin-left: 100px;
    margin-right: 100px;
    margin-bottom: 100px;
    font-family: 'Roboto', sans-serif;*/
  font-size: 17px;
  color: #000000;
  margin-top: 8%;
  margin-left: 5%;
  margin-right: 5%;
  margin-bottom: 5%;
  text-align: left;
  display: flex;
  flex-direction: column;
  flex-wrap: wrap;
}
.points {
  margin-top: 10px;
  margin-bottom: 10px;
  width: 70%;
}
h4 {
  margin-top: 10px;
  margin-bottom: 10px;
}
section {
  margin-top: 100px;
}
.row {
  margin-top: 20px;
}
* {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
}
h2 {
  font-size: 16px;
  font-weight: bold;
  margin: 0;
  word-wrap: break-word;
  word-break: break-all;
  white-space: normal;
}
p {
  font-size: 10px;
  margin: 0;
}
.rate {
  background-color: #ef9e3f;
  width: 30px;
  height: 30px;
  border-radius: 50%;
  display: flex;
  justify-content: center;
  align-items: center;
  position: relative;
  bottom: 290px;
  left: -69px;
}
.img {
  border-radius: 10px;
  overflow: hidden;
  padding: 0px;
  border: none;
}
.info {
  position: relative;
  padding: 10px;
  padding-top: 0px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: center;
}
.card:hover img {
  transform: scale(1.1);
  cursor: pointer;
}
.card .img {
  position: relative;
  overflow: hidden;
}

.card .img img {
  overflow: hidden;
  transition: all 0.5s ease-in-out;
  height: 100%;
  width: 100%;
}
.layer {
  position: absolute;
  inset: 0;
  background-color: rgba(0, 0, 0, 0.5);
  width: 100%;
  height: 100%;
  opacity: 0;
  transition: all 0.25s;
}
.card:hover .layer {
  opacity: 1;
  margin: auto;
  text-align: center;
  z-index: 1;
}
.scrollable-row {
  overflow-x: hidden;
  white-space: nowrap;
  width: 100%;
  transition: scroll-left 0.2s ease;
  scroll-behavior: smooth;
  padding-bottom: 20px;
}

.row {
  margin-top: 20px;
}

.card {
  width: 200px;
  margin-right: 20px;
  border-radius: 10px;
  position: relative;
  padding: 0px;
  box-shadow: 1px 1px 8px 0px #000000;
  margin-bottom: 25px;
}

.scrollable-row {
  margin-left: 20px;
  margin-right: 20px;
}
.scroll-button {
  position: absolute;
  z-index: 2;
  background-color: #ef9e3f;
  border: none;
  padding: 10px;
  cursor: pointer;
  font-size: 20px;
  transition: 0.3s;
  border-radius: 5px;
  top: 35%;
}

.left-button {
  left: 0px;
  border-radius: 0 5px 5px 0;
}

.right-button {
  right: 0px;
  border-radius: 5px 0px 0px 5px;
}

.scroll-button:hover {
  background-color: #bbb;
}
.carouselll {
  position: relative;
}
h2 {
  margin-bottom: 20px;
}
</style>