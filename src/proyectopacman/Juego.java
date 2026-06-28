package proyectopacman;
import java.util.Scanner;
public class Juego {
    
    // Atributos
    Jugador jugador;
    EnemigoAcechador acechador;
    // Tablero tablero;
    // Enemigo[] enemigos;
    boolean juegoTerminado;

    //Contructor
    public Juego(){
        jugador = new Jugador();
        acechador = new EnemigoAcechador();
        juegoTerminado = false;
    }
    
    //Metodos 
    public void iniciarJuego() {
        System.out.println("===== PAC-MAN =====");
        System.out.println("Juego iniciado");
        mostrarEstado();
    }
    public void ejecutarTurno() {
        Scanner lector = new Scanner(System.in);
        System.out.println("Movimiento:");
        System.out.println("W = Arriba");
        System.out.println("A = Izquierda");
        System.out.println("S = Abajo");
        System.out.println("D = Derecha");
        System.out.print("Ingrese movimiento: ");
        String tecla = lector.nextLine();
        if (tecla.equals("w")) {
            jugador.mover("arriba");
        } else if (tecla.equals("s")) {
            jugador.mover("abajo");
        } else if (tecla.equals("a")) {
            jugador.mover("izquierda");
        } else if (tecla.equals("d")) {
            jugador.mover("derecha");
        } else {
            System.out.println("Movimiento invalido.");
        }
        acechador.mover(jugador);
        acechador.verificarColision(jugador);
        verificarFinJuego();
        mostrarEstado();
    }
    public void verificarFinJuego() {
        if (!jugador.estaVivo()){
            juegoTerminado = true;
        }
    }
    public void mostrarEstado() {
        jugador.mostrarEstado();
        acechador.mostrarEstado();
    }
    public void generarEnemigos(int cantidad) {

    }
    public void actualizarTablero() {

    }

}