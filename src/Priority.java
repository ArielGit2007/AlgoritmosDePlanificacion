import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Priority {
public Queue<Proceso> tablaIngreso= new PriorityQueue<>();;

public void ejecutarPriority() {
    int tiempoActual = 0;
    double sumaEspera = 0;
    double sumaRetorno = 0;
    int totalProcesos = tablaIngreso.size();

    ArrayList<String> detallesEspera = new ArrayList<>();
    ArrayList<String> detallesRetorno = new ArrayList<>();

    System.out.println("\n====== DIAGRAMA DE GANTT (PRIORITY) ======");
    System.out.print("Línea de tiempo: ");

    while (!tablaIngreso.isEmpty()) {
        Proceso elegido = tablaIngreso.poll();

        if (tiempoActual < elegido.tiempoLlegada) {
            tiempoActual = elegido.tiempoLlegada;
        }

        int tiempoInicio = tiempoActual;
        int tiempoFin = tiempoInicio + elegido.rafaga;

        int tiempoEspera = tiempoInicio - elegido.tiempoLlegada;
        int tiempoRetorno = tiempoFin;

        sumaEspera += tiempoEspera;
        sumaRetorno += tiempoRetorno;

        detallesEspera.add(elegido.nombre + ": " + tiempoEspera);
        detallesRetorno.add(elegido.nombre + ": " + tiempoRetorno);

        System.out.print("[" + elegido.nombre + "] ");
        System.out.print(tiempoInicio + " -> " + tiempoFin + " | ");

        tiempoActual = tiempoFin;
    }
    System.out.println("\nTIEMPO DE ESPERA");
    for (String de : detallesEspera) System.out.println(de);

    System.out.println("\nTIEMPO DE RETORNO");
    for (String dr : detallesRetorno) System.out.println(dr);

    System.out.println("\n\nTiempo promedio de espera: " + (sumaEspera / totalProcesos));
    System.out.println("Tiempo promedio de retorno: " + (sumaRetorno / totalProcesos));
}
}
