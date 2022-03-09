import { App, defineAsyncComponent } from "vue";
import { RouteRecordRaw } from "vue-router";
import { upperFirst } from "lodash-es";

function getModules() {
  const components = import.meta.glob("../views/**/*Page.vue");
  return components;
}

function getComponents() {
  const components = import.meta.globEager("../views/**/*Page.vue");
  return components;
}

// 自动注册组件
export const asyncComponent = function (app: App<Element>): void {
  const modules = getModules();
  const components = getComponents();
  Object.keys(modules).forEach((key: string) => {
    const viewSrc = components[key];
    const file = viewSrc.default;
    if (!file.isComponents) return;
    const AsyncComponent = defineAsyncComponent(modules[key]);
    app.component(upperFirst(file.name), AsyncComponent);
  });
};

// 自动注册路由
export const vueRouters = function (): Array<RouteRecordRaw> {
  const routerList: Array<RouteRecordRaw> = [];
  const modules = getModules();
  const components = getComponents();
  const routersChildren: { [propName: string]: Array<RouteRecordRaw> } = {};
  for (const key of Object.keys(modules)) {
    const viewSrc = components[key];
    const file = viewSrc.default;
    const parent = file.parent as string;
    if (parent) {
      const children = routersChildren[parent] || [];
      children.push({
        path: `${file.routePath}`,
        name: `${file.routeName}`,
        component: modules[key]
      });
      routersChildren[parent] = children;
    } else {
      routerList.push({
        path: `${file.routePath}`,
        name: `${file.routeName}`,
        component: modules[key]
      });
    }
  }

  for (const router of routerList) {
    const name = router.name as string;
    const children = routersChildren[name];
    if (children) {
      router.children = children;
    }
  }

  return routerList;
};
