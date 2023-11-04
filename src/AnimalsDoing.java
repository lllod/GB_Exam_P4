import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AnimalsDoing {
    private final List<Animals> listAnimals;
    private static final String FILE_PATH = "animals_list.txt";

    public AnimalsDoing() {
        listAnimals = new ArrayList<>();
        loadAnimals();
    }

    public void addAnimal(Animals animal) {
        listAnimals.add(animal);
        saveAnimals();
    }

    public void printAnimalsCommands(String name) {
        for (Animals animal : listAnimals) {
            if (animal.getName().equals(name)) {
                animal.printCommands();
                return;
            }
        }
        System.out.println("Животное " + name + "не найдено");
    }

    public void addAnotherCommands(String name, String commands) {
        for (Animals animals : listAnimals) {
            if (animals.getName().equals(name)) {
                String anotherCommands = animals.getCommands() + ", " + commands;
                animals.addCommands(anotherCommands);
                saveAnimals();
                System.out.println("Животное обучено новым командам!");
                return;
            }
        }
        System.out.println("Животное " + name + "не найдено");
    }

    public void animalsList() {
        try {
            File file = new File(FILE_PATH);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String animalsList = scanner.nextLine();
                System.out.println(animalsList);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Файл с реестром животных не найден!");
        }
    }

    public void loadAnimals() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 4) {
                    String animalType = data[0];
                    String name = data[1];
                    String date = data[2];
                    String commands = String.join(",", Arrays.copyOfRange(data, 3, data.length));
                    Animals animals;
                    switch (animalType) {
                        case "Dog" -> animals = new Dogs(name, commands, date);
                        case "Cat" -> animals = new Cats(name, commands, date);
                        case "Hamster" -> animals = new Hamsters(name, commands, date);
                        case "Horse" -> animals = new Horses(name, commands, date);
                        case "Camel" -> animals = new Camels(name, commands, date);
                        case "Donkey" -> animals = new Donkeys(name, commands, date);
                        default -> {
                            System.out.println("Неподходящий тип животного: " + animalType);
                            continue;
                        }
                    }
                    listAnimals.add(animals);
                } else {
                    System.out.println("Данные некорректны!");
                }
            }
            System.out.println("Данные загружены");
        } catch (IOException e) {
            System.out.println("Ошибка: " + e);
        }
    }

    private void saveAnimals() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Animals animal : listAnimals) {
                String animalType = animal.getClass().getSimpleName();
                String name = animal.getName();
                String date = animal.getDate();
                String commands = animal.getCommands().replaceAll(",\\s+", ",");

                String line = animalType + " | Name: " + name + " | Birthday: " + date + " | Commands: " + commands;
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Данные сохранены");
        } catch (IOException e) {
            System.out.println("При сохранении данных возникла ошибка: " + e.getMessage());
        }
    }
}
