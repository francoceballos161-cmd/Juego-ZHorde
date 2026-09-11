public class Prueba {
    public static void main(String[] args) {
        AngelHerrera angel = new AngelHerrera(0, 0);
        System.out.println("Vida: " + angel.getVida());
        System.out.println("Defensa: " + angel.getDefensa());
        angel.habilidadUnica();

    Monstruo zombie = new Monstruo(4, 4, 5, 3, 3, 0, 0);
        System.out.println("Vida del monstruo: " + zombie.getVida());

    zombie.recibirDano(7);
    System.out.println("Vida del monstruo tras recibir dano: " + zombie.getVida());    
    
    Escenario mapa1 = new Escenario (6, 5);
    mapa1.inicializarVacio();
    System.out.println("El escenario tiene " + mapa1.filas + " filas y " + mapa1.columnas + " columnas");
    
    // Objetos, cajas y punto de partida en el mapa de nivel 1:
    
    Casilla c = mapa1.getCasilla(0, 0);
    c.setTipo("caja");
    System.out.println("Casilla 0,0: " + c.getTipo());

    c = mapa1.getCasilla(2, 3);
    c.setTipo("caja");
    System.out.println("Casilla 2,3: " + c.getTipo());

    c = mapa1.getCasilla(2, 1);
    c.setTipo("obstaculo"); // mitad trasera del auto
    System.out.println("Casilla 2,1: " + c.getTipo());

    c = mapa1.getCasilla(3, 1);
    c.setTipo("obstaculo"); // mitad delantera del auto
    System.out.println("Casiila 3,1: " + c.getTipo());

    c = mapa1.getCasilla(4, 3);
    c.setTipo("obstaculo"); // tachos
    System.out.println("Casilla 4,3: " + c.getTipo());

    c = mapa1.getCasilla(5, 0);
    c.setTipo("inicioPersonaje"); // bandera
    System.out.println("Casilla 5, 0: " + c.getTipo());

    Casilla prueba = mapa1.getCasilla(0, 0);
    System.out.println("Casilla en 0,0: " + prueba);

    int distancia = mapa1.calcularDistancia(0, 0, 3, 4);
    System.out.println("Distancia de (0,0) a (3,4): " + distancia);

    boolean hayLineaDeVision = true; // asumimos que sí hay, hasta encontrar un obstáculo

    for (int col = 1; col <= 3; col++) {
    Casilla intermedia = mapa1.getCasilla(2, col);
        if (intermedia.bloquea()) {
        hayLineaDeVision = false;
    }
}

    System.out.println("¿Hay línea de visión? " + hayLineaDeVision);

    }
}    