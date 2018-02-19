import Vue from 'vue'
import Router from 'vue-router'
import Panel from '@/components/Panel'
import List from '@/components/panel/List'

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
        }
      ]
    }
  ]
})
