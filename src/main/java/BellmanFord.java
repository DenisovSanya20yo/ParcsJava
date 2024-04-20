import java.util.Random;

public class BellmanFord {
    class Edge {
        int src, dest, weight;
        Edge() {
            src = dest = weight = 0;
        }
    };

    int V, E;
    Edge edge[];

    // Constructor
    BellmanFord(int v, int e) {
        V = v;
        E = e;
        edge = new Edge[e];
        generateEdges();
    }

    // Generates edges with random weights between -10 and 10
    private void generateEdges() {
        Random rand = new Random();

        for (int i = 0; i < E; i++) {
            edge[i] = new Edge(); // Initialize the Edge object
            edge[i].src = rand.nextInt(V); // Random source vertex
            edge[i].dest = rand.nextInt(V); // Random destination vertex
            edge[i].weight = rand.nextInt(21) - 10; // Random weight between -10 and 10
        }
    }

    void BellmanFord(BellmanFord graph, int src) {
        int V = graph.V, E = graph.E;
        int dist[] = new int[V];

        for (int i=0; i<V; ++i)
            dist[i] = Integer.MAX_VALUE;
        dist[src] = 0;

        for (int i=1; i<V; ++i) {
            for (int j=0; j<E; ++j) {
                int u = graph.edge[j].src;
                int v = graph.edge[j].dest;
                int weight = graph.edge[j].weight;
                if (dist[u]!=Integer.MAX_VALUE &&
                        dist[u]+weight<dist[v])
                    dist[v]=dist[u]+weight;
            }
        }

        for (int j=0; j<E; ++j) {
            int u = graph.edge[j].src;
            int v = graph.edge[j].dest;
            int weight = graph.edge[j].weight;
            if (dist[u] != Integer.MAX_VALUE &&
                    dist[u]+weight < dist[v])
                System.out.println("Graph contains negative weight cycle");
        }
        printArr(dist, V);
    }

    void printArr(int dist[], int V) {
        System.out.println("Vertex   Distance from Source");
        for (int i=0; i<V; ++i)
            System.out.println(i+"\t\t"+dist[i]);
    }

    public static void main(String[] args) {
        int V = 1000; // Number of vertices
        int E = 2000; // Number of edges

        BellmanFord graph = new BellmanFord(V, E);
        graph.BellmanFord(graph, 0);
    }
}
