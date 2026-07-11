package proyectopacman;
import java.util.Scanner;
public class Juego {
    
    // Atributos
    Jugador jugador;
    EnemigoAcechador acechador;
    EnemigoVelocista velocista;
    EnemigoTanque tanque;
    Tablero tablero;
    Punto[] puntos;
    ControlEnemigos controlEnemigos;
    boolean juegoTerminado;

    //Contructor
    public Juego(){
    jugador = new Jugador();
    acechador = new EnemigoAcechador();
    velocista = new EnemigoVelocista();
    tanque = new EnemigoTanque();
    tablero = new Tablero(10,10);
    puntos = new Punto[3];
    puntos[0] = new Punto(1,1);
    puntos[1] = new Punto(4,4);
    puntos[2] = new Punto(7,7);
    controlEnemigos = new ControlEnemigos(jugador, acechador, velocista, tanque);
    juegoTerminado = false;
    }
    
    //Metodos 
    public void iniciarJuego() {
        System.out.println("===== PAC-MAN =====");
        System.out.println("Juego iniciado");
        actualizarTablero();
        tablero.mostrar();
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
        controlEnemigos.moverEnemigos();
        controlEnemigos.verificarColisiones();
        actualizarTablero();
        verificarFinJuego();
        mostrarEstado();
        tablero.mostrar();
    }
    public void verificarFinJuego() {
        if (!jugador.estaVivo()){
            juegoTerminado = true;
        }
    }
    public void mostrarEstado() {
        jugador.mostrarEstado();
        controlEnemigos.mostrarEstadoEnemigos();
    }
    public void generarEnemigos(int cantidad){
    System.out.println(cantidad + " enemigos generados.");
    }
    public void actualizarTablero(){
        tablero.actualizar(jugador, acechador, velocista, puntos);
    }

}