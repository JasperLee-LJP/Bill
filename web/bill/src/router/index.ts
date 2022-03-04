import { createRouter, createWebHashHistory, Router, RouteRecordRaw } from "vue-router"

const routes: Array<RouteRecordRaw> = []

const router: Router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router