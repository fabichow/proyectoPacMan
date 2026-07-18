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
    Scanner lector;
    boolean juegoTerminado;

    //Contructor
    public Juego(){
    lector = new Scanner(System.in);
    System.out.println("========================================");
    System.out.println("      CONFIGURACION DEL TABLERO");
    System.out.println("========================================");
    System.out.print("Ingrese cantidad de filas: ");
    int filas = lector.nextInt();
    System.out.print("Ingrese cantidad de columnas: ");
    int columnas = lector.nextInt();
    lector.nextLine(); 
    if (filas < 10 || columnas < 10) {
        System.out.println("El tamaño minimo es 10x10.");
        filas = 10;
        columnas = 10;}
    muros = generarMuros(filas,columnas);
    tablero = new Tablero(filas,columnas,muros);
    jugador = new Jugador();
    Posicion posJugador = obtenerPosicionLibre(filas, columnas);
    generarPuntos((filas*columnas)/8, filas, columnas);
    jugador.fila = posJugador.fila;
    jugador.columna = posJugador.columna;
    acechador = new EnemigoAcechador();
    Posicion posAcechador = obtenerPosicionLibre(filas, columnas);
    acechador.fila = posAcechador.fila;
    acechador.columna = posAcechador.columna;
    velocista = new EnemigoVelocista();
    Posicion posVelocista = obtenerPosicionLibre(filas, columnas);
    velocista.fila = posVelocista.fila;
    velocista.columna = posVelocista.columna;
    tanque = new EnemigoTanque();
    Posicion posTanque = obtenerPosicionLibre(filas, columnas);
    tanque.fila = posTanque.fila;
    tanque.columna = posTanque.columna;
    tablero = new Tablero(filas,columnas,muros);
    controlEnemigos = new ControlEnemigos(jugador, acechador, velocista, tanque);
    juegoTerminado = false;
    }
    
    //Metodos 
    public void iniciarJuego() {
        System.out.println("===== PAC-MAN =====");
        System.out.println("Juego iniciado");
        actualizarTablero();
        mostrarInterfaz();
    }
    public void ejecutarTurno() {
        String tecla = lector.nextLine();
        if (tecla.equals("w")) {
            jugador.mover("arriba", tablero);
        } else if (tecla.equals("s")) {
            jugador.mover("abajo",tablero);
        } else if (tecla.equals("a")) {
            jugador.mover("izquierda", tablero);
        } else if (tecla.equals("d")) {
            jugador.mover("derecha", tablero);
        } else {
            System.out.println("Movimiento invalido.");
        }
        verificarPuntos();
        verificarVictoria();
        if(juegoTerminado){
            return;
        }
        controlEnemigos.moverEnemigos();
        controlEnemigos.verificarColisiones();
        if(jugador.fueGolpeado && jugador.estaVivo()){
            Posicion p = obtenerPosicionSegura(tablero.filas, tablero.columnas);
            jugador.fila = p.fila;
            jugador.columna = p.columna;
            jugador.fueGolpeado = false;
        }
        actualizarTablero();
        verificarFinJuego();
        mostrarInterfaz();
    }
    public void mostrarInterfaz(){
        tablero.mostrar();
        jugador.mostrarEstado();
        System.out.println();
        System.out.println("Controles:");
        System.out.println("W = Arriba");
        System.out.println("A = Izquierda");
        System.out.println("S = Abajo");
        System.out.println("D = Derecha");
        System.out.println("Ingrese movimiento: ");
    }
    public void verificarFinJuego() {
        if (!jugador.estaVivo()){
            juegoTerminado = true;
        }
    }
    public void verificarVictoria(){
        for(int i = 0; i < puntos.length; i++){
            if(!puntos[i].fueRecolectado()){
                return;
            }
        }
        System.out.println("========================================");
        System.out.println("                GANASTE");
        System.out.println("========================================");
        juegoTerminado = true;
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
        for(int j = 0; j < columnas; j++){
            muros[indice++] = new Muro(0, j);
            muros[indice++] =
                    new Muro(filas - 1, j);
        }
        for(int i = 1; i < filas - 1; i++){
            muros[indice++] = new Muro(i, 0);
            muros[indice++] =
                    new Muro(i, columnas - 1);
        }
        while(indice < muros.length){
            int fila =
                    random.nextInt(filas - 2) + 1;
            int columna =
                    random.nextInt(columnas - 2) + 1;
            if((fila == 1 && columna == 1)||(fila == 1 && columna == 2)||(fila == 2 && columna == 1) ||(fila == 2 && columna == 2)){
                continue;
            }
            boolean repetido = false;
            for(int i = 0; i < indice; i++){
                if(muros[i].getFila() == fila &&
                   muros[i].getColumna() == columna){
                    repetido = true;
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
            Posicion p = obtenerPosicionLibre(filas, columnas);
            puntos[i] = new Punto(p.fila,p.columna);
        }
    }
    public void verificarPuntos(){
        for(int i = 0;  i < puntos.length; i++){
            if(jugador.fila == puntos[i].fila && jugador.columna == puntos[i].columna && !puntos[i].fueRecolectado()){
                jugador.recogerPunto(puntos[i]);
            }
        }
    }
    
    public boolean posicionOcupada(int fila,int columna){
        for(int i = 0; i < muros.length; i++){
            if(muros[i].getFila() == fila && muros[i].getColumna() == columna){
                return true;
            }
        }
        if(jugador != null){
            if(jugador.fila == fila &&
               jugador.columna == columna){
                return true;
            }
        }
        if(acechador != null){
            if(acechador.fila == fila && acechador.columna == columna){
                return true;
            }
        }
        if(velocista != null){
            if(velocista.fila == fila && velocista.columna == columna){
                return true;
            }
        }
        if(tanque != null){
            if(tanque.fila == fila && tanque.columna == columna){
                return true;
            }
        }
        if (puntos != null) {
            for (int i = 0; i < puntos.length; i++) {
                if (puntos[i] != null) {
                    if (puntos[i].fila == fila &&
                        puntos[i].columna == columna) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public Posicion obtenerPosicionLibre(int filas, int columnas){
        Random random = new Random();
        while(true){
            int fila = random.nextInt(filas);
            int columna = random.nextInt(columnas);
            if(!posicionOcupada(fila,columna)){
                return new Posicion(fila,columna);
            }
        }
    }
    
    public boolean posicionSegura(int fila, int columna){
        if(posicionOcupada(fila, columna)){
            return false;
        }
        int distanciaMinima = 3;
        if(Math.abs(fila - acechador.fila) + Math.abs(columna - acechador.columna) < distanciaMinima)
            return false;
        if(Math.abs(fila - velocista.fila) + Math.abs(columna - velocista.columna) < distanciaMinima)
            return false;
        if(Math.abs(fila - tanque.fila) + Math.abs(columna - tanque.columna) < distanciaMinima)
            return false;
        return true;
    }
    
    public Posicion obtenerPosicionSegura(int filas, int columnas){
        Random random = new Random();
        int intentos = 0;
        while(intentos < 100){
            int fila = random.nextInt(filas);
            int columna = random.nextInt(columnas);
            if(posicionSegura(fila, columna)){
                return new Posicion(fila, columna);
            }
            intentos++;
        }
        return obtenerPosicionLibre(filas, columnas);
    }
    public void mostrarEstado() {
        jugador.mostrarEstado();
        controlEnemigos.mostrarEstadoEnemigos(); //Comentar para apagar enemigos
    }
    public void generarEnemigos(int cantidad){
    System.out.println(cantidad + " enemigos generados.");
    }
    public void actualizarTablero(){
        tablero.actualizar(jugador, acechador, velocista, tanque, puntos);
    }

}
