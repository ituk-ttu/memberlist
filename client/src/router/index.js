import Vue from 'vue'
import Router from 'vue-router'
import Panel from '@/components/Panel'
import List from '@/components/panel/List'
import Member from '@/components/panel/Member'
import Login from '@/components/Login'
import Verify from '@/components/Verify'
import EditUser from '@/components/panel/EditUser'
import AccessList from '@/components/panel/AccessList'
import AddRoom from '@/components/panel/AddRoom'

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
          path: '/user/:id',
          name: 'Member',
          component: Member
        },
        {
          path: '/accessList',
          name: 'AccessList',
          component: AccessList
        },
        {
          path: '/user/:id/edit',
          name: 'EditMember',
          component: EditUser
        },
        {
          path: '/room',
          name: 'AddRoom',
          component: AddRoom
        }
      ]
    }
  ]
})
