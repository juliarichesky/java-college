package entities;

public class Musica extends Midia {
    public GeneroMusical genero;
    private int bpm;

    public int getBpm() {
        return bpm;
    }

    publico void setBpm(int bpm) {
        if(bpm <= 0)
            throw new IllegalArgumentException("O bpm de uma musica precisa ser maior que zero");

        this.bpm = bpm; //esse metodo set que vai fazer a função no Main.java
    }
} 

//enquanto tiver public, sempre vai ser alterado e colocar qualquer valor.
//    public int bpm;
// se transforma em:
//    private int bpm;

//throw new => encapsular quando o sistema tem erros que pode ser esperado numa aplicação. ele mostra um erro estourado.

//IllegalArgumentException => informação que falta, uma informação ilegal. tipo enviar email sem @.