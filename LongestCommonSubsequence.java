import java.util.ArrayList;
import java.util.HashMap;

class LongestCommonSubsequence {

    public static void main(String[] args) {
        System.out.println(longestCommonSubsequenceBruteForce("BCDAACD", "ACDBAC"));
        System.out.println(longestCommonSubsequenceDp("BCDAACD", "ACDBAC"));
    }

    public static int longestCommonSubsequenceBruteForce(String text1, String text2) {
        // S1 = {B , C , D , A , A , C , D}
        // S2 = {A , C , D , B , A , C}
        // char[] S1 = new char[] { 'B', 'C', 'D', 'A', 'A', 'C', 'D' };
        // char[] S2 = new char[] { 'A', 'C', 'D', 'B', 'A', 'C' };

        char[] S1 = text1.toCharArray();
        char[] S2 = text2.toCharArray();

        ArrayList<Character> output = new ArrayList<>();

        int s1_length = S1.length;
        int s2_length = S2.length;
        int while_loop = s1_length;

        int startPoint = 0;
        int endPoint = 0;

        int finalResult = -1;

        HashMap<Integer, String> allResult = new HashMap<>();
        int startChar = 0;

        while (while_loop > 0) {
            for (int i = startPoint; i < s1_length; i++) {
                for (int j = endPoint; j < s2_length; j++) {
                    // find start point
                    if (S1[i] == S2[j]) {

                        output.add(S1[i]);

                        // end of s2 string
                        if (j == s2_length - 1 || i == s1_length - 1) {
                            // index of new string
                            startChar++;

                            startPoint = startChar;

                            // reset end point
                            endPoint = 0;

                            StringBuilder stringBuilder = new StringBuilder();
                            for (Character character : output) {
                                stringBuilder.append(character);
                            }

                            allResult.put(startPoint, stringBuilder.toString());

                            int outputSize = output.size();

                            if (outputSize > finalResult) {
                                finalResult = outputSize;
                            }

                            // reset output
                            output.clear();

                            while_loop--;

                            // reset i
                            i = startPoint - 1;

                        } else {
                            startPoint = i + 1;
                            endPoint = j + 1;
                        }

                        break;
                    }
                }
            }
        }

        System.out.println(allResult);

        return finalResult;
    }

    public static int longestCommonSubsequenceDp(String text1, String text2) {
        int[][] Dp = new int[text1.length() + 1][text2.length() + 1];

        for (int i = 1; i <= text1.length(); i++) {
            for (int j = 1; j <= text2.length(); j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    Dp[i][j] = Dp[i - 1][j - 1] + 1;
                } else {
                    Dp[i][j] = Math.max(Dp[i - 1][j], Dp[i][j - 1]);
                }
            }
        }

        return Dp[text1.length()][text2.length()];
    }

}