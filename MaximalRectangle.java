public class MaximalRectangle {
    public static void main(String[] args) {
        int area = maximalRectangle(new char[][] {
                { '1', '0', '1', '0', '0' },
                { '1', '0', '1', '1', '1' },
                { '1', '1', '1', '1', '1' },
                { '1', '0', '0', '1', '0' }
        });
        System.out.println("Maximal Rectangle area is :" + area);
    }

    private static int maximalRectangle(char[][] matrix) {
        int[][] left = new int[matrix.length][matrix[0].length];
        int[][] right = new int[matrix.length][matrix[0].length];
        int[][] height = new int[matrix.length][matrix[0].length];

        // create left matrix
        for (int i = 0; i < matrix.length; i++) {
            int lastValue = 0;
            for (int j = 0; j < matrix[0].length; j++) {
                int currentValue = matrix[i][j];
                if (currentValue == '1') {
                    if (i == 0) {
                        left[i][j] = lastValue;
                    } else {
                        left[i][j] = Math.max(lastValue, left[i - 1][j]);
                    }
                } else {
                    lastValue = j + 1;
                    left[i][j] = 0;
                }
            }
        }

        // create right matrix
        for (int i = 0; i < matrix.length; i++) {
            int lastValue = matrix[0].length;
            for (int j = matrix[0].length - 1; j >= 0; j--) {
                char currentValue = matrix[i][j];
                if (currentValue == '1') {
                    if (i == 0) {
                        right[i][j] = lastValue;
                    } else {
                        right[i][j] = Math.min(lastValue, right[i - 1][j]);
                    }
                } else {
                    lastValue = j;
                    right[i][j] = matrix[0].length;
                }
            }
        }

        int maxArea = 0;

        // create hieght matrix
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                char currentValue = matrix[i][j];
                int tempArea;
                if (currentValue == '1') {
                    if (i == 0) {
                        height[i][j] = 1;
                    } else {
                        height[i][j] = height[i - 1][j] + 1;
                    }
                    tempArea = height[i][j] * (right[i][j] - left[i][j]);

                    if (tempArea > maxArea) {
                        maxArea = tempArea;
                    }
                } else {
                    height[i][j] = 0;
                }
            }
        }

        return maxArea;
    }
}
