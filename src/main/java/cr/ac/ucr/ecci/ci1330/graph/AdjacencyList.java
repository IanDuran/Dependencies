package cr.ac.ucr.ecci.ci1330.graph;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class AdjacencyList<V> {

    private final int INITIAL_SIZE = 20;
    private final int ENLARGING_SIZE = 5;
    private boolean directed;
    private boolean weighted;
    private V[] values;
    private List<Node>[] adjacencies;
    private int vertexes;
    private int edges;


    /**
     * Constructor for the AdjacencyList class.
     * @param directed if the graph will be directed.
     * @param weighted if the graph will have weights.
     */
    public AdjacencyList(boolean directed, boolean weighted){
        this.directed = directed;
        this.weighted = weighted;
        values = (V[]) new Object[INITIAL_SIZE];
        adjacencies = new LinkedList[INITIAL_SIZE];
        vertexes = 0;
        edges = 0;
    }

    /**
     * Returns if the graph is directed or not.
     * @return true of the graph is directed, false otherwise.
     */
    public boolean isDirected() {
        return directed;
    }

    /**
     * Adds a node to the graph, if it's not already in it.
     * If the values array is full, it is enlarged, so as the adjacencies array.
     * @param value the value to add.
     */
    public void addNode(V value) {
        if(!this.contains(value)){
            if(vertexes >= values.length)
                this.enlarge();

            adjacencies[vertexes] = new LinkedList<Node>();
            values[vertexes] = value;
            vertexes++;
        }
    }

    /**
     * Adds two nodes to the graph and an edge between them with no weight
     * if the graph is not weighted.
     * @param value1 the first value to add.
     * @param value2 the second value to add.
     */
    public void addNodes(V value1, V value2) {
        if(!this.weighted){
            this.addNode(value1);
            this.addNode(value2);
            this.addEdge(value1, value2);
        }
    }

    /**
     * Adds and edge between the two given nodes if the nodes are contained in the graph and the graph is not weighted
     * If the conditions are met, it gets the indexes of the two given nodes and adds a new adjacency to the list
     * that corresponds to the first one with the second one as destination. If the graph is not directed, it does
     * the same in the opposite way.
     * @param value1 the first value.
     * @param value2 the second value.
     */
    public void addEdge(V value1, V value2) {
        if(!this.weighted && this.contains(value1) && this.contains(value2) && !this.areLinked(value1, value2)){
            int firstIndex = this.getIndex(value1);
            int secondIndex = this.getIndex(value2);
            if(firstIndex != -1 && secondIndex != -1){
                adjacencies[firstIndex].add(new Node(secondIndex, 1));
                if(!this.isDirected())
                    adjacencies[secondIndex].add(new Node(firstIndex, 1));
            }
            edges++;
        }
    }

    /**
     * Adds and edge between the two given nodes with the weight passed if the graph is weighted.
     * First it checks the above conditions, then it gets the indexes of the two nodes and
     * adds an adjacency in the list of the first one with the second one as destination.
     * If the graph is not directed, it does it hte other wasy arround as well.
     * @param value1 the first value.
     * @param value2 the second value.
     * @param weight the weight
     */
    public void addEdge(V value1, V value2, double weight) {
        if(this.weighted && this.contains(value1) && this.contains(value2) && !this.areLinked(value1, value2)) {
            int firstIndex = this.getIndex(value1);
            int secondIndex = this.getIndex(value2);
            if (firstIndex != -1 && secondIndex != -1) {
                adjacencies[firstIndex].add(new Node(secondIndex, weight));
                if (!this.isDirected())
                    adjacencies[secondIndex].add(new Node(firstIndex, weight));
            }
            edges++;
        }
    }

    /**
     * Tells if the graph contains the given node.
     * @param value the value to search.
     * @return true if the node is in the graph, false otherwise.
     */
    public boolean contains(V value) {
        return this.getIndex(value) != -1;
    }

    /**
     * Tells if the given nodes are linked by an edge.
     * @param value1 the first value.
     * @param value2 the second value.
     * @return true if the nodes are linked, false otherwise.
     */
    public boolean areLinked(V value1, V value2) {
        boolean linked = false;
        if(this.contains(value1) && this.contains(value2) && this.getWeight(value1, value2) != -1){
            linked = true;
        }
        return linked;
    }

    /**
     * Returns the weight between two given nodes, if the nodes are in the graph and if they are linked, otherwise
     * it returns -1. If the above conditions are met, it gets the indexes of the nodes, searches the list of the first
     * looking for the value of the second and then returns the weight of the adjacency.
     * @param value1 the first value.
     * @param value2 the second value.
     * @return the weight of the edge between the to given nodes.
     */
    public double getWeight(V value1, V value2) {
        double weight = -1;
        if(this.weighted && this.contains(value1) && this.contains(value2)){
            int firstIndex = this.getIndex(value1);
            int secondIndex = this.getIndex(value2);
            int counter = 0;
            while (weight == -1 && counter < adjacencies[firstIndex].size()) {
                if (adjacencies[firstIndex].get(counter).getValue() == secondIndex)
                    weight = adjacencies[firstIndex].get(counter).getWeight();

                counter++;
            }
        }
        return weight;
    }

    /**
     * Removes a node from the graph.
     * If the node is contained within the graph, it gets the index of the node, then it calls the moveDown
     * method to get rid of the node and its adjacency list. Then it goes over every adjacency checking the destinations.
     * If the destination is the eliminated node, the adjacency is removed from the list, if the destination is a node with
     * a higher position than the eliminated node, the destination number is decreased by one, since all the values
     * after the eliminated node are one position down from their previous one.
     * @param value the value to remove.
     */
    public void removeValue(V value) {
        if(this.contains(value)){
            int index = this.getIndex(value);
            this.moveDown(index);
            vertexes--;
            for(int i = 0; i < vertexes; i++){
                List<Node> currentAdjacencies = adjacencies[i];
                for(int j = 0; j < currentAdjacencies.size(); j++){
                    if(currentAdjacencies.get(j).getValue() == index){
                        currentAdjacencies.remove(j);
                        j--;
                    }else if(currentAdjacencies.get(j).getValue() > index){
                        currentAdjacencies.get(j).setValue(currentAdjacencies.get(j).getValue() - 1);
                    }
                }
            }
        }
    }

    /**
     * Removes an edge from between the two given nodes, if the nodes exist in the graph
     * and are linked. If the conditions are met, it gets the indexes of the two nodes.
     * Then it searches the adjacency list of the first node looking for the second as destination,
     * if it encounters the adjacency, it's eliminated. If the graph is not directed, it does the same
     * the other way around as well.
     * @param value1 the first value.
     * @param value2 the second value.
     */
    public void removeEdge(V value1, V value2) {
        if(this.contains(value1) && this.contains(value2) && this.areLinked(value1, value2)){
            int firstIndex = this.getIndex(value1);
            int secondIndex = this.getIndex(value2);
            int counter = 0;
            boolean eliminated = false;
            while (!eliminated && counter < adjacencies[firstIndex].size()) {
                if (adjacencies[firstIndex].get(counter).getValue() == secondIndex) {
                    adjacencies[firstIndex].remove(counter);
                    eliminated = true;
                }
                counter++;
            }
            if (!isDirected()) {
                counter = 0;
                eliminated = false;
                while (!eliminated && counter < adjacencies[secondIndex].size()) {
                    if (adjacencies[secondIndex].get(counter).getValue() == firstIndex) {
                        adjacencies[secondIndex].remove(counter);
                        eliminated = true;
                    }
                    counter++;
                }
            }
            edges--;
        }
    }

    /**
     * Returns a list with all the nodes adjacent to the given one, if the node is contained in the graph.
     * First it creates a new LinkedList. Then, if the node is in the graph, it gets the node's index
     * and adds the value of every adjacency represented in the list.
     * @param value the value.
     * @return a list with all the nodes adjacent to the given one.
     */
    public List<V> getAdjacentNodes(V value) {
        List<V> adjacentNodesValues = new LinkedList<V>();
        if(this.contains(value)){
            int index = this.getIndex(value);
            List<Node> adjacentNodes = adjacencies[index];
            if(adjacentNodes != null){
                for(int i = 0; i < adjacentNodes.size(); i++){
                    adjacentNodesValues.add(values[adjacentNodes.get(i).getValue()]);
                }
            }
        }
        return adjacentNodesValues;
    }

    /**
     * Returns a list with all the values in the graph.
     * @return a list with all the values in the graph.
     */
    public List<V> getValues() {
        List<V> valueList = new LinkedList<V>();
        for(int i = 0; i < vertexes; i++)
            valueList.add(values[i]);

        return valueList;
    }

    /**
     * Returns the number of nodes in the graph.
     * @return the number of nodes in the graph.
     */
    public int V() {
        return vertexes;
    }

    /**
     * Returns the number of edges in the graph.
     * @return the number of edges in the graph.
     */
    public int E() {
        return edges;
    }

    /**
     * Returns a new instance of the AdjacencyListIterator class.
     * @return a new instance of the AdjacencyListIterator class.
     */

    public Iterator<V> iterator() {
        return new AdjacencyListIterator(values, vertexes);
    }

    /**
     * Returns the size of the graph.
     * @return the size of the graph.
     */
    public int size() {
        return vertexes;
    }

    /**
     * Returns the total weight of all the edges of the graph.
     * @return the total weight of all the edges of the graph.
     */
    public double getWeight() {
        double totalWeight = 0;
        if(this.weighted){
            for(int i = 0; i < vertexes; i++){
                for(int j = 0; j < adjacencies[i].size(); j++){
                    totalWeight += adjacencies[i].get(j).getWeight();
                }
            }
            if(!this.isDirected())
                totalWeight /= 2;
        }
        return totalWeight;
    }

    /**
     * Tells whether the graph is empty or not.
     * @return true if the graph is empty, false otherwise.
     */
    public boolean isEmpty() {
        return vertexes == 0;
    }

    /**
     * Clears the graph by resetting all of its variables.
     */
    public void clear() {
        values = (V[]) new Object[INITIAL_SIZE];
        adjacencies = new LinkedList[INITIAL_SIZE];
        vertexes = 0;
        edges = 0;
    }

    /**
     * Returns an array with all the values of the nodes.
     * @return an array with all the values of the nodes.
     */
    public V[] getValuesAsArray() {
        V[] returnedValues = (V[]) new Object[vertexes];
        for(int i = 0; i < vertexes; i++){
            returnedValues[i] = values[i];
        }
        return returnedValues;
    }

    /**
     * Returns a double matrix that represents the graph.
     * @return a double matrix that represents the graph.
     */
    public double[][] getGraphStructureAsMatrix() {
        double[][] graphMatrix = new double[vertexes][vertexes];
        for(int i = 0; i < vertexes; i++){
            for(int j = 0; j < vertexes; j++){
                if(i != j){
                    graphMatrix[i][j] = this.getWeight(values[i], values[j]);
                }
            }
        }
        return graphMatrix;
    }

    /**
     * Private method used to enlarge the values array and the adjacencies list array
     * of they ever became full.
     */
    private void enlarge(){
        V[] newArray = (V[]) new Object[values.length + ENLARGING_SIZE];
        List<Node> newAdjacencies[] = new LinkedList[adjacencies.length + ENLARGING_SIZE];
        for(int i = 0; i < newArray.length; i++) {
            newArray[i] = values[i];
            newAdjacencies[i] = adjacencies[i];
        }
        values = newArray;
        adjacencies = newAdjacencies;
    }

    /**
     * Private method that moves down all the elements in the values array and the adjacencies list array.
     * Used when the remove method is called.
     * @param index the index of the object to be eliminated.
     */
    private void moveDown(int index){
        for(int i = index; i < vertexes - 1; i++) {
            values[i] = values[i + 1];
            adjacencies[i] = adjacencies[i + 1];
        }
    }

    /**
     * Private method that searches th values array for a given value and then returns its position in the values array.
     * @param value searched value
     * @return position of the value in the values array.
     */
    private int getIndex(V value){
        int index = -1;
        int counter = 0;
        while(counter < vertexes && index == -1){
            if(values[counter].equals(value))
                index = counter;

            counter++;
        }
        return index;
    }

    /**
     * Private class used to represent the adjacencies of a node.
     * It has a double for the weight and an integer that represents the destination node
     * as well as their setters and getters.
     */
    private class Node{
        private int value;
        private double weight;

        public Node(int value, double weight){
            this.value = value;
            this.weight = weight;
        }

        public Node(int value){
            this.value = value;
            this.weight = 0;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public double getWeight() {
            return weight;
        }

        public void setWeight(double weight) {
            this.weight = weight;
        }
    }

    /**
     * Iterator class for the AdjacencyList.
     */
    private class AdjacencyListIterator implements Iterator<V> {
        private int index;
        private int maxIndex;
        private V[] values;

        public AdjacencyListIterator(V[] values, int maxIndex){
            this.values = values;
            this.maxIndex = maxIndex;
            this.index = 0;
        }

        public boolean hasNext() {
            return index < maxIndex;
        }

        public V next() {
            V next = values[index];
            index++;
            return next;
        }

        public void remove() {

        }
    }
}