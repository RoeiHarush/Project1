import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;

public class Animal2Factory {

    Scanner scanner = new Scanner(System.in);

    //TODO: Make createAnimal return Optional<Animal2>
    public Animal createAnimal(String animalType) {

        String kind;
        String name;
        System.out.println(Arrays.toString(AnimalType.values()));
        kind = scanner.nextLine();

        Optional<AnimalType> animalTypeOptional =
                Arrays.stream(AnimalType.values())
                        .filter(type -> type.name().equals(kind))
                        .findAny();

        if (animalTypeOptional.isPresent()) {
            System.out.println("name your " + animalTypeOptional.get().name());
            name = scanner.nextLine();
            return new Animal(animalTypeOptional.get(), name);
        } else {
            //TODO: Remove this
            throw new RuntimeException();
        }
    }
}
