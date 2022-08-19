import request from "@/utils/request";

export function fetchEmployeeData(params) {
  return request({
    url: "http://localhost:8080/api/employee/data",
    method: "GET",
    params
  })
}
