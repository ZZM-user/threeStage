import Vue from 'vue'
import adminPermission from './permission/adminPermission'

const install = function(Vue) {
  Vue.directive('admin', adminPermission)
}
if (window.Vue) {
  window['admin'] = adminPermission
  Vue.use(install)
}
export default install
