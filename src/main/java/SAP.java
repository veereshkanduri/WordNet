import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;

public class SAP {

    private final Digraph digraph;

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        digraph = new Digraph(G);

    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        if (v > digraph.V() - 1 || w > digraph.V() - 1 || v < 0 || w < 0) {
            throw new java.lang.IllegalArgumentException();
        }

        BreadthFirstDirectedPaths bfdp1 = new BreadthFirstDirectedPaths(digraph, w);
        BreadthFirstDirectedPaths bfdp2 = new BreadthFirstDirectedPaths(digraph, v);

        int distance = 0;
        int ancestor = ancestor(v, w);
        if (ancestor == -1) {
            distance = -1;
        }
        else {
            distance = bfdp1.distTo(ancestor) + bfdp2.distTo(ancestor);
        }

        return distance;
    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {

        if (v > digraph.V() - 1 || w > digraph.V() - 1 || v < 0 || w < 0) {
            throw new java.lang.IllegalArgumentException();
        }

        BreadthFirstDirectedPaths bfdp1 = new BreadthFirstDirectedPaths(digraph, v);
        BreadthFirstDirectedPaths bfdp2 = new BreadthFirstDirectedPaths(digraph, w);

        int distance = 0;
        int ancestor = -1;

        if (v == w) {
            return v;
        }

        for (int i = 0; i < digraph.V(); i++) {
            if (bfdp1.hasPathTo(i) && bfdp2.hasPathTo(i)) {
                int currentLength = bfdp1.distTo(i) + bfdp2.distTo(i);
                if (distance == 0) {
                    distance = currentLength;
                }
                if (currentLength <= distance) {
                    distance = currentLength;
                    ancestor = i;
                }
            }
        }
        return ancestor;
    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        BreadthFirstDirectedPaths bfdp1 = new BreadthFirstDirectedPaths(digraph, v);
        BreadthFirstDirectedPaths bfdp2 = new BreadthFirstDirectedPaths(digraph, w);

        int ancestor = ancestor(v, w);
        int distance = -1;
        if (ancestor == -1) {
            return -1;
        }
        else {
            distance = bfdp1.distTo(ancestor) + bfdp2.distTo(ancestor);
        }
        return distance;
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        BreadthFirstDirectedPaths bfdp1 = new BreadthFirstDirectedPaths(digraph, v);
        BreadthFirstDirectedPaths bfdp2 = new BreadthFirstDirectedPaths(digraph, w);

        int distance = -1;
        int ancestor = -1;

        for (int i = 0; i < digraph.V(); i++) {
            if (bfdp1.hasPathTo(i) && bfdp2.hasPathTo(i)) {
                int currentLength = bfdp1.distTo(i) + bfdp2.distTo(i);
                if (distance == -1) {
                    distance = currentLength;
                }
                if (currentLength < distance) {
                    distance = currentLength;
                    ancestor = i;
                }
            }
        }
        return ancestor;
    }

    // do unit testing of this class
    public static void main(String[] args) {
    }
}