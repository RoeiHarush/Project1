public enum FoodType {

    BANANA("BANANA"),
    MEAT("MEAT"),
    FISH("FISH"),
    BAMBA("BAMBA");

    FoodType(String name) {
        this.kind = name;
    }

    final String kind;

}
