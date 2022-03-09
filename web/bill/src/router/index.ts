import {
  createRouter,
  createWebHashHistory,
  Router,
  RouteRecordRaw
} from "vue-router";
import { vueRouters } from "./routes";

const routes: Array<RouteRecordRaw> = vueRouters();
// console.log("routes: ", routes);

const router: Router = createRouter({
  history: createWebHashHistory(),
  routes
});

export default router;
