<template>
  <header
    class="h-60px border-b border-gray-300 border-solid flex items-center justify-between"
  >
    <!-- <h1 class="ml-20px text-2xl">个人记账系统</h1> -->
    <h1 class="ml-20px text-2xl">测试系统</h1>
    <HeaderMenu :menus="menus" :active-index="activeMenu"></HeaderMenu>
    <div class="mr-20px flex items-center">
      <el-avatar :size="30" :src="avatarUrl"></el-avatar>
      <span class="ml-10px">JasperTest</span>
    </div>
  </header>
  <div class="my-10px mx-auto w-11/12">
    <router-view></router-view>
  </div>
</template>

<script lang="ts">
export default {
  routePath: "/",
  routeName: "home"
};
</script>

<script setup lang="ts">
import { ref } from "vue";
import { useRoute } from "vue-router";

import HeaderMenu from "./components/HeaderMenu.vue";
import { Menu } from "./models";

let avatarUrl = ref(
  "https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png"
);
let activeMenu = ref(1);
const menus: Array<Menu> = [
  { name: "流水", index: 1, route: "fund" },
  { name: "统计", index: 2, route: "statistic" }
];

const route = useRoute();
const menu = menus.find(item => item.route === route.name);
if (menu) {
  activeMenu.value = menu.index;
}
</script>
