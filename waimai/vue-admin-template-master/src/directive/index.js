import Vue from 'vue'
import AdminPermission from './permission/AdminPermission'
import EnterprisePermission from '@/directive/permission/EnterprisePermission'

const install = function(Vue) {
  Vue.directive('admin', AdminPermission)
  Vue.directive('enterprise', EnterprisePermission)
}
if (window.Vue) {
  window['admin'] = AdminPermission
  window['enterprise'] = EnterprisePermission
  Vue.use(install)
}
export default install
