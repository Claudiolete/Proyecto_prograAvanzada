
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Robot
{
    // creacion de las partes que contiene el robot
    private  Brazo brazo_L, brazo_R;
    private  Pierna pierna_L,pierna_R;
    private  Ala ala_L,ala_R;
    private Cabeza cabeza;
    private  Arma arma_misiles = new Arma("Arma de misiles");
    private  Cabina cabina;
    private ArrayList <Arma>  armas_lasers= new ArrayList<Arma>();//Se crea un arrayList para almacenar armas lasers que quiere el usuario
    private Pista pista = new Pista(1000);
    Scanner scanner = new Scanner(System.in);
    Random random = new Random(System.currentTimeMillis());

    //Constructor de la clase robot
    Robot()
    {
        //Instanciacion de las partes del robot
        brazo_L = new Brazo();
        brazo_R = new Brazo();
        pierna_L = new Pierna();
        pierna_R = new Pierna();
        ala_L = new Ala();
        ala_R = new Ala();
        cabina = new Cabina();
        cabeza = new Cabeza();
        arma_misiles.setEstado(true);
        //Impresion estetica
        System.out.println("   ----------------------------------------------");
        System.out.println("   |             Ensamblaje exitoso              |");
        System.out.println("   |               Alfa Trofymchuk               |");
        System.out.println("   |              Listo para operar              |");
        System.out.println("   ----------------------------------------------");
    }

    //Segun el modelo que eliga el usuario se crean las armas necesarias
    public void creacionArmas(int x)
    {
        for(int i = 0 ; i < (x-1) ; i++)
        {
            Arma arma_laser = new Arma("Arma laser "+(i+1) );
            agregarLasers(arma_laser);
        }
    }

    //Metodo para agregar objetivo tipo arma a un arreglo
    public void agregarLasers(Arma x)
    {
        this.armas_lasers.add(x);//En el Arraylist tipo arma agregamos los objetos creados
    }
    //Se establece modo fighter
    public int modoFighter()
    {
        //Se activan y desactivan clases necesarias en este modo
        ala_R.setEstado(true);
        ala_L.setEstado(true);
        brazo_R.setEstado(false);
        brazo_L.setEstado(false);
        pierna_R.setEstado(false);
        pierna_L.setEstado(false);
        brazo_L.setEstado_arma(false);
        brazo_R.setEstado_arma(false);
        cabeza.setEstado(false);
        return 1;
    }
    //Se establece modo gerwalk
    public int modoGerwalk(int opcion_brazo_arma)
    {
        //Se activan y desactivan clases necesarias en este modo
        brazo_R.setEstado(true);
        brazo_L.setEstado(true);
        pierna_R.setEstado(true);
        pierna_L.setEstado(true);
        cabeza.setEstado(false);
        brazo_L.setEstado_arma(false);
        brazo_R.setEstado_arma(false);
        ala_L.setEstado(true);
        ala_R.setEstado(true);

        //Segun la opcion del usuario se activa la arma en brazo o en el otro
        if(opcion_brazo_arma == 1)
        {
            brazo_L.setEstado_arma(true);
        }
        if(opcion_brazo_arma == 2)
        {
            brazo_R.setEstado_arma(true);
        }
        return 2;
    }
    //Se establece modo battroid
    public int modoBattroid(int opcion_brazo_arma)
    {
        ala_L.setEstado(false);
        ala_R.setEstado(false);
        pierna_L.setEstado(true);
        pierna_R.setEstado(true);
        brazo_L.setEstado(true);
        brazo_R.setEstado(true);
        cabeza.setEstado(true);
        cabina.setAltura(-cabina.getAltura());
        cabina.setVolando(false);
        cabina.setVelocidad(-cabina.getVelocidad());
        brazo_L.setEstado_arma(false);
        brazo_R.setEstado_arma(false);
        if(opcion_brazo_arma == 1)
        {
            brazo_L.setEstado_arma(true);
        }
        if(opcion_brazo_arma == 2)
        {
            brazo_R.setEstado_arma(true);
        }
        return 3;

    }
    //IMPRESION DEL ESTADO ACTUAL DEL ROBOT
    public void impresionEstado()
    {
        //Se comparan los estados de las partes para obtener el modo del robot
        if(ala_L.isEstado() && ala_R.isEstado())
        {
            if(pierna_L.isEstado())
            {
                System.out.println("El modo de VARIABLE FIGHTER VALKYRIE es : G.E.R.W.A.L.K.");
            }
            else
            {
                System.out.println("El modo de VARIABLE FIGHTER VALKYRIE es : FIGHTER");
            }
        }
        else
        {
            System.out.println("El modo de VARIABLE FIGHTER VALKYRIE es : BATTROID");
        }
        //Se imprime si esta estacionado
        if(cabina.isEstacionado())
        {
            System.out.println("Se encuentra detenido");
        }
        //Impresion altura de vuelo
        if (cabina.getAltura() > 0) {
            System.out.println("Vuelo a :" + cabina.getAltura() + " metros");
        } else {
            System.out.println("VARIABLE FIGHTER VALKYRIE se encuentra en tierra ");
        }

        //Se imprime la velocidad en caso de no se este en modo battroid
        if (!cabeza.isEstado())
        {
            System.out.println("Velocidad :" + cabina.getVelocidad() + " km/h");
        }
        //Se imprime si existe una pista
        if (cabina.isEstacionado() && cabina.getVelocidad() == 0 && !cabeza.isEstado())
        {
            System.out.println("Existe una pista de " + pista.getLongitud() + " metros");
        }
        //Se imprime en que brazo se encuentra el arma
        if ( ( pierna_L.isEstado() && pierna_R.isEstado() && ala_L.isEstado() && ala_R.isEstado() ) || ( cabeza.isEstado()) )
        {
            if (brazo_L.isEstado_arma()) {
                System.out.println("Arma en brazo izquierdo");
            }
            else
            {
                System.out.println("Arma en brazo derecho");
            }
        }
    }
    // METODOS PARA AUMENTAR Y DISMINUIR ALTURA/VELOCIDAD
    public void aumentarVelocidad()
    {
        int velocidad=0;
        velocidad = random.nextInt(50)+1;
        cabina.setVelocidad(velocidad);
        if (cabina.getVelocidad()>0)
        {
            cabina.setEstacionado(false);
        }
    }
    public void disminuirVelocidad()
    {
        int velocidad=0;
        velocidad = random.nextInt(50)+1;
        cabina.setVelocidad(-velocidad);
        if(cabina.getVelocidad() == 0)
        {
            cabina.setEstacionado(true);
        }
    }
    public void despegar()
    {
        if (cabina.isVolando())
        {
            System.out.println("| YA ESTA EN ALTURA NO PUEDE DESPEGAR |");
        }
        if ( ( cabina.getVelocidad() >= 350 ) && ( cabina.getAltura() == 0 ) )
        {
            cabina.setAltura(1);
            cabina.setVolando(true);
            System.out.println("| HA DESPEGADO |");
        }
        if (cabina.getVelocidad() < 350 && !cabina.isVolando())
        {
            System.out.println("--------------------------------------------------");
            System.out.println("|     IMPOSIBLE DESPEGAR CON ESA VELOCIDAD      |");
            System.out.println("--------------------------------------------------");
            System.out.println("|     DEBE AUMENTAR LA VELOCIDAD A 350 KM/H     |");
            System.out.println("--------------------------------------------------");
        }

    }
    public void aumentarAlturaVuelo()
    {
        int altura=0;
        altura = random.nextInt(50)+1;
        //aumentar altura en modo fighter
        if (ala_L.isEstado() && ala_R.isEstado() && !brazo_R.isEstado() && !brazo_L.isEstado()) {
            if (cabina.getAltura() > 0) {
                cabina.setAltura(altura);
                cabina.setVolando(true);
                cabina.setEstacionado(false);
            } else {
                System.out.println("--------------------------------------------------");
                System.out.println("DEBE DESPEGAR PARA AUMENTAR LA ALTURA DE VUELO");
                System.out.println("--------------------------------------------------");
            }
        }
        //aumentar altura en modo gerwalk
        else
        {
            cabina.setAltura(altura);
            cabina.setVolando(true);
        }

    }
    public void disminuirAlturaVuelo()
    {
        int altura=0;
        altura = random.nextInt(50)+1;
        cabina.setAltura(-altura);
        cabina.setVolando(false);
    }
    //////////////////////////////
    //Metodo para obtener altura
    public double obtenerAltura()
    {
        return this.cabina.getAltura();
    }
    ///Menu para disparar
    public void menuDisparar()
    {
        int opcionArma =1, objetivo;
        //Menu disparar para modo fighter y gerwalk
        if (!cabeza.isEstado())
        {
            while (opcionArma != 0) {
                //Impresion de armas e instrucciones disponibles para usuario
                System.out.println("--------------------------------------------------");
                System.out.println("   | " + "(1) " + arma_misiles.getTipo_arma() + " |  ");
                for (int x = 0; x < armas_lasers.size(); x++) {
                    System.out.println("   | " + "(" + (x + 2) + ") " + armas_lasers.get(x).getTipo_arma() + "    |   ");
                }
                System.out.println("--------------------------------------------");
                System.out.println("| Ingrese [0] para volver a menu principal   |");
                System.out.println("--------------------------------------------");
                System.out.println("|   ¿Con cual arma desea disparar?   |");
                opcionArma = scanner.nextInt();
                while (opcionArma > armas_lasers.size() + 1 || opcionArma < 0) {
                    System.out.println("|ARMA NO VALIDA|");
                    opcionArma = scanner.nextInt();
                }
                ///////////////
                System.out.println("--------------------------------------------------");
                //Impresion de disparos segun sea el caso
                switch (opcionArma) {
                    case 1:
                        System.out.println("DISPARANDO ARMA MISILES...");
                        System.out.println("WHRRRRRRRRRRRRRRR!");
                        break;
                    case 2:
                        System.out.println("DISPARANDO ARMA LASER 1...");
                        System.out.println("ZAAAAAAAAAAAAAAAP!");
                        break;
                    case 3:
                        System.out.println("DISPARANDO ARMA LASER 2...");
                        System.out.println("ZAAAAAAAAAAAAAAAP!");
                        break;
                    case 4:
                        System.out.println("DISPARANDO ARMA LASER 3...");
                        System.out.println("ZAAAAAAAAAAAAAAAP!");
                        break;
                }
                //Random para simular la precision de disparo
                if (opcionArma != 0) {
                    objetivo = random.nextInt(2) + 1;
                    if (objetivo == 1) {
                        System.out.println("| SUCCESS OBJETIVO DERRIBADO |");
                    }
                    if (objetivo == 2) {
                        System.out.println("| OBJETIVO NO DERRIBADO |");
                    }
                }
                System.out.println("--------------------------------------------------");
            }
        }
        //Menu disparo para modo battroid
        else
        {
            while (opcionArma != 0)
            {
                System.out.println("--------------------------------------------------");
                System.out.println("   | " + "(1) " + arma_misiles.getTipo_arma() + " |  ");
                System.out.println("---------------------------------------------");
                System.out.println("| Ingrese [0] para volver a menu principal   |");
                System.out.println("| Ingrese [1] para disparar                  |");
                System.out.println("---------------------------------------------");
                opcionArma = scanner.nextInt();
                while (opcionArma > 1 || opcionArma < 0) {
                    System.out.println("|ARMA NO VALIDA|");
                    opcionArma = scanner.nextInt();
                }
                objetivo = random.nextInt(2) + 1;
                System.out.println("--------------------------------------------------");
                switch (opcionArma) {
                    case 1:
                        System.out.println("DISPARANDO ARMA MISILES...");
                        System.out.println("WHRRRRRRRRRRRRRRR!");
                        break;
                    case 2:
                        System.out.println("DISPARANDO ARMA LASER 1...");
                        System.out.println("ZAAAAAAAAAAAAAAAP!");
                        break;
                    case 3:
                        System.out.println("DISPARANDO ARMA LASER 2...");
                        System.out.println("ZAAAAAAAAAAAAAAAP!");
                        break;
                    case 4:
                        System.out.println("DISPARANDO ARMA LASER 3...");
                        System.out.println("ZAAAAAAAAAAAAAAAP!");
                        break;
                }
                if (opcionArma != 0) {
                    if (objetivo == 1) {
                        System.out.println("| SUCCESS : OBJETIVO DERRIBADO |");
                    }
                    if (objetivo == 2) {
                        System.out.println("| OBJETIVO NO DERRIBADO |");
                    }
                }
            }
        }


    }
    //caminar
    public void caminar() {
        System.out.println("|  La velocidad ha disminuido a 0 Km/h |");
        cabina.setVelocidad( (-cabina.getVelocidad()) );
        pierna_L.caminar();
    }
}
