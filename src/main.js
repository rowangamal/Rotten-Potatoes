import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import VueGtag from 'vue-gtag';
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';
createApp(App).use(store).use(router).mount('#app')
App.use(VueGtag, {
    config: { id: "G-4LV8JPWB1G" }
});
