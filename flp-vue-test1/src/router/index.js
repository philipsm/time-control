import Vue from 'vue'
import Router from 'vue-router'
import AppIndex from '@/components/home/Appindex'
import Login from '@/components/Login'
import Register from '@/components/Register'
import Home from '@/components/Home'
import LibraryIndex from '@/components/library/LibraryIndex'
import AdminIndex from '@/components/admin/AdminIndex'
// import DashBoard from '../components/admin/dashboard/admin/index'
import ArticleDetails from '../components/jotter/ArticleDetails'
import Articles from '../components/jotter/Articles'
import Editor from '../components/admin/content/ArticleEditor'

//时间管理模块相关路由
import TimePlanIndex from '@/components/time/TimePlanIndex'


Vue.use(Router)

export default new Router({
  mode: "history",
  routes: [
    {
      path: '/',
      name: 'index',
      redirect: '/index',
      component: AppIndex,
      meta: {
        requireAuth: true
      }
    },

    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/register',
      name: 'Register',
      component: Register
    },
    {
      path: '/home',
      name: 'Home',
      component: Home,
      redirect: "/index",
      children: [
        {
          path: '/index',
          name: 'AppIndex',
          component: AppIndex,
          meta: {
            requireAuth: true
          }
        },
        {
          path: '/library',
          name: 'Library',
          component: LibraryIndex,
          meta: {
            requireAuth: true
          }
        },
        {
          path: '/timePlan',
          name: 'mounted',
          component: TimePlanIndex,
          meta: {
            requireAuth: true
          }
        }
      ]
    },
    {
      path: '/admin',
      name: 'Admin',
      component: AdminIndex,
      meta: {
        requireAuth: true
      },
      children: [
        // {
        //   path: '/admin/dashboard',
        //   name: 'dashboard',
        //   component: DashBoard,
        //   meta: {
        //     requireAuth: true
        //   }
        // }
      ]
    }


  ]
})

// 用于创建默认路由
export const createRouter = routes => new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'Default',
      redirect: '/home',
      component: Home
    },
    {
      // home页面并不需要被访问，只是作为其它组件的父组件
      path: '/home',
      name: 'Home',
      component: Home,
      redirect: '/index',
      children: [
        {
          path: '/index',
          name: 'AppIndex',
          component: AppIndex
        },
        {
          path: '/jotter',
          name: 'Jotter',
          component: Articles
        },
        {
          path: '/jotter/article',
          name: 'Article',
          component: ArticleDetails
        },
        {
          path: '/admin/content/editor',
          name: 'Editor',
          component: Editor,
          meta: {
            requireAuth: true
          }
        },
        {
          path: '/library',
          name: 'Library',
          component: LibraryIndex
        }
      ]
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/register',
      name: 'Register',
      component: Register
    },
    {
      path: '/admin',
      name: 'Admin',
      component: AdminIndex,
      meta: {
        requireAuth: true
      },
      children: [
        {
          path: '/admin/dashboard',
          name: 'dashboard',
          component: DashBoard,
          meta: {
            requireAuth: true
          }
        }
      ]
    }
  ]
})
