package proyectopacman;

public class EnemigoVelocista {//Este enemigo avanza cada 3 turnos 2 casillas y los demas enmigos lo puden traspasar .Ronald Gutierrez

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

        if (!activo) {
            return;
        }

        contadorTurnos++;
        
        if (contadorTurnos % 3 != 0) {
            return;
        }

        Tablero tablero = Tablero.tableroActual;
        boolean yaAtaco = false;

        for (int i = 0; i < 2 && !yaAtaco; i++) {

            if (fila < j.fila && tablero.esMovimientoValido(fila + 1, columna)) {
                fila++;
            } else if (fila > j.fila && tablero.esMovimientoValido(fila - 1, columna)) {
                fila--;
            }

            if (columna < j.columna && tablero.esMovimientoValido(fila, columna + 1)) {
                columna++;
            } else if (columna > j.columna && tablero.esMovimientoValido(fila, columna - 1)) {
                columna--;
            }

            if (fila == j.fila && columna == j.columna) {
                j.recibirDaño(1); 
                System.out.println("¡El velocista te alcanzó!");
                yaAtaco = true;
            }
        }
    }

    public boolean estaActivo() {
        return activo;
    }

    public boolean verificarColision(Jugador j) {
        if (fila == j.fila && columna == j.columna) {
            j.recibirDaño(1); 
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
