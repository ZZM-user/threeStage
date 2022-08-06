import request from '@/utils/request'

export function fetchCarData(params) {
  return request({
      url: 'http://localhost:8080/api/dealer/data',
      method: 'GET',
      params
    }
  )
}
