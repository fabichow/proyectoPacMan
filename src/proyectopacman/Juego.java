package proyectopacman;
import java.util.Scanner;
public class Juego {
    
    // Atributos
    Jugador jugador;
    EnemigoAcechador acechador;
    EnemigoVelocista velocista;
    EnemigoTanque tanque;
    Tablero tablero;
    Muro[] muros;
    Punto[] puntos;
    ControlEnemigos controlEnemigos;
    boolean juegoTerminado;

    //Contructor
    public Juego(){
    Scanner lector = new Scanner(System.in);
    System.out.println("========================================");
    System.out.println("      CONFIGURACION DEL TABLERO");
    System.out.println("========================================");
    System.out.print("Ingrese cantidad de filas: ");
    int filas = lector.nextInt();
    System.out.print("Ingrese cantidad de columnas: ");
    int columnas = lector.nextInt();
    if (filas < 10 || columnas < 10) {
        System.out.println("El tamaño minimo es 10x10.");
        filas = 10;
        columnas = 10;}
    muros = generarMuros(filas,columnas);
    tablero = new Tablero(filas,columnas,muros);
    gererarPuntos((filas*columnas)/8, filas, columnas);
    jugador = new Jugador();
    acechador = new EnemigoAcechador();
    velocista = new EnemigoVelocista();
    tanque = new EnemigoTanque();
    tablero = new Tablero(filas,columnas,muros);
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
        verificarPuntos();
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
    public void generarPuntos(int cantidad, int filas, int columnas){
        Random random = new Random();
        puntos = new Punto[cantidad];
        for(int = 0; i < cantidad; i++){
            int fila = random.nextInt(filas);
            int columna = random.nextInt(columnas);
            puntos[i] = new Punto(fila,columna);
        }
    }
    public verificarPuntos(){
        for(int i = 0;  i < puntos.length; i++){
            if(jugador.fila == puntos[i].fila && jugador.columna == puntos[i].columna && !puntos[i].fueRecolectado()){
                jugador.recogerPunto(puntos[i]);
            }
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
