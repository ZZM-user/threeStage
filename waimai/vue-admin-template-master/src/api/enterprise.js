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
 * 商家状态
 * @returns {[{label: string, value: number},{label: string, value: number}]}
 * @constructor
 */
export function EnterpriseStatus() {
  return [
    { label: '正常', value: 1 },
    { label: '停用', value: 0 }
  ]
}
