package proyectopacman;

public class ControlEnemigos{

    //Atributos

    Jugador jugador;
    EnemigoAcechador acechador;
    EnemigoVelocista velocista;
    //tercer enemigo;

    //Constructor
    public ControlEnemigos(Jugador jugador, EnemigoAcechador acechador, EnemigoVelocista velocista){
        this.jugador = jugador;
        this.acechador = acechador;
        this.velocista = velocista;
    }
    // Metodos
    public void moverEnemigos(){
        acechador.mover(jugador);
        velocista.mover(jugador);
    }
    
    public void verificarColisiones(){
    acechador.verificarColision(jugador);
    velocista.verificarColision(jugador);
    }
    public void eliminarEnemigosInactivos(){
        if(!acechador.estaActivo()){
            System.out.println("Acechador eliminado");
        }
    }
    public void generarMovimientos(){
        moverEnemigos();
    }
    public void mostrarEstadoEnemigos(){
        acechador.mostrarEstado();
        velocista.mostrarEstado();
    }
}