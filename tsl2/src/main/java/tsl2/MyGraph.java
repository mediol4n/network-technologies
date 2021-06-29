package tsl2;

import java.util.Random;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

public class MyGraph {
    private SimpleWeightedGraph<Integer, DefaultWeightedEdge> graph;
    private static final double defaultReliability = 0.95;
    private int randSrc, randDest;

    private void createGraph() {

        for(int i = 1; i <= 20; i++) {
            graph.addVertex(i);
        }

        DefaultWeightedEdge e1 = graph.addEdge(1, 2);
        graph.setEdgeWeight(e1, defaultReliability);
        DefaultWeightedEdge e2 = graph.addEdge(2, 3);
        graph.setEdgeWeight(e2, defaultReliability);
        DefaultWeightedEdge e3 = graph.addEdge(3, 4);
        graph.setEdgeWeight(e3, defaultReliability);
        DefaultWeightedEdge e4 = graph.addEdge(4, 5);
        graph.setEdgeWeight(e4, defaultReliability);
        DefaultWeightedEdge e5 = graph.addEdge(5, 6);
        graph.setEdgeWeight(e5, defaultReliability);
        DefaultWeightedEdge e6 = graph.addEdge(6, 7);
        graph.setEdgeWeight(e6, defaultReliability);
        DefaultWeightedEdge e7 = graph.addEdge(7, 8);
        graph.setEdgeWeight(e7, defaultReliability);
        DefaultWeightedEdge e8 = graph.addEdge(8, 9);
        graph.setEdgeWeight(e8, defaultReliability);
        DefaultWeightedEdge e9 = graph.addEdge(9, 10);
        graph.setEdgeWeight(e9, defaultReliability);
        DefaultWeightedEdge e10 = graph.addEdge(10, 11);
        graph.setEdgeWeight(e10, defaultReliability);
        DefaultWeightedEdge e11 = graph.addEdge(11, 12);
        graph.setEdgeWeight(e11, defaultReliability);
        DefaultWeightedEdge e12 = graph.addEdge(12, 13);
        graph.setEdgeWeight(e12, defaultReliability);
        DefaultWeightedEdge e13 = graph.addEdge(13, 14);
        graph.setEdgeWeight(e13, defaultReliability);
        DefaultWeightedEdge e14 = graph.addEdge(14, 15);
        graph.setEdgeWeight(e14, defaultReliability);
        DefaultWeightedEdge e15 = graph.addEdge(15, 1);
        graph.setEdgeWeight(e15, defaultReliability);

        DefaultWeightedEdge e16 = graph.addEdge(16, 17);
        graph.setEdgeWeight(e16, defaultReliability);
        DefaultWeightedEdge e17 = graph.addEdge(17, 18);
        graph.setEdgeWeight(e17, defaultReliability);
        DefaultWeightedEdge e18 = graph.addEdge(18, 19);
        graph.setEdgeWeight(e18, defaultReliability);
        DefaultWeightedEdge e19 = graph.addEdge(19, 20);
        graph.setEdgeWeight(e19, defaultReliability);
        DefaultWeightedEdge e20 = graph.addEdge(20, 16);
        graph.setEdgeWeight(e20, defaultReliability);

        DefaultWeightedEdge e21 = graph.addEdge(1, 16);
        graph.setEdgeWeight(e21, defaultReliability);
        DefaultWeightedEdge e22 = graph.addEdge(4, 17);
        graph.setEdgeWeight(e22, defaultReliability);
        DefaultWeightedEdge e23 = graph.addEdge(7, 18);
        graph.setEdgeWeight(e23, defaultReliability);
        DefaultWeightedEdge e24 = graph.addEdge(10, 19);
        graph.setEdgeWeight(e24, defaultReliability);
        DefaultWeightedEdge e25 = graph.addEdge(13, 20);
        graph.setEdgeWeight(e25, defaultReliability);    
    }
    
    public SimpleWeightedGraph<Integer, DefaultWeightedEdge> getGraph() {
        graph = new SimpleWeightedGraph<Integer, DefaultWeightedEdge>(DefaultWeightedEdge.class);
        createGraph();
        return graph;
    }
    
    public void addRandomEdge() {
    	Random random = new Random();
    	for (int i = 0; i < 2; i++) {
	    	randSrc = random.nextInt(20)+1;
			randDest = random.nextInt(20)+1;
			
			while (randSrc == randDest || graph.containsEdge(randSrc, randDest)) {
	    		randSrc = random.nextInt(20)+1;
	    		randDest = random.nextInt(20)+1;
			}
	    	System.out.println("DODANO EDGE: (" + randSrc + ", " + randDest + ") ");
			DefaultWeightedEdge e = graph.addEdge(randSrc, randDest);
			graph.setEdgeWeight(e, defaultReliability);
    	}
    } 
    

   
    public SimpleWeightedGraph<Integer, DefaultWeightedEdge> getGraphWith5Extra() {
        graph = new SimpleWeightedGraph<Integer, DefaultWeightedEdge>(DefaultWeightedEdge.class);
        createGraph();
        DefaultWeightedEdge e = graph.addEdge(1, 4);
        graph.setEdgeWeight(e, defaultReliability);  
        DefaultWeightedEdge e2 = graph.addEdge(4, 7);
        graph.setEdgeWeight(e2, defaultReliability); 
        DefaultWeightedEdge e3 = graph.addEdge(7, 10);
        graph.setEdgeWeight(e3, defaultReliability);
        DefaultWeightedEdge e4 = graph.addEdge(10, 13);
        graph.setEdgeWeight(e4, defaultReliability);
        DefaultWeightedEdge e5 = graph.addEdge(13, 1);
        graph.setEdgeWeight(e5, defaultReliability);
        return graph;
    } 
    
    public SimpleWeightedGraph<Integer, DefaultWeightedEdge> getGraphWith10Extra() {
        graph = new SimpleWeightedGraph<Integer, DefaultWeightedEdge>(DefaultWeightedEdge.class);
        createGraph();
        DefaultWeightedEdge e = graph.addEdge(2, 16);
        graph.setEdgeWeight(e, defaultReliability);  
        DefaultWeightedEdge e2 = graph.addEdge(3, 17);
        graph.setEdgeWeight(e2, defaultReliability); 
        DefaultWeightedEdge e3 = graph.addEdge(5, 17);
        graph.setEdgeWeight(e3, defaultReliability);
        DefaultWeightedEdge e4 = graph.addEdge(6, 18);
        graph.setEdgeWeight(e4, defaultReliability);
        DefaultWeightedEdge e5 = graph.addEdge(8, 18);
        graph.setEdgeWeight(e5, defaultReliability);
        DefaultWeightedEdge e6 = graph.addEdge(9, 19);
        graph.setEdgeWeight(e6, defaultReliability);  
        DefaultWeightedEdge e7 = graph.addEdge(11, 19);
        graph.setEdgeWeight(e7, defaultReliability); 
        DefaultWeightedEdge e8 = graph.addEdge(12, 20);
        graph.setEdgeWeight(e8, defaultReliability);
        DefaultWeightedEdge e9 = graph.addEdge(14, 20);
        graph.setEdgeWeight(e9, defaultReliability);
        DefaultWeightedEdge e10 = graph.addEdge(15, 16);
        graph.setEdgeWeight(e10, defaultReliability);
        
        return graph;
    } 
    
   
}