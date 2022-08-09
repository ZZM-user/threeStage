import request from '@/utils/request'

/**
 * 分页查询
 * @param params
 * @returns {*}
 */
export function fetchEnterpriseData(params) {
  return request({
    url: 'http://localhost:8080/api/enterprise/data',
    method: 'GET',
    params
  })
}

/**
 * 查找
 * @returns {*}
 * @param id
 */
export function findEnterpriseData(id) {
  return request({
    url: 'http://localhost:8080/api/enterprise/' + id,
    method: 'GET'
  })
}

/**
 * 查询商家信息（主要是为了分类）
 * @returns {*}
 * @param params
 */
export function findEnterprisesData(params) {
  return request({
    url: 'http://localhost:8080/api/enterprise/enterprises',
    method: 'GET',
    params
  })
}

/**
 * 通过审核
 * @returns {*}
 * @param data
 */
export function changeEnterpriseState(data) {
  return request({
    url: 'http://localhost:8080/api/enterprise/approved',
    method: 'POST',
    data
  })
}

/**
 * 校验电话号是否存在
 * @returns {*}
 * @param params
 */
export function checkPhoneIsExists(params) {
  return request({
    url: 'http://localhost:8080/api/enterprise/exists/',
    method: 'GET',
    params
  })
}

/**
 * 新增
 * @param data
 * @returns {*}
 */
export function addEnterpriseData(data) {
  return request({
    url: 'http://localhost:8080/api/enterprise/add',
    method: 'POST',
    data
  })
}

/**
 * 修改
 * @param data
 * @returns {*}
 */
export function editEnterpriseData(data) {
  return request({
    url: 'http://localhost:8080/api/enterprise/update',
    method: 'POST',
    data
  })
}

/**
 * 删除
 * @param data
 * @returns {*}
 */
export function delEnterpriseData(data) {
  return request({
    url: 'http://localhost:8080/api/enterprise/del',
    method: 'POST',
    data
  })
}

/**
 * 商家状态
 * @returns {[{label: string, value: number},{label: string, value: number}]}
 * @constructor
 */
export function EnterpriseStatus() {
  return [
    { label: '未审核', value: 2 },
    { label: '正常', value: 0 },
    { label: '停用', value: 1 }
  ]
}
