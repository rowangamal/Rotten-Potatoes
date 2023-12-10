<template>
  <navBar />
  <div class="all">
  <div class="contain">
    <h1>About Rotten Potatoes</h1>

    <div>
      Welcome to Rotten Potatoes, Where Movie Reviews Simmer and Sizzle!
    </div>
    <h4>Our Story</h4>
    <div class="points">
      At Rotten Potatoes, we believe in the power of a good film to transport
      you to different worlds, evoke emotions, and spark conversations. Whether
      you're a seasoned cinephile or just looking for your next movie night
      pick, Rotten Potatoes is your go-to destination for insightful and
      entertaining movie reviews. What sets Rotten Potatoes apart is our
      commitment to delivering reviews that are as diverse as the films
      themselves.
      <!-- Our team of passionate and seasoned reviewers brings a wide range of perspectives and tastes to the table, ensuring that you get a well-rounded view of the cinematic landscape.
            From blockbuster hits to indie gems, we cover it all. Our reviews are crafted with care, providing you with a mix of critical analysis,
            audience reactions, and a dash of humor. Think of us as your cinematic sous chefs, carefully curating a menu of movie options for every palate. -->
    </div>
    <h4>Our Mission</h4>
    <div class="points">
      We're here to help you decide what to watch and to help you discover new
      movies and TV shows you'll love. We provide several ways to interact with
      our reviews, including a rating system and comments.
    </div>
    <h4>Contact Us/Support</h4>
    <div class="points">
      If you have any questions or concerns,
      <a href="mailto:rottenpotatoes@gmail.com">contact us through this link</a>
    </div>
  </div>
  </div>
</template>

<script>
import navBar from "@/components/navBar.vue";
import $store from "../store/index.js";

export default {
  name: "AboutView",
  components: {
    navBar,
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

</style>