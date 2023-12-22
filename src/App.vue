<template>
  <router-view/>
</template>

<style>
#app {
  font-family: "Monomaniac One", sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  letter-spacing: 0,25px;
  text-transform: capitalize;
}


a {
  font-weight: bold;
  color: white;
  text-decoration: none;
}

.all{
  animation-name: mainAnimate;
  animation-duration: 0.8s;
}
@keyframes mainAnimate {
  0% {
    transform: translateY(150px);
    opacity: 0;
  }

  100% {
    transform: translateY(0px);
    opacity: 1;
  }
}
a.router-link-exact-active {
  color: white;
  text-decoration: none;
}
</style>
