<template>
  <div class="scrollable-row">
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
        @openmoviepage="openmoviepage"
      />
    </div>
  </div>
</template>

<script>
import movieCard from "./movieCard.vue";
export default {
  props: ["category"],
  components: {
    movieCard,
  },
  created() {
    this.movies = this.trending();
    console.log(this.movies);
  },
  data() {
    return {
      movies: [],
      movie_id: "",
    };
  },
  methods: {
    changeStateMovie() {
      this.$emit("changeStateMovie", "MoviePage", this.movie_id);
    },
    openmoviepage(movieId) {
      this.movie_id = movieId;
      this.changeStateMovie();
    },
    trending() {
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
              rate: response.results[i].vote_average,
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
              rate: response.results[i].vote_average,
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
    },
  },
};
</script>

<style scoped>
.scrollable-row {
  overflow-x: auto;
  white-space: nowrap;
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
}
</style>