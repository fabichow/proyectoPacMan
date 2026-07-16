package proyectopacman;

public class PoderVelocidad {
    // Atributos
    String tipo;
    int duracion;
    int duracionMaxima;
    int cantidadMovimientos;
    boolean activo;

    // Constructor
    public PoderVelocidad() {
        this.tipo = "velocidad";
        this.duracionMaxima = 2;
        this.duracion = 0;
        this.cantidadMovimientos = 2;
        this.activo = false;
    }

    // Metodos
    public void activar(Jugador j) {
        activo = true;
        duracion = duracionMaxima; // se reinicia cada vez que se activa
        j.velocidad = cantidadMovimientos;
        j.poderActivo = true;
        System.out.println("Poder velocidad activado: puedes realizar " 
                + cantidadMovimientos + " movimientos por turno durante " 
                + duracionMaxima + " turnos");
    }

    // Se llama una sola vez por turno.
    public void reducirTurno(Jugador j) {
        if (!activo) return;
        duracion--;
        if (duracion <= 0) {
            activo = false;
            j.velocidad = 1;
            j.poderActivo = false;
            System.out.println("Poder velocidad terminado");
        }
    }

    public int obtenerCantidadMovimientos() {
        return cantidadMovimientos;
    }

    public boolean estaActivo() {
        return activo;
    }

    public String descripcion() {
        return "Tipo: " + tipo + " | Permite " + cantidadMovimientos 
                + " movimientos durante " + duracionMaxima + " turnos";
    }
}
