public class LoraCrawford extends Personaje {

    public LoraCrawford (int fila, int columna) {
        super(5, 1, 1, 1, 2, fila, columna);
        aplicarModificadorInicial();
    }

    @Override 
    public void aplicarModificadorInicial() {
        this.alcance = 3;
    }

    @Override 
    public void habilidadUnica() {
        System.out.println("Una vez por nivel, transforma un dado de Energia de 1 a en 6");    
    }
}