<script setup>
import { ref, defineProps } from 'vue';
import axios from "axios";

import Navbar from "./../components/Navbar.vue"

const produto = ref({});

const props = defineProps({
    id: String
})

const buscarProduto = () => {
  axios.get("http://localhost:8090/produtos/id?id=" + props.id).then((response) => {
    produto.value = response.data;
  });
};

buscarProduto();
</script>

<template>
  <navbar :eh-vitrine="false" />

  <div class="container-fluid p-4">
    <div class="card">
      <div class="card-body">
        <div class="d-flex">
          <div class="p-5">
            <img
              :src="produto.imagem.url"
              class="img-store"
              alt="..."
            />
          </div>
          <div class="flex-fill pt-5">
            <div class="produto p-3">
              <h4 class="lead">{{ produto.titulo }}</h4>
              <h4>R$ {{ produto.preco ? produto.preco.toFixed(2).replace(".", ",") : produto.preco }}</h4>
            </div>
            <div class="descricao p-3">
              <h4 class="lead">Descrição</h4>
              <p>
                {{ produto.descricao }}
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
