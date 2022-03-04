import { App, defineAsyncComponent } from 'vue'
import { RouteRecordRaw } from 'vue-router'
import { lowerFirst, upperFirst } from "lodash-es"

function getModules() {
  const components = import.meta.glob('../views/**/*.vue')
  return components
}

function getComponents() {
  const components = import.meta.globEager('../views/**/*.vue')
  return components
}

// 自动注册组件
export const asyncComponent = function (app: App<Element>): void {
  const modules = getModules();
  const components = getComponents();
  Object.keys(modules).forEach((key: string) => {
    const viewSrc = components[key];
    const file = viewSrc.default;
    if (!file.isComponents) return
    const AsyncComponent = defineAsyncComponent(modules[key])
    app.component(upperFirst(file.name), AsyncComponent)
  });
};

// 自动注册路由
export const vueRouters = function (): Array<RouteRecordRaw> {
  let routerList: Array<RouteRecordRaw> = [];
  const modules = getModules();
  const components = getComponents();
  Object.keys(modules).forEach(key => {
    const viewSrc = components[key];
    const file = viewSrc.default;
    if (!file.isRouter) return
    // 首字母转小写 letterToLowerCase 首字母转大写 letterToUpperCase
    routerList.push({
      path: `/${lowerFirst(file.name)}`,
      name: `${upperFirst(file.name)}`,
      component: modules[key]
    });
  })
  return routerList
}