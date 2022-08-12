import request from '@/utils/request'

export function fetchCarTrainInfo(params) {
  return request({
    url: 'http://localhost:8080/api/carModelManage/data',
    method: 'GET',
    params
  })

}
