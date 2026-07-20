package proyectopacman;

public class Tablero {

    // Atributos
    int filas;
    int columnas;
    char[][] matriz;
    Muro[] muros;

    public static Tablero tableroActual;

    public Tablero(int filas, int columnas, Muro[] muros) {
        this.filas = filas;
        this.columnas = columnas;
        this.muros = (muros != null) ? muros : new Muro[0];

        matriz = new char[filas][columnas];

        inicializar();

        tableroActual = this;
    }

    public static Tablero crearConBordeAutomatico(int filasJugables, int columnasJugables, Muro[] murosInternos) {
        int filasTotales = filasJugables + 2;
        int columnasTotales = columnasJugables + 2;

        Muro[] borde = generarMurosBorde(filasTotales, columnasTotales);
        Muro[] todosLosMuros = combinarMuros(borde, murosInternos);

        return new Tablero(filasTotales, columnasTotales, todosLosMuros);
    }

    private static Muro[] generarMurosBorde(int filasTotales, int columnasTotales) {
        int cantidad = (filasTotales * 2) + ((columnasTotales - 2) * 2);
        Muro[] borde = new Muro[cantidad];
        int indice = 0;

        for (int j = 0; j < columnasTotales; j++) {
            borde[indice++] = new Muro(0, j);
            borde[indice++] = new Muro(filasTotales - 1, j);
        }
        for (int i = 1; i < filasTotales - 1; i++) {
            borde[indice++] = new Muro(i, 0);
            borde[indice++] = new Muro(i, columnasTotales - 1);
        }
        return borde;
    }

    private static Muro[] combinarMuros(Muro[] borde, Muro[] internos) {
        int cantidadInternos = (internos == null) ? 0 : internos.length;
        Muro[] combinado = new Muro[borde.length + cantidadInternos];
        System.arraycopy(borde, 0, combinado, 0, borde.length);
        if (internos != null) {
            System.arraycopy(internos, 0, combinado, borde.length, internos.length);
        }
        return combinado;
    }

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
            Punto[] puntos,
            Posicion posCuracion,
            Posicion posVelocidad,
            Posicion posCongelar) {

        inicializar();

        for (int i = 0; i < muros.length; i++) {
            matriz[muros[i].getFila()][muros[i].getColumna()] = '#';
        }

        for (int i = 0; i < puntos.length; i++) {
            if (!puntos[i].fueRecolectado()
                    && dentroDeLimites(puntos[i].fila, puntos[i].columna)) {
                matriz[puntos[i].fila][puntos[i].columna] = '*';
            }
        }
        if(dentroDeLimites(posCuracion.fila, posCuracion.columna)){
            matriz[posCuracion.fila][posCuracion.columna] = 'C';
        }

        if(dentroDeLimites(posVelocidad.fila, posVelocidad.columna)){
            matriz[posVelocidad.fila][posVelocidad.columna] = 'S';
        }

        if(dentroDeLimites(posCongelar.fila, posCongelar.columna)){
            matriz[posCongelar.fila][posCongelar.columna] = 'F';
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
