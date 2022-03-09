<template>
  <div class="login-page flex justify-end items-center">
    <div class="my-10px mr-p10 flex flex-col items-center">
      <h1 class="text-4xl text-white">登 录</h1>
      <div
        class="mt-50px p-50px bg-white bg-opacity-20 w-450px rounded-3xl flex flex-col items-center"
      >
        <el-form
          ref="loginFormRef"
          :model="loginForm"
          :rules="rules"
          class="w-full mt-10px"
        >
          <el-form-item prop="username">
            <el-input
              v-model="loginForm.username"
              placeholder="请输入用户名"
              size="large"
              :prefix-icon="User"
            ></el-input>
          </el-form-item>
          <el-form-item prop="password">
            <el-input
              v-model="loginForm.password"
              placeholder="请输入密码"
              class="mt-30px"
              type="password"
              size="large"
              :prefix-icon="Lock"
            ></el-input>
          </el-form-item>
        </el-form>
        <div class="flex justify-between w-full text-white">
          <span class="cursor-pointer hover:underline">注册</span>
          <span class="cursor-pointer hover:underline">忘记密码?</span>
        </div>
        <div class="mt-50px flex justify-center">
          <el-button class="bg-white" size="large" @click="auth"
            >立 即 登 录</el-button
          >
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
export default {
  routePath: "/login",
  routeName: "login"
};
</script>

<script setup lang="ts">
import { reactive, ref } from "vue";
import { useRouter } from "vue-router";
import { User, Lock } from "@element-plus/icons-vue";
import type { ElForm } from "element-plus";
import { login } from "@/api/auth";

type FormInstance = InstanceType<typeof ElForm>;

const loginForm = reactive({ username: "", password: "" });
const loginFormRef = ref<FormInstance>();
const rules = reactive({
  username: [{ required: true, message: "请输入用户名", trigger: "blur" }],
  password: [{ required: true, message: "请输入密码", trigger: "blur" }]
});
const router = useRouter();

const auth = () => {
  const { username, password } = loginForm;
  if (!loginFormRef.value) return;
  loginFormRef.value.validate(async valid => {
    if (!valid) return;
    let res = await login({ username, password });
    if (!res) return;
    router.push({
      name: "home"
    });
  });
};
</script>

<style lang="less" scoped>
.login-page {
  width: 100vw;
  min-height: 100vh;
  min-width: 1000px;
  background: center no-repeat url("@/assets/images/login-bg.jpg");
  background-size: 100% 100%;

  ::v-deep(.el-input) {
    .el-input__prefix {
      color: white;
    }
    .el-input__inner {
      background-color: transparent;
      color: white;
      box-shadow: none;
      border-bottom: 1px white solid;
      border-radius: 0;

      &::placeholder {
        color: #d1d0d0;
      }
    }
  }
}
</style>
