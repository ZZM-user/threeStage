import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/api/login',
    method: 'post',
    data
  })
}

export function getInfo(params) {
  return request({
    url: '/api/user/info',
    method: 'get',
    params
  })
}

export function logout() {
  return request({
    url: '/api/logout',
    method: 'post'
  })
}

export function getCaptcha(params) {
  return request({
    url: '/api/captcha/arithmetic',
    method: 'GET',
    params
  })
}
