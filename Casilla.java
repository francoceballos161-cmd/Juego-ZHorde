// Casilla: clase concreta, independiente (no hereda de nada)
// Representa una celda del tablero: su tipo, fila y columna

public class Casilla {

    protected String tipo; // "vacio", "obstaculo", "caja", "inicioPersonaje", "inicioMonstruo"
    protected int fila;
    protected int columna;

    public Casilla(String tipo, int fila, int columna) {
        this.tipo = tipo;
        this.fila = fila;
        this.columna = columna;
    }

    public String getTipo() {
        return this.tipo;
    }

    public int getFila() {
        return this.fila;
    }
    public int getColumna() {
        return this.columna;
    }
    public void setTipo(String nuevoTipo) {
        this.tipo = nuevoTipo;
    }

    // Devuelve true si esta casilla bloquea el paso y la linea de vision
    // Aplica tanto a Obstaculos como a Cajas sin abrir, segun el manual.
    public boolean bloquea() { // Boolean solo puede valer "true" o "false"
        return this.tipo.equals("obstaculo") || this.tipo.equals("caja");
    }
}
