package primary;



public class AjaxDriver {
	
	public static void main(String[] args) { 
		Ajax graph = new Ajax();
		graph.addEdge("1", "2");
		graph.addEdge("2", "3");
		graph.addEdge("1", "3");
		graph.addEdge("2", "4");
		graph.addEdge("3", "4");
		graph.addEdge("3", "5");
		graph.topoPrint("1");
		System.out.println(graph.topoSort("1"));
	}

}
