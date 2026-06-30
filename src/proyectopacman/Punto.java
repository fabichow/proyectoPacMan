package proyectopacman;

public class Punto {

    int fila;
    int columna;
    int valor;
    boolean recolectado;

    public Punto(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
        this.valor = 10;
        this.recolectado = false;
    }

    public int obtenerValor() {
        if (!recolectado) {
            recolectado = true;
            return valor;
        }
        return 0;
    }

    public boolean fueRecolectado() {
        return recolectado;
    }
}