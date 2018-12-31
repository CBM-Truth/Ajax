package primary;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class Strings {

    public static final ForkJoinPool POOL = new ForkJoinPool();

    public static final int SEQUENTIAL_CUTOFF = 3;

    public static void stringTask(String[] arr) {
        Pair result = POOL.invoke(new ParallelStringTask(arr, 0, arr.length));
        System.out.printf("Total length = %d\nIndex of longest string (%s) = %d\n",
                                result.len, arr[result.index], result.index);
    }

    private static class Pair {

        public int len;

        public int index;

        public Pair(int len, int index) {
            this.len = len;
            this.index = index;
        }
    }

    private static class ParallelStringTask extends RecursiveTask<Pair> {
        String[] arr;
        int lo, hi;

        public ParallelStringTask(String[] arr, int lo, int hi) {
            this.arr = arr;
            this.lo = lo;
            this.hi = hi;
        }

        @Override
        protected Pair compute() {
            if (hi - lo <= SEQUENTIAL_CUTOFF) {
                Pair retVal = new Pair(arr[lo].length(), lo);
                for (int i = lo + 1; i < hi; i++) {
                    int length = arr[i].length();
                    if (length > retVal.len) {
                        retVal.index = i;
                    }
                    retVal.len += length;
                }
                return retVal;
            }
            int mid = lo + (hi - lo) / 2;
            ParallelStringTask left = new ParallelStringTask(arr, lo, mid);
            ParallelStringTask right = new ParallelStringTask(arr, mid, hi);

            left.fork();

            Pair rightResult = right.compute();
            Pair leftResult = left.join();

            int newIndex = (leftResult.len >= rightResult.len) ? leftResult.index : rightResult.index;

            return new Pair(leftResult.len + rightResult.len, newIndex);
        }
    }
}
