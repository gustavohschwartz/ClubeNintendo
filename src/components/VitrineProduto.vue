<script setup>
defineProps(["jogo"]);

// Evento -> comprar
const emit = defineEmits(["comprar"]);
const comprar = (jogo) => {
  emit("comprar", jogo);
};
</script>

<template>
  <div class="card store-card">
    <img
      v-bind:src="jogo.imagem.url"
      class="card-img-top img-preview mt-3 mx-auto d-block"
      alt="..."
    />
    <div class="card-body">
      <p class="card-text">{{ jogo.titulo }}</p>
      <h4 class="card-title">
        R$ {{ jogo.preco.toFixed(2).replace(".", ",") }}
      </h4>
      <a
        href="#"
        id="btn-comprar"
        class="btn btn-sm btn-outline-primary store-btn-outline my-2"
        @click="comprar(jogo)"
        >Comprar</a
      >
    </div>
  </div>
</template>
