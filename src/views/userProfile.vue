<template>
  <navBar />
  <div class="all">
    <section v-if="movs.length === 0" class="content">
      <div class="left-content">
        <ul class="info">
          <li @click="offWrap">User info</li>
          
          <li><router-link to="/watchList"> Watch list </router-link></li>
          <li @click="wrap">Potato Wrapped 2023</li>
        </ul>
      </div>
      <div class="right-content" v-show="!this.wrapped">
        <div class="image-container">
          <img
            v-if="this.user.gender === 'male'"
            class="user-image"
            src="../assets/MrPotato.webp"
            alt="../assets/MrPotato2.webp"
          />
          <img
            v-else
            class="user-image"
            src="../assets/MRSPOTATO.webp"
            alt="../assets/mrsPotato2.jpg"
          />
        </div>
        <div class="user-data">
          <div class="email">
            <label style="align-content: start">Email</label>
            <div style="min-width: 100%" class="data">
              {{ this.user.email }}
            </div>
          </div>
          <div class="names">
            <div>
              <label>First name</label>
              <div class="data">{{ this.user.firstName }}</div>
            </div>
            <div>
              <label>Last name</label>
              <div class="data">{{ this.user.lastName }}</div>
            </div>
            <div>
              <label>Username</label>
              <div class="data">{{ this.user.userName }}</div>
            </div>
            <div>
              <label>Gender</label>
              <div class="data">{{ this.user.gender }}</div>
            </div>
          </div>
        </div>
      </div>
      <div class="potato-wrap" v-show="this.wrapped">
        <!-- <h2>Genre Preferences</h2>
        <pre>{{ analysisResults.genrePreferences }}</pre> -->
        <ul class="display">
          <h1>{{ this.user.firstName }}'s Potato Wrapped</h1>
          <h2>THIS YEAR YOUR TOP GENRE WERE:</h2>
          <ul>
            <li
              v-for="(count, genre) in analysisResults.genrePreferences"
              :key="genre"
            >
              {{ genre }}: {{ count }}
            </li>
          </ul>
          <h2>YOUR AVERAGE RATING IN 2023 WAS:</h2>
          <li>{{ analysisResults.averageRating }}</li>

          <h2>MOVIES WERE POTATO FAVORED/WATCHLISTED</h2>
          <li>{{ analysisResults.movieCount }}</li>
        </ul>
      </div>
    </section>
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
import $store from "../store/index.js";

import navBar from "@/components/navBar.vue";
import movieCard from "@/components/movieCard.vue";
export default {
  components: {
    navBar,
    movieCard,
  },
  data() {
    return {
      user: {},
      wrapped: false,
      analysisResults: {},
      email: "",
      movs: [],
    };
  },
  watch: {
    "$store.state.searchMovs": function (newMovs) {
      this.movs = newMovs;
    },
  },
  methods: {
    wrap() {
      this.wrapped = !this.wrapped;
      console.log(this.email);
      console.log("here");
      if (this.wrapped == true) {
        this.getAnalysisData();
      }
    },
    offWrap() {
      this.wrapped = false;
    },
    getAnalysisData() {
      console.log(this.email);
      console.log("hi from fetch potato wrap");
      fetch(`http://localhost:8080/potatoWrap/${this.user.email}`, {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
        },
      })
        .then((res) => {
          return res.json();
        })
        .then((data) => {
          this.analysisResults = data;
          this.analysisResults.averageRating =
            this.analysisResults.averageRating.toFixed(2);
          console.log(this.analysisResults);
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
  mounted() {
    this.user = $store.state.currUser;
    this.email = $store.state.currUser.email;
    console.log(this.user);
  },
};
</script>

<style scoped>
.content {
  display: flex;
  margin-top: 70px;
}
.left-content {
  background-color: #000;
  display: flex;
  width: 25%;
  height: 720px;
  flex-direction: column;
  justify-content: space-around;
}
.info {
  display: flex;
  flex-direction: column;
  justify-items: space-between;
}
.info li {
  list-style: none;
  color: #eeee;
  font-size: 30px;
  margin: 30px 10px;
  cursor: pointer;
}
.right-content {
  display: block;
  width: 75%;
  height: 720px;
  background-color: #eee;
}
.image-container {
  margin: 60px 60px 0px 70px;
  width: 170px;
  height: 170px;
  overflow: hidden;
  border-radius: 50%;
  border: 2px solid #333;
}
.user-image {
  width: 100%;
  height: 100%;
  border-radius: 50%;
  object-fit: contain;
}
.user-data {
  display: flex;
  flex-direction: column;
  margin: 20px;
  padding: 15px;
  align-items: center;
  width: 600px;
  font-size: 30px;
}
.names {
  display: flex;
  padding: 25px;
  margin: 20px;
  flex-wrap: wrap;
  gap: 5px 80px;
}
.email {
  justify-items: flex-start;
  justify-content: flex-start;
}
.line {
  display: flex;
  flex-direction: row;
  justify-content: space-around;
}
.data {
  background-color: rgb(202, 202, 194);
  min-width: 200px;
  max-width: max-content;
}
a.router-link-exact-active {
  color: white;
  text-decoration: none;
}

a.router-link-exact-active:hover {
  color: #ef9e3f;
}

a {
  color: white;
  text-decoration: none;
}
.potato-wrap h2 {
  color: #333;
  font-size: 1.5rem;
  margin-bottom: 10px;
}

/* List styling */
.potato-wrap ul {
  list-style-type: none;
  padding: 0;
}
.potato-wrap {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 30px;
}

.potato-wrap li {
  margin-bottom: 5px;
  background-color: #f0f0f0;
  padding: 10px;
  border-radius: 10px;
  width: 500px;
  transition: background-color 0.3s ease;
}
.potato-wrap li:hover {
  background-color: #e0e0e0;
}
.potato-wrap pre {
  margin-bottom: 5px;
  background-color: #f0f0f0;
  padding: 10px;
  border-radius: 10px;
  /* overflow: auto; */
  width: 500px;
}

/* Button styling */
button {
  background-color: #4caf50;
  color: white;
  border: none;
  padding: 10px 15px;
  font-size: 1rem;
  cursor: pointer;
  border-radius: 4px;
}

/* Button hover effect */
button:hover {
  background-color: #45a049;
}

/* Transition effect */
.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.5s;
}

.fade-enter,
.fade-leave-to {
  opacity: 0;
}
.display {
  display: flex;
  flex-direction: column;
  margin: 20px;
  margin-top: 50px;
  padding: 15px;
  align-items: center;
  width: 600px;
  font-size: 30px;
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
.body {
  margin-top: 100px;
}
</style>