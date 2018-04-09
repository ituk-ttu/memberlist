import Vue from 'vue'
import Router from 'vue-router'
import Panel from '@/components/Panel'
import List from '@/components/panel/List'
import Member from '@/components/panel/Member'
import Login from '@/components/Login'
import Verify from '@/components/Verify'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/login/:email/:key',
      name: 'Verify',
      component: Verify
    },
    {
      path: '/',
      component: Panel,
      children: [
        {
          path: '',
          name: 'List',
          component: List
        },
        {
          path: '/member/:id',
          name: 'Member',
          component: Member
        }
      ]
    }
  ]
})
