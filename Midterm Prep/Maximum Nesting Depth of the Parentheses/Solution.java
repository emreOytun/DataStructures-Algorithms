class Solution {
    public int maxDepth(String s) {
        int maxDepth = 0;
        int curDepth = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '(') ++curDepth;
            else if (s.charAt(i) == ')') {
                maxDepth = Math.max(curDepth, maxDepth);
                --curDepth;
            }
        }
        return maxDepth;
    }
}