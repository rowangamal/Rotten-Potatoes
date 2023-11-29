<template>
  <navBar v-show="state===''" @changeState="changeState"/>
  <signUpForm  v-show="state =='signUp'" @changeState="changeState"/>
  <loginForm v-show="state=='login'"/>
</template>

<script>
import navBar from "./components/navBar.vue";
import signUpForm from "./components/signUpForm.vue";
import loginForm from "./components/loginForm.vue";
export default {
  name: "App",
  components: {
    navBar,
    signUpForm,
    loginForm,
  },
  data() {
    return {
      login: false,
      state : "",
    };
  },
    methods: {
    changeState(newState) {
      this.state = newState;
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
