package proyectopacman; 

public class EnemigoTanque { //Enemigo que se mueve lento pero hace 3 de daño. Luis Portilla

    //Atributos
    int fila;
    int columna;
    int daño;
    boolean activo;
    int velocidad;
    String tipo;
    
    //Constructor
    public EnemigoTanque(){
        tipo = "Tanque";
        fila = 8;
        columna = 8;
        daño = 3;
        activo = true;
        velocidad = 1;
    }
    public EnemigoTanque(int fila, int columna){
        tipo = "Tanque";
        this.fila = fila;
        this.columna = columna;
        daño = 3;
        activo = true;
        velocidad = 1;
    }
    //Metodos
    public void mover(Jugador j) {
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
        System.out.println("El tanque te alcanzo!");
    }
    public void verificarColision(Jugador j) {
        if (fila == j.fila && columna == j.columna){
            atacar(j);
        }
    }
    public boolean estaActivo(){
        return activo;
    }
    public void mostrarEstado() {
        System.out.println("========Tanque=========");
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