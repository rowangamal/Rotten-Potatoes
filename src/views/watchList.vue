<template>
  <navBar />
  <section>
    <div class="all">
        <div
          v-if="movs.length === 0"
          class="row flex-wrap justify-content-center"
        >
          <h2>Your Watchlist</h2>
          <movieCard
            class="col-2"
            v-for="movie in movies"
            :key="movie"
            :img="movie.img"
            :rate="movie.rate"
            :title="movie.title"
            :date="movie.date"
            :id="movie.id"
          />
        </div>
        <div class="body" v-else>
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
        </div>
      </div>
  </section>
</template>

<script>
import $store from "../store/index.js";
import navBar from "@/components/navBar.vue";
import movieCard from "@/components/movieCard.vue";

export default {
  mounted() {
    console.log($store.state.currUser);
  },
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
      movies: $store.state.currUser.watchlist,
      movs: [],
    };
  },
};
</script>

<style scoped>
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
h2{
  margin-bottom: 20px;
}
</style>