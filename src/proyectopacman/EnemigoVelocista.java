package proyectopacman;

public class EnemigoVelocista { //Enemigo que avanza dos casillas de es por turno que realiza el jugador. Ronald Gutierrez     

    //Atributos
    int fila;
    int columna;
    int daño;
    boolean activo;

    public EnemigoVelocista() {

        //Constructor
        fila = 6;
        columna = 6;
        daño = 1;
        activo = true;
    }

    public void mover(Jugador j) {

        // se mueve 2 veces por turno (su habilidad)
        for (int i = 0; i < 2; i++) {

            if (fila < j.fila) {
                fila++;
            } else if (fila > j.fila) {
                fila--;
            }

            if (columna < j.columna) {
                columna++;
            } else if (columna > j.columna) {
                columna--;
            }

            verificarColision(j);
        }
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
}   
