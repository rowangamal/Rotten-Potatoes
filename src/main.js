import { createApp } from 'vue'
import App from './App.vue'
import VueGtag from 'vue-gtag';
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';

const app = createApp(App);
app.use(VueGtag, {
  config: { id: "G-4LV8JPWB1G" } 
});
app.mount('#app')
// app.use(VueGtag, {
//   config: { id: "419282769" } 
// });
// window.dataLayer = window.dataLayer || [];
//   function gtag() {
//     dataLayer.push(arguments);
//   }
//   gtag('js', new Date());
//   gtag('config', '419282769');
//   ga('create', '419282769', {
//     'cookieDomain': 'none'
//   });
// "419282769"