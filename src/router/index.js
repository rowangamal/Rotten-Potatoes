import { createRouter, createWebHistory } from 'vue-router'
import home from '../views/home.vue'
import about from '../views/about.vue'
import loginForm from '../views/loginForm.vue'
import signUpForm from '../views/signUpForm.vue'
import forgetPassword from '../views/forgetPassword.vue'
import moviePage from '../views/moviePage.vue'
import securityForm from '../views/securityForm.vue'
import newPassword from '../views/newPassword.vue'
import userProfile from '../views/userProfile.vue'
import watchList from '../views/watchList.vue'
import favourites from '../views/favourites.vue'
const routes = [
  {
    path: '/',
    name: 'home',
    component: home,
  },
  {
    path: '/about',
    name: 'about',
    component : about
  },
  {
    path: '/login',
    name: 'loginForm',
    component : loginForm
  },
  {
    path: '/signup',
    name: 'signup',
    component : signUpForm
  },
  {
    path: '/forgetpassword',
    name: 'forgetpassword',
    component : forgetPassword,
  },
  {
    path: '/movie/:id',
    name: 'movie',
    component : moviePage,
    props : true
  },
  {
    path: '/security',
    name : 'securityForm',
    component : securityForm,
  },
  {
    path: '/newpassword',
    name : 'newPassword',
    component : newPassword,
  },
  {
    path: '/profile',
    name : 'profile',
    component : userProfile,
  },
  {
    path: '/watchList',
    name : 'watchList',
    component : watchList,
  },
  {
    path: '/favourites',
    name : 'favourites',
    component : favourites,
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
