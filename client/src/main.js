// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import VueConfig from 'vue-config'
import VueResource from 'vue-resource'

import './assets/scss/style.scss'
import '../node_modules/bootstrap/js/dist/index'

Vue.config.productionTip = false

const config = {
  API_BASE: process.env.API_BASE
}
Vue.use(VueConfig, config)

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
