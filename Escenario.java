// Escenario: clase concreta, independiente (no hereda de nada)
// Tiene (contiene) una matriz de objetos Casilla — no es un tipo de Casilla
public class Escenario {

    protected Casilla[][] grid;
    protected int filas;
    protected int columnas;

    public Escenario (int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.grid = new Casilla[filas][columnas];
    }

    public void inicializarVacio() {
         for (int f = 0; f < this.filas; f++) {
            for (int c = 0; c < this.columnas; c++) {
            this.grid[f][c] = new Casilla("vacio", f, c);
            }
        }
    }

    public Casilla getCasilla(int fila, int columna) {
        return this.grid[fila][columna];
    }

    // Calcula el costo minimo de MOviemiento (o Alcance) entre dos posiciones del grid,
    // Segun la regla del manual: diagonal cuesta 3, ortogonal cuesta 2
    public int calcularDistancia(int fila1, int columna1, int fila2, int columna2) {

        // Mathabs(x) devuelve el valor absoluto de x (siempre positivo, sin importar el sigo)
        int diferenciaFila = Math.abs(fila2 - fila1);
        int diferenciaColumna = Math.abs(columna2 - columna1);

        // Math.min(a, b) devuelve el menor de los dos numeros
        int movimientosDiagonales = Math.min(diferenciaFila, diferenciaColumna);

        // Lo que sobra del eje mas largo, despues de usar los diagonales posibles
        int movimientosOrtogonales = Math.abs(diferenciaFila - diferenciaColumna);

        // Costo total: cada diagonal cuesta 3, cada ortogonal cuesta 2
        int costoTotal = (movimientosDiagonales * 3) + (movimientosOrtogonales * 2);
        
        return costoTotal;
    }
    // Verifica si hay linea de vision entre dos casillas de la MISMA fila
    // Version simplificada ya que no sirve para columnas distintas ni diagonales
    public boolean hayLineaDeVisionMismaFila(int fila, int columnaOrigen, int columnaDestino) {
        boolean hayVision = true;

        // Determinamos cual clumna es menor y cual mayor, para recorrer siempre "de menor a mayor"
        int inicio = Math.min(columnaOrigen, columnaDestino) + 1;
        int fin = Math.max(columnaOrigen, columnaDestino) -1;
        
        for (int col = inicio; col <= fin; col++) {
            Casilla intermedia = this.getCasilla(fila, col);
            if (intermedia.bloquea()) {
                hayVision = false;
            }
        }
        return hayVision;
    }
}