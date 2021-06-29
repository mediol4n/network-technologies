package tsl2;

import org.jgrapht.GraphPath;
import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.graph.*;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import javax.print.DocFlavor.INPUT_STREAM;



public class Main {
    
    
    private static Random generator = new Random();
    private static int[][] A = new int[20][20];
    private static int[][] C = new int[20][20];
    //private static int[][] N = new int[20][20];
    private static int[][] N = {
    	{ 0, 18, 62, 52, 71, 42, 33, 91, 48, 54, 7, 82, 53, 7, 27, 23, 49, 24, 92, 64},
    { 1, 0, 76, 83, 34, 34, 76, 18, 93, 99, 43, 92, 34, 22, 29, 86, 68, 24, 85, 95},
    { 33, 15, 0, 4, 87, 80, 19, 70, 3, 3, 40, 5, 72, 83, 0, 60, 9, 30, 45, 29},
    { 29, 98, 50, 0, 40, 6, 48, 15, 92, 73, 83, 30, 33, 82, 78, 58, 84, 3, 70, 96},
    { 94, 20, 16, 37, 0, 79, 75, 4, 51, 92, 91, 26, 85, 41, 19, 94, 98, 37, 81, 89},
    { 88, 7, 12, 54, 70, 0, 45, 83, 27, 13, 96, 39, 96, 67, 44, 41, 10, 90, 96, 42},
    { 82, 97, 10, 21, 5, 24, 0, 15, 7, 99, 78, 45, 25, 5, 15, 38, 65, 88, 8, 66},
    { 38, 40, 89, 78, 68, 26, 53, 0, 19, 37, 89, 49, 95, 71, 80, 59, 14, 22, 69, 81},
    { 89, 89, 31, 35, 4, 1, 51, 14, 0, 85, 43, 69, 91, 39, 7, 13, 36, 61, 93, 92},
    { 70, 56, 76, 64, 17, 59, 10, 81, 77, 0, 1, 84, 16, 1, 54, 18, 70, 45, 31, 63},
    { 60, 20, 9, 83, 21, 60, 92, 42, 54, 68, 0, 69, 51, 83, 74, 99, 97, 36, 42, 70},
    { 78, 66, 6, 2, 33, 0, 42, 46, 6, 50, 87, 0, 58, 78, 96, 53, 20, 19, 81, 75},
    { 19, 59, 2, 0, 29, 53, 10, 24, 55, 14, 85, 0, 0, 27, 27, 81, 19, 98, 7, 21},
    { 46, 0, 71, 45, 70, 80, 9, 35, 13, 95, 39, 92, 7, 0, 57, 82, 62, 72, 95, 32},
    { 78, 93, 92, 9, 99, 11, 29, 66, 25, 19, 53, 28, 29, 15, 0, 22, 28, 54, 30, 98},
    { 5, 85, 1, 78, 82, 46, 65, 45, 31, 58, 21, 70, 66, 85, 8, 0, 27, 80, 89, 8},
    { 34, 30, 95, 48, 62, 9, 54, 27, 91, 94, 93, 90, 59, 75, 81, 55, 0, 97, 36, 39},
    { 65, 8, 17, 94, 65, 42, 46, 25, 62, 36, 5, 13, 25, 61, 19, 13, 56, 0, 9, 14},
    { 77, 9, 27, 99, 43, 23, 40, 77, 12, 93, 13, 22, 92, 90, 69, 14, 73, 38, 0, 39},
    { 25, 74, 67, 58, 9, 33, 8, 74, 66, 33, 31, 63, 55, 50, 82, 74, 23, 95, 93, 0} };
    private static int diffN = 5;
    private static  double T_max = 0.05;
    static MyGraph net = new MyGraph();
    
    
    private static int subpoint = 2;
    private static int sumN = 0;

    private static int packetSize = 1024;
    private static int capacityDiff = 100*packetSize;

