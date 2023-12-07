<script setup>

const exampleModal = document.getElementById('store-modal')
if (exampleModal) {
  exampleModal.addEventListener('show.bs.modal', event => {
    // Button that triggered the modal
    const button = event.relatedTarget
    // Extract info from data-bs-* attributes
    const recipient = button.getAttribute('data-bs-whatever')
    // If necessary, you could initiate an Ajax request here
    // and then do the updating in a callback.

    // Update the modal's content.
    const modalTitle = exampleModal.querySelector('.modal-title')
    const modalBodyInput = exampleModal.querySelector('.modal-body input')

    modalTitle.textContent = `New message to ${recipient}`
    modalBodyInput.value = recipient
  })
}

</script>

<template>
  <div id="store-modal" class="modal" tabindex="-1">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-body p-4 text-center">
          <img src="src/assets/check.svg" alt="..." class="mb-3"/>
          <h5>Pedido recebido com sucesso</h5>
          <p>
            Esperamos que você aproveite ao máximo sua experiência com estes
            jogos!
          </p>
          <router-link to="/" class="btn btn-primary store-btn-primary mb-2">
            Continuar comprando
          </router-link>
        </div>
      </div>
    </div>
  </div>
</template>
