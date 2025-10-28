import entities.GeneroMusical;
import entities.Midia;
import entities.Musica;

ArrayList<Midia> listaDeReproducao = new ArrayList<> ();
ArrayList<Midia> biblioteca = new ArrayList<> ();

void main() {
    IO.println("Bem vindo ao seu player de mídia!");

    while(true){
        IO.println("""
                Digite a opção desejada:
                1 - Adicionar música
                2 - Adicionar podcast
                3 - Adicionar mídia a lista de reprodução
                4 - Play
                5 - Sair
                """);
        var opcao = Integer.parseInt(IO.readln());

        switch (opcao) {
            case 1 -> AdicionarMusica();
            case 2 -> IO.println("Ainda não implementada");
            case 3 -> AdicionarListaReproducao();
            case 4 -> Reproduzir();
            case 5 -> System.exit(0);
        }
    }
}

public void AdicionarMusica(){
    IO.println("Digite o título da música");
    var titulo = IO.readln();
    IO.println("Digite o artista da música");
    var artista = IO.readln();
    IO.println("""
        Escolha o gênero da música
            1 - ROCK,
            2 - POP,
            3 - FUNK,
            4 - SERTANEJO,
            5 - METAL,
            6 - RAP,
            7 - HIPHOP
            8 - OUTROS
    """);
    var genero = GeneroMusical.values()[Integer.parseInt(IO.readln())];

    IO.println("Digite o BPM da música");

    var bpm = Integer.parseInt(IO.readln());
    while (bpm <=0) {
        IO.println("Bpm da música precisa ser maior que zero, digite novamente!");
        bpm = Integer.parseInt(IO.readln());
    }

    //bpm = -1; // atribuir pelo sistema um valor inválido, isso vai passar o bloqueio da interface

    try { // fazer uma tentativa de cadastro, se houver algum erro, ele consegue ser capturado
        var musica = new Musica();
        musica.titulo = titulo;
        musica.artista = artista;
        musica.genero = genero; // Atributo publico - atribuição é direta
        musica.setBpm(bpm); // Atributo privado - atribuição precisa de um setter

        // isso aqui não vai ser executado se houver algum erro
        biblioteca.add(musica);
    }
    catch (Exception e){ // Se houver algum erro, ele vai ser capturado aqui
        e.printStackTrace();
        IO.println("Erro de cadastro de música, tente novamente!");
    }
}

public void AdicionarListaReproducao()
{
    IO.println("Digite o número da mídia a ser adicionada:");
    var opcao = Integer.parseInt(IO.readln());
    listaDeReproducao.add(biblioteca.get(opcao));
    IO.println("Mídia adicionada à lista de reprodução!");
}

public void Reproduzir(){
    for(var midia : listaDeReproducao)
        IO.println("Reproduzindo: " + midia.titulo);
    IO.println("Player pausado");
}