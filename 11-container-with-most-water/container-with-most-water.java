class Solution {
    public int maxArea(int[] height) {
        int left=0;
        int max=0;
        int right=height.length-1;
        while(left<right){
            int width=right-left;
            int ht=Math.min(height[right],height[left]);
            int area=width*ht;
            max=Math.max(max,area);
            if(height[left]<height[right]){
                left++;
            }
            else{
                right--;
            }
        }
        return max; 
    }
}