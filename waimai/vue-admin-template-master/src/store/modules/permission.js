import { adminRotes, constantRoutes, enterpriseRotes } from '@/router'
import store from '@/store'

const permission = {
  state: {
    routers: constantRoutes,
    // 动态 添加路由
    addRouters: []
  },
  mutations: {
    SET_ROUTERS: (state, routers) => {
      state.addRouters = routers
      // 所有的路由 = 通用路由 + 商家 or 管理员  路由
      state.routers = constantRoutes.concat(routers)
    }
  },
  actions: {
    GenerateRoutes({ commit }) {
      return new Promise(resolve => {
        const loginType = store.getters.loginType
        // 1、管理员  2、商家
        if (loginType === 1) {
          commit('SET_ROUTERS', adminRotes)
        } else if (loginType === 2) {
          commit('SET_ROUTERS', enterpriseRotes)
        }
        resolve()
      })
    }
  }
}

export default permission
