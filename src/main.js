import { createApp } from "vue"

import "./style.css"
import App from "./App.vue"
import Principal from "./view/Principal.vue"
import VendaFinalizada from "./view/VendaFinalizada.vue"

import { createRouter, createWebHashHistory } from "vue-router"

const routes = [
  { path: "/", component: Principal },
  { path: "/VendaFinalizada", component: VendaFinalizada }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

createApp(App).use(router).mount("#app")
