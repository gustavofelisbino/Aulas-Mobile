fun main() {
    val materia = listOf("Matematica", "Ingles", "Portugues", "Quimica", "Biologia")

    val animais = listOf("Cachorro", "Gato", "Pássaro", "Peixe", "Hamster")

    val goat = listOf("Messi")

    val listaDeRegras = listOf(
        Requisito("Mínimo de 5 caracteres") { it.length >= 5 },

        Requisito("Deve conter um número entre 1 e 10") { it.any { numero -> numero.isDigit() && numero.digitToInt() >= 1 && numero.digitToInt() <= 10 } },

        Requisito("Deve conter uma letra maiúscula") { it.any { letra -> letra.isUpperCase() } },

        Requisito("Deve conter uma matéria escolar") { materia.any { materia -> it.contains(materia) } },

        Requisito("Deve conter um caracter especial") { it.any { caracter -> !caracter.isDigit() && !caracter.isLetter() && !caracter.isWhitespace() } },

        Requisito("Deve conter uma letra minúscula") { it.any { letra -> letra.isLowerCase() } },

        Requisito("Não deve conter espaços") { it.none { caracter -> caracter.isWhitespace() } },

        Requisito("Deve começar com uma letra maiúscula") { it.first().isUpperCase() },

        Requisito("Deve terminar com um número") { it.last().isDigit() },

        Requisito("Deve conter 20 caracteres ou menos") { it.length <= 25 },

        Requisito("Deve conter um animal de estimação") { animais.any { animal -> it.contains(animal) } },

        Requisito("Deve conter o melhor jogador de futebol da história") { goat.any { goat -> it.contains(goat) } }
    )

    var senhaAprovada = false
    var tentativas = 0

    do {
        println("\nDigite sua senha:")
        val entrada = readLine() ?: ""
        tentativas++

        var erroEncontrado: String? = null

        for (regra in listaDeRegras) {
            with(regra) {
                if (!validacao(entrada)) {
                    erroEncontrado = mensagemErro
                }
            }
            if (erroEncontrado != null) break
        }
        if (erroEncontrado != null) {
            println("❌ERRO: $erroEncontrado")
            println("Tentativas: $tentativas")

        } else {
            println("✅SUCESSO! Senha aceite pelo Overlord.")
            println("Você conseguiu em $tentativas tentativa(s)!")
            senhaAprovada = true
        }
    } while (!senhaAprovada)
}

data class Requisito(
    val mensagemErro: String,
    val validacao: (String) -> Boolean
)