import request from '@/utils/request'

export function login(data) {
  return request({
    url: 'http://localhost:8080/api/login',
    method: 'post',
    data
  })
}

export function getInfo(params) {
  return request({
    url: 'http://localhost:8080/api/user/info',
    method: 'get',
    params
  })
}

export function getCaptcha(params) {
  return request({
    url: 'http://localhost:8080/api/captcha/png',
    method: 'get',
    params
  })
}

export function logout() {
  return request({
    url: '/vue-admin-template/user/logout',
    method: 'post'
  })
}
