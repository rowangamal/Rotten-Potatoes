import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import VueGtag from 'vue-gtag';
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';
const app=createApp(App);
//bug because i should add view gtag after create app, before mount
app.use(VueGtag, {
  config: { id: "G-4LV8JPWB1G" }
});
app.use(store).use(router).mount('#app')

