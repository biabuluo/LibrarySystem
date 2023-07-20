import Vue from 'vue'
import Router from 'vue-router'
import AppIndex from '../components/home/AppIndex'
import Login from '../components/Login'
import Home from '../components/Home'
import LibraryIndex from '../components/library/LibraryIndex'
import markindex from '../components/mark/markindex'
import personindex from '../components/percen/personindex'
import managerindex from '../components/manager/managerindex'
import mana_home from '../components/manager/mana_home/mana_home'
import mana_user from '../components/manager/mana_user/mana_user'
import mana_book from '../components/manager/mana_books/mana_books'
import mana_cate from '../components/manager/mana_category/mana_cate'
import mana_borrow from '../components/manager/mana_borrow/mana_borrow'
Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
     {
      path: '/',
      name: 'login',
      redirect: '/login',
      component: Login,
      meta: {
        requireAuth: true
      }
    },
    {
      path: '/home',
      name: 'Home',
      component: Home,
      redirect: '/index',
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
          path: '/jotter',
          name: 'mark',
          component: markindex,
          meta: {
            requireAuth: true
          }
        },
        {
          path: '/admin',
          name: 'percen',
          component: personindex,
          meta: {
            requireAuth: true
          }
        },
        {
          path: '/manager',
          name: 'manager',
          component: managerindex,
          meta: {
            requireAuth: true
          },
          children:[
            {
              path: '/manager/mana_home',
              name: 'mana_home',
              component: mana_home,
              meta: {
                requireAuth: true
              }
            },
            {
              path: '/manager/mana_user',
              name: 'mana_user',
              component: mana_user,
              meta: {
                requireAuth: true
              }
            },
            {
              path: '/manager/mana_books',
              name: 'mana_books',
              component: mana_book,
              meta: {
                requireAuth: true
              }
            },
            {
              path: '/manager/mana_cate',
              name: 'mana_cate',
              component: mana_cate,
              meta: {
                requireAuth: true
              }
            },
            {
              path: '/manager/mana_borrow',
              name: 'mana_borrow',
              component: mana_borrow,
              meta: {
                requireAuth: true
              }
            },
          ]
        },
      ]
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
  ]
})
