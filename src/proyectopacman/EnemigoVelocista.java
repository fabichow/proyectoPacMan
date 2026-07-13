package proyectopacman;

public class EnemigoVelocista { //Enemigo que avanza dos casillas por turno que realiza el jugador. Ronald Gutierrez     
    
    int fila;
    int columna;
    int daño;
    boolean activo;
    String tipo;

    public EnemigoVelocista() {
        fila = 6;
        columna = 6;
        daño = 1;
        activo = true;
        tipo = "velocista";
    }

    public void mover(Jugador j) {
        for (int i = 0; i < 2; i++) {
            if (fila < j.fila) { fila++; }
            if (fila > j.fila) { fila--; }
            if (columna < j.columna) { columna++; }
            if (columna > j.columna) { columna--; }
            verificarColision(j);
        }
    } 
    public boolean estaActivo(){
        return activo;
    }
    
    public void verificarColision(Jugador j) {
        if (fila == j.fila && columna == j.columna) {
            j.recibirDaño(daño);
            System.out.println("¡El velocista te alcanzó!");
        }
    }

    public void mostrarEstado() {
        System.out.println("========Velocista=========");
        System.out.println("Fila: " + fila);
        System.out.println("Columna: " + columna);
        System.out.println("Daño: " + daño);
    }

    public boolean estaActivo() {   //     ControlEnemigos lo llama
        return activo;              //     comentado, pero si lo activas, lo necesitas
    }
}
