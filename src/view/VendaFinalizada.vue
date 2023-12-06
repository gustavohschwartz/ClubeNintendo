<script setup>
import Navbar from "./../components/Navbar.vue"
</script>

<template>
  <navbar @buscarJogo="buscar()" :eh-vitrine="false" />

  <div class="container-fluid p-4">
    <div class="card">
      <div class="card-body">
        <h5>Pedido recebido com sucesso</h5>
        <p>
          Esperamos que você aproveite ao máximo sua experiência com estes
          jogos!
        </p>
        <router-link to="/" class="btn btn-primary store-btn-primary">
          Continuar comprando
        </router-link>
      </div>
    </div>
  </div>
</template>
