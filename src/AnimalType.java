
public enum AnimalType {
    TIGER("ROAR") {
        @Override
        int eat(Food food) {
            if (food.getQuantity() > 0) {
                food.reduceStock();
                dbFoodManager.updateQuantity(food.getName(), food.getQuantity());
                switch (food.getName()) {
                    case "BANANA", "WORMS" -> {
                        return 1;
                    }
                    case "MEAT", "FISH" -> {
                        return 10;
                    }
                    default -> {
                        return 0;
                    }
                }
            } else {
                return 0;
            }
        }
    },
    CAT("MEOW") {
        @Override
        int eat(Food food) {
            if (food.getQuantity() > 0) {
                food.reduceStock();
                dbFoodManager.updateQuantity(food.getName(), food.getQuantity());
                switch (food.getName()) {
                    case "BANANA", "WORMS" -> {
                        return 1;
                    }
                    case "MEAT", "FISH" -> {
                        return 10;
                    }
                    default -> {
                        return 0;
                    }
                }
            } else {
                return 0;
            }
        }
    },
    COW("MOO") {
        @Override
        int eat(Food food) {
            if (food.getQuantity() > 0) {
                food.reduceStock();
                dbFoodManager.updateQuantity(food.getName(), food.getQuantity());
                switch (food.getName()) {
                    case "BANANA" -> {
                        return 10;
                    }
                    case "MEAT", "FISH" -> {
                        return -10;
                    }
                    default -> {
                        return 0;
                    }
                }
            } else {
                return 0;
            }
        }
    };


    AnimalType(String sound) {
        this.sound = sound;
    }
    final DbFoodManager dbFoodManager = new DbFoodManager("jdbc:postgresql://localhost:5432/zoo","postgres", "roei");

    abstract int eat(Food food);

    final String sound;
}
