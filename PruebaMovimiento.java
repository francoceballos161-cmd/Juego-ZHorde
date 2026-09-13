import java.util.List;
import java.util.ArrayList;

public class PruebaMovimiento {
    public static void main(String[] args) {
        Escenario mapa = new Escenario(6, 5);
        mapa.inicializarVacio();

        // Mismos obstaculos que en PruebaVision
        mapa.getCasilla(2, 1).setTipo("obstaculo"); // auto (parte trasera)
        mapa.getCasilla(3, 1).setTipo("obstaculo"); // auto (parte delantera)
        mapa.getCasilla(4, 3).setTipo("obstaculo"); // barriles/basura

        // Un Monstruo parado en (3,2), para probar el caso de "ocupada por Monstruo"
        List<Monstruo> monstruos = new ArrayList<>();
        monstruos.add(new Monstruo(4, 4, 4, 3, 3, 3, 2));

        int filaJugador = 4, columnaJugador = 1;

        // Caso 1: casilla vecina libre -> deberia ser valido
        boolean caso1 = mapa.esMovimientoValido(filaJugador, columnaJugador, 4, 2, monstruos);
        System.out.println("Mover a (4,2) libre (esperado true): " + caso1);

        // Caso 2: casilla vecina con Obstaculo -> deberia ser invalido
        boolean caso2 = mapa.esMovimientoValido(filaJugador, columnaJugador, 3, 1, monstruos);
        System.out.println("Mover a (3,1) obstaculo (esperado false): " + caso2);

        // Caso 3: casilla ocupada por un Monstruo -> deberia ser invalido
        boolean caso3 = mapa.esMovimientoValido(4, 2, 3, 2, monstruos);
        System.out.println("Mover a (3,2) ocupada por Monstruo (esperado false): " + caso3);

        // Caso 4: casilla fuera del tablero -> deberia ser invalido
        boolean caso4 = mapa.esMovimientoValido(0, 0, -1, 0, monstruos);
        System.out.println("Mover a (-1,0) fuera del tablero (esperado false): " + caso4);

        // Caso 5: costo de un paso ortogonal vs diagonal
        System.out.println("Costo ortogonal (4,1)->(4,2) (esperado 2): " + mapa.costoDePaso(4, 1, 4, 2));
        System.out.println("Costo diagonal (4,1)->(3,0) (esperado 3): " + mapa.costoDePaso(4, 1, 3, 0));
    }
}