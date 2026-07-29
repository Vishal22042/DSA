class Solution {
    static final long LIMIT = 1_000_000L;

    public String smallestPalindrome(String s, int k) {
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) cnt[c - 'a']++;

        int[] half = new int[26];
        int halfLen = 0;
        char mid = 0;

        for (int i = 0; i < 26; i++) {
            half[i] = cnt[i] / 2;
            halfLen += half[i];
            if ((cnt[i] & 1) == 1) mid = (char) ('a' + i);
        }

        if (countWays(half, halfLen) < k) return "";

        StringBuilder left = new StringBuilder();

        for (int pos = 0; pos < halfLen; pos++) {
            for (int c = 0; c < 26; c++) {
                if (half[c] == 0) continue;

                half[c]--;

                long ways = countWays(half, halfLen - pos - 1);

                if (ways >= k) {
                    left.append((char) ('a' + c));
                    break;
                } else {
                    k -= ways;
                    half[c]++;
                }
            }
        }

        StringBuilder ans = new StringBuilder();
        ans.append(left);
        if (mid != 0) ans.append(mid);
        ans.append(new StringBuilder(left).reverse());

        return ans.toString();
    }

    private long countWays(int[] freq, int total) {
        long res = 1;
        int rem = total;

        for (int f : freq) {
            if (f == 0) continue;
            res = multiplyCap(res, comb(rem, f));
            if (res >= LIMIT) return LIMIT;
            rem -= f;
        }
        return Math.min(res, LIMIT);
    }

    private long comb(int n, int r) {
        if (r < 0 || r > n) return 0;
        r = Math.min(r, n - r);

        long res = 1;

        for (int i = 1; i <= r; i++) {
            long num = n - r + i;
            long den = i;

            long g = gcd(num, den);
            num /= g;
            den /= g;

            g = gcd(res, den);
            res /= g;
            den /= g;

            if (res > LIMIT / num) return LIMIT;

            res *= num;
            res /= den;

            if (res >= LIMIT) return LIMIT;
        }

        return Math.min(res, LIMIT);
    }

    private long multiplyCap(long a, long b) {
        if (a == 0 || b == 0) return 0;
        if (a >= LIMIT || b >= LIMIT) return LIMIT;
        if (a > LIMIT / b) return LIMIT;
        return Math.min(a * b, LIMIT);
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long t = a % b;
            a = b;
            b = t;
        }
        return a;
    }
}