public class Prueba {
    public static void main(String[] args) {
        AngelHerrera angel = new AngelHerrera(0, 0);
        System.out.println("Vida: " + angel.getVida());
        System.out.println("Defensa: " + angel.getDefensa());
        angel.habilidadUnica();

    Monstruo zombie = new Monstruo(4, 4, 5, 3, 3, 0, 0);
        System.out.println("Vida del monstruo: " + zombie.getVida());
    }
}    