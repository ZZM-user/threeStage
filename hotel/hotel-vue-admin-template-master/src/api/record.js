import request from "@/utils/request";

export function fetchRecordData(params) {
  return request({
    url: "http://localhost:8080/api/record/data",
    method: "GET",
    params
  })
}

export function enterRecord(data) {
  return request({
    url: "http://localhost:8080/api/record/enter",
    method: "POST",
    data
  })
}
