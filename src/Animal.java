public class Animal {

    private AnimalType kind;
    private String name;
    int points;

    public Animal(AnimalType kind, String name) {
        this.kind = kind;
        this.name = name;
        this.points = 0;
    }

    int getPoints() {
        return points;
    }

    String getName() {
        return name;
    }

    AnimalType getKind() {
        return kind;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getAnimalInfo() {
        return this.getKind() + " " + this.getName() + " " + this.getPoints();
    }


    public void makeSound() {
        System.out.println(kind.sound);
    }


    public void eat(Food food) {
        points += kind.eat(food);
    }

}
