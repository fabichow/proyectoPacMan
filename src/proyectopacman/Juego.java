package proyectopacman;
import java.util.Scanner;
import java.util.Random;
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
    generarPuntos((filas*columnas)/8, filas, columnas);
    jugador = new Jugador();
    acechador = new EnemigoAcechador();
    velocista = new EnemigoVelocista();
    tanque = new EnemigoTanque();
    tablero = new Tablero(filas,columnas,muros);
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
public Muro[] generarMuros(int filas, int columnas){

    Random random = new Random();

    int cantidadMurosInternos = (filas * columnas) / 10;

    int murosBorde =
            (filas * 2) +
            ((columnas - 2) * 2);

    Muro[] muros =
            new Muro[murosBorde + cantidadMurosInternos];

    int indice = 0;

    // Borde superior e inferior
    for(int j = 0; j < columnas; j++){

        muros[indice++] = new Muro(0, j);

        muros[indice++] =
                new Muro(filas - 1, j);

    }

    // Borde izquierdo y derecho
    for(int i = 1; i < filas - 1; i++){

        muros[indice++] = new Muro(i, 0);

        muros[indice++] =
                new Muro(i, columnas - 1);

    }

    // Muros internos aleatorios
    while(indice < muros.length){

        int fila =
                random.nextInt(filas - 2) + 1;

        int columna =
                random.nextInt(columnas - 2) + 1;

        // Zona segura del jugador
        if((fila == 1 && columna == 1) ||
           (fila == 1 && columna == 2) ||
           (fila == 2 && columna == 1) ||
           (fila == 2 && columna == 2)){

            continue;

        }

        boolean repetido = false;

        for(int i = 0; i < indice; i++){

            if(muros[i].getFila() == fila &&
               muros[i].getColumna() == columna){

                repetido = true;
                break;

            }

        }

        if(!repetido){

            muros[indice++] =
                    new Muro(fila, columna);

        }

    }

    return muros;

}
    public void generarPuntos(int cantidad, int filas, int columnas){
        Random random = new Random();
        puntos = new Punto[cantidad];
        for(int i = 0; i < cantidad; i++){
            int fila = random.nextInt(filas);
            int columna = random.nextInt(columnas);
            puntos[i] = new Punto(fila,columna);
        }
    }
    public void verificarPuntos(){
        for(int i = 0;  i < puntos.length; i++){
            if(jugador.fila == puntos[i].fila && jugador.columna == puntos[i].columna && !puntos[i].fueRecolectado()){
                jugador.recogerPunto(puntos[i]);
            }
        }
    }
    
    public Posicion obtenerPosicionLibre(int filas, int columnas){
    Random random = new Random();
    while(true){
        int fila = random.nextInt(filas);
        int columna = random.nextInt(columnas);
        boolean ocupada = false;
        for(int i = 0; i < muros.length; i++){
            if(muros[i].getFila() == fila && muros[i].getColumna() == columna){
                ocupada = true;
            }
        }
        if(!ocupada){
            return new Posicion(fila,columna);
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
        tablero.actualizar(jugador, acechador, velocista, tanque, puntos);
    }

}
