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

// 会员状态
export function ContamerStatus() {
  return [
    { label: '停用', value: 1 },
    { label: '正常', value: 0 }
  ]
}
