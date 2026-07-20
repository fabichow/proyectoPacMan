package proyectopacman; 

public class EnemigoAcechador { //Enemigo que aumenta su velocidad mientras mas lejos del jugador se encuentre. Luis Portilla

    //Atributos
    int fila;
    int columna;
    int daño;
    boolean activo;
    int velocidad;
    String tipo;
    
    //Constructor
    public EnemigoAcechador(){
        tipo = "Acechador";
        fila = 3;
        columna = 3;
        daño = 1;
        activo = true;
        velocidad = 1;
    }
    public EnemigoAcechador(int fila, int columna){
        tipo = "Acechador";
        this.fila = fila;
        this.columna = columna;
        daño = 1;
        activo = true;
        velocidad = 1;
    }
    //Metodos
    public void mover(Jugador j) {
        actualizarVelocidad(j);
        Tablero tablero = Tablero.tableroActual;
        for (int i = 0; i < velocidad; i++) {
            int nuevaFila = fila;
            int nuevaColumna = columna;
            if (fila < j.fila) {
                nuevaFila++;
            } else if (fila > j.fila) {
                nuevaFila--;
            } else if (columna < j.columna) {
                nuevaColumna++;
            } else if (columna > j.columna) {
                nuevaColumna--;
            }
            if (tablero.esMovimientoValido(nuevaFila, nuevaColumna)) {
                fila = nuevaFila;
                columna = nuevaColumna;
            }
        }
    }
    public void atacar(Jugador j) {
        j.recibirDaño(daño);
        System.out.println("El acechador te alcanzo!");
    }
    public void verificarColision(Jugador j) {
        if (fila == j.fila && columna == j.columna){
            atacar(j);
        }
    }
     public int calcularDistancia(Jugador j) {
        int diferenciaFilas = Math.abs(fila - j.fila);
        int diferenciaColumnas = Math.abs(columna - j.columna);
        int distancia = diferenciaFilas + diferenciaColumnas;
        return distancia;
    }
    public void actualizarVelocidad(Jugador j) {
        int distancia = calcularDistancia(j);
        if (distancia <= 4) {
            velocidad = 1;
        } else if (distancia <= 8) {
            velocidad = 2;
        } else {
            velocidad = 3;
        }
    }
    public boolean estaActivo(){
        return activo;
    }
    public void mostrarEstado() {
        System.out.println("========Acechador=========");
        System.out.println("Tipo: " + tipo);
        System.out.println("Fila: " + fila);
        System.out.println("Columna: " + columna);
        System.out.println("Dano: " + daño);
        System.out.println("Velocidad: " + velocidad);
        if (activo){
            System.out.println("Estado: Activado");
        }else{
            System.out.println("Estado: Desactivado");
        }
    }
}