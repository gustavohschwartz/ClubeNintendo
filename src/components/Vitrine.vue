<script setup>
import Produto from "./VitrineProduto.vue";

defineProps(["jogosVitrine"])

// Evento -> comprar
const emit = defineEmits(["adicionarAoCarrinho"])
const comprar = (jogo) => { emit("adicionarAoCarrinho", jogo) }
</script>

<template>
  <div class="produtos flex-grow-1">
    <div class="row row-cols-md-1 row-cols-lg-2 row-cols-xl-4">
      <!-- Vitrine vazia -->
      <template v-if="jogosVitrine.length == 0">
        <div class="col flex-fill">
          <div class="card">
            <div class="card-body">Nenhum produto encontrado. Tente outra pesquisa.</div>
          </div>
        </div>
      </template>

      <!-- Vitrine com produtos -->
      <div class="col" v-for="jogo in jogosVitrine" :key="jogo.nome">
        <produto :jogo="jogo" @comprar="comprar" />
      </div>
    </div>
  </div>
</template>
