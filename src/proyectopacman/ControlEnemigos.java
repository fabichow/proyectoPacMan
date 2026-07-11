package proyectopacman;

public class ControlEnemigos{

    //Atributos

    Jugador jugador;
    EnemigoAcechador acechador;
    EnemigoVelocista velocista;
    EnemigoTanque tanque;
    //tercer enemigo;

    //Constructor
    public ControlEnemigos(Jugador jugador, EnemigoAcechador acechador, EnemigoVelocista velocista, EnemigoTanque tanque){
        this.jugador = jugador;
        this.acechador = acechador;
        this.velocista = velocista;
        this.tanque = tanque;
    }
    // Metodos
    public void moverEnemigos(){
        acechador.mover(jugador);
        velocista.mover(jugador);
        tanque.mover(jugador);
    }
    
    public void verificarColisiones(){
    acechador.verificarColision(jugador);
    velocista.verificarColision(jugador);
    tanque.verificarColision(jugador);
    }
    public void eliminarEnemigosInactivos(){
        if(!acechador.estaActivo()){
            System.out.println("Acechador eliminado");
        }
        //if(!velocista.estaActivo()){
        //    System.out.println("Acechador eliminado");
        //}
        if(!tanque.estaActivo()){
            System.out.println("tanque eliminado");
        }
    }
    public void generarMovimientos(){
        moverEnemigos();
    }
    public void mostrarEstadoEnemigos(){
        acechador.mostrarEstado();
        velocista.mostrarEstado();
        tanque.mostrarEstado();
    }
}