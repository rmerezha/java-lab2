public class Main {
    public static void main(String[] args) {
        test("Madam Anna went kayaking with her mom.");
        test("Step on no pets, racecar, and level are all palindromes.");
        test("A man, a plan, a canal, Panama! Wow, radar detected level!");
        test("Civic duty is important, but kayak trips are fun too.");
        test("No palindromes here, just random words like apple banana cherry.");
        test("Never odd or even, level up your stats, racecar time!");
    }

    private static void test(String text) {
        System.out.println("Text: " + text);
        System.out.println("Longest palindrome: " + findLongestPalindrome(text));
        System.out.println();
    }

    private static String findLongestPalindrome(String text) {
        if (text == null || text.isEmpty()) return "";

        String lettersOnly = "";
        int[] originalIndexes = new int[text.length()];
        int idx = 0;

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (Character.isLetter(c)) {
                lettersOnly = lettersOnly + Character.toLowerCase(c);
                originalIndexes[idx++] = i;
            }
        }

        String s = lettersOnly;
        int maxLen = 0, startIndex = 0;

        for (int i = 0; i < s.length(); i++) {
            int[] odd = expand(s, i, i);
            int[] even = expand(s, i, i + 1);
            int[] longer = (odd[1] - odd[0] > even[1] - even[0]) ? odd : even;
            if (longer[1] - longer[0] > maxLen) {
                maxLen = longer[1] - longer[0];
                startIndex = longer[0];
            }
        }

        if (maxLen == 0) return "";

        int originalStart = originalIndexes[startIndex];
        int originalEnd = originalIndexes[startIndex + maxLen - 1] + 1;
        return text.substring(originalStart, originalEnd);
    }

    private static int[] expand(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return new int[]{left + 1, right};
    }
}
