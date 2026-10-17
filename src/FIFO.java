import java.util.ArrayList;
import java.util.Scanner;

public class FIFO {

    public ArrayList<Proceso> tablaIngreso = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    public void IngresarProceso(){
        Proceso p = new Proceso();
        System.out.println("Ingrese el nombre del proceso");
        p.nombre = scanner.nextLine();
        System.out.println("Ingrese la rafaga");
        p.rafaga = scanner.nextInt();
        System.out.println("Ingrese el tiempo de llegada");
        p.tiempoLlegada = scanner.nextInt();
        System.out.println("Ingrese la prioridad");
        p.prioridad = scanner.nextInt();
        scanner.nextLine();

        tablaIngreso.add(p);
    }

    public void prepararColaPlanificacion() {
        // Ordena la lista por tiempo de llegada (Burbuja)
        for (int i = 0; i < tablaIngreso.size() - 1; i++) {
            for (int j = 0; j < tablaIngreso.size() - i - 1; j++) {
                if (tablaIngreso.get(j).tiempoLlegada > tablaIngreso.get(j + 1).tiempoLlegada) {
                    Proceso aux = tablaIngreso.get(j);
                    tablaIngreso.set(j, tablaIngreso.get(j + 1));
                    tablaIngreso.set(j + 1, aux);
                }
            }
        }
    }

    public void ejecutarFIFO() {
        int tiempoActual = 0;
        double sumaEspera = 0;
        double sumaRetorno = 0;
        int totalProcesos = tablaIngreso.size();

        ArrayList<String> detallesEspera = new ArrayList<>();
        ArrayList<String> detallesRetorno = new ArrayList<>();

        System.out.println("\n====== DIAGRAMA DE GANTT (ORDEN DE EJECUCIÓN FIFO) ======");
        System.out.print("Línea de tiempo: ");

        // Usamos la lista ordenada directamente
        for (Proceso actual : tablaIngreso) {

            if (tiempoActual < actual.tiempoLlegada) {
                tiempoActual = actual.tiempoLlegada;
            }

            int tiempoInicio = tiempoActual;
            int tiempoFin = tiempoInicio + actual.rafaga;

            int tiempoEspera = tiempoInicio - actual.tiempoLlegada;
            int tiempoRetorno = tiempoFin;

            sumaEspera += tiempoEspera;
            sumaRetorno += tiempoRetorno;

            detallesEspera.add(actual.nombre + " = (" + tiempoInicio + "-" + actual.tiempoLlegada + ") = " + tiempoEspera);
            detallesRetorno.add(actual.nombre + " = " + tiempoRetorno);

            System.out.print("| " + actual.nombre + " (" + tiempoInicio + " a " + tiempoFin + ") ");

            tiempoActual = tiempoFin;
        }
        System.out.println("|");

        System.out.println("\nTIEMPO DE ESPERA");
        for (String de : detallesEspera) System.out.println(de);

        System.out.println("\nTIEMPO DE RETORNO");
        for (String dr : detallesRetorno) System.out.println(dr);

        System.out.println("\nTIEMPO MEDIA DE ESPERA");
        System.out.printf("TEM = %.1f ut\n", (sumaEspera / totalProcesos));

        System.out.println("\nTIEMPO DE RETORNO MEDIO");
        System.out.printf("TRM = %.1f ut\n", (sumaRetorno / totalProcesos));
    }
}
