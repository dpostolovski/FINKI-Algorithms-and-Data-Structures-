//import java.util.Iterator;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Queue;
import java.util.Stack;
import java.util.LinkedList;

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
        String ret= "INDEX:"+index+" SOSEDI:";
        for(int i=0;i<neighbors.size();i++)
            ret+=neighbors.get(i).index+" ";
        return ret;

    }

    @Override
    public boolean equals(Object obj) {
        @SuppressWarnings("unchecked")
        GraphNode<E> pom = (GraphNode<E>)obj;
        return (pom.index == index);
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
        for (int i = 0; i < num_nodes; i++)
            adjList[i] = new GraphNode<E>(i, null);
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

    /************************TOPOLOGICAL SORT*******************************************************************/

    void dfsVisit(Stack<Integer> s, int i, boolean[] visited){
        if(!visited[i]){
            visited[i] = true;
            Iterator<GraphNode<E>> it = adjList[i].getNeighbors().iterator();
            System.out.println("dfsVisit: "+i+" Stack="+s);
            while(it.hasNext()){
                dfsVisit(s, it.next().getIndex(), visited);
            }
            s.push(i);
            System.out.println("--dfsVisit: "+i+" Stack="+s);
        }
    }


    void topological_sort_dfs(){
        boolean visited[] = new boolean[num_nodes];
        for(int i=0;i<num_nodes;i++){
            visited[i] = false;
        }

        Stack<Integer> s = new Stack<Integer>();

        for(int i=0;i<num_nodes;i++){
            dfsVisit(s,i,visited);
        }
        System.out.println("----Stack="+s);
        while(!s.isEmpty()){
            System.out.println(adjList[s.pop()]);
        }
    }

    /***********************************************************************************************************/

    @Override
    public String toString() {
        String ret = new String();
        for (int i = 0; i < this.num_nodes; i++)
            ret += i + ": " + adjList[i] + "\n";
        return ret;
    }

}

class DistanceCalculator extends Graph<Integer>
{
    public DistanceCalculator(int num_nodes, Integer[] list) {
        super(num_nodes, list);
    }

    int calculateDistance(int start, int end)
    {
        boolean[] visited = new boolean[num_nodes];
        Queue<GraphNode<Integer>> queue = new LinkedList<>();
        adjList[start].setInfo(0);
        ((LinkedList<GraphNode<Integer>>) queue).addLast(adjList[start]);

        visited[start] = true;

        while(!queue.isEmpty())
        {
            GraphNode<Integer> curr = queue.poll();
            if(curr.getIndex() == end)
                return curr.getInfo();

            Iterator<GraphNode<Integer>> it = curr.getNeighbors().iterator();

            while(it.hasNext())
            {
                GraphNode<Integer> toVisit = it.next();

                if(!visited[ toVisit.getIndex() ])
                {
                    toVisit.setInfo(curr.getInfo() + 1);
                    visited[toVisit.getIndex()] = true;
                    ((LinkedList<GraphNode<Integer>>) queue).addLast(toVisit);
                }
            }
        }
        return -1;

    }
}


public class CountFacebookFriends {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n;
        n = Integer.parseInt(br.readLine());

        Integer[] list = new Integer[n];
        for(int i =0; i < list.length; i++)
            list[i] = 0;

        DistanceCalculator graph = new DistanceCalculator(n,list);

        for(int i =0; i < n;i++)
        {
            int numFriends = Integer.parseInt(br.readLine());
            for(int j = 0; j < numFriends; j++)
            {
                String friend[] = br.readLine().split(" ");
                graph.addEdge(i, Integer.parseInt(friend[0]));
                graph.addEdge(Integer.parseInt(friend[0]), i);
            }
        }

        int distance = graph.calculateDistance(Integer.parseInt(br.readLine()), Integer.parseInt(br.readLine()) );
        System.out.println(distance);


    }
}
