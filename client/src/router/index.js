import Vue from 'vue'
import Router from 'vue-router'
import Panel from '@/components/Panel'
import List from '@/components/panel/List'
import Member from '@/components/panel/Member'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Panel',
      component: Panel,
      children: [
        {
          path: '',
          name: 'List',
          component: List
        },
        {
          path: '/user',
          name: 'Member',
          component: Member
        }
      ]
    }
  ]
})
