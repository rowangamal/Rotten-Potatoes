<template>
  <navBar />
  <div class="all">
    <div class="carousel">
      <h3>Trending Now</h3>
      <carousel :category="cat" />
      <h3>Now Playing</h3>
      <carousel :category="cat2" />
      <h3>Top Rated</h3>
      <carousel :category="cat3" />
      <h3>Upcoming</h3>
      <carousel :category="cat4" />
    </div>
  </div>
</template>

<script>
// @ is an alias to /src
import navBar from "@/components/navBar.vue";
import carousel from "@/components/carousel.vue";
import $store from "../store/index.js";

export default {
  components: {
    navBar,
    carousel,
  },
  mounted() {
    console.log($store.state.currUser);
    console.log($store.state.loginStatus);
  },
  data() {
    return {
      cat: "popular",
      cat2: "top_rated",
      cat3: "upcoming",
      cat4: "popular",
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


</style>
