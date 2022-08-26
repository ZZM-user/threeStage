import request from '@/utils/request'

/**
 * 获取数据
 * @param params
 * @returns {*}
 */
export function fetchMerchandiseData(params) {
  return request({
    url: '/api/merchandise/data',
    method: 'GET',
    params
  })
}

/**
 * 商家版
 * @param params
 * @returns {*}
 */
export function fetchMerchandise(params) {
  return request({
    url: '/api/merchandise/byself',
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
    url: '/api/merchandise/' + id,
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
    url: '/api/merchandise/add',
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
    url: '/api/merchandise/changeState',
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
    url: '/api/merchandise/update',
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
    url: '/api/merchandise/del',
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

/**
 * 口味推荐
 * @returns {[{name: string, value: string[]},{name: string, value: string[]},{name: string, value: string[]}]}
 */
export function flavorsListApi() {
  return [
    {
      name: '甜度',
      value: '甜度',
      tags: ['无糖', '少糖', '半糖', '多糖', '全糖']
    },
    {
      name: '辣度',
      value: '辣度',
      tags: ['不辣', '微辣', '中辣', '重辣']
    },
    {
      name: '温度',
      value: '温度',
      tags: ['常温', '加冰', '加热']
    }
  ]
}
