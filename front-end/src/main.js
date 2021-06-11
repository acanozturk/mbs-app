import Vue from 'vue';
import App from './App.vue';
import router from './router';
import Slider from '@jeremyhamm/vue-slider';
import store from './store/store';

Vue.use(Slider);
Vue.config.productionTip = false;

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app');
