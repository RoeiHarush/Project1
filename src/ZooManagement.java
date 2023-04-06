import java.sql.SQLException;
import java.util.*;

public class ZooManagement {
    Scanner scanner = new Scanner(System.in);
    public List<Animal> animals = new ArrayList<>();
    public List<Food> foods = new ArrayList<>();
    AnimalFactory animalFactory = new AnimalFactory();
    FoodFactory foodFactory = new FoodFactory();
    DbFoodManager dbFoodManager = new DbFoodManager("jdbc:postgresql://localhost:5432/zoo", "postgres", "roei");
    DbZooManager dbZooManager = new DbZooManager("jdbc:postgresql://localhost:5432/zoo", "postgres", "roei");


    public void run() throws SQLException {
        dbZooManager.retrieveAnimals(animals);
        dbFoodManager.retrieveFoods(foods);

        String input = "";
        System.out.println("Welcome to the zoo");
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
                case "11"-> orderFood();
            }
        }
    }


    public void createAnimal() {
        Optional<Animal> optionalAnimal = animalFactory.createAnimal(scanner.nextLine());

        if (optionalAnimal
                .isPresent()) {
            Animal newAnimal = optionalAnimal.get();
            while (animals.stream()
                    .anyMatch(animal -> animal.getName().equals(newAnimal.getName()))) {
                System.out.println("An animal with the name " + newAnimal.getName() + " already exists. Please choose a different name.");
                newAnimal.setName(scanner.nextLine());
            }
            animals.add(newAnimal);
            dbZooManager.insertAnimal(newAnimal);
        }
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
        String foodName = scanner.nextLine().toUpperCase();
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

    void animalStatistics() {
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
                .ifPresent(animal -> System.out.println("here is the information about the animal " + animal.getAnimalInfo()));
    }
    private void createFood() {
        Optional<Food> optionalFood = foodFactory.createFood(scanner.nextLine().toUpperCase());
        if (optionalFood.isPresent()) {
            Food food = optionalFood.get();
            if (!foods.contains(food)) {
                foods.add(food);
            } else {
                System.out.println("Food already exists!");
            }
        }
    }
    private void printFoods() {
        foods.forEach(food -> System.out.println(food.getFoodInfo()));
    }

    public void orderFood() {
        String foodName;
        int wantedQuantity;
        System.out.println("Existing food types:");
        foods.forEach(food-> System.out.println(food.getName()));
        System.out.println("enter wanted food to order");
        foodName = scanner.nextLine();
        System.out.println("what is your wanted quantity to order");
        wantedQuantity = scanner.nextInt();
        foods.stream().filter(food->foodName.equals(food.getName()))
                .findAny()
                .ifPresent(food -> food.order(wantedQuantity));

        }
}
