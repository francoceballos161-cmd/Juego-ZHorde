public class DwayneCooper extends Personaje {
    
    public DwayneCooper(int fila, int columna) {
        super(6, 1, 1, 1, 2, fila, columna);
        aplicarModificadorInicial();
    }
    
    @Override 
    public void aplicarModificadorInicial() {
    // Dwayne Cooper no modifica ningun atributo al entrar en juego
    // Su habilidad (robar un Objeto gratis) se implementara mas adelante
    // Cuando exista la clase Mercado
    }

    @Override 
    public void habilidadUnica() {
        System.out.println("robar objeto gratis + el bonus de +1 disparo al comprar armas");
    }
}