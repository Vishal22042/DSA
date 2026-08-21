import java.util.HashMap;

class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int maxLen = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        
        int start = 0;
        
        for (int end = 0; end < n; end++) {
            char ch = s.charAt(end);
            
            if (map.containsKey(ch) && map.get(ch) >= start) {
                start = map.get(ch) + 1;
            }
            
            map.put(ch, end);
            maxLen = Math.max(maxLen, end - start + 1);
        }
        
        return maxLen;
    }
}