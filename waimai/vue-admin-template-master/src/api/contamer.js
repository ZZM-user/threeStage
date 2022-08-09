import request from '@/utils/request'

/**
 * 分页查询
 * @param params
 * @returns {*}
 */
export function fetchContamerData(params) {
  return request({
    url: 'http://localhost:8080/api/contamer/data',
    method: 'GET',
    params
  })
}

/**
 * 新增
 * @returns {*}
 * @param id
 */
export function findContamerData(id) {
  return request({
    url: 'http://localhost:8080/api/contamer/' + id,
    method: 'GET'
  })
}

/**
 * 新增
 * @param data
 * @returns {*}
 */
export function addContamerData(data) {
  return request({
    url: 'http://localhost:8080/api/contamer/add',
    method: 'POST',
    data
  })
}

/**
 * 通过审核
 * @returns {*}
 * @param data
 */
export function changeContamerState(data) {
  return request({
    url: 'http://localhost:8080/api/contamer/changeState',
    method: 'POST',
    data
  })
}

/**
 * 校验登录名是否存在
 * @returns {*}
 * @param params
 */
export function checkLoginIdIsExists(params) {
  return request({
    url: 'http://localhost:8080/api/contamer/exists/',
    method: 'GET',
    params
  })
}

/**
 * 修改
 * @param data
 * @returns {*}
 */
export function editContamerData(data) {
  return request({
    url: 'http://localhost:8080/api/contamer/update',
    method: 'POST',
    data
  })
}

/**
 * 删除
 * @param data
 * @returns {*}
 */
export function delContamerData(data) {
  return request({
    url: 'http://localhost:8080/api/contamer/del',
    method: 'POST',
    data
  })
}

/**
 * 账号状态
 * @returns {[{label: string, value: number},{label: string, value: number}]}
 * @constructor
 */
export function ContamerStatus() {
  return [
    { label: '正常', value: 0 },
    { label: '停用', value: 1 }
  ]
}
