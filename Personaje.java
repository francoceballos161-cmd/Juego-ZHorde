public abstract class Personaje extends Entidad {

    // Constructor: se ejecuta cuando creas un Personaje nuevo y recibe valores iniciales
    public Personaje(int vidaInicial, int movimientoInicial, int ataqueInicial, int defensaInicial, int alcanceInicial, int filaInicial, int columnaInicial) {
        super(vidaInicial, movimientoInicial, ataqueInicial, defensaInicial, alcanceInicial, filaInicial, columnaInicial);
    }
     // Setter: metodo publico para modificar el valor desde afuera de la clase
    public void setFila(int nuevaFila) {
        this.fila = nuevaFila;
    }
    public void setColumna(int nuevaColumna) {
        this.columna = nuevaColumna;
    }
    // Metodo abstracto: cada Personaje concreto debe implementar esto a su manera
    public abstract void aplicarModificadorInicial();

    public abstract void habilidadUnica();
}    