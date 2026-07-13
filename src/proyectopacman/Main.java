package proyectopacman;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner lector = new Scanner(System.in);

        int opcion;

        System.out.println("########################################");
        System.out.println("#                                      #");
        System.out.println("#              PAC - MAN              #");
        System.out.println("#                                      #");
        System.out.println("########################################");
        System.out.println();

        System.out.println("========================================");
        System.out.println("               MENU");
        System.out.println("========================================");
        System.out.println("1. Iniciar Juego");
        System.out.println("2. Salir");
        System.out.println("========================================");
        System.out.print("Seleccione una opcion: ");

        opcion = lector.nextInt();

        switch (opcion) {

            case 1:

                System.out.println();
                System.out.println("========================================");
                System.out.println("         INICIANDO JUEGO...");
                System.out.println("========================================");
                System.out.println();

                Juego juego = new Juego();

                juego.iniciarJuego();

                while (!juego.juegoTerminado) {

                    juego.ejecutarTurno();

                }

                System.out.println();
                System.out.println("========================================");
                System.out.println("            FIN DEL JUEGO");
                System.out.println("========================================");

                break;

            case 2:

                System.out.println();
                System.out.println("========================================");
                System.out.println("      GRACIAS POR JUGAR PAC-MAN");
                System.out.println("========================================");

                break;

            default:

                System.out.println();
                System.out.println("========================================");
                System.out.println("          OPCION INVALIDA");
                System.out.println("========================================");

        }

        lector.close();
    }
}
