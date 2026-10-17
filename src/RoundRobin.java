import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RoundRobin {
    FIFO fifo = new FIFO();
    private int quantum;

    public RoundRobin(int quantum) {
        this.quantum = quantum;
    }

    public void ejecutarRoundRobin() {
        int tiempoActual = 0;
        double sumaEspera = 0;
        double sumaRetorno = 0;
        int totalProcesos = fifo.tablaIngreso.size();
        fifo.prepararColaPlanificacion();

        Map<Proceso, Integer> rafagasOriginales = new HashMap<>();
        for (Proceso proceso : fifo.tablaIngreso) {
            rafagasOriginales.put(proceso, proceso.rafaga);
        }

        ArrayList<String> detallesEspera = new ArrayList<>();
        ArrayList<String> detallesRetorno = new ArrayList<>();

        System.out.println("\n====== DIAGRAMA DE GANTT (ROUND ROBIN) ======");
        System.out.print("Línea de tiempo: ");

        while (!fifo.tablaIngreso.isEmpty()) {
            Proceso actual = fifo.tablaIngreso.remove(0);

            if (tiempoActual < actual.tiempoLlegada) {
                tiempoActual = actual.tiempoLlegada;
            }

            int tiempoInicio = tiempoActual;
            int tiempoFin;

            if (actual.rafaga > quantum) {
                tiempoFin = tiempoInicio + quantum;
                actual.rafaga -= quantum;
                fifo.tablaIngreso.add(actual);
            } else {
                tiempoFin = tiempoInicio + actual.rafaga;
                actual.rafaga = 0;

                int tiempoRetorno = tiempoFin;
                int tiempoEspera = tiempoRetorno
                        - actual.tiempoLlegada
                        - rafagasOriginales.get(actual);

                sumaEspera += tiempoEspera;
                sumaRetorno += tiempoRetorno;
                detallesEspera.add(actual.nombre + ": " + tiempoEspera);
                detallesRetorno.add(actual.nombre + ": " + tiempoRetorno);
            }

            System.out.print("[" + actual.nombre + "] ");
            System.out.print(tiempoInicio + " -> " + tiempoFin + " | ");
            tiempoActual = tiempoFin;
        }

        System.out.println("\nTIEMPO DE ESPERA");
        for (String detalle : detallesEspera) {
            System.out.println(detalle);
        }

        System.out.println("\nTIEMPO DE RETORNO");
        for (String detalle : detallesRetorno) {
            System.out.println(detalle);
        }

        System.out.println("\n\nTiempo promedio de espera: " + (sumaEspera / totalProcesos)+" ut");
        System.out.println("Tiempo promedio de retorno: " + (sumaRetorno / totalProcesos)+" ut");
    }
}
