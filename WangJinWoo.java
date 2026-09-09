public class WangJinWoo extends Personaje {
    
    public WangJinWoo(int fila, int columna) {
        super(4, 1, 1, 1, 2, fila, columna);
        aplicarModificadorInicial();
    }
    
    @Override 
    public void aplicarModificadorInicial() {
    this.movimiento = 3;
    }

    @Override 
    public void habilidadUnica() {
    // Habilidad enfocada en el Mercado todavia no programado. Lo dejamos para despues
    System.out.println("Cada compra en el Mercado, gana 2 Monedas extra de presupuesto");
    }
}