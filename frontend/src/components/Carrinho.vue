<script setup>
import Produto from "./CarrinhoProduto.vue"

defineProps([
  "jogosCarrinho",
  "totalCarrinho",
  "ehVitrine"
])

const emit = defineEmits([
  "removerDoCarrinho",
  "irParaPagamento",
  "finalizarCompra",
  "cancelarCompra"
])

const removerProduto = (jogo) => { emit("removerDoCarrinho", jogo) }
const irParaPagamento = () => { emit("irParaPagamento") }
const finalizarCompra = () => { emit("finalizarCompra") };
const cancelarCompra = () => { emit("cancelarCompra") };
</script>

<template>
  <div class="carrinho ms-4">
    <div class="card">
      <div class="card-body">
        <h5>Meu carrinho</h5>

        <!-- Carrinho vazio -->
        <template v-if="jogosCarrinho.length == 0">
          <div class="p-5 text-center">O carrinho está vazio</div>
        </template>

        <!-- Carrinho com produtos -->
        <template v-else>
          <produto
            v-for="jogo in jogosCarrinho"
            :key="jogo.nome"
            :jogo="jogo"
            @removerProduto="removerProduto"
          />

          <div class="d-flex gap-2">
            <h5 class="flex-fill">Total</h5>
            <h5>R$ {{ totalCarrinho }}</h5>
          </div>

          <div class="d-grid gap-2 mt-2">
            <!-- Botões da vitrine -->
            <button
              v-if="ehVitrine"
              @click="irParaPagamento"
              class="btn btn-primary store-btn-primary"
            >
              Ir para pagamento
            </button>

            <!-- Botões do pagamento -->
            <button
              v-if="!ehVitrine"
              @click="finalizarCompra"
              class="btn btn-primary store-btn-primary"
            >
              Finalizar compra
            </button>
            <button
              v-if="!ehVitrine"
              @click="cancelarCompra"
              class="btn btn-outline-primary store-btn-outline"
            >
              Cancelar
            </button>
          </div>
        </template>
      </div>
    </div>
  </div>
</template>
