import { createApp } from "vue"

import "./style.css"
import App from "./App.vue"
import Principal from "./view/Principal.vue"
import DetalhesProduto from "./view/DetalhesProduto.vue"
import MeusPedidos from "./view/MeusPedidos.vue"

import { createRouter, createWebHashHistory } from "vue-router"

const routes = [
  { path: "/", component: Principal },
  { path: "/produto/:id", component: DetalhesProduto, props: true },
  { path: "/pedidos", component: MeusPedidos }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

createApp(App).use(router).mount("#app")
