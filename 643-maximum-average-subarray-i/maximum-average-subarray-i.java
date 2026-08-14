class Solution {
    public double findMaxAverage(int[] nums, int k) {
        long sum = 0;
        long max = Long.MIN_VALUE;
        for(int i = 0; i < k; i++){
            sum = sum + nums[i];
        }
        max = sum;

        for(int i = k; i < nums.length; i++){
            sum = sum + nums[i] - nums[i-k];
            max = Math.max(sum,max);
        }
        return(double) max/k;
    }
}