import request from '@/utils/request'

/**
 * 分页查询
 * @param params
 * @returns {*}
 */
export function fetchMerchandiseList(params) {
  return request({
    url: 'http://localhost:8080/api/merchandise/data',
    method: 'get',
    params
  })
}

/**
 * 更改菜品状态
 * @param params
 * @returns {*}
 */
export function changeMerchandiseAccountStatus(params) {
  return request({
    url: 'http://localhost:8080/api/merchandise/还没写',
    method: 'post',
    params
  })
}

/**
 * 添加菜品信息
 * @param params
 * @returns {*}
 */
export function addMerchandiseInfo(params) {
  return request({
    url: 'http://localhost:8080/api/merchandise/还没写',
    method: 'post',
    params
  })
}

/**
 * 修改菜品信息
 * @param params
 * @returns {*}
 */
export function updateMerchandiseInfo(params) {
  return request({
    url: 'http://localhost:8080/api/merchandise/还没写',
    method: 'post',
    params
  })
}

export function MerchandiseStatus() {
  return [
    {
      label: '停售',
      value: 0
    },
    {
      label: '启售',
      value: 1
    }
  ]
}
