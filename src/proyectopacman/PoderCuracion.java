package proyectopacman; 

public class PoderCuracion {

    //Atributos
    String tipo;
    
    //Constructor
    public PoderCuracion() {
        tipo = "Curacion";
    }
    //Metodos
    public void activar(Jugador j) {
        j.recuperarSalud(1);
        System.out.println("¡Has recuperado 1 punto de salud!");
    }

    public String descripcion() {
        return "Recupera 1 punto de salud.";
    }

    public void mostrarEstado() {
        System.out.println("Tipo: " + tipo);
        System.out.println(descripcion());
    }
}