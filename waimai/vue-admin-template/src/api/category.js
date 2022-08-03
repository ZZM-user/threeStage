import request from '@/utils/request'

/**
 * 分页查询
 * @param params
 * @returns {*}
 */
export function fetchCategoryList(params) {
  return request({
    url: 'http://localhost:8080/api/category/data',
    method: 'get',
    params
  })
}

/**
 * 添加分类信息
 * @param params
 * @returns {*}
 */
export function addCategoryInfo(params) {
  return request({
    url: 'http://localhost:8080/api/category/还没写',
    method: 'post',
    params
  })
}

/**
 * 修改分类信息
 * @param params
 * @returns {*}
 */
export function updateCategoryInfo(params) {
  return request({
    url: 'http://localhost:8080/api/category/还没写',
    method: 'post',
    params
  })
}
