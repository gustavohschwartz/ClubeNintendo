<script setup>
import { ref } from "vue";

defineProps(["dadosCliente"]);
</script>

<template>
  <div class="cadastro flex-grow-1">
    <div class="card">
      <div class="card-body">
        <h5>Destinatário</h5>
        <div class="row">
          <div class="col">
            <div class="form-group">
              <label for="inputNome">Nome</label>
              <input
                type="text"
                class="form-control"
                id="inputNome"
                v-model="dadosCliente.nome"
                @input="$emit('update:dadosCliente.nome', $event.target.value)"
              />
            </div>
          </div>
          <div class="col">
            <div class="form-group">
              <label for="inputSobrenome">Sobrenome</label>
              <input
                type="text"
                class="form-control"
                id="inputSobrenome"
                v-model="dadosCliente.sobrenome"
                @input="$emit('update:dadosCliente.sobrenome', $event.target.value)"
              />
            </div>
          </div>
        </div>
      </div>
    </div>

    <div class="card my-4">
      <div class="card-body">
        <h5>Endereço</h5>
        <div class="row">
          <div class="col">
            <div class="form-group">
              <label for="inputCep">CEP</label>
              <input
                type="text"
                class="form-control"
                id="inputCep"
                v-model="dadosCliente.cep"
                @input="$emit('update:dadosCliente.cep', $event.target.value)"
              />
            </div>
          </div>
          <div class="col">
            <div class="form-group">
              <label for="inputRua">Rua</label>
              <input
                type="text"
                class="form-control"
                id="inputRua"
                v-model="dadosCliente.rua"
                @input="$emit('update:dadosCliente.rua', $event.target.value)"
              />
            </div>
          </div>
        </div>

        <div class="row">
          <div class="col">
            <div class="form-group">
              <label for="inputNumero">Número</label>
              <input
                type="text"
                class="form-control"
                id="inputNumero"
                v-model="dadosCliente.numero"
                @input="$emit('update:dadosCliente.numero', $event.target.value)"
              />
            </div>
          </div>
          <div class="col">
            <div class="form-group">
              <label for="inputComplemento">Complemento</label>
              <input
                type="text"
                class="form-control"
                id="inputComplemento"
                v-model="dadosCliente.complemento"
                @input="$emit('update:dadosCliente.rua', $event.target.value)"
              />
            </div>
          </div>
          <div class="col-6">
            <div class="form-group">
              <label for="inputBairro">Bairro</label>
              <input
                type="text"
                class="form-control"
                id="inputBairro"
                v-model="dadosCliente.bairro"
                @input="$emit('update:dadosCliente.bairro', $event.target.value)"
              />
            </div>
          </div>
        </div>

        <div class="row">
          <div class="col">
            <div class="form-group">
              <label for="inputEstado">Estado</label>
              <input
                type="text"
                class="form-control"
                id="inputEstado"
                v-model="dadosCliente.estado"
                @input="$emit('update:dadosCliente.estado', $event.target.value)"
              />
            </div>
          </div>

          <div class="col">
            <div class="form-group">
              <label for="inputCidade">Cidade</label>
              <input
                type="text"
                class="form-control"
                id="inputCidade"
                v-model="dadosCliente.cidade"
                @input="$emit('update:dadosCliente.bairro', $event.target.value)"
              />
            </div>
          </div>
        </div>
      </div>
    </div>

    <div class="card">
      <div class="card-body">
        <h5>Pagamento</h5>
        <div class="form-check">
          <input
            class="form-check-input"
            type="radio"
            name="radioPagamento"
            id="radioPIX"
            value="PIX"
            v-model="dadosCliente.formaPagamento"
            @input="$emit('update:dadosCliente.formaPagamento', $event.target.value)"
            checked
          />
          <label class="form-check-label" for="radioPIX"> PIX </label>
        </div>
        <div class="form-check">
          <input
            class="form-check-input"
            type="radio"
            name="radioPagamento"
            id="radioBoleto"
            value="Boleto"
            v-model="dadosCliente.formaPagamento"
            @input="$emit('update:dadosCliente.formaPagamento', $event.target.value)"
          />
          <label class="form-check-label" for="radioBoleto"> Boleto </label>
        </div>
      </div>
    </div>
  </div>
</template>
