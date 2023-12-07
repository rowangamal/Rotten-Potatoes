<template>
  <nav>
    <ul class="nav nav-pills">
      <li class="nav-item">
        <a class="nav-link" @click="changeStateBack"
          ><img src="..\assets\Group 5.png" alt=""
        /></a>
      </li>

      <li class="nav-item">
        <div class="input-group">
          <input
            v-model="search"
            placeholder="Search for a movie"
            type="text"
            style="width: 500px"
            class="form-control"
          />
          <div class="input-group-append searchbtn">
            <a
              class="nav-link dropdown-toggle"
              data-bs-toggle="dropdown"
              href="#"
              role="button"
              aria-expanded="false"
              ><button class="btn btn-dark" type="button">all</button></a
            >
            <ul class="dropdown-menu">
              <li><a class="dropdown-item" href="#">msh 3arf lesa</a></li>
            </ul>
          </div>
          <i class="fa-solid fa-magnifying-glass"></i>
        </div>
      </li>
      <li class="nav-item">
        <a class="nav-link links" @click="toggleAbout"><p>About</p></a>
      </li>
      <li class="nav-item">
        <a class="nav-link links" href="#"><p>Watch list</p></a>
      </li>
      <li class="nav-item">
        <a @click="changeStateSignup" v-if="!loginState" class="nav-link links"
          ><p>Signup/Login</p></a
        >
        <a @click="changeStateProfile" v-else class="nav-link links"
          ><i class="fa-solid fa-user"></i
        ></a>
        <a
          class="nav-link dropdown-toggle"
          data-bs-toggle="dropdown"
          href="#"
          role="button"
          aria-expanded="false"
        ></a>
        <ul class="dropdown-menu">
          <li><h5>{{user}}</h5></li>
          <li><a class="dropdown-item" href="#">msh 3arf lesa</a></li>
          <li><button v-if="loginState" class="dropdown-item" @click="login">Sign Out</button></li>
        </ul>
      </li>
    </ul>
  </nav>
</template>

<script>
import {storeID} from "./id.js";
import about from "./about.vue";
export default {
  name: "navBar",
  components: {
    about,
  },
  data() {
    return {
      search: "",
      user:"",
      showAbout:false,
    };
  },
  updated(){
    if(true){
            const userDataString = localStorage.getItem("userData");
            this.user = JSON.parse(userDataString).userName;
            console.log(this.user);
    }
    else{
      this.user="";
    }
  },
  props: ["loginState"],
  methods: {
    changeStateSignup() {
      this.$emit("changeState", "signUpForm");
    },
    changeStateBack() {
      this.$emit("changeState", "");
    },
    changeStateProfile() {
      this.$emit("changeState", "userProfile");
    },
    login(){
      this.$emit("login", false);
      localStorage.removeItem("token");
      storeID.currUser=null;
      this.$emit("changeState", "");     
    },
    toggleAbout(){
      this.showAbout = !this.showAbout;
    }
  },
};
</script>

<style scoped>
*{
  z-index: 4;
}
nav {
  background-color: #ef9e3f;
  position: fixed;
  top: 0;
  width: 100%;
}
.nav-pills {
  display: flex;
  justify-content: space-evenly;
  list-style: none;
}
a {
  text-decoration: none;
  color: #fff;
  font-size: 14px;
  cursor: pointer;
}
img {
  width: 100px;
  height: 45px;
}
.dropdown-item {
  color: #000;
  font-size: 20px;
}
.form-control {
  height: 40px;
  border-radius: 0px 20px 20px 0;
  border: none;
  margin-top: 14px;
}
.form-control:focus {
  box-shadow: none;
  outline: none;
}
a:hover {
  text-decoration: none;
  color: #000;
}

.links {
  margin-top: 20px;
}
.input-group {
  position: relative;
  border-radius: 20px 0 0 20px;
}
.searchbtn {
  position: absolute;
  left: -67px;
  height: 40px;
  border-radius: 20px 0 0 20px;
  border: none;
  margin-top: 6px;
}

.searchbtn .btn {
  padding: 10px 20px 13px 20px;
  border-radius: 20px 0 0 20px;
  z-index: 1;
  font-size: 10px;
}

.nav-item a {
  display: inline-block;
}

.fa-user {
  margin: -10px 0 0 0;
  padding: 10px;
  background-color: #fff;
  color: #ee9e3f;
  border-radius: 50%;
}

.fa-magnifying-glass {
  position: absolute;
  right: 0;
  bottom: 0;
  padding: 12px;
  background-color: #fff;
  color: #ee9e3f;
  z-index: 1;
}
h5{
  text-align: center;
  margin: 0;
  padding: 0;
  background-color: #ee9e3f
}
</style>