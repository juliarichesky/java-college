package entities;

public class Jogo extends Conteudo {
    public String plataforma;
    public String plataforma; // Campos com apenas atribuição eles são publicos
    private double mediaAvaliacoes = calcularAvaliacoes(); // Campos calculados são privados

    public double calcularAvaliacoes(){
        if(avaliacoes.isEmpty()) return 0;
        int soma = 0;
        for(var avaliacao : avaliacoes)
            soma += avaliacao.nota;

        return (double) soma / avaliacoes.size();
    }

    public void recalcularMediaAvaliacoes(){
        mediaAvaliacoes = calcularAvaliacoes();
    }

    public double getMediaAvaliacoes(){
        return mediaAvaliacoes;
    }

    @Override
    public String toString() {
        return "entities.Jogo{" +
                "nome='" + nome + '\'' +
                ", plataforma='" + plataforma + '\'' +
        return "Jogo{" +
                "nota=" + mediaAvaliacoes +
                ", dataLancamento=" + dataLancamento +
                ", nome='" + nome + '\'' +
                ", plataforma='" + plataforma + '\'' +
                '}';
    }
}