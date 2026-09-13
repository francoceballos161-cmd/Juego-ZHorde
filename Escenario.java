// Escenario: clase concreta, independiente (no hereda de nada)
// Tiene (contiene) una matriz de objetos Casilla — no es un tipo de Casilla
import java.util.List;

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
    // Centro a centro, sirve como aproximacion rapida 
    // No respeta la regla del manual (ver haLineaDeVision)

    public boolean hayLineaDeVisionCentroACentro(int fila1, int columna1, int fila2, int columna2) {
        int dFila = Math.abs(fila2 - fila1);
        int dColumna = Math.abs(columna2 - columna1);
        int signoFila = (fila1 < fila2) ? 1 : -1;
        int signoColumna = (columna1 < columna2) ? 1 : -1;

        int fila = fila1;
        int columna = columna1;
        int error = dFila - dColumna;

        while (fila != fila2 || columna != columna2) {
            int error2 = 2 * error;

            if (error2 > -dColumna) {
                error -= dColumna;
                fila += signoFila;
            }
            if (error2 < dFila) {
                error += dFila;
                columna += signoColumna;
            }
            if (fila == fila2 && columna == columna2) {
                break;
            }
            if (this.getCasilla(fila, columna).bloquea()) {
                return false;
            }
        }
        return true;
    }

    // Verion 2 fiel al manual "Si puedes trazar una linea desde CUALQUIER esquina
    // de tu casilla hasta CUALQUIER esquina de la casilla del monstruo, sin pasar
    // por el INTERIOR de una casilla de Obstaculo, tienes Linea de Vision."
    // Probamos las 4x4 16 combinaciones de esquinas; con UNA sola de vision libre
    public boolean hayLineaDeVision(int fila1, int columna1, int fila2, int columna2) {
        double [][] esquinas = {{0,0},{1,0},{0,1},{1,1}}; //(dx, dy) relativo a la celda
        
        for (double[] e1: esquinas) {
            for (double[] e2: esquinas) {
                double x1 = columna1 + e1[0];
                double y1 = fila1 + e1[1];
                double x2 = columna2 + e2[0];
                double y2 = fila2 + e2[1];

                if (!combinacionBloqueada(x1, y1, x2, y2, fila1, columna1, fila2, columna2)) {
                    return true; // Encontramos una linea libre: ya hay vision
                }
            }
        }
        return false;
    }

    // Revisa si el segmento (x1,y1)-(x2,y2) atraviesa el INTERIOR de algun Obstaculo
    // las casillas de origen y destino no cuentan coo obstaculo para si mismas
    private boolean combinacionBloqueada(double x1, double y1, double x2, double y2,
        int filaOrigen, int columnaOrigen, int filaDestino, int columnaDestino) {

            for (int f = 0; f < this.filas; f++) {
                for (int c = 0; c < this.columnas; c++) {
                    if ((f == filaOrigen && c == columnaOrigen) || (f == filaDestino && c == columnaDestino)) {
                        continue;
                    }
                    if (this.getCasilla(f, c).bloquea() && segmentoCruzaCasilla(x1, y1, x2, y2, f, c)) {
                        return true; 
                    }
                }
            }
            return false;
        }    
        
        // Lianf-Barsky: True solo si el segmento entra de verdad al INTERIOR del cuadrado
        // de la casilla (fila,columna) -- si solo lo toca por el borde/esquina, no cuenta
        private boolean segmentoCruzaCasilla(double x1, double y1, double x2, double y2,int fila, int columna) {
            double xmin = columna, xmax = columna + 1;
            double ymin = fila, ymax = fila + 1;
            double dx = x2 - x1;
            double dy = y2 - y1;
            
            double[] p = {-dx, dx, -dy, dy};
            double[] q = {x1 - xmin, xmax - x1, y1 - ymin, ymax - y1};

            double u1 = 0.0, u2 = 1.0;
            for (int i = 0; i < 4; i++) {
                if (p[i] == 0) {
                    if (q[i] < 0) {
                        return false; // paralelo al borde y afuera, asi no cruza
                    }
                    continue;
                }
                double r = q[i] / p[i];
                if (p[i] < 0) {
                    u1 = Math.max(u1, r);
                } else {
                    u2 = Math.min(u2, r);
                }
            }
            if (u1 > u2) {
                return false;
            }
            return (u2 - u1) > 1e-9; // Cruce coon largo realm no un simple roce
        }

        // Revisa si alguno de los Monstruos de la lista esta parado en (fila, columna)
        public boolean estaOcupadaPorMonstruo(int fila, int columna, List<Monstruo> monstruos) {
            for (Monstruo m: monstruos) {
                if (m.getFila() == fila && m.getColumna() == columna) {
                    return true;
                }
            }
            return false;
        }

        // Valida UN solo paso de MOviemiento (a una casilla vecina: ortogonal o diagonal)
        // Segun el manual: no podes terminar el movimiento en una casilla ocupada
        // Por un Obstaculo o caja sin abrir, ni por un MOnstruo

        public boolean esMovimientoValido(int filaOrigen, int columnaOrigen, int filaDestino, int columnaDestino,
            List<Monstruo> monstruos) {
                int dFila = Math.abs(filaDestino - filaOrigen);
                int dColumna = Math.abs(columnaDestino - columnaOrigen);

                // Tiene que ser una casilla vecina a lo sumo 1 de distancia en cada eje
                // Y no puede ser la misma casilla donde ya estas parado
                if (dFila > 1 || dColumna > 1 || (dFila == 0 && dColumna == 0)) {
                    return false;
                }

                // Tiene que estar dentro del tablero
                if (filaDestino < 0 || filaDestino >= this.filas || columnaDestino < 0 || columnaDestino >= this.columnas) {
                    return false;
                }

                // No puede ser Obstaculo ni Caja sin abrir
                if (this.getCasilla(filaDestino, columnaDestino).bloquea()) {
                    return false;
                }

                // NO puede haber un Monstruo parado ahi
                if (this.estaOcupadaPorMonstruo(filaDestino, columnaDestino, monstruos)) {
                    return false;
                }

                return true;
            }

            // COsto en Puntos de Movimiento de UN solo paso (2 ortogonal, 3 diagonal)
            // Reutiliza calcularDistancia, que ya da el resultado correcto para un vecino
            public int costoDePaso(int filaOrigen, int columnaOrigen, int filaDestino, int columnaDestino) {
                return this.calcularDistancia(filaOrigen, columnaOrigen, filaDestino, columnaDestino);
            }        
        }
              
