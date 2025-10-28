import entities.GeneroMusical;
import entities.Midia;
import entities.Musica;

ArrayList<Midia> listaDeReproducao = new ArrayList<Midia> (); //estou usando o Midia dentro do segundo ArrayList, mas não é necessário.
ArrayList<Midia> biblioteca = new ArrayList<Midia> ();

void main() {
    IO.println("Bem vindo ao seu player de mídia!");

    while(true){
        IO.println("""
                Digite a opção desejada:
                1 - Adicionar música
                2 - Adicionar podcast
                3 - Adicionar mídia a lista de reprodução
                4 - Play
                5 - Exluir da biblioteca
                6 - Sair
                """);
        var opcao = Integer.parseInt(IO.readln());

        if(opcao == 1)
            AdicionarMusica();
        else if(opcao == 2)
            IO.println("Ainda não implementada.");
        else if(opcao == 3)
            AdicionarListaReproducao();
        else if(opcao == 4)
            Reproduzir();
        else if(opcao == 5)
            ExcluirMidia();
        else if(opcao == 6)
            System.exit(0);
    }

    //ou podemos usar o switch case:
            // switch (opcao) {
            // case 1 -> AdicionarMusica();
            // case 2 -> IO.println("Ainda não implementada");
            // case 3 -> AdicionarListaReproducao();
            // case 4 -> Reproduzir();
            // case 5 -> ExcluirMidia();
            // case 6 -> System.exit(0);
        // }
}

public void AdicionarMusica() {
    IO.println("Digite o título da música");
    var titulo = IO.readln();
    IO.println("Digite o artista da música");
    var artista = IO.readln();
    IO.println("""
        Escolha o gênero da música
            1- ROCK,
            2- POP,
            3- FUNK,
            4 - SERTANEJO,
            5 - METAL,
            6 - RAP,
            7 - HIPHOP,
            8 - OUTROS
    """);
    var genero = GeneroMusical.values()[Integer.parseInt(IO.readln())]; //aqui é tipo o DOM, vai pegando o caminho.

    IO.println("Digite o BPM da música");

    var bpm = Integer.parseInt(IO.readln());
    while (bpm <=0) {
        IO.println("Bpm da música precisa ser maior que 0, digite novamente!");
        bpm = Integer.parseInt(IO.readln());
    }
    
    //bpm = -1; //atribuir pelo sistema um valor inválido, isso vai passar o bloqueio da interface.

    try { //fazer uma tentativa de cadastro, se houver algum erro, ele consegue ser capturado.
        var musica = new Musica();
        musica.titulo = titulo;
        musica.artista = artista;
        musica.genero = genero;
        musica.setBpm(bpm); //campo privado, nao temos mais acesso direito. só pode fazer atribuição com setter, que foi o que alteramos em Musica.java

        // isso aqui nao vai ser executado se houver algum erro.
        biblioteca.add(musica);
    } catch (Exception e) { //vai pegar a excecao estourada e guardar num lugar. por isso usa o throw. se houver algum erro, ele vai ser capturado aqui. "e" é a variável.
        e.printStackTrace(); //mostra exatamente onde foi aquele erro.
        IO.println("Erro de cadastro de música, tente novamente!");
    }
}

public void AdicionarListaReproducao() {
    IO.println("Digite o número da mídia a ser adicionada:");
    var opcao = Integer.parseInt(IO.readln());
    listaDeReproducao.add(biblioteca.get(opcao));
    IO.println("Mídia adicionada à lista de reprodução!");
}

public void Reproduzir() {
    for(var midia : listaDeReproducao)
        IO.println("Reproduzindo: " + midia.titulo);
    IO.println("Player pausado");
}

public void ExcluirMidia() {
    if (biblioteca.isEmpty()) {
        IO.println("A biblioteca está vazia. Não há mídias para excluir.");
        return;
    }

    // listar as midias
    IO.println("Midias disponíveis na biblioteca para exclusão:");
    for (int i = 0; i < biblioteca.size(); i++) {
        var midia = biblioteca.get(i);
        IO.println((i + 1) + " - " + midia.titulo + " por " + midia.artista);
    }

    IO.println("Digite o número da mídia a ser EXCLUÍDA:");

    try {
        var opcao = Integer.parseInt(IO.readln());
        int indice = opcao - 1;

        if (indice >= 0 && indice < biblioteca.size()) {
            // remove da biblioteca
            var midiaRemovida = biblioteca.remove(indice);
            IO.println("Mídia " + midiaRemovida.titulo + " EXCLUÍDA da biblioteca!");

            // opcional: remover também da lista de reprodução
            listaDeReproducao.removeIf(m -> m.equals(midiaRemovida));
            // Note: O método 'equals' padrão de Midia (que herda de Object)
            // compara se são o *mesmo objeto*. Se você não sobrescreveu
            // 'equals', esta remoção só funcionará se o objeto Midia
            // na listaDeReproducao for a mesma instância de objeto.
            // Para maior robustez, você teria que garantir que
            // o objeto Midia na listaDeReproducao é o mesmo.
        } else {
            IO.println("Número de mídia inválido. Nenhuma mídia foi excluída.");
        }
    } catch (NumberFormatException e) {
        IO.println("Erro: A opção deve ser um número inteiro.");
    }
}

// try e cath: tentativa de cadastro
// enum: enumerador para definir. maneira padrao para fazer cadastro.