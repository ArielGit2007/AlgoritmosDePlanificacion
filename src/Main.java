import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Creamos una única lista central de procesos en el Main
        java.util.ArrayList<Proceso> procesosCompartidos = new java.util.ArrayList<>();
        Scanner teclado = new Scanner(System.in);

        System.out.print("¿Cuántos procesos deseas ingresar? ");
        int cantidad = teclado.nextInt();

        System.out.println("\n--- INGRESO DE PROCESOS ---");
        // Usamos un objeto temporal solo para capturar la entrada por consola
        FIFO capturador = new FIFO();
        for (int i = 0; i < cantidad; i++) {
            System.out.println("\n[Proceso " + (i + 1) + " de " + cantidad + "]");
            capturador.IngresarProceso();
        }

        // Guardamos los procesos ingresados en nuestra lista central
        procesosCompartidos.addAll(capturador.tablaIngreso);

        boolean salir = false;
        while (!salir) {
            System.out.println("\n=================================");
            System.out.println("   MENÚ ALGORITMOS PLANIFICACIÓN");
            System.out.println("=================================");
            System.out.println("1. Ejecutar simulación FIFO");
            System.out.println("2. Ejecutar simulación SJF");
            System.out.println("3. Ejecutar simulación Priority");
            System.out.println("4. Salir");
            System.out.print("Elija una opción: ");
            int opcion = teclado.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("\n>>> EJECUTANDO FIFO <<<");
                    FIFO simuladorFIFO = new FIFO();
                    // Clonamos la lista para que FIFO no altere los datos originales
                    for (Proceso p : procesosCompartidos) {
                        simuladorFIFO.tablaIngreso.add(new Proceso(p.nombre, p.rafaga, p.tiempoLlegada, p.prioridad));
                    }
                    simuladorFIFO.prepararColaPlanificacion();
                    simuladorFIFO.ejecutarFIFO();
                    break;

                case 2:
                    System.out.println("\n>>> EJECUTANDO SJF <<<");
                    SJF simuladorSJF = new SJF();
                    // Clonamos la lista para que SJF no altere los datos originales
                    for (Proceso p : procesosCompartidos) {
                        simuladorSJF.tablaIngreso.add(new Proceso(p.nombre, p.rafaga, p.tiempoLlegada, p.prioridad));
                    }
                    simuladorSJF.ejecutarSJF();
                    break;
                case 3:
                    System.out.println("\n>>> EJECUTANDO PRIORITY <<<");
                    Priority simuladorPriority = new Priority();
                    for(Proceso p : procesosCompartidos) {
                        simuladorPriority.tablaIngreso.add(new Proceso(p.nombre, p.rafaga, p.tiempoLlegada, p.prioridad));
                    }
                    simuladorPriority.ejecutarPriority();
                    break;
                case 4:
                    salir = true;
                    System.out.println("Programa finalizado.");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        }
        teclado.close();
    }
}

