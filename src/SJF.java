import java.util.ArrayList;
import java.util.Scanner;

public class SJF {
    public ArrayList<Proceso> tablaIngreso = new ArrayList<>();

    public void ejecutarSJF() {
        int tiempoActual = 0;
        double sumaEspera = 0;
        double sumaRetorno = 0;
        int totalProcesos = tablaIngreso.size();

        ArrayList<String> detallesEspera = new ArrayList<>();
        ArrayList<String> detallesRetorno = new ArrayList<>();

        System.out.println("\n====== DIAGRAMA DE GANTT (SJF NO APROPIATIVO) ======");
        System.out.print("Línea de tiempo: ");

        // Lista donde guardaremos el orden real en que se ejecutan
        ArrayList<Proceso> ordenEjecucion = new ArrayList<>();

        // Procesamos hasta que hayamos seleccionado todos los procesos
        while (ordenEjecucion.size() < totalProcesos) {
            Proceso elegido = null;

            // Recorremos todos los procesos para buscar al candidato óptimo
            for (Proceso p : tablaIngreso) {
                // Filtramos únicamente los que ya llegaron al sistema
                if (p.tiempoLlegada <= tiempoActual) {
                    if (elegido == null) {
                        elegido = p;
                    }
                    // Si encontramos otro listo con ráfaga menor, lo cambiamos
                    else if (p.rafaga < elegido.rafaga) {
                        elegido = p;
                    }
                    // Si tienen la misma ráfaga, desprecintamos por orden de llegada (FIFO)
                    else if (p.rafaga == elegido.rafaga && p.tiempoLlegada < elegido.tiempoLlegada) {
                        elegido = p;
                    }
                }
            }

            // Manejo del tiempo ocioso de la CPU si nadie ha llegado aún
            if (elegido == null) {
                tiempoActual++;
                continue;
            }

            // Añadimos el proceso seleccionado al orden de ejecución y lo removemos de los pendientes
            ordenEjecucion.add(elegido);
            tablaIngreso.remove(elegido);

            // Cálculos de tiempos para la tabla del profesor
            int tiempoInicio = tiempoActual;
            int tiempoFin = tiempoInicio + elegido.rafaga;

            int tiempoEspera = tiempoInicio - elegido.tiempoLlegada;
            int tiempoRetorno = tiempoFin; // Tiempo de Fin exacto del gráfico

            sumaEspera += tiempoEspera;
            sumaRetorno += tiempoRetorno;

            detallesEspera.add(elegido.nombre + " = (" + tiempoInicio + "-" + elegido.tiempoLlegada + ") = " + tiempoEspera);
            detallesRetorno.add(elegido.nombre + " = " + tiempoRetorno);

            System.out.print("| " + elegido.nombre + " (" + tiempoInicio + " a " + tiempoFin + ") ");

            // El reloj avanza la ráfaga completa
            tiempoActual = tiempoFin;
        }
        System.out.println("|");

        // IMPRESIÓN DE RESULTADOS
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
