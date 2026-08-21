class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int k=Integer.MAX_VALUE;
        int sum=0;
        int left=0;
        for(int i=0;i<nums.length;i++){
            sum+=nums[i];
            while(sum>=target){
                k=Math.min(k,i-left+1);
                sum-=nums[left];
                left++;

            }
        }
        return k == Integer.MAX_VALUE ? 0 : k;
    }
}