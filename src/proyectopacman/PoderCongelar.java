package proyectopacman;

public class PoderCongelar {

//Atributos
String tipo;
int duracion;
boolean activo;

//Constructor
public PoderCongelar() {
    this.tipo = "congelar";
    this.duracion = 2;
    this.activo = false;
}

// Metodos
public void activar() {
    activo = true;
    System.out.println("Enemigos congelados durante " + duracion + " turnos");
}

public void reducirTurno() {

    if (activo) {
        duracion--;

        if (duracion <= 0) {
            activo = false;
            System.out.println("Los enemigos vuelven a moverse");
        }
    }
}

public boolean estaActivo() {
    return activo;
}

public String descripcion() {
    return "Tipo: " + tipo + " | Duracion: 2 turnos";
}

}
