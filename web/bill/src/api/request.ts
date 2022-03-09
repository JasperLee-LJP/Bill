import axios from "axios";
import { ElMessage } from "element-plus";
import { debounce } from "lodash-es";

import router from "@/router/index";

const MessageError = debounce(ElMessage.error, 200);

const request = axios.create();
request.interceptors.request.use(
  config => {
    return config;
  },
  error => {
    return Promise.reject(error);
  }
);
request.interceptors.response.use(
  response => {
    console.log("response: ", response);
    if (response.status >= 200 && response.status < 300) {
      return response.data;
    } else {
      return undefined;
    }
  },
  error => {
    const status = error?.response?.status;
    const messae = error?.response?.data?.message;
    const path = router.currentRoute.value.path;
    if (status === 401 && path !== "/login") {
      router.replace("/login");
    } else if (messae) {
      MessageError(messae);
    } else {
      MessageError("服务器错误，请联系管理员");
    }
    return undefined;
  }
);

export default request;
