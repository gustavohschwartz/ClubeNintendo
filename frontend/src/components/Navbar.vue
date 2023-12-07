<script setup>
import { ref } from "vue"

const valorPesquisa = ref("")

defineProps(["ehVitrine"])

const emit = defineEmits(["pesquisarJogo"])
const pesquisarJogo = (valorPesquisa) => { emit("pesquisarJogo", valorPesquisa); }
</script>

<template>
  <nav class="navbar sticky-top store-bg-navbar">
    <!-- Venda Finalizada -->
    <div class="container-fluid px-4" v-if="!ehVitrine">
      <a class="navbar-brand mx-auto" href="#">
        <img src="./../assets/logo.svg" alt="Clube Nintendo" />
      </a>
    </div>

    <!-- Vitrine de produtos -->
    <div class="container-fluid px-4" v-if="ehVitrine">
      <a class="navbar-brand" href="#">
        <img src="./../assets/logo.svg" alt="Clube Nintendo" />
      </a>

      <form class="d-flex mx-5 store-search" role="search">
        <div class="input-group">
          <input
            type="text"
            v-model="valorPesquisa"
            class="form-control border-white"
            placeholder="Pesquise pelo nome do jogo"
            aria-label="Pesquise pelo nome do jogo"
            aria-describedby="button-addon2"
            @keyup.enter="pesquisarJogo(valorPesquisa)"
          />
          <button
            class="btn btn-light text-body-secondary"
            type="button"
            @click="pesquisarJogo(valorPesquisa)"
          >
            Pesquisar
          </button>
        </div>
      </form>

      <router-link
        to="/pedidos"
        class="btn btn-outline-light"
      >
        Meus pedidos
      </router-link>
    </div>
  </nav>
</template>
