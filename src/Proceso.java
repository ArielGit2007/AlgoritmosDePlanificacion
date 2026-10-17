public class Proceso implements Comparable<Proceso>{
    public String nombre;
    public int rafaga;
    public int tiempoLlegada;
    public int prioridad;


    // 1. Constructor vacío (Obligatorio para tus métodos que usan Scanner)
    public Proceso() {
    }

    // 2. Constructor con parámetros (Obligatorio para que funcione el Menú del Main)
    public Proceso(String nombre, int rafaga, int tiempoLlegada, int prioridad) {
        this.nombre = nombre;
        this.rafaga = rafaga;
        this.tiempoLlegada = tiempoLlegada;
        this.prioridad = prioridad;
    }

    @Override
    public int compareTo(Proceso other) {
        return Integer.compare(prioridad, other.prioridad);
    }
}
