public class Cow extends Animal{
    public Cow(String name) {
        super("Cow", name, "Moo");
    }

    @Override
    public void eat(String food) {
        switch (food) {
            case "Banana" -> {
                System.out.println(super.getKind() + " is eating banana");
                points += 1;
            }
            case "Meat" -> System.out.println(super.getKind() + " Cant eat Meat");
            case "Fish"->  System.out.println(super.getKind() + " Cant eat Fish");
            case "Chocolate" -> {
                System.out.println(super.getKind() + " can't eat chocolate");
                points -= 10;
            }

        }
    }
}
