import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private final AnimalsDoing animalsDoing;
    private final Scanner scanner;
    private Counter count;

    public Menu(AnimalsDoing animalsDoing) {
        this.animalsDoing = animalsDoing;
        scanner = new Scanner(System.in);
        count = new Counter();
    }

    public void showMenu() {
        while (true) {
            try {
                System.out.println();
                System.out.println("Реестр питомника:");
                System.out.println("1. Добавить животное");
                System.out.println("2. Посмотреть команды, которым обучено животное");
                System.out.println("3. Обучить животное другим командам");
                System.out.println("4. Посмотреть всех животных");
                System.out.println("5. Посмотреть общее число животных");
                System.out.println("0. Выйти из реестра");
                System.out.println("Выбирете номер пункта:");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> addAnimal();
                    case 2 -> printAnimalsCommands();
                    case 3 -> addAnotherCommads();
                    case 4 -> animalsDoing.animalsList();
                    case 5 -> count.print();
                    case 0 -> {
                        System.out.println("Реестр закрыт!");
                        return;
                    }
                    default -> System.out.println("Введенный номер некорректен! Попробуйте еще раз");
                }
            } catch (InputMismatchException e) {
                System.out.println("Неверный формат ввода! Попробуйте еще раз");
                scanner.nextLine();
            }
        }
    }

    private void addAnimal() {
        System.out.println("Введите кличку животного:");
        String name = scanner.nextLine();
        System.out.println("Введите дату рождения животного в формате ГГГГ-ММ-ДД):");
        String date = scanner.nextLine();
        System.out.println("Введите команды, которым обучено животное:");
        String commands = scanner.nextLine();
        System.out.println("Выбирите тип животного:");
        System.out.println("1. Собака");
        System.out.println("2. Кошка");
        System.out.println("3. Хомяк");
        System.out.println("4. Лошадь");
        System.out.println("5. Верблюд");
        System.out.println("6. Осел");
        int animalType = scanner.nextInt();
        scanner.nextLine();
        Animals animal;
        switch (animalType) {
            case 1 -> animal = new Dogs(name, commands, date);
            case 2 -> animal = new Cats(name, commands, date);
            case 3 -> animal = new Hamsters(name, commands, date);
            case 4 -> animal = new Horses(name, commands, date);
            case 5 -> animal = new Camels(name, commands, date);
            case 6 -> animal = new Donkeys(name, commands, date);
            default -> {
                System.out.println("Неверный тип животного!");
                return;
            }
        }
        animalsDoing.addAnimal(animal);
        count.add();
        System.out.println("Животное добавлено в реестр под номером: " + count.getCount());
    }

    private void printAnimalsCommands() {
        System.out.println("Введите кличку животного:");
        String name = scanner.nextLine();
        animalsDoing.printAnimalsCommands(name);
    }

    private void addAnotherCommads() {
        System.out.println("Введите кличку животного:");
        String name = scanner.nextLine();
        System.out.println("Введите другие команды через запятую:");
        String commands = scanner.nextLine();
        animalsDoing.addAnotherCommands(name, commands);
        System.out.println("Животное обучено другим командам!");
    }





}