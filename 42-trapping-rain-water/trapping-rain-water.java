class Solution {
    public int trap(int[] height) {
        int left = 0;
        int right = height.length - 1;

        int leftWall = 0;
        int rightWall = 0;
        int water = 0;

        while (left < right) {

            if (height[left] <= height[right]) {

                if (height[left] >= leftWall) {
                    leftWall = height[left];
                } else {
                    water += leftWall - height[left];
                }

                left++;

            } else {

                if (height[right] >= rightWall) {
                    rightWall = height[right];
                } else {
                    water += rightWall - height[right];
                }

                right--;
            }
        }

        return water;
    }
}