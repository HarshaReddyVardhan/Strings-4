// Time Complexity : O(N log N * M) 
// N = number of logs, M = average length of each log.
// Reason: We are sorting all logs, which costs O(N log N). For each comparison, we split logs and compare strings, which takes O(M). Hence overall O(N log N * M).
//
// Space Complexity : O(M) 
// Reason: Each split operation creates temporary arrays of size proportional to log length (M). Sorting is in-place, so extra space is minimal.
//
// Did this code successfully run on Leetcode : Yes


// Approach :
// 1. The algorithm sorts the logs based on a custom comparator that distinguishes between digit-logs and letter-logs.  
// 2. If both logs are digit-logs, they remain in the same relative order (return 0).  
// 3. If one is a letter-log and the other is a digit-log, the letter-log is prioritized to come first.  
// 4. If both are letter-logs, they are sorted lexicographically by their contents (after the identifier).  
// 5. If the contents are identical, they are further sorted by their identifiers to maintain order.  


// Code:
class Solution {
    public String[] reorderLogFiles(String[] logs) {
        Arrays.sort(logs, (a,b) ->{
            String []s1 = a.split(" ",2);
            String []s2 = b.split(" ",2);
            char c1 = s1[1].charAt(0);
            char c2 = s2[1].charAt(0);
            if(Character.isDigit(c1) && Character.isDigit(c2)){
                return 0;
            }
            if(!Character.isDigit(c1) && Character.isDigit(c2)){
                return -1;
            }
            if(Character.isDigit(c1) && !Character.isDigit(c2)){
                return 1;
            }
            int val = s1[1].compareTo(s2[1]);
            if(val != 0)
                return val;
            return s1[0].compareTo(s2[0]);
        });
        return logs;
    }
}
