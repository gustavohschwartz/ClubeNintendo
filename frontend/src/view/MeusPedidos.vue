<script setup>
import { ref } from "vue";
import axios from "axios";

import Navbar from "./../components/Navbar.vue";
import DadosCliente from "./../data/DadosCliente"

const pedidos = ref([]);
const dadosCliente = ref(DadosCliente);

const buscarPedidos = () => {
  axios.get("http://localhost:8090/pedidos").then((response) => {
    pedidos.value = response.data;
  });
};

buscarPedidos();
</script>

<template>
  <navbar :eh-vitrine="false" />

  <div class="container-fluid p-4">
    <div class="row">
      <div class="col-4 mb-4">
        <div class="card">
          <div class="card-body">
            <h5>Meus dados</h5>
            
            <div class="pt-2">
              <h6>Nome</h6>
              <p>{{ dadosCliente.nome }} {{ dadosCliente.sobrenome }}</p>
            </div>

            <div>
              <h6>Endereço</h6>
              <div>{{ dadosCliente.rua }}, {{ dadosCliente.numero }} - {{ dadosCliente.complemento }}</div>
              <div>{{ dadosCliente.bairro }} - {{ dadosCliente.cidade }} ({{ dadosCliente.estado }})</div>
            </div>
          </div>
        </div>
      </div>
      <div class="col">
        <div class="card">
          <div class="card-body">
            <h5>Pedidos</h5>

            <template v-if="pedidos.length == 0">
              <div class="p-5 text-center">Nenhum pedido realizado</div>
            </template>

            <table class="table" v-if="pedidos.length > 0">
              <tr>
                <th>Nº</th>
                <th>Destinatário</th>
                <th>Produto</th>
                <th>Qtd.</th>
                <th>Pagamento</th>
                <th>Valor</th>
              </tr>
              <tr v-for="pedido in pedidos">
                <td>#{{ pedido.id }}</td>
                <td>{{ pedido.nomeCompleto }}</td>
                <td>{{ pedido.produto.titulo }}</td>
                <td>{{ pedido.quantidade }}</td>
                <td>{{ pedido.formaPagamento }}</td>
                <td>R$ {{ pedido.valorTotal.toFixed(2).replace(".", ",") }}</td>
              </tr>
            </table>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
