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
 * 上架状态
 * @returns {[{label: string, value: number},{label: string, value: number}]}
 */
export function MerchandiseStatus() {
  return [
    { label: '上架中', value: 1 },
    { label: '未上架', value: 0 }
  ]
}
