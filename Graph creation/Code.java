import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class UnorientedGraph
{
    private boolean[][] addjacencyMatrix;

    public UnorientedGraph(int n) {
        addjacencyMatrix = new boolean[n][n];
    }

    public void addEdge(int j, int k)
    {
        addjacencyMatrix[j][k] = addjacencyMatrix[k][j] = true;
    }

    public boolean adjacent(int j, int k)
    {
        return (addjacencyMatrix[j][k] || addjacencyMatrix[k][j]);
    }

    public void deleteEdge(int j, int k)
    {
        addjacencyMatrix[k][j] = addjacencyMatrix[j][k] = false;
    }

    public String printNode(int n)
    {
        char[] arr = new char[1];
        arr[0] = ((char) ('A' + n));
        return String.copyValueOf(arr);
    }

    public void printMatrix()
    {
        for(int i=0; i < addjacencyMatrix.length; i++)
        {
            for(int j = 0; j < addjacencyMatrix[i].length; j++)
            {
                if(addjacencyMatrix[i][j])
                    System.out.print("1 ");
                else
                    System.out.print("0 ");
            }
            System.out.println("");
        }
    }
}

public class GraphCreate {

    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n;
        n = Integer.parseInt(br.readLine());

        UnorientedGraph graph = new UnorientedGraph(0);
        for(int i =0; i < n;i++)
        {
            StringTokenizer st= new StringTokenizer(br.readLine(), " ");
            String action = st.nextToken();

            if(action.equals("CREATE"))
            {
                graph = new UnorientedGraph(Integer.parseInt(st.nextToken()));
            }

            if(action.equals("ADDEDGE"))
            {
                graph.addEdge( Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken() ) );
            }

            if(action.equals("PRINTNODE"))
            {
                System.out.println(graph.printNode(Integer.parseInt(st.nextToken())));
            }

            if(action.equals("ADJACENT"))
            {
                if(graph.adjacent( Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())))
                    System.out.println("1");
                else
                    System.out.println("0");
            }

            if(action.equals("DELETEEDGE"))
            {
                graph.deleteEdge(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
            }

            if(action.equals("PRINTMATRIX"))
            {
                graph.printMatrix();
            }
        }
    }
}
