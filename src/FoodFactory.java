import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;

public class FoodFactory {

    Scanner scanner = new Scanner(System.in);
    public Optional<Food> createFood(String foodType) {
        String kind;
        int quantity;

        System.out.println(Arrays.toString(FoodType.values()));
        kind = scanner.nextLine().toUpperCase();

        Optional<FoodType> foodTypeOptional = Arrays.stream(FoodType.values())
                .filter(type -> type.name().equals(kind))
                .findAny();

        if (foodTypeOptional.isPresent()) {
            System.out.println("how much " + foodTypeOptional.get().name()+ "you want to order");
            quantity  = scanner.nextInt();
            Food food = new Food(foodTypeOptional.get(), quantity);
            return Optional.of(food);
        } else {
            return Optional.empty();
        }
    }

}
