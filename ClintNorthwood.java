public class ClintNorthwood extends Personaje {
    
    public ClintNorthwood(int fila, int columna) {
        super(5, 1, 1, 1, 2, fila, columna);
        aplicarModificadorInicial();
    }
    
    @Override 
    public void aplicarModificadorInicial() {
        this.ataque = 2;
    }

    @Override 
    public void habilidadUnica() {
        System.out.println("Si Recibe 2 o mas puntos de daño, devulve 1 punto de daño");
    }
}