import { getInfo, login, logout } from '@/api/user'
import { getToken, removeToken, setToken } from '@/utils/auth'
import { resetRouter } from '@/router'

const getDefaultState = () => {
  return {
    token: getToken(),
    account: '',
    avatar: '',
    // 1、管理员 2、商家
    loginType: 2,
    id: undefined,
    // 后台管理员 or 商家名称
    name: ''
  }
}

const state = getDefaultState()

const mutations = {
  RESET_STATE: (state) => {
    Object.assign(state, getDefaultState())
  },
  SET_TOKEN: (state, token) => {
    state.token = token
  },
  SET_ACCOUNT: (state, account) => {
    state.account = account
  },
  SET_AVATAR: (state, avatar) => {
    state.avatar = avatar
  },
  SET_LOGIN_TYPE: (state, loginType) => {
    state.loginType = loginType
  },
  SET_ID: (state, id) => {
    state.id = id
  },
  SET_NAME: (state, name) => {
    state.name = name
  }
}

const actions = {
  // user login
  login({ commit }, userInfo) {
    const { account, loginPwd, code, uuid } = userInfo
    return new Promise((resolve, reject) => {
      login({ account: account.trim(), loginPwd, uuid, code }).then(response => {
        const { code, data, message } = response
        if (code === 0) {
          commit('SET_TOKEN', data)
          setToken(data)
          resolve()
        }
        reject(message)
      }).catch(error => {
        reject(error)
      })
    })
  },

  // get user info
  getInfo({ commit }) {
    return new Promise((resolve, reject) => {
      getInfo().then(response => {
        const { data } = response

        if (!data) {
          return reject('身份验证失败，请重新登录！')
        }
        const { id, name, account, avatar, loginType } = data
        commit('SET_ACCOUNT', account)
        commit('SET_AVATAR', avatar)
        commit('SET_LOGIN_TYPE', loginType)
        commit('SET_ID', id)
        commit('SET_NAME', name)
        resolve(data)
      }).catch(error => {
        reject(error)
      })
    })
  },

  // user logout
  logout({ commit, state }) {
    return new Promise((resolve, reject) => {
      logout().then(() => {
        removeToken() // must remove  token  first
        resetRouter()
        commit('RESET_STATE')
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  // remove token
  resetToken({ commit }) {
    return new Promise(resolve => {
      removeToken() // must remove  token  first
      commit('RESET_STATE')
      resolve()
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}

