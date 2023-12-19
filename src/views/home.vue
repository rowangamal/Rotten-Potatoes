<template>
  <navBar />
  <div class="all">
    <div v-if="movs.length===0" class="carousel">
      <h3>Trending Now</h3>
      <carousel :category="cat" />
      <h3>Now Playing</h3>
      <carousel :category="cat2" />
      <h3>Top Rated</h3>
      <carousel :category="cat3" />
      <h3>Upcoming</h3>
      <carousel :category="cat4" />
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
</template>

<script>
// @ is an alias to /src
import navBar from "@/components/navBar.vue";
import carousel from "@/components/carousel.vue";
import $store from "../store/index.js";
import movieCard from '@/components/movieCard.vue';

export default {
  components: {
    navBar,
    carousel,
    movieCard
  },
  watch: {
    '$store.state.searchMovs': function (newMovs) {
      this.movs = newMovs;
    },
  },
  mounted() {
    this.movs=$store.state.searchMovs;
    console.log($store.state.currUser);
    console.log($store.state.loginStatus);
  },
  data() {
    return {
      cat: "popular",
      cat2: "top_rated",
      cat3: "upcoming",
      cat4: "popular",
      movs:[]
    };
  },
};
</script>

<style scoped>
h3 {
  text-align: left;
  margin-left: 5%;
  margin-bottom: 20px;
  margin-top: 100px;
}
.carousel {
  margin-top: 100px;
}
.row{
  display: flex;
  flex-flow: row wrap;
  width: 100vw;
}
.body{
  margin-top: 85px;
  
}
.all{
  overflow-x: hidden;
}
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
  margin-bottom: 70px;
  position: relative;
  border: solid 2px transparent;
  box-shadow: 2px 2px 5px -5px #000000;
}
</style>
