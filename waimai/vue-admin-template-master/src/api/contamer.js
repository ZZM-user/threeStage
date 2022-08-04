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
