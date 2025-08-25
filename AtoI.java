
// Time Complexity : O(N)
// Reason: We traverse the string once (after trim), processing each character at most once.
//
// Space Complexity : O(1)
// Reason: Only a few scalar variables are used; no auxiliary data structures grow with input size.
//
// Did this code successfully run on Leetcode : Yes


// Approach :
// 1) Trim leading/trailing spaces, then read an optional sign; use a long accumulator `ans` to safely build the number without 32-bit overflow.
// 2) Iterate through subsequent characters; if a non-digit is encountered, stop parsing immediately.
// 3) While parsing digits, perform an early overflow guard: if positive and `ans > Integer.MAX_VALUE/10`, return `Integer.MAX_VALUE`; if negative and `-ans < Integer.MIN_VALUE/10`, return `Integer.MIN_VALUE`.
// 4) Update `ans = ans * 10 + (currentDigit)`; after the loop, apply the sign if it was negative.
// 5) Finally clamp the result into 32-bit range by returning `Integer.MAX_VALUE` or `Integer.MIN_VALUE` when `ans` exceeds bounds, otherwise cast to `int` and return.


// Code:
class Solution {
    public int myAtoi(String s) {
        s = s.trim();
        long ans = 0;
        int n = s.length();
        if (n < 1)
            return 0;

        int i = 0;
        boolean negative = false;

        if (s.charAt(i) == '-') {
            negative = true;
            i++;
        } else if (s.charAt(i) == '+') {
            i++;
        }

        while (i < n) {
            char c = s.charAt(i);
            if (!Character.isDigit(c))
                break;

            if (!negative) {
                if (ans > Integer.MAX_VALUE / 10)
                    return Integer.MAX_VALUE;
            } else {
                if (-ans < Integer.MIN_VALUE / 10)
                    return Integer.MIN_VALUE;
            }

            ans = ans * 10 + (c - '0');
            i++;
        }

        if (negative) ans = -ans;

        if (ans > Integer.MAX_VALUE) return Integer.MAX_VALUE;
        if (ans < Integer.MIN_VALUE) return Integer.MIN_VALUE;
        return (int) ans;
    }
}
