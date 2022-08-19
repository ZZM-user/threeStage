import request from "@/utils/request";

export function fetchRoomData(params) {
  return request({
    url: "http://localhost:8080/api/room/data",
    method: "GET",
    params
  })
}
