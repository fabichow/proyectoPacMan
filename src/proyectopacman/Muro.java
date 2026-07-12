package proyectopacman;

public class Muro {

//Atributos
int fila;
int columna;

//Constructor
public Muro(int fila, int columna) {
    this.fila = fila;
    this.columna = columna;
}

// Metodos
public int getFila() {
    return fila;
}

public int getColumna() {
    return columna;
}

public boolean bloquea(int fila, int columna) {
    return this.fila == fila && this.columna == columna;
}

}
