// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import VueConfig from 'vue-config'
import VueResource from 'vue-resource'
import 'vue-awesome/icons'
import Icon from 'vue-awesome/components/Icon'
import VueJwtDecode from 'vue-jwt-decode'

import './assets/scss/style.scss'
import '../node_modules/bootstrap/js/dist/index'

Vue.config.productionTip = false

const config = {
  API_BASE: process.env.API_BASE
}
Vue.use(VueConfig, config)
Vue.use(VueResource)
Vue.use(VueJwtDecode)
Vue.component('icon', Icon)

Vue.http.interceptors.push((request, next) => {
  request.headers.set('Authorization', 'Bearer ' + localStorage.getItem('accessToken'))
  next((response) => {
    if (response.status === 401 && localStorage.getItem('refreshToken') != null) {
      Vue.http.post(process.env.API_BASE + '/auth/refresh', { token: localStorage.getItem('refreshToken') })
        .then(res => {
          localStorage.setItem('accessToken', res.headers.get('Authorization').substring(7))
          localStorage.setItem('refreshToken', res.body)
          let method = request.method.toLowerCase()
          Vue.emit('refreshToken')
          return Vue.http[method](request.url, request.params)
        },
        res => {
          Vue.router.push({ name: 'Login' })
        })
    } else {
      return response
    }
  })
})

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