    public static void main(String[] args) {
    	//Checking my topology
        //generateN();
        generateA(net.getGraph());
        generateC();
        System.out.println("Matrix N : ");
        printMatrix(N);
        System.out.println("Matrix A : ");
        printMatrix(A);
        System.out.println("Matrix C : ");
        printMatrix(C);

        calculateSumN();
        checkGraph(0);
        System.out.println("");
        System.out.println("");
        System.out.println("");


        
        switch(subpoint) {
        case 1: 
        	for (int i = 0; i < 5; i++) {
                increaseN();
                generateA(net.getGraph());
                checkGraph(0);
        	}
        	break;
        case 2:
        	for (int i = 0; i < 5; i++) {
        		generateA(net.getGraph());
        		increaseCapacity();
        		checkGraph(0);
        	}
        	break;
        case 3:
        	
        	updateA(5);
            generateC();
            System.out.println("");
            checkGraph(5);
            /*
            updateA(10);
            generateC();
            System.out.println("");
            checkGraph(10);
            */
            break;
        case 4:
            T_max = 0.01;
            System.out.println("T_MAX = " + T_max);
            checkGraph(0);
            System.out.println("");
            System.out.println("");
            System.out.println("");
            
            T_max = 0.005;
            System.out.println("T_MAX = " + T_max);
            checkGraph(0);
            System.out.println("");
            System.out.println("");
            System.out.println("");
            
            T_max = 0.0025;
            System.out.println("T_MAX = " + T_max);
            checkGraph(0);
            System.out.println("");
            System.out.println("");
            System.out.println("");
        default:
        	break;
        }
    }


