import request from '@/utils/request'

/**
 * 获取数据
 * @param params
 * @returns {*}
 */
export function fetchMerchandiseData(params) {
  return request({
    url: 'http://localhost:8080/api/merchandise/data',
    method: 'GET',
    params
  })
}

/**
 * 新增
 * @returns {*}
 * @param id
 */
export function findMerchandiseData(id) {
  return request({
    url: 'http://localhost:8080/api/merchandise/' + id,
    method: 'GET'
  })
}

/**
 * 新增
 * @param data
 * @returns {*}
 */
export function addMerchandiseData(data) {
  return request({
    url: 'http://localhost:8080/api/merchandise/add',
    method: 'POST',
    data
  })
}

/**
 * 通过审核
 * @returns {*}
 * @param data
 */
export function changeMerchandiseState(data) {
  return request({
    url: 'http://localhost:8080/api/merchandise/changeState',
    method: 'POST',
    data
  })
}

/**
 * 修改
 * @param data
 * @returns {*}
 */
export function editMerchandiseData(data) {
  return request({
    url: 'http://localhost:8080/api/merchandise/update',
    method: 'POST',
    data
  })
}

/**
 * 删除
 * @param data
 * @returns {*}
 */
export function delMerchandiseData(data) {
  return request({
    url: 'http://localhost:8080/api/merchandise/del',
    method: 'POST',
    data
  })
}

/**
 * 上架状态
 * @returns {[{label: string, value: number},{label: string, value: number}]}
 */
export function MerchandiseStatus() {
  return [
    { label: '上架中', value: 1 },
    { label: '未上架', value: 0 }
  ]
}
