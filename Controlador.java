
import java.util.Scanner;
public class Controlador
{
    Scanner scanner = new Scanner(System.in);
    Robot robot;
    Pista pista;
    int menu;
    Controlador()
    {
        robot = new Robot();
    }

    /*
     Modelo 1 ---> 1 arma (cañon de misiles )
     Modelo 2 ---> 2 armas (cañon de misiles y laser)
     Modelo 3 ---> 4 arma (cañon de misiles y 3 lasers)
   */
    public void eleccionModelo()
    {
        int  modelo = 0 ;
        System.out.println(" Seleccione el modelo de Valkyrie [1] [2] [3] ");
        System.out.println("");
        System.out.println(" |Modelo 1| -----> | 1 arma  |cañon de misiles|            |");
        System.out.println(" |Modelo 2| -----> | 2 armas |cañon de misiles| |laser|    |");
        System.out.println(" |Modelo 3| -----> | 4 armas |cañon de misiles| |3 lasers| |");

        modelo = scanner.nextInt();

        while ( modelo > 3 || modelo < 1)
        {
            System.out.println("MODELO INVALIDO");
            modelo = scanner.nextInt();
        }
        if (modelo==3)
        {
            modelo=modelo+1;
        }
        robot.creacionArmas(modelo);
    }

    //Menu para cada estado del robot
    public void menu() throws InterruptedException {
        menu = robot.modoFighter();
        while(true)
        {
            int opcion;
            //menu del modo fighter
            while (menu == 1) {
                System.out.println("--------------------------------------------------");
                robot.impresionEstado();
                System.out.println("--------------------------------------------------");
                System.out.println("(1) Establecer modo G.E.R.W.A.L.K ");
                System.out.println("(2) Establecer modo BATTROID ");
                System.out.println("(3) Aumentar velocidad ");
                System.out.println("(4) Disminuir velocidad");
                System.out.println("(5) Despegar ");
                System.out.println("(6) Aumentar altura de vuelo");
                System.out.println("(7) Disminuir altura de vuelo");
                System.out.println("(8) Disparar");
                opcion = scanner.nextInt();
                //Eleccion de opcion ingresada
                while (opcion > 8 || opcion < 1)
                {
                    System.out.println("| OPCION NO VALIDA||");
                    opcion = scanner.nextInt();
                }
                switch (opcion)
                {
                    case 1: System.out.println("En que brazo desea tener el cañon de misiles");
                            System.out.println("[Brazo izquierdo (1)]   [ Brazo derecho (2) ]");
                            menu = robot.modoGerwalk(scanner.nextInt());
                            break;
                    case 2: if (robot.obtenerAltura() <= 200 )
                            {
                                System.out.println("En que brazo desea tener el cañon de misiles");
                                System.out.println("[Brazo izquierdo (1)]   [ Brazo derecho (2) ]");
                                menu = robot.modoBattroid(scanner.nextInt());
                            }
                            else
                            {
                                System.out.println("|IMPOSIBLE TRANSFORMARSE A BATTROID CON ESA ALTURA|");
                                System.out.println("LA ALTURA NO DEBE EXCEDER LOS 200 MTS");
                            }
                            break;
                    case 3: robot.aumentarVelocidad(); break;
                    case 4: robot.disminuirVelocidad(); break;
                    case 5: robot.despegar(); break;
                    case 6: robot.aumentarAlturaVuelo(); break;
                    case 7: robot.disminuirAlturaVuelo(); break;
                    case 8: robot.menuDisparar();break;

                }

            }
            //menu del modo gerwalk
            while (menu == 2) {
                System.out.println("--------------------------------------------------");
                robot.impresionEstado();
                System.out.println("--------------------------------------------------");
                System.out.println("(1) Establecer modo FIGHTER");
                System.out.println("(2) Establecer modo BATTROID ");
                System.out.println("(3) Aumentar velocidad ");
                System.out.println("(4) Disminuir velocidad ");
                System.out.println("(5) Aumentar altura de vuelo");
                System.out.println("(6) Disminuir altura de vuelo");
                System.out.println("(7) Disparar");
                System.out.println("(8) Movimiento terrestre");
                opcion = scanner.nextInt();
                while (opcion > 8 || opcion < 1)
                {
                    System.out.println("| OPCION NO VALIDA||");
                    opcion = scanner.nextInt();
                }
                switch (opcion)
                {
                    case 1: if (robot.obtenerAltura() > 0)
                            {
                                menu = robot.modoFighter();
                            }
                            else
                            {
                                System.out.println("|NECESITA ESTAR VOLANDO PARA TRANSFORMARSE A FIGHTER|");
                            }
                            break;
                    case 2: if (robot.obtenerAltura() <= 200)
                            {
                                System.out.println("En que brazo desea tener el cañon de misiles");
                                System.out.println("[Brazo izquierdo (1)]   [ Brazo derecho (2) ]");
                                menu = robot.modoBattroid(scanner.nextInt());
                            }
                            else
                            {
                                System.out.println("|  DEMASIADA ALTURA PARA TRANSFORMARSE A BATTROID |");
                                System.out.println("|  LA ALTURA NO DEBE EXCEDER LOS 200 MTS          |");
                            }
                            break;
                    case 3: robot.aumentarVelocidad(); break;
                    case 4: robot.disminuirVelocidad(); break;
                    case 5: robot.aumentarAlturaVuelo(); break;
                    case 6: robot.disminuirAlturaVuelo(); break;
                    case 7: robot.menuDisparar();break;
                    case 8: if (robot.obtenerAltura() == 0 ) {
                        robot.caminar();
                    }
                    else
                    {
                        System.out.println("| IMPOSIBLE CAMINAR EN EL AIRE |");
                    }

                }
            }
            while(menu == 3)
            {
                System.out.println("--------------------------------------------------");
                robot.impresionEstado();
                System.out.println("--------------------------------------------------");
                System.out.println("(1) Establecer modo G.E.R.W.A.L.K ");
                System.out.println("(2) Disparar");
                System.out.println("(3) Movimiento terrestre");
                opcion = scanner.nextInt();
                while (opcion > 3 || opcion < 1)
                {
                    System.out.println("| OPCION NO VALIDA||");
                    opcion = scanner.nextInt();
                }
                switch (opcion)
                {
                    case 1:
                            System.out.println("En que brazo desea tener el cañon de misiles");
                            System.out.println("[Brazo izquierdo (1)]   [ Brazo derecho (2) ]");
                            menu = robot.modoGerwalk(scanner.nextInt());
                            break;
                    case 2: robot.menuDisparar(); break;
                    case 3: robot.caminar();break;
                }

            }
        }

    }

}