    private static void increaseCapacity() {
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				if (i != j) {
					C[i][j] += capacityDiff;
				}
			}
		}
		
	}


	private static void increaseN() {
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				if (i != j) {
					N[i][j] += diffN;
				}
			}
		}
		
	}


	private static void checkGraph(int version) {
        SimpleWeightedGraph<Integer, DefaultWeightedEdge> graph;
        ArrayList<DefaultWeightedEdge> edges = new ArrayList<DefaultWeightedEdge>();
        double weight;
        double random;
        int timeout = 0;
        int cohesive = 0;
        int all = 0;
        double sum = 0;
        int counter = 0;
        while (all != 1000) {
        	switch(version) {
        	case 5:
        		graph = net.getGraphWith5Extra();
        		break;
        	case 10:
        		graph = net.getGraphWith10Extra();
        	default:
        		graph = net.getGraph();
        		break;		
        		
        	}
            edges.addAll(graph.edgeSet());
            for (DefaultWeightedEdge edge : edges) {
                weight = graph.getEdgeWeight(edge);
                random = generator.nextDouble();
                if (random > weight) {
                    graph.removeEdge(edge);
                }
            }
            if (isCohesive(graph)) {
                generateA(graph);
                double t = calculateDelay(graph);
                if (t > 0 && t < T_max) {
                	sum+=t;
                    counter++;
                } else if (t == 0) {
                	timeout++;
                }
            } else {
            	cohesive++;
            }
            all++;
        }

            calculateAverageDelay(sum, counter, version);
            calculateReliablility(counter, all, version);
            System.out.println("");
            System.out.println("OVERLOAD - " + timeout);
            System.out.println("COHESIVE - " + cohesive);
        System.out.println("");
    }
	


    private static void generateN() {
        for (int i = 0; i < 20; i++) {
        	//System.out.print("{ ");
            for (int j = 0; j < 20; j++) {
                if (i == j) {
                    N[i][j] = 0;
                }
                else {
                    N[i][j] = generator.nextInt(100);
                }
                //System.out.print(N[i][j]+", ");
            }
            //System.out.println("}");
        }
    }

    private static boolean isCohesive(SimpleWeightedGraph<Integer, DefaultWeightedEdge> graph) {
        return new ConnectivityInspector<Integer, DefaultWeightedEdge>(graph).isGraphConnected();
    }


    private static void calculateAverageDelay(double sum, int counter, int version) {
        if (counter != 0) {
        System.out.println("MyGraph v." + version + " average delay: " + sum / counter + "   " + sum + "/" + counter);
        }
    }

    private static void calculateReliablility(double sum, int counter, int version) {
        if (counter != 0) {
        System.out.println("MyGraph v." + version + " reliability: " + sum / counter + "   " + sum + "/" + counter);
        }
    }

    private static void generateA(SimpleWeightedGraph<Integer, DefaultWeightedEdge> net) {
        restartA();
        GraphPath<Integer, DefaultWeightedEdge> path;
        ArrayList<Integer> nodes;
        int val;
        for (int i = 1; i <= 20; i++) {
            for (int j = 1; j <= 20; j++) {
                if (i == j) continue;
                path = DijkstraShortestPath.findPathBetween(net, i, j);
                nodes = (ArrayList<Integer>) path.getVertexList();
                val = N[i - 1][j - 1];
                for (int n = 0; n < nodes.size() - 1; n++) {
	                A[nodes.get(n) - 1][nodes.get(n + 1) - 1] += val;
	                //A[nodes.get(n + 1) - 1][nodes.get(n) - 1] += val;
                }
            }
        }
    }

    private static void restartA() {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                A[i][j] = 0;
            }
        }
    }

    private static void generateC() {
        for (int i = 0; i < 20; i++) {
            for (int j = i; j < 20; j++) {
                if (i == j) {

                } else if (A[i][j] != 0) {
                	Random rand = new Random();
                    C[i][j] = (A[i][j] + A[j][i] + 100 + rand.nextInt(100))*packetSize;
                    C[j][i] = C[i][j];
                }
            }
        }
    }

    private static void printMatrix(int[][] matrix) {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
            	int mil = 1000000;
                while(mil > 1) {
	                if (matrix[i][j] / mil == 0) {
	                    System.out.print("0");
	                }
	                mil = mil/10;
                }
                System.out.print(matrix[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }

    private static void calculateSumN() {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                sumN += N[i][j];
            }
        }
    }
    
    private static double calculateDelay(SimpleWeightedGraph<Integer, DefaultWeightedEdge> graph) {
        double sum = 0;
        double downSum = 0.0;
        int es;
        int et;

        for (DefaultWeightedEdge e : graph.edgeSet()) {
            es = (int) graph.getEdgeSource(e);
            et = (int) graph.getEdgeTarget(e);
            downSum = (C[es - 1][et - 1]/packetSize) - A[es - 1][et - 1];
            if (downSum == 0 || downSum < 0) {
                return 0;
            }
            sum += A[es - 1][et - 1] / downSum;
        }
        return sum / sumN;
    }

    private static void updateA(int version) {
    	int value = averageCapacity();
    	if (version == 5) {
    		C[0][12] = value;
    		C[12][0] = value;
    		C[12][9] = value;
    		C[9][12] = value;
    		C[9][6] = value;
    		C[6][9] = value;
    		C[6][3] = value;
    		C[3][6] = value;
    		C[3][0] = value;
    		C[0][3] = value;
    	} else if (version == 10) {
    		C[1][15] = value;
    		C[15][1] = value;
    		C[2][16] = value;
    		C[16][2] = value;
    		C[16][4] = value;
    		C[4][16] = value;
    		C[5][17] = value;
    		C[17][5] = value;
    		C[7][17] = value;
    		C[17][7] = value;
    		C[8][18] = value;
    		C[18][8] = value;
    		C[10][18] = value;
    		C[18][10] = value;
    		C[11][19] = value;
    		C[19][11] = value;
    		C[13][19] = value;
    		C[19][13] = value;
    		C[14][15] = value;
    		C[15][14] = value;
    	}
    	
    }


	private static int averageCapacity() {
		int counter = 0;
		int sum = 0;
		for(int i = 0; i < 19; i++) {
			for (int j = 0; j < 19; j++) {
				if (C[i][j] != 0) {
					sum += C[i][j];
					counter += 1;
				}
			}
		}
		return sum/counter;
	}
 
}