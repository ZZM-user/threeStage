const getters = {
  sidebar: state => state.app.sidebar,
  device: state => state.app.device,
  token: state => state.user.token,
  avatar: state => state.user.avatar,
  name: state => state.user.name,
  id: state => state.user.id,
  // 登录类型、全路由、增加的路由
  loginType: state => state.user.loginType,
  // 给侧边栏用的，/layout/components/Sidebar/index.vue
  allRouters: state => state.permission.routers,
  // 给最外层permission的动态路由追加
  addRouters: state => state.permission.addRouters

}
export default getters
