public class AngelHerrera extends Personaje {
    
    // Constructor de la subclase: llama al constructor del padre con "super(...)"
    public AngelHerrera(int fila, int columna) {
        super(5, 1, 1, 1, 2, fila, columna); // Valores base: vid=5 mov=1, ataq=1, def=1, alc=2 
        aplicarModificadorInicial(); // Se aplica apenas nace    
    }

    @Override  // le avisa a Java que este metodo reemplaza al abstracto de la clase padre
    public void aplicarModificadorInicial() {
        // aca cada personaje modifica sus propios valores
        this.defensa = 2; // Angel Herrera sube a 2 su defensa
    }

    @Override 
    public void habilidadUnica() {
        // aca va el comportamiento unico de este personaje
        System.out.println("Ignorar monstruos una vez por");
    }
}