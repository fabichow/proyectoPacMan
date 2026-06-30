package proyectopacman;

public class Tablero {

    int filas;
    int columnas;
    char[][] matriz;

    public Tablero(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        matriz = new char[filas][columnas];
        inicializar();
    }

    public void inicializar() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matriz[i][j] = '.';
            }
        }
    }

    public void actualizar(Jugador j, EnemigoAcechador e1, EnemigoVelocista e2, Punto[] puntos) {

        inicializar();

        // puntos
        for (int i = 0; i < puntos.length; i++) {
            if (!puntos[i].fueRecolectado()) {
                matriz[puntos[i].fila][puntos[i].columna] = '*';
            }
        }

        // jugador
        matriz[j.fila][j.columna] = 'J';

        // enemigos
        matriz[e1.fila][e1.columna] = 'A'; // Acechador
        matriz[e2.fila][e2.columna] = 'V'; // Velocista
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