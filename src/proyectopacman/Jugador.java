package proyectopacman;

public class Jugador {

    // ===== Atributos =====

    String nombre;
    int fila;

    int columna;
    int salud;
    int puntaje;
    int velocidad;
    boolean poderActivo;

    //Constructor
    public Jugador(){
        nombre = "Pac-man";
        fila = 0;
        columna = 0;
        salud = 3;
        puntaje = 0;
        velocidad = 1;
        poderActivo = false;
        
    }
    // Metodos
    public void mover(String direccion) {
        if (direccion.equals("arriba")) {
            fila --;
        } else if (direccion.equals("abajo")) {
            fila ++;
        } else if (direccion.equals("derecha")) {
            columna ++;
        } else if (direccion.equals("izquierda")) {
            columna --;}
    }
    public void recogerPunto(Punto p) {
        puntaje += p.obtenerValor();
    }
    public void recibirDaño(int cantidad) {
        salud -= cantidad;
    }
    // public void usarPoder(Poder p) {

    // }
    public boolean estaVivo() {
        return salud > 0;
    }
    public void mostrarEstado() {
        System.out.println("========Jugador=========");
        System.out.println("Nombre: " + nombre);
        System.out.println("Fila: " + fila);
        System.out.println("Columna: " + columna);
        System.out.println("Salud: " + salud);
        System.out.println("Puntaje: " + puntaje);
        
        if (poderActivo){
            System.out.println("Poder: Activado");
        }else{
            System.out.println("Poder: Desactivado");
        }
        
        
    }
}