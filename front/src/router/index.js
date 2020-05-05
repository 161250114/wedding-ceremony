import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/login/login'
import Register from '@/components/register/register'
import Consult from '@/components/consult/consult'
import SendHappiness from'@/components/happiness/sendhappiness'
import Happiness from '@/components/happiness/happiness'
import ApplyWedding from '@/components/wedding/applywedding'
import PersonalInfo from '@/components/personalInfo/personalInfo'
import Wedding from '@/components/wedding/wedding'
import WeddingRecord from '@/components/wedding/weddingrecord'
import ConsultList from '@/components/consult/consultlist'
import ChatRoom from "../components/chatRoom/ChatRoom"
import Check from "../components/check/Check"
import Recommend from "../components/recommend/Recommend"
import Search from "../components/search/Search"

Vue.use(Router)

export default new Router({
  mode:'history',
  routes: [
    {
      path: '/',
      redirect:'/recommend',
      component: Recommend
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
      path: '/search',
      component: Search
    },
    {
      path: '/recommend',
      component: Recommend
    },
    {
      path: '/check/:id',
      component: Check
    },
    {
      path: '/chatRoom',
      component: ChatRoom
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
      redirect:'/personalInfo/baseInfo',
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
          path:'billList',
          component:() => import('@/components/personalInfo/children/billList')
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
        },
        {
          path:'friendApply',
          component:() => import('@/components/personalInfo/children/friendApply')
        },
        {
          path:'dateApply',
          component:() => import('@/components/personalInfo/children/dateApply')
        }
      ]
    },
    {
      path:'/wedding',
      component:Wedding
    },
    {
      path:'/weddingrecord',
      component:WeddingRecord
    },
    {
      path:'/consultlist',
      component:ConsultList
    }
  ]
})
