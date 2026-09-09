public abstract class Entidad {

    // Atributo: se declara con un modificador de acceso, el tipo de dato y el nombre
    protected int vida;
    protected int movimiento;
    protected int ataque;
    protected int defensa;
    protected int alcance;
    protected int fila;
    protected int columna;

    // Constructor: se ejecuta cuando creas un Personaje nuevo y recibe valores iniciales
    public Entidad(int vidaInicial, int movimientoInicial, int ataqueInicial, int defensaInicial, int alcanceInicial, int filaInicial, int columnaInicial) {
        this.vida = vidaInicial;
        this.movimiento = movimientoInicial;
        this.ataque = ataqueInicial;
        this.defensa = defensaInicial;
        this.alcance = alcanceInicial;
        this.fila = filaInicial;
        this.columna = columnaInicial;
    }

    // Getter: metodo publico para leer el valor desde afuera de la clase
    public int getVida() {
        return this.vida;
    }   
    public int getMovimiento() {
        return this.movimiento;
    }
    public int getAtaque() {
        return this.ataque;
    }
    public int getDefensa() {
        return this.defensa;
    }
    public int getAlcance() {
        return this.alcance;
    }
     public int getColumna() {
        return this.columna;
    }
    public int getFila() {
        return this.fila;
    }
     // Setter: metodo publico para modificar el valor desde afuera de la clase
    public void setFila(int nuevaFila) {
        this.fila = nuevaFila;
    }
    public void setColumna(int nuevaColumna) {
        this.columna = nuevaColumna;
    }
}    