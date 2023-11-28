<template>
  <!-- <navBar/> -->
  <signUpForm />
</template>

<script>
import navBar from './components/navBar.vue'
import signUpForm from './components/signUpForm.vue'
export default {
  
  name: 'App',
  components: {
    navBar,
    signUpForm
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
*{
  box-sizing: border-box;
  margin: 0;
  padding: 0;
}
img{
  width: 100%;
  height: 100%;
}

</style>
