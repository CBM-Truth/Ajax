package primary;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class Sum {

    public static final ForkJoinPool POOL = new ForkJoinPool();

    public static final int SEQUENTIAL_CUTOFF = 5;

    public static int recursiveSum(int[] arr, int i) {
        return (i == arr.length) ? 0 : arr[i] + recursiveSum(arr, i + 1);
    }

    public static int parallelSum(int[] arr) {
        return POOL.invoke(new ParallelSumTask(arr, 0, arr.length));
    }

    private static class ParallelSumTask extends RecursiveTask<Integer> {
        int[] arr;
        int lo, hi;

        public ParallelSumTask(int[] arr, int lo, int hi) {
            this.arr = arr;
            this.lo = lo;
            this.hi = hi;
        }

        @Override
        protected Integer compute() {
            if (hi - lo <= SEQUENTIAL_CUTOFF) {
                int sum = 0;
                for (int i = lo; i < hi; i++) {
                    sum += arr[i];
                }
                return sum;
            }
            int mid = lo + (hi - lo) / 2;
            ParallelSumTask left = new ParallelSumTask(arr, lo, mid);
            ParallelSumTask right = new ParallelSumTask(arr, mid, hi);

            left.fork();

            int rightResult = right.compute();
            int leftResult = left.join();
            return leftResult + rightResult;
        }
    }
}
