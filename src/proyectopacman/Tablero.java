package proyectopacman;

public class Tablero {

    //Atributos
    int filas;
    int columnas;
    char[][] matriz;

     //Constructor
    public Tablero(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        matriz = new char[filas][columnas];
        inicializar();
    }
    
    // Metodos
    public void inicializar() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matriz[i][j] = '.';
            }
        }
    }
    public boolean esMovimientoValido(int fila, int columna) {
        return fila >= 0 && fila < filas && columna >= 0 && columna < columnas;
    }
    public void actualizar(Jugador j, EnemigoAcechador e1, EnemigoVelocista e2, Punto[] puntos) {

        inicializar();

        // puntos
        for (int i = 0; i < puntos.length; i++) {
            if (!puntos[i].fueRecolectado()) && esMovimientoValido(puntos[i].fila, puntos[i].columna)){
                matriz[puntos[i].fila][puntos[i].columna] = '*';
            }
        }

        // jugador 
        if (esMovimientoValido(j.fila, j.columna)) {
            matriz[j.fila][j.columna] = 'J';
        }

        // Enemigo  Acechador
        if (esMovimientoValido(e1.fila, e1.columna)) {
            matriz[e1.fila][e1.columna] = 'A';
        }
        // Enemigo Velocista
       if (esMovimientoValido(e2.fila, e2.columna)) {
            matriz[e2.fila][e2.columna] = 'V';
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
