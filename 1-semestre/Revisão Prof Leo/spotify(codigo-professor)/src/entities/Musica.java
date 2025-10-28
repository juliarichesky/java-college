package entities;

public class Musica extends Midia {
    public GeneroMusical genero;
    private int bpm;

    public int getBpm() {
        return bpm;
    }

    public void setBpm(int bpm) {
        if(bpm <= 0)
            throw new IllegalArgumentException("O bpm de uma musica precisa ser maior que zero");

        this.bpm = bpm;
    }
}
