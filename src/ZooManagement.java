import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.sql.*;


public class ZooManagement {
    Scanner scanner = new Scanner(System.in);
    public List<Animal> animals = new ArrayList<>();
    public List<Food> foods = new ArrayList<>();
    Animal2Factory animalFactory = new Animal2Factory();


    public void run() {
        String input = "";
        System.out.println("Welcome to the Virtual zoo");
        System.out.println("in order to create new animal press '1'");
        System.out.println("in order to feed animal press'2'");
        System.out.println("in order to print information about animal press'3'");
        System.out.println("to see the weakest animal in the zoo press '4'");
        System.out.println("to see the strongest animal in the zoo press '5'");
        System.out.println("to see all the statistics about the animals in the zoo press '6'");
        System.out.println("to see all the animals in the zoo press '7'");
        System.out.println("in order to exit the code press '8'");
        while (!input.equals("10")) {
            input = scanner.nextLine();
            switch (input) {

                case "1" -> createAnimal();
                case "2" -> feedAnimals();
                case "3" -> getAnimal();
                case "4" -> printWeakest();
                case "5" -> printStrongest();
                case "6" -> animalStatistics();
                case "7" -> printAnimals();
                case "8" -> createFood();
                case "9" -> printFoods();

            }
        }
    }

    public void createAnimal() {
        animals.add(animalFactory.createAnimal(scanner.nextLine()));

    }

    public void printWeakest() {
        animals.stream()
                .min(Comparator.comparingInt(Animal::getPoints))
                .ifPresent(weakest -> System.out.println("The weakest animal is: " + weakest.getName()));
    }
    public void feedAnimals() {
        System.out.println("what animal you want to feed ");
        animals.forEach(animal -> System.out.println(animal.getName()));
        String animalName = scanner.nextLine();
        System.out.println("what food you want to give the animals");
        foods.forEach(food -> System.out.println(food.getName()));
        String foodName = scanner.nextLine();
        animals.stream()
                .filter(animal -> animalName.equals(animal.getName()))
                .findAny()
                .ifPresent(animal -> foods
                        .stream()
                        .filter(food -> foodName.equals(food.getName()))
                        .findAny()
                        .ifPresent(animal::eat));
    }


    private void printAnimals() {
        System.out.println("List of animals:");
        animals.forEach(animal -> System.out.println(animal.getAnimalInfo()));
    }

    private void animalStatistics() {
        int currentPoints;
        int totalPoints = 0;
        for (Animal animal : animals) {
            currentPoints = animal.getPoints();
            totalPoints += currentPoints;
        }

        System.out.println("Number of animals of each kind:");
        System.out.println("the number of the animals in the zoo is : " + animals.size());
        System.out.print("the weakest animal in the zoo ");
        printWeakest();
        System.out.println("the strongest animal in the zoo");
        printStrongest();
        System.out.println(("the average of the points in the zoo: " + totalPoints / animals.size()));
    }

    private void printStrongest() {
        animals.stream()
                .max(Comparator.comparingInt(Animal::getPoints))
                .ifPresent(max -> System.out.println("The weakest animal is: " + max.getName()));
    }
    private void getAnimal() {
        System.out.println("enter the animal name");
        String animalName = scanner.nextLine();
         animals.stream()
                .filter(animal -> animalName.equals(animal.getName()))
                 .findAny()
                 .ifPresent(animal2 -> System.out.println("here is the information about the animal "+animal2.getAnimalInfo()));

    }



    private void createFood() {
        System.out.println("what food you want to add to the menu");
        String foodName = scanner.nextLine();
        System.out.println("how much " + foodName + " would you like to order");
        int amount = scanner.nextInt();
        Food food = new Food(foodName, amount);
        foods.add(food);
    }

    private void printFoods() {
        foods.forEach(food -> System.out.println(food.getFoodInfo()));
    }

}
