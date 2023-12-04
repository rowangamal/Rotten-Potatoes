<template>
  <navBar
    :loginState="this.loginState"
    @login="login"
    v-show="state == '' || state == 'MoviePage'"
    @changeState="changeState"
  />
  <signUpForm v-show="state == 'signUpForm'" @changeState="changeState" />
  <loginForm
    v-show="state == 'loginForm'"
    @changeState="changeState"
    @login="login"
  />
  <forgetPassword
    v-show="state == 'forgetPassword'"
    @changeStateSecurity="changeStateSecurity"
    @changeState="changeState"
  />
  <securityForm
    v-show="state == 'securityForm'"
    @changeState="changeState"
    @changeStateForget="changeStateForget"
    :email="this.email"
    :question="this.question"
    :answer="this.answer"
  />
  <newPassword
    v-show="state == 'newPassword'"
    @changeState="changeState"
    :email="this.email"
  />
  <div class="carousel" v-show="state == ''">
    <h3 v-show="state == ''">Trending Now</h3>
    <carousel
      :category="cat"
      v-show="state == ''"
      @changeStateMovie="changeStateMovie"
    />
    <h3 v-show="state == ''">Now Playing</h3>
    <carousel
      :category="cat2"
      v-show="state == ''"
      @changeStateMovie="changeStateMovie"
    />
    <h3 v-show="state == ''">Top Rated</h3>
    <carousel
      :category="cat3"
      v-show="state == ''"
      @changeStateMovie="changeStateMovie"
    />
    <h3 v-show="state == ''">Upcoming</h3>
    <carousel
      :category="cat4"
      v-show="state == ''"
      @changeStateMovie="changeStateMovie"
    />
  </div>
  <moviepage :id="id" v-show="state == 'MoviePage'" />
</template>

<script>
import navBar from "./components/navBar.vue";
import signUpForm from "./components/signUpForm.vue";
import loginForm from "./components/loginForm.vue";
import carousel from "./components/carousel.vue";
import forgetPassword from "./components/forgetPassword.vue";
import securityForm from "./components/securityForm.vue";
import newPassword from "./components/newPassword.vue";
import moviepage from "./components/moviepage.vue";
import movieCard from "./components/movieCard.vue";
export default {
  name: "App",
  components: {
    navBar,
    signUpForm,
    loginForm,
    carousel,
    forgetPassword,
    securityForm,
    newPassword,
    moviepage,
    movieCard,
  },
  data() {
    return {
      loginState: false, // state that control showing and hiding things depends on login state
      state: "", // state that control the components that will be shown
      cat: "popular",
      cat2: "now_playing",
      cat3: "top_rated",
      cat4: "upcoming",
      email: "",
      question: "",
      answer: "",
      id: "",
    };
  },
  methods: {
    changeState(newState) {
      this.state = newState;
    },
    changeStateSecurity(newState, Email, Question, Answer) {
      this.state = newState;
      this.email = Email;
      this.question = Question;
      this.answer = Answer;
    },
    login(newLogin) {
      this.loginState = newLogin;
    },
    changeStateMovie(newState, Id) {
      this.state = newState;
      this.id = Id;
      console.log(this.id);
    },
    changeStateForget(newState,Email) {
      this.state = newState;
      this.email = Email;
    },
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
h3 {
  text-align: left;
  margin-left: 5%;
  margin-bottom: 20px;
  margin-top: 100px;
}
.carousel {
  margin-top: 100px;
}
.moviepage {
  margin: 0px;
  padding: 0px;
}
</style>
