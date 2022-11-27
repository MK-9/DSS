import java.util.HashMap;

class FibonachiProblem {

    public static void main(String[] args) {
        System.out.println(memoizedFib(10));
        System.out.println(dynamicFib(10));
    }

    private static HashMap<Integer, Integer> cache = new HashMap<>();

    public static int memoizedFib(int n) {
        if (n == 0 || n == 1) {
            return n;
        }

        if (cache.get(n) != null) {
            return cache.get(n);
        }

        int result = memoizedFib(n - 1) + memoizedFib(n - 2);
        cache.put(n, result);
        return result;
    }

    public static int dynamicFib(int n) {
        int output[] = new int[n + 1];
        output[0] = 0;
        output[1] = 1;
        for (int i = 2; i < output.length; i++) {
            output[i] = output[i - 1] + output[i - 2];
        }

        return output[n];
    }

}