import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/home/home'
import Login from '@/components/login/login'
import Register from '@/components/register/register'
import Consult from '@/components/consult/consult'
import SendHappiness from'@/components/happiness/sendhappiness'
import Happiness from '@/components/happiness/happiness'
import ApplyWedding from '@/components/wedding/applywedding'
import PersonalInfo from '@/components/personalInfo/personalInfo'
import Wedding from '@/components/wedding/wedding'
import WeddingDetail from '@/components/wedding/weddingdetail'
import ConsultList from '@/components/consult/consultlist'
Vue.use(Router)

export default new Router({
  mode:'history',
  routes: [
    {
      path: '/home',
      name: 'home',
      component: Home
    },
    {
      path: '/login',
      component: Login
    },
    {
      path:'/register',
      component:Register
    },
    {
      path:'/consult',
      component:Consult
    },
    {
      path:'/sendhappiness',
      component:SendHappiness
    },
    {
      path:'/happiness',
      component:Happiness
    },
    {
      path:'/applywedding',
      component:ApplyWedding
    },{
      path:'/personalInfo',
      component:PersonalInfo,
      children:[
        {
          path:'baseInfo',
          component:() => import('@/components/personalInfo/children/baseInfo')
        },
        {
          path:'addAlbumMax',
          component:() => import('@/components/personalInfo/children/addAlbumMax')
        },
        {
          path:'album',
          component:() => import('@/components/personalInfo/children/album')
        },
        {
          path:'beVIP',
          component:() => import('@/components/personalInfo/children/beVIP')
        },
        {
          path:'changePassword',
          component:() => import('@/components/personalInfo/children/changePassword')
        },
        {
          path:'consumption',
          component:() => import('@/components/personalInfo/children/consumption')
        },
        {
          path:'dateRecord',
          component:() => import('@/components/personalInfo/children/dateRecord')
        },
        {
          path:'dateStandard',
          component:() => import('@/components/personalInfo/children/dateStandard')
        },
        {
          path:'friends',
          component:() => import('@/components/personalInfo/children/friends')
        },
        {
          path:'introduction',
          component:() => import('@/components/personalInfo/children/introduction')
        },
        {
          path:'limits',
          component:() => import('@/components/personalInfo/children/limits')
        },
        {
          path:'charge',
          component:() => import('@/components/personalInfo/children/charge')
        },
        {
          path:'uploadPhoto',
          component:() => import('@/components/personalInfo/children/uploadPhoto')
        }
      ]
    },
    {
      path:'/wedding',
      component:Wedding
    },
    {
      path:'/weddingdetail',
      component:WeddingDetail
    },
    {
      path:'/consultlist',
      component:ConsultList
    }
  ]
})
