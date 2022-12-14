import Vue from 'vue'
import Router from 'vue-router'
/* Layout */
import Layout from '@/layout'

Vue.use(Router)

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'/'el-icon-x' the icon show in the sidebar
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },

  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [{
      path: 'dashboard',
      name: 'Dashboard',
      component: () => import('@/views/dashboard/index'),
      meta: { title: '饿美了么', icon: 'el-icon-eleme' }
    }]
  },
  {
    path: '/employee',
    component: Layout,
    redirect: '/employee/index',
    children: [{
      path: '/employee/index',
      name: 'Index',
      component: () => import('@/views/employee/index'),
      meta: { title: '员工管理', icon: 'el-icon-s-cooperation' }
    }]
  },
  {
    path: '/enterprise',
    component: Layout,
    redirect: '/enterprise/index',
    children: [{
      path: '/enterprise/index',
      name: 'Index',
      component: () => import('@/views/enterprise/index'),
      meta: { title: '商家管理', icon: 'el-icon-s-check' }
    }]
  },
  {
    path: '/category',
    component: Layout,
    redirect: '/category/index',
    children: [{
      path: '/category/index',
      name: 'Index',
      component: () => import('@/views/category/index'),
      meta: { title: '分类管理', icon: 'el-icon-menu' }
    }]
  },
  {
    path: '/merchandise',
    component: Layout,
    redirect: '/merchandise/index',
    children: [{
      path: '/merchandise/index',
      name: 'Index',
      component: () => import('@/views/merchandise/index'),
      meta: { title: '菜品管理', icon: 'el-icon-dish-1' }
    }]
  },
  {
    path: '/contamer',
    component: Layout,
    redirect: '/contamer/index',
    children: [{
      path: '/contamer/index',
      name: 'Index',
      component: () => import('@/views/contamer/index'),
      meta: { title: '会员管理', icon: 'el-icon-user' }
    }]
  },

  // 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
