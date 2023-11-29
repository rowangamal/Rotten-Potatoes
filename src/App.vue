<template>
  <navBar :loginState="this.loginState" @login="login" v-show="state===''" @changeState="changeState"/>
  <signUpForm  v-show="state =='signUpForm'" @changeState="changeState"/>
  <loginForm v-show="state=='loginForm'" @changeState="changeState" @login="login"/>
  <carousel v-show="state==''"/>
</template>

<script>
import navBar from "./components/navBar.vue";
import signUpForm from "./components/signUpForm.vue";
import loginForm from "./components/loginForm.vue";
import carousel from "./components/carousel.vue";
export default {
  name: "App",
  components: {
    navBar,
    signUpForm,
    loginForm,
    carousel,
  },
  data() {
    return {
      loginState: false,// state that control showing and hiding things depends on login state
      state : "", // state that control the components that will be shown
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
