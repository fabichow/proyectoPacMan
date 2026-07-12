package proyectopacman;

public class PoderVelocidad {

```
//Atributos
String tipo;
int duracion;
int cantidadMovimientos;

//Constructor
public PoderVelocidad() {
    this.tipo = "velocidad";
    this.duracion = 2;
    this.cantidadMovimientos = 2;
}

// Metodos
public void activar(Jugador j) {

    j.velocidad = cantidadMovimientos;
    j.poderActivo = true;

    System.out.println("Poder velocidad activado: puedes realizar 2 movimientos por turno durante 2 turnos");
}

public void reducirTurno(Jugador j) {

    duracion--;

    if (duracion <= 0) {

        j.velocidad = 1;
        j.poderActivo = false;

        System.out.println("Poder velocidad terminado");
    }
}

public int obtenerCantidadMovimientos() {
    return cantidadMovimientos;
}

public boolean estaActivo() {
    return duracion > 0;
}

public String descripcion() {
    return "Tipo: " + tipo + " | Permite 2 movimientos durante 2 turnos";
}
```

}
