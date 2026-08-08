import java.util.*;

class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        int[] exact = new int[m + 1];
        Arrays.fill(exact, -1);
        exact[m] = n;

        ArrayList<Integer>[] positions = new ArrayList[26];

        for (int i = 0; i < 26; i++) {
            positions[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            positions[word1.charAt(i) - 'a'].add(i);
        }

        for (int j = m - 1; j >= 0; j--) {
            exact[j] = previousPosition(
                    positions[word2.charAt(j) - 'a'],
                    exact[j + 1]
            );
        }

        int[] last1 = new int[n + 1];
        int[] last2 = new int[n + 1];

        Arrays.fill(last1, -1);
        Arrays.fill(last2, -1);

        int p1 = -1;
        int p2 = -1;

        for (int i = 0; i < n; i++) {
            if (p1 == -1 || word1.charAt(i) != word1.charAt(p1)) {
                p2 = p1;
                p1 = i;
            } else {
                p1 = i;
            }

            last1[i + 1] = p1;
            last2[i + 1] = p2;
        }

        int[] one = new int[m + 1];
        Arrays.fill(one, -1);
        one[m] = n;

        for (int j = m - 1; j >= 0; j--) {

            int candidate1 = -1;

            if (one[j + 1] != -1) {
                candidate1 = previousPosition(
                        positions[word2.charAt(j) - 'a'],
                        one[j + 1]
                );
            }

            int candidate2 = -1;

            if (exact[j + 1] != -1) {
                candidate2 = previousDifferent(
                        word1,
                        last1,
                        last2,
                        exact[j + 1],
                        word2.charAt(j)
                );
            }

            one[j] = Math.max(candidate1, candidate2);
        }

        int[] answer = new int[m];

        int prev = -1;
        boolean usedMismatch = false;

        for (int j = 0; j < m; j++) {

            boolean found = false;

            for (int i = prev + 1; i < n; i++) {

                if (word1.charAt(i) == word2.charAt(j)) {

                    if (one[j + 1] > i) {
                        answer[j] = i;
                        prev = i;
                        found = true;
                        break;
                    }

                }
                else if (!usedMismatch) {

                    if (exact[j + 1] > i) {
                        answer[j] = i;
                        prev = i;
                        usedMismatch = true;
                        found = true;
                        break;
                    }
                }
            }

            if (!found) {
                return new int[0];
            }
        }

        return answer;
    }

    private int previousPosition(
            ArrayList<Integer> list,
            int limit
    ) {
        int left = 0;
        int right = list.size() - 1;
        int answer = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (list.get(mid) < limit) {
                answer = list.get(mid);
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return answer;
    }

    private int previousDifferent(
            String word1,
            int[] last1,
            int[] last2,
            int limit,
            char c
    ) {
        if (limit <= 0) {
            return -1;
        }

        int pos = last1[limit];

        if (pos != -1 && word1.charAt(pos) != c) {
            return pos;
        }

        return last2[limit];
    }
}