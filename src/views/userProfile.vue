<template>
  <navBar />
  <div class="all">
    <section class="content">
      <div class="left-content">
        <ul class="info">
          <li>User info</li>
          <li><router-link to="/favourites"> Favourites </router-link> </li>
          <li><router-link to="/watchList"> Watch list </router-link> </li>
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
        <h2>Genre Preferences</h2>
        <pre>{{ analysisResults.genrePreferences }}</pre>
        
        <h2>Year Frequency</h2>
        <pre>{{ analysisResults.yearFrequency }}</pre>
        
        <h2>Average Rating</h2>
        <pre>{{ analysisResults.averageRating }}</pre>
        
        <h2>Movie Count</h2>
        <pre>{{ analysisResults.movieCount }}</pre>
      </div>
    </section>
  </div>
</template>

<script>
import $store from "../store/index.js";

import navBar from "@/components/navBar.vue";
export default {
  components: {
    navBar,
  },
  data() {
    return {
      user: {},
      wrapped: false,
      analysisResults: {},
      email: "",
    };
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
    getAnalysisData(){
      console.log(this.email);
      console.log("hi from fetch potato wrap")
      fetch(`http://localhost:8080/potatoWrap/${this.user.email}`,{
        method: "GET",
        headers: {
          "Content-Type": "application/json",
        },
      }).then((res) => {
        return res.json();
      }).then((data) => {
        this.analysisResults = data;
        console.log(this.analysisResults);
      }).catch((err) => {
        console.log(err);
      });
    }
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
</style>