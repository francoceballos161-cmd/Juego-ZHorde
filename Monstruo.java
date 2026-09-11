// Monstruo: clase concreta, hija de Entidad (extends Entidad).
// El constructor solo envía sus valores iniciales a Entidad mediante super(...).
public class Monstruo extends Entidad {

    public Monstruo(int vidaInicial, int movimientoInicial, int ataqueInicial, int defensaInicial, int alcanceInicial, int filaInicial, int columnaInicial) {
        super(vidaInicial, movimientoInicial, ataqueInicial, defensaInicial, alcanceInicial, filaInicial, columnaInicial);
    }    
}