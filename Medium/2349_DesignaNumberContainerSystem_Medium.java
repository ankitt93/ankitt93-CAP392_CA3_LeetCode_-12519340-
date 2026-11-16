class NumberContainers {
    private java.util.Map<Integer, Integer> indexToNumber;
    private java.util.Map<Integer, java.util.TreeSet<Integer>> numberToIndices;

    public NumberContainers() {
        indexToNumber = new java.util.HashMap<>();
        numberToIndices = new java.util.HashMap<>();
    }

    public void change(int index, int number) {
        if (indexToNumber.containsKey(index)) {
            int oldNumber = indexToNumber.get(index);
            if (oldNumber == number) {
                return;
            }
            java.util.TreeSet<Integer> indices = numberToIndices.get(oldNumber);
            if (indices != null) {
                indices.remove(index);
                if (indices.isEmpty()) {
                    numberToIndices.remove(oldNumber);
                }
            }
        }
        indexToNumber.put(index, number);
        numberToIndices.putIfAbsent(number, new java.util.TreeSet<>());
        numberToIndices.get(number).add(index);
    }

    public int find(int number) {
        if (!numberToIndices.containsKey(number)) {
            return -1;
        }
        java.util.TreeSet<Integer> indices = numberToIndices.get(number);
        return indices.isEmpty() ? -1 : indices.first();
    }
}