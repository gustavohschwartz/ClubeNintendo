<script setup>
defineProps(["jogo"])

// Evento -> removerProduto
const emit = defineEmits(["removerProduto"])
const removerProduto = (jogo) => { emit("removerProduto", jogo) }
</script>

<template>
  <div class="d-flex gap-3 my-4">
    <img v-bind:src="jogo.imagem.url" class="img-thumbnail img-carrinho" alt="..." />

    <div class="flex-fill">
      {{ jogo.titulo }}
      <div class="text-body-secondary" style="font-size: 12px">
        Qtd. {{ jogo.qtd }}
      </div>
      <div class="text-body-secondary" style="font-size: 12px">
        Valor Un. R$ {{ jogo.preco.toFixed(2).replace(".", ",") }}
      </div>
    </div>

    <span
      class="material-icons text-body-secondary"
      @click="removerProduto(jogo)"
      style="cursor: pointer"
    >
      delete
    </span>
  </div>
</template>
