class Solution {
    public boolean isPalindrome(String s) {
        String str = s.toLowerCase().replaceAll("[^a-zA-Z0-9]","");
        if(str.equals("")||str.equals(null)){
            return true;
        }
        for(int i=0;i<str.length();i++){
            char start=str.charAt(i);
            char end=str.charAt(str.length()-1-i);
            if(start!=end) return false;
        }
        return true;
    }
}