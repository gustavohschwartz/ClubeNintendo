<script setup>
import { ref, computed } from "vue"
import axios from "axios"

import Navbar from "../components/Navbar.vue"
import Vitrine from "../components/Vitrine.vue"
import Carrinho from "./../components/Carrinho.vue"
import CadastroPagamento from "./../components/CadastroPagamento.vue"
import PedidoFinalizado from "./../components/PedidoFinalizado.vue"
import DadosCliente from "./../data/DadosCliente"

const ehVitrine = ref(true) // Inicializa modo de visualização na vitrine
const jogosVitrine = ref([]) // Inicializa array de jogos da vitrine
const jogosCarrinho = ref([]) // Inicializa array de jogos do carrinho
const jogoPesquisado = ref('') // Inicializa const auxiliar da pesquisa 
const dadosCliente = ref(DadosCliente) // Inicializa dados do cliente
const modalPedidoFinalizado = ref(null)

const jogosFiltrados = computed(() => {
  if (jogoPesquisado.value === '')
    return jogosVitrine.value

    return encontrarJogoPeloNome(jogoPesquisado.value, jogosVitrine.value)
})

// Atribui texto da pesquisa para variável jogoPesquisado
const pesquisarJogo = (valorPesquisa) => {
  jogoPesquisado.value = valorPesquisa
}

// Filtra jogos pelo nome em um array
const encontrarJogoPeloNome = (nomeJogo, array) => {
  return array.filter((jogo) => {
    return jogo.titulo.toLowerCase().includes(nomeJogo.toLowerCase())
  })
}

const adicionarAoCarrinho = (jogo) => {
  let indiceJogoNoCarrinho = jogosCarrinho.value.indexOf(jogo) // Verifica se jogo já está no carrinho
  
  if(indiceJogoNoCarrinho < 0) {
    jogo.qtd = 1
    jogosCarrinho.value.push(jogo) // Adiciona no carrinho
  } else {
    jogosCarrinho.value[indiceJogoNoCarrinho].qtd += 1 // Incrementa qtd no carrinho
  }
}

const removerDoCarrinho = (jogo) => {
  let indiceJogoNoCarrinho = jogosCarrinho.value.indexOf(jogo)
  
  jogosCarrinho.value[indiceJogoNoCarrinho].qtd = 0 // Zera qtd
  jogosCarrinho.value.splice(indiceJogoNoCarrinho, 1) // Remove do array
}

const calcularTotalCarrinho = () => {
  let total = 0
  
  for (let i = 0; i < jogosCarrinho.value.length; i++)
    total = total + (jogosCarrinho.value[i].qtd * jogosCarrinho.value[i].preco)
  
  return total.toFixed(2).replace(".", ",")
}

const limparCarrinho = () => {
  ehVitrine.value = true
  jogosCarrinho.value = []
}

const irParaPagamento = () => {
  // Muda visualização para formulário de pagamento
  ehVitrine.value = false 
}

const cancelarCompra = () => {
  // Muda visualização para vitrine de produtos
  ehVitrine.value = true
}

const finalizarCompra = () => {
  let dados
  let nomeCliente = dadosCliente.value.nome + " " + dadosCliente.value.sobrenome
  
  for(let i = 0; i < jogosCarrinho.value.length; i++) {
    dados = 
      "idProduto=" + jogosCarrinho.value[i].id +
      "&quantidade=" + jogosCarrinho.value[i].qtd +
      "&nomeCompleto=" + nomeCliente +
      "&formaPagamento=" + dadosCliente.value.formaPagamento

    axios.post("http://localhost:8090/pedidos?" + dados)
      .then(() => {
        modalPedidoFinalizado.value.abrirModal()
        limparCarrinho()
      })
  }
}

const buscarJogosVitrine = () => {
  axios.get("http://localhost:8090/produtos")
    .then((response) => {
      jogosVitrine.value = response.data
    })
}

buscarJogosVitrine();

</script>

<template>
  <navbar @pesquisarJogo="pesquisarJogo" :eh-vitrine="ehVitrine" />

  <div class="container-fluid d-flex p-4">
    <pedido-finalizado
      ref="modalPedidoFinalizado"
    />

    <vitrine 
      v-if="ehVitrine"
      :jogos-vitrine="jogosFiltrados"
      @adicionarAoCarrinho="adicionarAoCarrinho"
    />

    <cadastro-pagamento
      v-if="!ehVitrine"
      v-model:dadosCliente="dadosCliente"
    />
    
    <carrinho 
      :jogos-carrinho="jogosCarrinho"
      :total-carrinho="calcularTotalCarrinho()"
      :eh-vitrine="ehVitrine"
      @removerDoCarrinho="removerDoCarrinho"
      @irParaPagamento="irParaPagamento"
      @cancelarCompra="cancelarCompra"
      @finalizarCompra="finalizarCompra"
    />
  </div>
</template>
