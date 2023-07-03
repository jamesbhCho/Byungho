package hw10;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class HW10 {

	class Edge{

		int src; // the actual node
		int dest;
		double weight;
		public Edge(int src, int dest, double weight) {
			this.src = src;
			this.dest = dest;
			this.weight = weight;
		}
	}
	class Graph implements Connectable{
		LinkedList<LinkedList<Edge>> adjList;
		int edgeCnt;
		int nodeCnt;
		public Graph() {
			adjList = new LinkedList<>();
		}

		/*Documentation:
		 * For this implementation of nodeSet, the objective is to 
		 * return the set of nodes. That is, in the context of my 
		 * implementation of the Graph class, return the src for 
		 * Edge class. As each of the src value represents the node,
		 * we simply traverse through the outer Linkedlist first and 
		 * because all the edges in one node has the same src value
		 * we append the first edge's src to the set and keep traversing
		 * through. 
		 * Time complexity: O(n)
		 * with n being the number of nodes. Because we are only checking the 
		 * first src value of the edge class which takes constant time, we 
		 * only need to traverse through the outer linkedlist which takes n times.
		 * Space complexity: O(n)
		 * We are allocating new space for Set. Since it is adding only the nodes
		 * it will need n amount of space.
		 */
		@Override
		public Set<Integer> nodeSet() {// return the set of nodes
			Set<Integer> sets = new HashSet<>();
			for(int i=0; i< adjList.size(); i++) {
				LinkedList<Edge> currentNode = adjList.get(i);
				sets.add(currentNode.getFirst().src); 
			}
			return sets;
		}
		/*Documentation:
		 * The implementation of getNeighbots encompasses a logic analogous 
		 * to the nodeSet. Except for a fact that instead of simply adding the node,
		 * this adds the target node for the input node. That is, for each of the 
		 * linkedlist within the outer linkedlist, if the input node is found, add 
		 * that node to the set. The -1 indicates that target node does not exist. 
		 * Because nodes are set of integers and src and dest needs to be integers, 
		 * I have indicated -1 as null. This would induce the if condition as 
		 * if the node exist and target node exist add the target node to the set.
		 * Time complexity: O(N + E)
		 * With n being the number of nodes and E being the number of edges.
		 * The searching for the right node takes O(n * E). The adding of the target
		 * node takes constant time. 
		 * Space complexity: O(n)
		 * Here like above implementation of nodeSet(), it is allocating new space for sets each
		 * time it adds. Thus it takes O(n) elements to store within Sets.
		 */
		@Override
		public Set<Integer> getNeighbors(int node) {// return the set of neighbors connected to 'node'
			Set<Integer> sets = new HashSet<>();
			for (LinkedList<Edge> edgeList : adjList) {
				for (Edge edge : edgeList) {
					if (edge.src == node && edge.dest != -1) {
						sets.add(edge.dest);
					} 

				}
			}
			return sets;
		}
		/*Documentation:
		 * for this implementation of BFS, the objective is to visit the neighbor
		 * for each node first before going onto the next node. This can be implemented
		 * by queue DS, where for each node placed in the queue, we take it out(visited) 
		 * then check for its neighbor. This checking works as traversing the graph as
		 * unvisited nodes are feeded into the queue to repeat the above process until 
		 * queue is empty. The FIFO mechanism of queue makes it feasable to traverse 
		 * the branch of the node before moving onto the next one. 
		 * Time complexity:  O(n* ( n + E))
		 * with n refering  to the number of nodes and E refering to the number of edges.
		 * The traversing through each nodes in the graph takes O(n) while getting the neighbors
		 * takes O(n+E). Thus, if we combine then it takes O(n* ( n + E)).
		 *  Space Complexity: O(n)
		 * As the space complexity heeds to the maximum space used at a given function, and 
		 * because ArrayList, and stack all have space complexity of O(n) with 
		 * n refering to the number of nodes. 
		 */
		@Override //O(n+E)
		public Iterator<Integer> breadthFirstIterator(int src) {// return the iterator on nodes in a breadth-first manner
			ArrayList<Integer> visited = new ArrayList<>();
			Queue<Integer> queue = new LinkedList<>();
			ArrayList<Integer> res = new ArrayList<>();

			visited.add(src);
			queue.add(src);

			//print current and bring the next element to the queue
			while (!queue.isEmpty()) {
				int node = queue.poll();
				res.add(node);
				visited.add(node);
				for (int neighbor : getNeighbors(node)) {
					if (!visited.contains(neighbor)) {
						queue.offer(neighbor);
					}
				}
			}

			return res.iterator();
		}
		/*Documentation:
		 * for this implementation of DFS, the logic is to start from the root node 
		 * and traverse through its neighbors, while marking them if they are unvisited
		 * and backtrack on every nodes to find the unvisited node. Hence, we need three DSs:
		 * one for checking the visted nodes, one for storing DFS order, and one for backtracking.
		 * We first push the root node into the stack to check its neighbor while doing so we mark
		 * the root node as the first value in the result array and visited. Keep looping with the 
		 * feeded stack element from getNeighbors which returns a node connected to the next stack element
		 * until there is no more left in the stack meaning we have traverse through the entire graph
		 * with no unvisited nodes.
		 * Time complexity: O(n* ( n + E))
		 * with n refering to the number of nodes stack has to go through and E refering to the 
		 * edges which is obtained via getNeighbors method. Overall, the time complexity result
		 * to O(n* (n+E)) as it needs to check every node and its neighbor which takes O(n+E).
		 * Space Complexity: O(n)
		 * As the space complexity heeds to the maximum space used at a given function, and 
		 * because ArrayList, and stack all have space complexity of O(n) with 
		 * n refering to the number of nodes. 
		 */
		@Override
		public Iterator<Integer> depthFirstIterator(int src) {// same as above in a depth-first manner

			ArrayList<Integer> visited = new ArrayList<>(); //flag check
			ArrayList<Integer> dfsArr = new ArrayList<>(); //result array
			Stack<Integer> stack = new Stack<>(); 

			//first put the root node
			stack.push(src);

			//go through the stack of unvisited node
			//and for each unvisited node add it to the 
			//result array and the visited array
			while (!stack.isEmpty()) {//O(n)
				int node = stack.pop();

				if (!visited.contains(node)) {
					dfsArr.add(node);
					visited.add(node);

					//traverse through the next node
					//and feed into the stack if not visited
					for (int neighbor : getNeighbors(node)) {//O(E) visits edges
						if (!visited.contains(neighbor)) {
							stack.push(neighbor);
						}
					}
				}
			}

			return dfsArr.iterator();
		}
		/*Documentation:
		 * For this implemenation of addNode, There are two cases:
		 * node exist, node does not exist.
		 * If the node exist, we dont do anything. If the node 
		 * do not exist we add a new node. With that being said, we first 
		 * search if there is a node like an input node within the Linkedlist by
		 * traversing through the outer linkedList and checking the node value 
		 * within the inner linkedlist of Edges. If there is a node, we dont do anything.
		 * If there isn't we add the node. The way I initialize a new node is by 
		 * creating a new edge class which is defined by new edge(Source, Target, Weight).
		 * Since we are adding just the node and not linking it with any other node, 
		 * I have passed only the input node as its source node and made the rest -1. 
		 * Conventionally, 0 should be fine as weight 0 and target 0 does not make sense, but
		 * because the nodes are set of integers, I just defined positive integers as the
		 * nodes and its weight, and negative to indicate null.
		 * Time complexity: O(n)
		 * We are only traversing through the nodes via only checking for the first element of 
		 * the inner linkedlist. Therefore, it runs n times with n being the number of nodes.
		 * Space complexity: O(1)
		 * with n being the number of node to be added. Since it is a single operation where 
		 * node is being added one by one to the pre-existing space of memory, it take constant 
		 * space of 1 for each addition of the node.
		 */
		@Override
		public void addNode(int node) { // add a new vertex into the current graph.
			boolean nodeExists = false;
			for (LinkedList<Edge> edgeList : adjList) {
				if (!edgeList.isEmpty() && edgeList.getFirst().src == node) {//duplicate node 
					nodeExists = true;
					break;
				}
			}
			if (!nodeExists) {
				Edge newNode = new Edge(node, -1, -1);//-1 indicates null target node and null weight
				LinkedList<Edge> nodeEdges = new LinkedList<>();
				nodeEdges.add(newNode);
				adjList.add(nodeEdges);
				nodeCnt++;
			}
		}


		/*Documentation:
		 * For this implementation of addEdge, there are four cases to consider:
		 * 1. there is already an edge between the two
		 * 2. there are nodes for each of the source and target
		 * 3. there are nodes for one of them
		 * 4. there are no nodes
		 * For each cases different measures need to be taken. That is, for the first case,
		 * we would need to iterate through the adjacency list and for every linkedList<Edge>
		 * inside them we are traversing through the number of nodes(n)
		 * while also traversing through the edges(E) O(n*E). If there is source and target that matches
		 * with a certain edge, return false.
		 * For the second to fourth case, I have made variables of LinkedList<Edge> for source and target node
		 * to determine their existance. To elaborate, we iterate through the adjacency list and 
		 * grab the first Edge class for each node as the source for every edge within that one node is all the same.
		 * Compare that to the input parameters and assign to each of the variables. Depending on if they are null or not,
		 * the rest of the code will either add the edge only or add the node as well. 
		 * Time complexity: O(n+E)
		 * For each node in the adjacency list it goes through its nested linkedlist(Edges) to the examine if there is an edge 
		 * or not. In the worst case, it has to go through all edges as well making the time complexity O(n+E)
		 * Space complexity: O(1)
		 * The additional edges or nodes only add single new object to the pre-existing space of Graph and Edges.
		 * Thus, constant space compleixty.
		 */
		@Override //O(n*E)
		public boolean addEdge(int source, int target, double w) {// add a new edge. also add non-existing nodes. return false if edge already exists. 

			//Edge exists
			for (LinkedList<Edge> edgeList : adjList) {//for each ll in adjList O(n) # of nodes
				for (Edge edge : edgeList) {//for each edge in edgeList O(E) # of edges
					//if there is an already an edge
					if ((edge.src == source && edge.dest == target) || (edge.src == target && edge.dest == source)) {
						return false; 
					}
				}
			}

			//1. source node or target node does not exist
			//2. source node and target node does not exist
			//3. they both exist

			LinkedList<Edge> srcNode = null;
			LinkedList<Edge> tgtNode = null;

			//checking for the existance of the source and the target node
			for (LinkedList<Edge> edgeList : adjList) {
				if (!edgeList.isEmpty()) {
					if (edgeList.getFirst().src == source) {
						srcNode = edgeList;
					} else if (edgeList.getFirst().src == target) {
						tgtNode = edgeList;
					}
				}
				//if neither of them exist
				if (srcNode != null && tgtNode != null) {
					break;
				}
			}

			//create new node if they dont exist
			if (srcNode == null) {
				srcNode = new LinkedList<>();
				srcNode.add(new Edge(source, target, w));
				adjList.add(srcNode);
				nodeCnt++;

			} else {  //source node exist
				srcNode.add(new Edge(source, target, w));
			}
			//no target node
			if (tgtNode == null) {
				tgtNode = new LinkedList<>();
				tgtNode.add(new Edge(target, source, w));
				adjList.add(tgtNode);
				nodeCnt++;
			} else {//target node exist
				tgtNode.add(new Edge(target, source, w));
			}
			//for each edge added(current and target node)
			edgeCnt += 2;
			return true;
		}

		/*Documentation:
		 * for this implementation of remove node, the logic of traversal is tantamount
		 * to above methods of traversing through the nested linkedlist. The only difference
		 * is using the iterator to go to the next element. There are two criteria that needs to be
		 * considered for the removal of a node: 
		 * 1. it is just a single node 
		 * 2. it is a node with edges
		 * For the first case we simly just remove it from the outer Linkedlist.
		 * However, for the second cases we have to check for every edge and remove
		 * everey single one associated to that node. Hence, using the iterator we 
		 * sever all connections to that node.
		 * Time complexity: O(n*E)
		 * The searching for that specific nodes takes n times with n being the number of nodes, and 
		 * deleting the edges takes E times with respect to number of edges. 
		 */
		@Override
		public boolean removeNode(int node) { // remove node. return false if node doesn't exist.
			int removed;
			boolean res = false;
			for (LinkedList<Edge> edgeList : adjList) {
				removed = edgeList.getFirst().src;
				//deleting edges
				for(Edge edge : edgeList) {
					if(edge.src == node) {
						edgeList.remove();
						edgeCnt--;
					}	
				}if(edgeList.isEmpty() && removed == node) {//removing node
					adjList.remove();
					nodeCnt--;
					res = true;
					break;
				}

			}
			return res;
		}



		/*Documentation:
		 * For this implementation of removeEdge, it is analagous to the removeNode as
		 * removeNode method removes edges as well. The only difference is that removeNode 
		 * method removes edges associated with that node while removeEdge sever connection 
		 * between the two node parameters. It is done so via the condition that checks 
		 * if the edge is associated with the input source and input target or vice versa. 
		 * It checks for both as removing an edge between two nodes recquire severing from
		 * both direction. Therefore, it iterates through the nodes and for ech edges it contains
		 * it checks for the aforementioned condition and removes it.
		 * Time complexity: O(n*E)
		 * with n refering to the number of nodes and E refering to the number of edges. 
		 * For every nodes it checks every edges associated with that node to check the 
		 * edge to remove between the two parameters.
		 * Space complexity: O(1)
		 * There is no new space being allocated. 
		 */
		@Override
		public boolean removeEdge(int source, int target) { // remove edge. return false if edge doesn't exist.
			for (LinkedList<Edge> edgeList : adjList) {
				Iterator<Edge> iterator = edgeList.iterator();
				while (iterator.hasNext()) {
					Edge edge = iterator.next();
					if ((edge.src == source && edge.dest == target) || (edge.src == target && edge.dest == source)) {//finding the edge connecting source and target
						iterator.remove();
						edgeCnt--;
						return true;
					}
				}
			}
			return false;
		}
		/*Documentation:
		 * isEdge checks for the connection between two nodes. That is, iterate through the nodes
		 * and check for the edges between the two nodes and sine it is undirected, if it one way(source -> target)
		 * matches it is considered to be an edge. To surmise, traverse through the nodes and seach for 
		 * the edge containing input source and target as its src and dest and return boolean value 
		 * in accordance.
		 * Time complexity: O(n+E)
		 * Like described above it needs to traverse through the nodes and to search for the edge between
		 * two nodes it needs go through the edges. Therefore O(n*E)
		 * Space complexity: O(1)
		 * There is no new space being allocated. 
		 */
		@Override 
		public boolean isEdge(int source, int target) {// Returns true if source-target is a valid edge. The source-target order doesn't matter in undirected graphs.
			for (LinkedList<Edge> edgeList : adjList) {
				Iterator<Edge> iterator = edgeList.iterator();
				while (iterator.hasNext()) {
					Edge edge = iterator.next();
					if ((edge.src == source && edge.dest == target)) {//if edge exists	
						return true;
					}
				}
			}
			return false;
		}
		/*Documentation:
		 * for this implementation of getWeight, it needs to traverse through an extent 
		 * to edge as it needs to check its weight. Therefore, similar to most of the 
		 * traversals above, it goes through the nodes and checks if the source and 
		 * the target matches via going though each Edge and checking its src values and
		 * dest values. When it matches, simply return its weight.
		 * Time complexity: O(n+E)
		 * As mentioned, it needs to search for the edge associated with the two nodes
		 * to get the weight. 
		 * Space complexity: O(1)
		 * There is no new space being allocated.  
		 */
		@Override 
		public double getWeight(int source, int target) {// return weight of edge (what if edge doesn't exist?).
			for (LinkedList<Edge> edgeList : adjList) {
				Iterator<Edge> iterator = edgeList.iterator();
				while (iterator.hasNext()) {
					Edge edge = iterator.next();
					if ((edge.src == source && edge.dest == target)) {//if edge exists	
						return edge.weight;
					}
				}
			}//negative = does not exist
			return -1;
		}
		/*Documentation:
		 * similar to the one above for getWeight, it needs to traverse through the nodes
		 * and arrive at the Edge where it satisfies its source as the input source and its 
		 * dest and its input target and vice versa. Thus, if it is found it is simply 
		 * overriden via assigning the weight as its input w. If it does not fall
		 * into the condition we simply return false as the edge does not exist.
		 * Time complexity: O(n+E)
		 * As mentioned, it needs to search for the edge associated with the two nodes
		 * to get the edge which takes n times with n refering to the number of nodes and
		 * in order to set the weight at the designated position within
		 * the list of edges which take E times with e refering to the number of edges. 
		 * Space complexity: O(1)
		 * There is no new space being allocated.  
		 */
		@Override
		public boolean setWeight(int source, int target, double w) {// set the weight. existing weights are overwritten. if edge doesn't exist, return false;
			boolean res = false;
			for (LinkedList<Edge> edgeList : adjList) {
				Iterator<Edge> iterator = edgeList.iterator();
				while (iterator.hasNext()) {
					Edge edge = iterator.next();
					if ((edge.src == source && edge.dest == target) || (edge.src == target && edge.dest == source)) {//if edge exists
						edge.weight	= w;
						res = true;
					}
				}
			}
			return res;
		}
		/*Documentation:
		 * I have applied the logic of adding and subtracting nodeCnt whenever 
		 * it needs to be meddled. That is, for the addNode() it would increment 
		 * the nodeCnt and vice versa for the removeNode().
		 * Time complexity: O(1)
		 * It takes constant time as it is reserved as a variable that can be 
		 * accessed within this class.
		 * Space complexity: O(1)
		 * There is no new space being allocated.  
		 */
		@Override
		public int numNodes() {// return the total number of nodes. should have O(1) time complexity.
			return nodeCnt;
		}
		/*Documentation:
		 * I have applied the logic of adding and subtracting edgeCnt whenever 
		 * it needs to be meddled. That is, for the addEdge() it would increment 
		 * the edgeCnt and vice versa for the removeNode() and removeEdge(). The only 
		 * difference is dividing by two as adjacency list allows the source node 
		 * and the target node to have edge for each of them if they are connected 
		 * which makes it two edge when in reality there is a single connection 
		 * between the two nodes.
		 * Time complexity: O(1)
		 * It takes constant time as it is reserved as a variable that can be 
		 * accessed within this class.
		 * Space complexity: O(1)
		 * There is no new space being allocated.  
		 */
		@Override
		public int numEdges() {// return the total number of edges. should have O(1) time complexity.
			return edgeCnt/2;
		}

	}
	public static void main(String[] args) {
		HW10 hw = new HW10();
		Graph graph = hw. new Graph();
		Graph graph2 = hw. new Graph();
		Graph graph3 = hw. new Graph();
		/*
		 graph.addNode(1); 
		 //System.out.println(graph.numNodes()); 
		 graph.addEdge(1, 2,12); 
		 System.out.println("are the nodes connected? " + graph.isEdge(1,2));//true 
		 System.out.println("num of nodes: " + graph.numNodes()); //2
		 System.out.println("num of edges: " + graph.numEdges()); //1
		 System.out.println("set of nodes are : " + graph.nodeSet());
		 System.out.println("the set of neighbors are: " + graph.getNeighbors(1));
		 System.out.println("did the nodes successfully get removed? " +graph.removeNode(2)); //true 
		 System.out.println("num of nodes: " +graph.numNodes());//1 
		 System.out.println("are the nodes connected? " +graph.isEdge(1, 2)); //false 
		 System.out.println("num of edges: " +graph.numEdges()); //0
		 System.out.println("weight of the edge between the two is " +graph.getWeight(1, 2));//-1 = does not eixst
		 System.out.println(graph.addEdge(2, 1, 50));//one node added; true
		 System.out.println(graph.numNodes()); 
		 System.out.println(graph.numEdges());
		 System.out.println(graph.isEdge(1, 2)); //true
		 System.out.println(graph.setWeight(1, 2, 600)); //true
		 System.out.println(graph.getWeight(1, 2));
		 System.out.println(graph.getWeight(2, 1));
		 */
		
		graph2.addEdge(1, 2, 2);
		graph2.addEdge(2, 3, 3);
		graph2.addEdge(1, 4, 4);
		Iterator<Integer> dfs = graph2.depthFirstIterator(1);
		System.out.print("dfs ");
		while(dfs.hasNext()) {
			System.out.print(dfs.next());
		}
		System.out.println();
		graph3.addEdge(1, 2, 2);
		graph3.addEdge(2, 3, 3);
		graph3.addEdge(1, 4, 4);
		Iterator<Integer> bfs = graph2.breadthFirstIterator(1);
		System.out.print("bfs ");
		while(bfs.hasNext()) {
			System.out.print(bfs.next());
		}
		 
	}

}
