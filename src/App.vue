<template>
  <navBar :loginState="this.loginState" @login="login" v-show="state===''" @changeState="changeState"/>
  <signUpForm  v-show="state =='signUpForm'" @changeState="changeState"/>
  <loginForm v-show="state=='loginForm'" @changeState="changeState" @login="login"/>
  <forgetPassword v-show="state=='forgetPassword'" @changeState="changeState"/>
  <securityForm v-show="state=='securityForm'" @changeState="changeState"/>
  <h1 v-show="state==''">TRENDING</h1>
  <carousel :category="cat" v-show="state==''"/>
  <h1 v-show="state==''">NOW PLAYING</h1>
  <carousel :category="cat2" v-show="state==''"/>
  <h1 v-show="state==''">TOP RATED</h1>
  <carousel :category="cat3" v-show="state==''"/>
  <h1 v-show="state==''">UPCOMING</h1>
  <carousel :category="cat4" v-show="state==''"/>
</template>

<script>
import navBar from "./components/navBar.vue";
import signUpForm from "./components/signUpForm.vue";
import loginForm from "./components/loginForm.vue";
import carousel from "./components/carousel.vue";
import forgetPassword from "./components/forgetPassword.vue";
import securityForm from './components/securityForm.vue';
export default {
  name: "App",
  components: {
    navBar,
    signUpForm,
    loginForm,
    carousel,
    forgetPassword,
    securityForm,
  },
  data() {
    return {
      loginState: false,// state that control showing and hiding things depends on login state
      state : "", // state that control the components that will be shown
      cat:"popular",
      cat2:"now_playing",
      cat3:"top_rated",
      cat4:"upcoming"
    };
  },
    methods: {
    changeState(newState) {
      this.state = newState;
    },
    login(newLogin) {
      this.loginState = newLogin;
    }
  },

};
</script>

<style>
#app {
  font-family: "Monomaniac One", sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
* {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
}
img {
  width: 100%;
  height: 100%;
}
</style>
