package primary;



public class AjaxDriver {
	
	public static void main(String[] args) { 
		Ajax graph = new Ajax();
		graph.addEdge("5", "2");
		graph.addEdge("5", "0");
		graph.addEdge("4", "0");
		graph.addEdge("4", "1");
		graph.addEdge("2", "3");
		graph.addEdge("3", "1");
		graph.topoSortPrint("5");
	}

}
