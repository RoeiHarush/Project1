public enum AnimalType {
    TIGER("ROAR") {
        @Override
        int eat(Food food) {
            if (food.getQuantity() > 0) {
                food.reduceStock();
                switch (food.getName()) {
                    case "banana", "worms" -> {
                        return 1;
                    }
                    case "meat", "fish" -> {
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
                switch (food.getName()) {
                    case "banana", "worms" -> {
                        return 1;
                    }
                    case "meat", "fish" -> {
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
                switch (food.getName()) {
                    case "Banana" -> {
                        return 10;
                    }
                    case "meat", "fish", "Chocolate" -> {
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

    abstract int eat(Food food);

    final String sound;
}
