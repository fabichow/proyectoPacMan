package proyectopacman;

public class EnemigoVelocista {

    int fila;
    int columna;
    int daño;
    boolean activo;
    String tipo;

    int contadorTurnos; 

    public EnemigoVelocista() {
        fila = 6;
        columna = 6;
        daño = 1;
        activo = true;
        tipo = "velocista";
        contadorTurnos = 0; 
    }

    public void mover(Jugador j) {

        if (!activo) return;

        contadorTurnos++;

        if (contadorTurnos % 3 != 0) {
            return;
        }

        Tablero tablero = Tablero.tableroActual;

        for (int i = 0; i < 2; i++) {

            int nuevaFila = fila;
            int nuevaColumna = columna;

            if (fila < j.fila) { nuevaFila++; }
            if (fila > j.fila) { nuevaFila--; }
            if (columna < j.columna) { nuevaColumna++; }
            if (columna > j.columna) { nuevaColumna--; }

            if (tablero == null || tablero.esMovimientoValido(nuevaFila, columna)) {
                fila = nuevaFila;
            }

            if (tablero == null || tablero.esMovimientoValido(fila, nuevaColumna)) {
                columna = nuevaColumna;
            }
            if (verificarColision(j)) {
                break;
            }
        }
    }

    public boolean estaActivo() {
        return activo;
    }

    public boolean verificarColision(Jugador j) {
        if (fila == j.fila && columna == j.columna) {
            j.recibirDaño(daño);
            System.out.println("¡El velocista te alcanzó!");
            return true;
        }
        return false;
    }

    public void mostrarEstado() {
        System.out.println("========Velocista=========");
        System.out.println("Fila: " + fila);
        System.out.println("Columna: " + columna);
        System.out.println("Daño: " + daño);
    }
}
