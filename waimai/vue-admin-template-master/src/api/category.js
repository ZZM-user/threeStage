import request from '@/utils/request'

export function fetchCategoryData(params) {
  return request({
    url: '/api/category/data',
    method: 'GET',
    params
  })
}

export function fetchCategory(params) {
  return request({
    url: '/api/category/byself',
    method: 'GET',
    params
  })
}

/**
 * 查询商家信息（主要是为了分类）
 * @returns {*}
 * @param params
 */
export function findCategorysData(params) {
  return request({
    url: '/api/category/categorys',
    method: 'GET',
    params
  })
}

/**
 * 查找
 * @returns {*}
 * @param id
 */
export function findCategoryData(id) {
  return request({
    url: '/api/category/' + id,
    method: 'GET'
  })
}

/**
 * 新增
 * @param data
 * @returns {*}
 */
export function addCategoryData(data) {
  return request({
    url: '/api/category/add',
    method: 'POST',
    data
  })
}

/**
 * 修改
 * @param data
 * @returns {*}
 */
export function editCategoryData(data) {
  return request({
    url: '/api/category/update',
    method: 'POST',
    data
  })
}

/**
 * 删除
 * @param data
 * @returns {*}
 */
export function delCategoryData(data) {
  return request({
    url: '/api/category/del',
    method: 'POST',
    data
  })
}
