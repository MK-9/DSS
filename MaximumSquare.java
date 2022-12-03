public class MaximumSquare {
    public static void main(String[] args) {
        // Input: matrix
        char[][] matrix = new char[][] {
                { '1', '1', '1', '1', '0' },
                { '1', '1', '1', '1', '1' },
                { '1', '1', '1', '1', '1' },
                { '1', '1', '1', '1', '0' },
                { '1', '1', '1', '1', '1' }
        };

        System.out.println("maximum square:" + maximalSquareBruteForce(matrix));
        System.out.println("maximum square:" + maximumSquareDp(matrix));
    }

    public static int maximalSquareBruteForce(char[][] matrix) {

        int[][] leftToRightMatrix = new int[matrix.length][matrix[0].length];

        // the last continuous one
        int lastValue = 0;

        // traverse on matrix
        for (int i = matrix.length - 1; i >= 0; i--) {
            lastValue = 0;
            for (int j = matrix[i].length - 1; j >= 0; j--) {
                int value = matrix[i][j];
                if (value == '1') {
                    lastValue++;
                    leftToRightMatrix[i][j] = lastValue;
                } else {
                    lastValue = 0;
                }
            }
        }

        // area
        int area = 0;

        // traverse on leftToRightMatrix
        for (int i = 0; i < leftToRightMatrix.length; i++) {
            for (int j = 0; j < leftToRightMatrix[i].length; j++) {
                int width = leftToRightMatrix[i][j];

                if (width == 1 && area == 0) {
                    area = 1;
                }

                if (width > 0 && width != 1) {

                    // row count of square
                    int rowCountUp = 0;
                    int rowCountDown = 0;

                    // check result validation
                    boolean isAccepted = false;

                    // up traverse
                    int upValue = 0;

                    for (int k = i; k >= 0; k--) {
                        upValue = leftToRightMatrix[k][j];
                        if (upValue >= width) {
                            rowCountUp++;
                            isAccepted = true;
                        } else {
                            isAccepted = false;
                            break;
                        }
                    }

                    // down traverse
                    int downValue = 0;

                    int lastDownPoint = 0;
                    if (i + width - rowCountUp > leftToRightMatrix.length - 1) {
                        lastDownPoint = leftToRightMatrix.length - 1;
                    } else {
                        lastDownPoint = i + width - rowCountUp;
                    }

                    for (int k = i; k <= lastDownPoint; k++) {
                        downValue = leftToRightMatrix[k][j];
                        if (downValue >= width && k <= i + width - rowCountUp) {
                            rowCountDown++;
                            isAccepted = true;
                        } else {
                            isAccepted = false;
                            break;
                        }
                    }

                    if (isAccepted) {
                        if (rowCountUp + rowCountDown - 1 >= width) {
                            int temp_area = width * width;

                            // check area is bigger than last result
                            if (temp_area > area) {
                                area = temp_area;
                            }
                        }
                    }

                } else if (width == 1) {
                    if (area == 0) {
                        area = 1;
                    }
                }
            }
        }

        return area;
    }

    public static int maximumSquareDp(char[][] matrix) {

        // DP[i][j] show length in maximum square that is located it's bottomLeft point
        int[][] DP = new int[matrix.length][matrix[0].length];

        // last side length
        int mLength = 0;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {

                // fill first row and col in DP matrix
                if (i == 0 || j == 0) {
                    if (matrix[i][j] == '1') {
                        // fill DP
                        DP[i][j] = 1;

                        // update area
                        if (DP[i][j] > mLength)
                            mLength = 1;
                    }
                } else {

                    if (matrix[i][j] == '1') {
                        DP[i][j] = Math.min(Math.min(DP[i - 1][j], DP[i][j - 1]), DP[i - 1][j - 1]) + 1;
                    }
                    // update area
                    if (DP[i][j] > mLength)
                        mLength = DP[i][j];
                }
            }
        }

        return mLength * mLength;
    }

}
