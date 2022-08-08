import request from '@/utils/request'

/**
 * 分页查询
 * @param params
 * @returns {*}
 */
export function fetchEmployeeData(params) {
  return request({
    url: 'http://localhost:8080/api/employee/data',
    method: 'GET',
    params
  })
}

/**
 * 新增
 * @param params
 * @returns {*}
 */
export function findEmployeeData(id) {
  return request({
    url: 'http://localhost:8080/api/employee/' + id,
    method: 'GET'
  })
}

/**
 * 切换状态
 * @param params
 * @returns {*}
 */
export function changeEmployeeState(data) {
  return request({
    url: 'http://localhost:8080/api/employee/changeStatus',
    method: 'POST',
    data
  })
}

/**
 * 校验登录名是否存在
 * @returns {*}
 * @param params
 */
export function checkLoginNameIsExists(params) {
  return request({
    url: 'http://localhost:8080/api/employee/exists/',
    method: 'GET',
    params
  })
}

/**
 * 新增
 * @param data
 * @returns {*}
 */
export function addEmployeeData(data) {
  return request({
    url: 'http://localhost:8080/api/employee/add',
    method: 'POST',
    data
  })
}

/**
 * 修改
 * @param data
 * @returns {*}
 */
export function editEmployeeData(data) {
  return request({
    url: 'http://localhost:8080/api/employee/update',
    method: 'POST',
    data
  })
}

/**
 * 删除
 * @param data
 * @returns {*}
 */
export function delEmployeeData(data) {
  return request({
    url: 'http://localhost:8080/api/employee/del',
    method: 'POST',
    data
  })
}

/**
 * 账号状态
 * @returns {[{label: string, value: number},{label: string, value: number}]}
 * @constructor
 */
export function EmployeeStatus() {
  return [
    { label: '正常', value: 0 },
    { label: '停用', value: 1 }
  ]
}
