<template>
  <div class="carouselll">
    <button @click="scrollLeft" class="scroll-button left-button"><i class="fa-solid fa-caret-left"></i></button>
    <button @click="scrollRight" class="scroll-button right-button">
      <i class="fa-solid fa-caret-right"></i>
    </button>
    <div class="scrollable-row" ref="scrollContainer">
      <div class="row flex-nowrap">
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
    </div>
  </div>
</template>

<script>
import $store from "../store/index.js";

import movieCard from "./movieCard.vue";
export default {
  props: ["category"],
  components: {
    movieCard,
  },
  computed: {
    scrollContainer() {
      console.log(this.$refs.scrollContainer);
      return this.$refs.scrollContainer;
    },
    canScrollRight() {
      return (
        this.scrollContainer &&
        this.scrollContainer.scrollLeft <
          this.scrollContainer.scrollWidth - this.scrollContainer.clientWidth
      );
    },
  },
  created() {
    this.movies = this.trending();
  },
  data() {
    return {
      movies: [],
      movie_id: "",
      scrollStep: 225,
    };
  },
  methods: {
    scrollLeft() {
      if (true) {
        this.scrollContainer.scrollLeft -= this.scrollStep;
      }
    },
    scrollRight() {
      if (this.canScrollRight) {
        this.scrollContainer.scrollLeft += this.scrollStep;
      }
    },
    trending() {
      if (this.category) {
        const options = {
          method: "GET",
          headers: {
            accept: "application/json",
            Authorization:
              "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwNjQ3YWZiMWU3ZjAwODk2M2M3MTU4MjM5N2VlNjVjMSIsInN1YiI6IjY1NTAxZDM3OTY1M2Y2MDExYmZkYzkzYyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.7PEbDmBQNqrY3JJ7bRgmJV8S8tPVS8ziZ4TkWSJ2IqU",
          },
        };
        let m = [];

        fetch(
          `https://api.themoviedb.org/3/movie/${this.category}?language=en-US&page=1`,
          options
        )
          .then((response) => response.json())
          .then((response) => {
            for (let i = 0; i < 17; i++) {
              let x = {
                title: response.results[i].title,
                rate: Math.round(response.results[i].vote_average*10)/10,
                img:
                  "https://image.tmdb.org/t/p/original" +
                  response.results[i].poster_path,
                date: response.results[i].release_date,
                id: response.results[i].id,
              };
              this.movies.push(x);
            }
          })
          .catch((err) => console.error(err));

        fetch(
          `https://api.themoviedb.org/3/movie/${this.category}?language=en-US&page=2`,
          options
        )
          .then((response) => response.json())
          .then((response) => {
            for (let i = 0; i < 17; i++) {
              let x = {
                title: response.results[i].title,
                rate: Math.round(response.results[i].vote_average*10)/10,
                img:
                  "https://image.tmdb.org/t/p/original" +
                  response.results[i].poster_path,
                date: response.results[i].release_date,
                id: response.results[i].id,
              };
              this.movies.push(x);
            }
          })
          .catch((err) => console.error(err));
        return m;
      }
    },
  },
};
</script>

<style scoped>
.scrollable-row {
  overflow-x: hidden;
  white-space: nowrap;
  width: 100%;
  transition: scroll-left 0.2s ease;
  scroll-behavior: smooth;
}

.row {
  margin-top: 20px;
}

.card {
  width: 200px;
  margin-right: 20px;
  border-radius: 10px;
  position: relative;
  border: solid 2px transparent;
  box-shadow: 2px 2px 5px -5px #000000;
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
</style>