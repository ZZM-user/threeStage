import request from '@/utils/request'

export function fetchCategoryData(params) {
  return request({
    url: 'http://localhost:8080/api/category/data',
    method: 'GET',
    params
  })
}
