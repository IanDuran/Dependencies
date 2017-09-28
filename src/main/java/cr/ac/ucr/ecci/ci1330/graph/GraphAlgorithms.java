package cr.ac.ucr.ecci.ci1330.graph;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class GraphAlgorithms {
    /**
     * Returns whether the graph has cycles or not.
     * First it checks if the graph is not empty, then , is it is not empty, it creates an Iterator,
     * a Set of visited nodes and calls the private method dfsCycles with the first node, a null value for the
     * precursor, the graph and the visited set. Then it returns the opposite answer of the dfsCycles method
     * @return false if the flag has cycles, true otherwise.
     */
    public static <V> boolean isGraphAcyclic(AdjacencyList<V> graph) {
        boolean hasCycles = false;
        if(!graph.isEmpty()){
            Iterator<V> i = graph.iterator();
            Set<V> visited = new HashSet<V>();
            while(i.hasNext() && !hasCycles)
                hasCycles = dfsCycles(i.next(), null, graph, visited);
        }
        return !hasCycles;
    }

    /**
     * Private recursive method that tells if a graph has cycles using Depth First Search. It first creates the boolean value for the return,
     * then adds the current node to the set of visited nodes, after that, it gets the nodes adjacent to the
     * current one and for every one of them it checks if they are not visited, if they aren't, it makes the recursive
     * call with the corresponding adjacent node, the current node as the precursor, the graph and the visited set.
     * If the adjacent node was already visited, it checks if it wasn't the precursor, if it wasn't, it means the
     * algorithm ha reached a previously visited node, and thus, it has a cycle, so it returns true.
     * @param node current node being visited
     * @param precursor node visited immediately prior to the current one
     * @param graph graph that contains the nodes
     * @param visited set that contains the visited nodes
     * @param <V> class of the nodes
     * @return true if the graph has cycles, false otherwise
     */
    private static <V> boolean dfsCycles(V node, V precursor, AdjacencyList<V> graph, Set<V> visited){
        boolean hasCycle = false;
        visited.add(node);
        List<V> adjacentNodes = graph.getAdjacentNodes(node);
        for (int i = 0; i < adjacentNodes.size(); i++) {
            if (!visited.contains(adjacentNodes.get(i))){
                hasCycle = dfsCycles(adjacentNodes.get(i), node, graph, visited);
            }else if(!adjacentNodes.get(i).equals(precursor)){
                hasCycle = true;
            }
        }
        return hasCycle;
    }
}
