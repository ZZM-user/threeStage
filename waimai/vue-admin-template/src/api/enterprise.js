import request from '@/utils/request'

/**
 * 分页查询
 * @param params
 * @returns {*}
 */
export function fetchEnterpriseList(params) {
  return request({
    url: 'http://localhost:8080/api/enterprise/data',
    method: 'get',
    params
  })
}

export function stateListApi() {
  return [
    {
      label: '正常',
      value: 0
    },
    {
      label: '停用',
      value: 1
    },
    {
      label: '未审核',
      value: 2
    }
  ]
}
