package proyectopacman;

public class Tablero {

    // Atributos
    int filas;
    int columnas;
    char[][] matriz;
    Muro[] muros;

    // Constructor
    public Tablero(int filas, int columnas, Muro[] muros) {
        this.filas = filas;
        this.columnas = columnas;
        this.muros = muros;

        matriz = new char[filas][columnas];

        inicializar();
    }

    // Métodos

    public void inicializar() {

        for (int i = 0; i < filas; i++) {

            for (int j = 0; j < columnas; j++) {

                matriz[i][j] = '.';

            }

        }

    }

    public boolean dentroDeLimites(int fila, int columna) {

        return fila >= 0 &&
               fila < filas &&
               columna >= 0 &&
               columna < columnas;

    }

    public boolean hayMuro(int fila, int columna) {

        for (int i = 0; i < muros.length; i++) {

            if (muros[i].bloquea(fila, columna)) {

                return true;

            }

        }

        return false;

    }

    public boolean esMovimientoValido(int fila, int columna) {

        return dentroDeLimites(fila, columna)
                && !hayMuro(fila, columna);

    }

    public void actualizar(
            Jugador j,
            EnemigoAcechador e1,
            EnemigoVelocista e2,
            EnemigoTanque e3,
            Punto[] puntos) {

        inicializar();

        // Muros
        for (int i = 0; i < muros.length; i++) {

            matriz[
                muros[i].getFila()
            ][
                muros[i].getColumna()
            ] = '#';

        }


        for (int i = 0; i < puntos.length; i++) {

            if (!puntos[i].fueRecolectado()
                    && dentroDeLimites(
                            puntos[i].fila,
                            puntos[i].columna)) {

                matriz[
                    puntos[i].fila
                ][
                    puntos[i].columna
                ] = '*';

            }

        }


        if (dentroDeLimites(j.fila, j.columna)) {

            matriz[j.fila][j.columna] = 'J';

        }

       
        if (dentroDeLimites(e1.fila, e1.columna)) {

            matriz[e1.fila][e1.columna] = 'A';

        }

        
        if (dentroDeLimites(e2.fila, e2.columna)) {

            matriz[e2.fila][e2.columna] = 'V';

        }


        if (dentroDeLimites(e3.fila, e3.columna)) {

            matriz[e3.fila][e3.columna] = 'T';

        }

    }

    public void mostrar() {

        System.out.println("====== TABLERO ======");

        for (int i = 0; i < filas; i++) {

            for (int j = 0; j < columnas; j++) {

                System.out.print(matriz[i][j] + " ");

            }

            System.out.println();

        }

    }

}
