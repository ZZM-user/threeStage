import request from '@/utils/request'

/**
 * 分页查询
 * @param params
 * @returns {*}
 */
export function fetchEmployeeList(params) {
  return request({
    url: 'http://localhost:8080/api/employee/data',
    method: 'get',
    params
  })
}

/**
 * 更改账号状态
 * @param params
 * @returns {*}
 */
export function changeEmployeeAccountStatus(params) {
  return request({
    url: 'http://localhost:8080/api/employee/还没写',
    method: 'post',
    params
  })
}

/**
 * 添加员工信息
 * @param params
 * @returns {*}
 */
export function addEmployeeInfo(params) {
  return request({
    url: 'http://localhost:8080/api/employee/还没写',
    method: 'post',
    params
  })
}

/**
 * 修改员工信息
 * @param params
 * @returns {*}
 */
export function updateEmployeeInfo(params) {
  return request({
    url: 'http://localhost:8080/api/employee/还没写',
    method: 'post',
    params
  })
}

/**
 * 员工状态
 * @returns {[{label: string},{label: string}]}
 * @constructor
 */
export function EmployeeStatus() {
  return [
    { label: '正常', value: 1 },
    { label: '停用', value: 0 }
  ]
}
