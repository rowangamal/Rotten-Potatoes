<template>
  <navBar />
  <section>
    <div class="container">
      <h2>Your Favorites</h2>
      <div class="row flex-wrap justify-content-center">
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
  data() {
    return {
      movies: $store.state.currUser.favourites,
    };
  },
};
</script>

<style scoped>
section{
  margin-top: 100px;
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
</style>