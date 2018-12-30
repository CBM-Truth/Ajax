package primary;

public class AjaxDriver {

	public static final int NANO = 1000000000;
	
	public static void main(String[] args) { 
		Ajax<String> graph = new Ajax<>();
		graph.addEdge("7", "5");
		graph.addEdge("7", "6");
		graph.addEdge("5", "2");
		graph.addEdge("5", "4");
		graph.addEdge("6", "4");
		graph.addEdge("6", "3");
		graph.addEdge("2", "1");
		graph.addEdge("3", "1");
		graph.addEdge("1", "0");
		System.out.println(graph.topologicalSort("7"));
		graph.clear();

//		int[] inputs = {10000000};
//		for (int n : inputs) {
//			double time;
//			long start, end;
//			int[] testInput = new int[n];
//			for (int i = 0; i < testInput.length; i++) {
//				testInput[i] = i + 1;
//			}
////			System.out.printf("Computing recursive sum (n = %d)...\n", n);
////			start = System.nanoTime();
////			int s = recursiveSum(testInput);
////			end = System.nanoTime();
////			System.out.printf("Computed: %d\n", s);
////			time = (double) (end - start) / NANO;
////			System.out.printf("Computation took %.7fs\n", time);
//
//			System.out.printf("Computing iterative sum (n = %d)...\n", n);
//			start = System.nanoTime();
//			long s = iterativeSum(testInput);
//			end = System.nanoTime();
//			System.out.printf("Computed: %d\n", s);
//			time = (double) (end - start) / NANO;
//			System.out.printf("Computation took %.7fs\n", time);
//
//			System.out.printf("Computing parallel sum (n = %d)...\n", n);
//			start = System.nanoTime();
//			s = parallelSum(testInput);
//			end = System.nanoTime();
//			System.out.printf("Computed: %d\n", s);
//			time = (double) (end - start) / NANO;
//			System.out.printf("Computation took %.7fs\n", time);
//
//		}
	}

 	private static int recursiveSum(int[] arr) {
		return recursiveSum(arr, 0);
	}

	public static int recursiveSum(int[] arr, int i) {
		return (i == arr.length) ? 0 : arr[i] + recursiveSum(arr, i + 1);
	}

	private static long iterativeSum(int[] arr) {
		long sum = 0;
		for (int n : arr) {
			sum += n;
		}
		return sum;
	}

	private static long parallelSum(int [] arr) {
		return Sum.parallelSum(arr);
	}
}
