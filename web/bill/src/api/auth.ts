import request from "./request";

export function login({ username = "", password = "" } = {}) {
  return request({
    method: "post",
    url: "/api/user/login",
    data: { username, password }
  });
}
