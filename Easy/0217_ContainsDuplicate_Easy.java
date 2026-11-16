class Solution {
    public boolean containsDuplicate(int[] nums) {
        java.util.HashSet<Integer> seen = new java.util.HashSet<>();
        for (int num : nums) {
            if (!seen.add(num)) {
                return true;
            }
        }
        return false;
    }
}