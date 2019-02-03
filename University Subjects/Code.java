import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;
 
public class IzborPredmet {
 
    public static void main(String[] args) {
       
        Scanner scan = new Scanner(System.in);
       
        int n = scan.nextInt();
        scan.nextLine();
        Map<String, Integer> map = new HashMap<>();
        String [] vertices = new String[n];
       
        for (int i=0; i < n; ++i) {
            String vertex = scan.nextLine();
            vertices[i] = vertex;
            map.put(vertex, i);
        }
       
        Graph<String> graph = new Graph(n, vertices);
       
        int m = scan.nextInt();
        scan.nextLine();
       
        for (int i=0; i < m; ++i) {
            String line = scan.nextLine();
            String parts[] = line.split(" ");
            String node = parts[0];
           
                String node1 = parts[parts.length-1];
                graph.addEdge(map.get(node1), map.get(node));
           
        }
       
        String toSearch = scan.nextLine();
        LinkedList<GraphNode<String>> list = graph.adjList[map.get(toSearch)].getNeighbors();
        System.out.println(list.get(0).getInfo());
    }
 
}
 
class GraphNode<E> {
    private int index;//index (reden broj) na temeto vo grafot
    private E info;
    private LinkedList<GraphNode<E>> neighbors;
   
    public GraphNode(int index, E info) {
        this.index = index;
        this.info = info;
        neighbors = new LinkedList<GraphNode<E>>();
    }
   
    boolean containsNeighbor(GraphNode<E> o){
        return neighbors.contains(o);
    }
   
    void addNeighbor(GraphNode<E> o){
        neighbors.add(o);
    }
   
    void removeNeighbor(GraphNode<E> o){
        if(neighbors.contains(o))
            neighbors.remove(o);
    }
   
    @Override
    public String toString() {
        String ret= "INFO:"+info+" SOSEDI:";
        for(int i=0;i<neighbors.size();i++)
        ret+=neighbors.get(i).info+" ";
        return ret;
       
    }
 
    @Override
    public boolean equals(Object obj) {
        @SuppressWarnings("unchecked")
        GraphNode<E> pom = (GraphNode<E>)obj;
        return (pom.info.equals(this.info));
    }
 
    public int getIndex() {
        return index;
    }
 
    public void setIndex(int index) {
        this.index = index;
    }
 
    public E getInfo() {
        return info;
    }
 
    public void setInfo(E info) {
        this.info = info;
    }
 
    public LinkedList<GraphNode<E>> getNeighbors() {
        return neighbors;
    }
 
    public void setNeighbors(LinkedList<GraphNode<E>> neighbors) {
        this.neighbors = neighbors;
    }
 
   
   
}
 
class Graph<E> {
 
    int num_nodes;
    GraphNode<E> adjList[];
 
    @SuppressWarnings("unchecked")
    public Graph(int num_nodes, E[] list) {
        this.num_nodes = num_nodes;
        adjList = (GraphNode<E>[]) new GraphNode[num_nodes];
        for (int i = 0; i < num_nodes; i++)
            adjList[i] = new GraphNode<E>(i, list[i]);
    }
 
    @SuppressWarnings("unchecked")
    public Graph(int num_nodes) {
        this.num_nodes = num_nodes;
        adjList = (GraphNode<E>[]) new GraphNode[num_nodes];
    }
 
    int adjacent(int x, int y) {
        // proveruva dali ima vrska od jazelot so
        // indeks x do jazelot so indeks y
        return (adjList[x].containsNeighbor(adjList[y])) ? 1 : 0;
    }
 
    void addEdge(int x, int y) {
        // dodava vrska od jazelot so indeks x do jazelot so indeks y
        if (!adjList[x].containsNeighbor(adjList[y])) {
            adjList[x].addNeighbor(adjList[y]);
        }
    }
 
    void deleteEdge(int x, int y) {
        adjList[x].removeNeighbor(adjList[y]);
    }
 
    void dfsSearch(int node) {
        boolean visited[] = new boolean[num_nodes];
        for (int i = 0; i < num_nodes; i++)
            visited[i] = false;
        dfsRecursive(node, visited);
    }
 
    void dfsRecursive(int node, boolean visited[]) {
        visited[node] = true;
        System.out.println(node + ": " + adjList[node].getInfo());
 
        for (int i = 0; i < adjList[node].getNeighbors().size(); i++) {
            GraphNode<E> pom = adjList[node].getNeighbors().get(i);
            if (!visited[pom.getIndex()])
                dfsRecursive(pom.getIndex(), visited);
        }
    }
 
    void dfsNonrecursive(int node) {
        boolean visited[] = new boolean[num_nodes];
        for (int i = 0; i < num_nodes; i++)
            visited[i] = false;
        visited[node] = true;
        System.out.println(node+": " + adjList[node].getInfo());
        Stack<Integer> s = new Stack<Integer>();
        s.push(node);
 
        GraphNode<E> pom;
 
        while (!s.isEmpty()) {
            pom = adjList[s.peek()];
            GraphNode<E> tmp=null;
            for (int i = 0; i < pom.getNeighbors().size(); i++) {
                tmp = pom.getNeighbors().get(i);
                if (!visited[tmp.getIndex()])
                    break;
            }
            if(tmp!=null&&!visited[tmp.getIndex()]){
                visited[tmp.getIndex()] = true;
                System.out.println(tmp.getIndex() + ": " + tmp.getInfo());
                s.push(tmp.getIndex());
            }
            else
                s.pop();
        }
 
    }
       
    @Override
    public String toString() {
        String ret = new String();
        for (int i = 0; i < this.num_nodes; i++)
            ret += i + ": " + adjList[i] + "\n";
        return ret;
    }
 
}