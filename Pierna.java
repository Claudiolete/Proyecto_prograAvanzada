import java.util.Random;
import java.util.Scanner;

public class Pierna {

    private boolean estado= false;
    Scanner scanner = new Scanner(System.in);
    Random random = new Random(System.currentTimeMillis());

    public Pierna() {
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    ///Metodo para caminar
    public void caminar()
    {

        int controlCaminar, contador,metros = 0;
        contador = random.nextInt(2);

        System.out.println("| Ingrese [0] para dejar de caminar |");
        System.out.println("| Ingrese [1] para caminar |");

        while (true)
        {
            controlCaminar = scanner.nextInt();
            while(controlCaminar > 1 || controlCaminar < 0) {
                System.out.println("|ERROR|");
                controlCaminar = scanner.nextInt();
            }
            if(controlCaminar==0)
            {
                break;
            }
            if (contador%2 == 0) {

                System.out.println("|Pie izquierdo|");
                contador++;
                metros = ( random.nextInt(10) + 1 ) + metros ;
            }
            else{
                System.out.println("               |Pie derecho|");
                contador++;
                metros = ( random.nextInt(10) + 1 ) + metros ;
            }
            System.out.println("                                              --------------------------------------------------");
            System.out.println("                                              |        Metros caminados: " + metros +"     ");
            System.out.println("                                              --------------------------------------------------");
            System.out.println("                                                    | Ingrese [0] para dejar de caminar |");
            System.out.println("                                                    | Ingrese [1] para caminar          |");
            System.out.println("                                              --------------------------------------------------");

        }
        System.out.println("| Caminó " +metros +" metros |");
    }
}
