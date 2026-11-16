class FoodRatings {
    private class FoodItem implements Comparable<FoodItem> {
        String name;
        int rating;

        public FoodItem(String name, int rating) {
            this.name = name;
            this.rating = rating;
        }

        @Override
        public int compareTo(FoodItem other) {
            if (this.rating != other.rating) {
                return Integer.compare(other.rating, this.rating);
            }
            return this.name.compareTo(other.name);
        }
    }

    private java.util.Map<String, String> foodToCuisine;
    private java.util.Map<String, Integer> foodToRating;
    private java.util.Map<String, java.util.TreeSet<FoodItem>> cuisineToFoods;

    public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
        foodToCuisine = new java.util.HashMap<>();
        foodToRating = new java.util.HashMap<>();
        cuisineToFoods = new java.util.HashMap<>();

        for (int i = 0; i < foods.length; i++) {
            String food = foods[i];
            String cuisine = cuisines[i];
            int rating = ratings[i];

            foodToCuisine.put(food, cuisine);
            foodToRating.put(food, rating);

            cuisineToFoods.putIfAbsent(cuisine, new java.util.TreeSet<>());
            cuisineToFoods.get(cuisine).add(new FoodItem(food, rating));
        }
    }

    public void changeRating(String food, int newRating) {
        String cuisine = foodToCuisine.get(food);
        int oldRating = foodToRating.get(food);

        java.util.TreeSet<FoodItem> foodsSet = cuisineToFoods.get(cuisine);
        foodsSet.remove(new FoodItem(food, oldRating));

        foodToRating.put(food, newRating);
        foodsSet.add(new FoodItem(food, newRating));
    }

    public String highestRated(String cuisine) {
        return cuisineToFoods.get(cuisine).first().name;
    }
}