public class Main {
    public static void main(String[] args) {
        AnimalsDoing animalsDoing = new AnimalsDoing();
        Menu menu = new Menu(animalsDoing);
        menu.showMenu();
    }
}