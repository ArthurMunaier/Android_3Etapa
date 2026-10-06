package com.receitafacil.app.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.receitafacil.app.model.Receita

class ReceitaViewModel : ViewModel() {

    var estaLogado by mutableStateOf(false)
        private set

    val receitas = mutableStateListOf<Receita>().apply { addAll(receitasIniciais()) }

    fun fazerLogin() { estaLogado = true }

    fun fazerLogout() { estaLogado = false }

    fun alternarFavorita(receitaId: Int) {
        val i = receitas.indexOfFirst { it.id == receitaId }
        if (i != -1) receitas[i] = receitas[i].copy(favorita = !receitas[i].favorita)
    }

    fun remover(receitaId: Int) {
        receitas.removeAll { it.id == receitaId }
    }

    fun restaurar() {
        receitas.clear()
        receitas.addAll(receitasIniciais())
    }

    fun buscarReceita(receitaId: Int): Receita? = receitas.find { it.id == receitaId }

    private companion object {
        fun receitasIniciais() = listOf(
            Receita(1, "Panqueca de banana", "🥞", "Café da manhã", 15,
                listOf("1 banana madura", "1 ovo", "4 colheres de aveia", "1 pitada de canela"),
                "Amasse a banana, misture o ovo e a aveia. Doure colheradas em frigideira antiaderente por 2 minutos de cada lado."),
            Receita(2, "Salada caprese", "🥗", "Entrada", 10,
                listOf("3 tomates", "200 g de muçarela de búfala", "Folhas de manjericão", "Azeite e sal"),
                "Fatie tomates e muçarela, alterne no prato com o manjericão e finalize com azeite e sal."),
            Receita(3, "Macarrão alho e óleo", "🍝", "Prato principal", 20,
                listOf("250 g de espaguete", "4 dentes de alho", "1/4 xícara de azeite", "Pimenta calabresa", "Salsinha"),
                "Cozinhe o macarrão. Doure o alho fatiado no azeite, junte a pimenta, misture a massa e a salsinha."),
            Receita(4, "Frango grelhado com limão", "🍗", "Prato principal", 30,
                listOf("2 filés de frango", "Suco de 1 limão", "2 dentes de alho", "Sal e pimenta"),
                "Tempere o frango com limão, alho, sal e pimenta por 15 minutos. Grelhe 6 minutos de cada lado."),
            Receita(5, "Sopa de abóbora", "🍲", "Sopa", 40,
                listOf("500 g de abóbora", "1 cebola", "1 litro de caldo de legumes", "Gengibre ralado"),
                "Refogue a cebola, junte a abóbora e o caldo. Cozinhe até amolecer e bata no liquidificador."),
            Receita(6, "Omelete de queijo", "🍳", "Café da manhã", 10,
                listOf("3 ovos", "50 g de queijo", "Sal", "1 colher de manteiga"),
                "Bata os ovos com sal, despeje na manteiga quente, adicione o queijo e dobre ao meio."),
            Receita(7, "Brigadeiro", "🍫", "Sobremesa", 25,
                listOf("1 lata de leite condensado", "3 colheres de chocolate em pó", "1 colher de manteiga"),
                "Cozinhe tudo mexendo sempre até desgrudar do fundo. Esfrie, enrole e passe no granulado."),
            Receita(8, "Bolo de cenoura", "🥕", "Sobremesa", 50,
                listOf("3 cenouras", "3 ovos", "1 xícara de óleo", "2 xícaras de açúcar", "2 xícaras de farinha", "1 colher de fermento"),
                "Bata cenoura, ovos e óleo no liquidificador. Misture aos secos e asse a 180 °C por 40 minutos."),
            Receita(9, "Tapioca recheada", "🌮", "Lanche", 10,
                listOf("4 colheres de goma de tapioca", "Queijo coalho", "Coco ralado (opcional)"),
                "Espalhe a goma numa frigideira quente até formar a massa. Recheie, dobre e sirva."),
            Receita(10, "Risoto de cogumelos", "🍄", "Prato principal", 45,
                listOf("1 xícara de arroz arbóreo", "200 g de cogumelos", "1 cebola", "Caldo de legumes quente", "Parmesão"),
                "Refogue cebola e cogumelos, junte o arroz e adicione o caldo aos poucos mexendo. Finalize com parmesão.")
        )
    }
}
