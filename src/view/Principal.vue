<script setup>
import { ref, computed } from "vue"
import axios from "axios"

import Navbar from "../components/Navbar.vue"
import Vitrine from "../components/Vitrine.vue"
import Carrinho from "./../components/Carrinho.vue"
import CadastroPagamento from "./../components/CadastroPagamento.vue"

const ehVitrine = ref(true) // Inicializa modo de visualização
const jogosVitrine = ref([]) // Inicializa vitrine
const jogosCarrinho = ref([]) // Inicializa carrinho
const jogoPesquisado = ref('') // Inicializa pesquisa 
const jogosFiltrados = computed(() => {
  if (jogoPesquisado.value === '')
    return jogosVitrine.value

    return encontrarJogoPeloNome(jogoPesquisado.value, jogosVitrine.value)
})
const dadosCliente = ref({
  nome: 'Gustavo',
  sobrenome: 'Schwartz',
  cep: '89035-212',
  rua: 'Rua General Arthur Koehler, 343',
  numero: '343',
  complemento: 'Ap 701',
  bairro: 'Vila Nova',
  cidade: 'Blumenau',
  estado: 'Santa Catarina',
  pagamento: 'boleto'
})

// Atribui texto da pesquisa para variável jogoPesquisado
const pesquisarJogo = (valorPesquisa) => {
  jogoPesquisado.value = valorPesquisa
}

// Filtra jogos pelo nome em um array
const encontrarJogoPeloNome = (nomeJogo, array) => {
  return array.filter((jogo) =>
    jogo.nome.toLowerCase().includes(nomeJogo.toLowerCase())
  )
}

const adicionarAoCarrinho = (jogo) => {
  let indiceJogoNoCarrinho = jogosCarrinho.value.indexOf(jogo)
  
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
  jogosCarrinho.value = [];
}

const irParaPagamento = () => {
  ehVitrine.value = false
}

const cancelarCompra = () => {
  ehVitrine.value = true
}

const finalizarCompra = () => {
  for(let i = 0; i < jogosCarrinho.value.length; i++) {
    
    axios.defaults.headers.post['Access-Control-Allow-Origin'] = '*';
    axios.post("http://localhost:8090/pedidos?idProduto=" + jogosCarrinho.value[i].id + "&quantidade=" + jogosCarrinho.value[i].qtd, {
        headers: {
          "Cache-Control": "no-cache",
          "Content-Type": "application/x-www-form-urlencoded",
          "Access-Control-Allow-Origin": "*",
        },
      })
      .then(() => {
        ehVitrine = true
        limparCarrinho()
        alert('Pedido finalizado com sucesso')
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
