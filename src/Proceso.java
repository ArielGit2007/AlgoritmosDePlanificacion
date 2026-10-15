public class Proceso {
    public String nombre;
    public int rafaga;
    public int tiempoLlegada;
    public int tiempoEspera;
    public int tiempoRetorno;

    // 1. Constructor vacío (Obligatorio para tus métodos que usan Scanner)
    public Proceso() {
    }

    // 2. Constructor con parámetros (Obligatorio para que funcione el Menú del Main)
    public Proceso(String nombre, int rafaga, int tiempoLlegada) {
        this.nombre = nombre;
        this.rafaga = rafaga;
        this.tiempoLlegada = tiempoLlegada;
    }
}
