<script setup>
import { onMounted } from "vue"

let modalPedidoFinalizado = null

onMounted(() => {
  modalPedidoFinalizado = new bootstrap.Modal('#store-modal')
})

const abrirModal = () => {
  modalPedidoFinalizado.show()
}

const fecharModal = () => {
  modalPedidoFinalizado.hide()
}

defineExpose({
  abrirModal 
})
</script>

<template>
  <div id="store-modal" class="modal" tabindex="-1">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-body p-4 text-center">
          <img src="src/assets/check.svg" alt="..." class="mb-3"/>
          <h5>Pedido recebido com sucesso</h5>
          <p>
            Esperamos que você aproveite muito estes jogos!
          </p>
          <button @click="fecharModal" class="btn btn-primary store-btn-primary mb-2">
            Continuar comprando
          </button>
        </div>
      </div>
    </div>
  </div>
</template>
