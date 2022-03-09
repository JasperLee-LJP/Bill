<template>
  <div>
    <span
      class="px-20px cursor-pointer text-lg"
      :class="[menu.index === currentIndex ? 'text-red-500' : '']"
      v-for="menu in menus"
      :key="menu.index"
      @click="onClick(menu)"
    >
      {{ menu.name }}
    </span>
  </div>
</template>

<script setup lang="ts">
import { withDefaults, ref, watchEffect, defineProps, defineEmits } from "vue";
import { useRouter } from "vue-router";

import { Menu } from "../models";

export interface Props {
  menus?: Menu[];
  activeIndex?: number;
}

const props = withDefaults(defineProps<Props>(), {
  menus: () => [],
  activeIndex: 0
});
let currentIndex = ref(0);
watchEffect(() => {
  currentIndex.value = props.activeIndex;
});
const emit = defineEmits<{ (e: "change", menu: Menu): void }>();

const router = useRouter();
const onClick = (menu: Menu) => {
  currentIndex.value = menu.index;
  if (menu.route) {
    router.push({ name: menu.route });
  }
  emit("change", menu);
};
</script>
