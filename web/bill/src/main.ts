import { createApp } from 'vue'

import router from './router'

import App from './App.vue'

import './assets/style/tailwind.css'
import "./assets/style/reset.less";
import "./assets/style/common.less";

const app = createApp(App)

app.use(router)

app.mount('#app')
