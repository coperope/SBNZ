import "@babel/polyfill";
import "mutationobserver-shim";
import Vue from "vue";
import "./plugins/bootstrap-vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import vuetify from "./plugins/vuetify";
import 'material-design-icons-iconfont/dist/material-design-icons.css'
import Vuetify from 'vuetify/lib'

Vue.config.productionTip = false;

Vue.use(Vuetify)

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount("#app");
