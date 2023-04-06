import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;

public class AnimalFactory {

    Scanner scanner = new Scanner(System.in);

    public Optional<Animal> createAnimal(String animalType) {
        String kind;
        String name;

        System.out.println(Arrays.toString(AnimalType.values()));
        kind = scanner.nextLine().toUpperCase();

        Optional<AnimalType> animalTypeOptional = Arrays.stream(AnimalType.values())
                .filter(type -> type.name().equals(kind))
                .findAny();

        if (animalTypeOptional.isPresent()) {
            System.out.println("Name your " + animalTypeOptional.get().name());
            name = scanner.nextLine();
            Animal animal = new Animal(animalTypeOptional.get(), name);
            return Optional.of(animal);
        } else {
            return Optional.empty();
        }
    }
}
