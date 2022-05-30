package sociopath;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *  
 * @author Lidros
 * THIS IS NOT RELATED DIRECTLY TO SOCIOPATH ASSIGNMENT, THIS IS FOR REFERENCE PURPOSE FOR US
 */

import java.util.ArrayList;

public class WeightedGraph<T extends Comparable<T>, N extends Comparable<N>> {
    
    protected class Vertex <T extends Comparable<T>, N extends Comparable<N>>{    //T usually in String (to store name), N usually in integer-Weight (to store weight of the vertex)
        protected T vertexInfo;
        protected int indeg,outdeg;
        protected Vertex<T,N> nextVertex;
        protected Edge<T,N> firstEdge;

        public Vertex(T vertexInfo, Vertex<T, N> nextVertex) {
            this.vertexInfo = vertexInfo;
            this.nextVertex = nextVertex;
        }

        public Vertex(T vertexInfo, Vertex<T, N> nextVertex, Edge<T, N> firstEdge) {
            this.vertexInfo = vertexInfo;
            this.nextVertex = nextVertex;
            this.firstEdge = firstEdge;
        }
    }
    
    private class Edge <T extends Comparable<T>, N extends Comparable<N>>{
        private Vertex<T,N> toVertex;   //the vertex the edge points onto
        private N weight;   //the weight of the edge
        private Edge<T,N> nextEdge; //the next edge node (of this vertex) linked with this current edge
        
        public Edge(Vertex<T, N> toVertex, N weight, Edge<T, N> nextEdge) {
            this.toVertex = toVertex;
            this.weight = weight;
            this.nextEdge = nextEdge;
        }
    }
    
    protected Vertex<T,N> head;   //the head of the graph
    protected int size;   //size of the graph
    public WeightedGraph() {
    }
    
    public int getSize() {
        //returns the number of vertices in the graph
        return size;
    }

    public Vertex<T, N> getHead() {
        return head;
    }
    
    public T getHeadInfo() {
        //return the info(name) of the vertex
        return head.vertexInfo;
    }
    
    /**
     * Identifies whether a vertex exist in the graph based on the vertexInfo given
     * @param vertexInfo
     * @return the presence of the vertex
     */
    public boolean hasVertex(T vertexInfo) {
        //checks if the vertex exists (based on the vertexInfo)
        if (head == null)   //if graph is empty
            return false;
        
        Vertex<T, N> currentVertex = head;  //establish a pointer to traverse throughout vertices
        while (currentVertex != null) { //traverse the vertex nodes
            if (currentVertex.vertexInfo.compareTo(vertexInfo) == 0) //if current name of vertex same as requested, return true
                return true;
            currentVertex = currentVertex.nextVertex;   //pointer pointing to next vertex
        }
        return false;   //return false when there's no requested vertex name in the graph
    }
    
    /**
     * Identifies the presence of an edge in the graph based on from's info and to's info
     * 'from' is the vertex where the edge originated, 'to' is the destination of the edge
     * need to traverse through all vertices
     * for each vertex traversed, need to traverse through all its edges
     * @param fromInfo
     * @param toInfo
     * @return the presence of the edge
     */
    public boolean hasEdge(T fromInfo, T toInfo) {
        if (head == null)   //if graph is empty
            return false;   //cus nothing to be checked
        
        //traversing through the vertices, through all the vertices' edges
        Vertex<T, N> currentVertex = head;  //establish a pointer to traverse throughout vertices
        while (currentVertex != null) { //traverse the vertex nodes...
            if (currentVertex.vertexInfo.compareTo(fromInfo) == 0) {    //...until it reaches the vertex (origin of edge)
                Edge<T, N> currentEdge = currentVertex.firstEdge;   //establish a pointer to traverse througout the edges of currentVertex
                while (currentEdge != null) {
                    if (currentEdge.toVertex.vertexInfo.compareTo(toInfo) == 0) {
                        return true;    //return true if the 'to' is the right destination
                    }
                }
                currentEdge = currentEdge.nextEdge;    //pointer pointing to next edge
            }
            currentVertex = currentVertex.nextVertex;   //pointer pointing to next vertex
        }
        return false;   //return false when there's no requested vertex name in the graph
    }
    
    /**
     * Obtain the in-degree of a vertex
     * 1. Traverse the vertices until found the desire vertex
     * 2. return the vertex's in-deg
     * @param vertexInfo
     * @return 
     */
    public int getInDeg(T vertexInfo) {
        if (head == null)   //if graph is empty
            return -1;   //return -1 as a sign of no presence
        
        Vertex<T, N> currentVertex = head;  //establish a pointer to traverse throughout vertices
        while (currentVertex != null) { //traverse the vertex nodes...
            if (currentVertex.vertexInfo.compareTo(vertexInfo) == 0) {  //...until it reaches the desired vertex
                return currentVertex.indeg;
            }
            currentVertex = currentVertex.nextVertex;
        }
        
        return -1;   //return -1 as a sign of no presence
    }
    
    public int getOutDeg(T vertexInfo) {
        if (head == null)   //if graph is empty
            return -1;   //return -1 as a sign of no presence
        
        Vertex<T, N> currentVertex = head;  //establish a pointer to traverse throughout vertices
        while (currentVertex != null) { //traverse the vertex nodes...
            if (currentVertex.vertexInfo.compareTo(vertexInfo) == 0) {  //...until it reaches the desired vertex
                return currentVertex.outdeg;
            }
            currentVertex = currentVertex.nextVertex;
        }
        return -1;   //return -1 as a sign of no presence
    }
    
    public T getVertex(int position){   //getting the info of vertex at a position
        if (position > size-1 || position < 0) {
            return null;
        }
        Vertex<T, N> currentVertex = head; //pointer
        for (int i = 0; i < position; i++) {    //traverse to the position
            currentVertex = currentVertex.nextVertex;
        }
        return currentVertex.vertexInfo;
    }
    
    public boolean addVertex(T vertexInfo) {
        if (!hasVertex(vertexInfo)) {
            

            Vertex<T, N> newVertex = new Vertex<>(vertexInfo, null);

            if (head == null) {
                // if the graph is empty, the new vertex is the head of the graph
                head = newVertex;
            } else {
                // if the graph is not empty
                Vertex<T, N> currentVertex = head; // starting from head vertex
                while (currentVertex.nextVertex != null) // traverse until currentVertex is the final vertex
                    currentVertex = currentVertex.nextVertex;

                currentVertex.nextVertex = newVertex; // add the new vertex next to the final vertex
                // the new vertex is going to be the new final vertex
            }
            size++; // increase the number of vertices in the graph
            return true;
        } else
            return false;
    }

    
    /**
     * add directed edge (have from and to) to a vertex (sourceVertex)
     * @param sourceInfo to identify sourceVertex
     * @param destInfo to set toVertex of the new edge to be added into sourceVertex
     * @param weight
     * @return boolean whether the operation successfully carried out or no
     */
    public boolean addEdge(T sourceInfo, T destInfo, N weight)  {
        if (head == null)   //if graph is empty
            return false;   //cus nothing to be checked
        
        //1. traversing through the vertices to set pointer on sourceVertex
        Vertex<T, N> sourceVertex = head;  //establish a pointer to traverse to desired sourceVertex
        while (sourceVertex != null) { //traverse the vertex nodes...
            if (sourceVertex.vertexInfo.compareTo(sourceInfo) == 0) {   //...until it reaches the desired sourceVertex
                
                //2. traversing through the vertices to set pointer on destinationVertex
                Vertex<T, N> destVertex = head; //establish pointer to traverse to desired destVertex
                while (destVertex != null) {
                    if (destVertex.vertexInfo.compareTo(destInfo) == 0) {
                        
                        //3. makes a new edge to be put as firstEdge of A vertex
                        Edge<T, N> currentEdge = sourceVertex.firstEdge;    //highlight the first edge of the source vertex
                        Edge<T, N> newEdge = new Edge<>(destVertex, weight, currentEdge);   //attach currentEdge(firstEdge of source Vertex) as next edge, now newEdge be the firstEdge
                        
                        sourceVertex.firstEdge = newEdge;   //make newEdge as firstEdge of sourceVertex
                        sourceVertex.outdeg++;
                        destVertex.indeg++;
                        return true;    //return true when mission succeeded
                    }
                    
                    destVertex = destVertex.nextVertex; //part of traversing to find destVertex
                }
                return false;
            }
            sourceVertex = sourceVertex.nextVertex; //part of traversing to find sourceVertex
        }
        return false;   //mission failed successfully, returning false hope to my country
    }
    
    /**
     * same as addEdge method, but it is undirected graph
     * A point to B
     * B point to A
     * @param Ainfo to traverse graph to identify A
     * @param Binfo to traverse graph to identify B
     * @param weight
     * @return boolean whether the operation successfully carried out or no
     */
    public boolean addUndirectedEdge(T Ainfo, T Binfo, N weight) {
        if (head == null)   //if graph is empty
            return false;   //cus nothing to be checked
        
        //1. traversing through the vertices to set pointer on sourceVertex
        Vertex<T, N> Avertex = head;  //establish a pointer to traverse to desired sourceVertex
        while (Avertex != null) { //traverse the vertex nodes...
            if (Avertex.vertexInfo.compareTo(Ainfo) == 0) {   //...until it reaches the desired A Vertex
                
                //2. traversing through the vertices to set pointer on BVertex
                Vertex<T, N> Bvertex = head; //establish pointer to traverse to desired B Vertex
                while (Bvertex != null) {
                    if (Bvertex.vertexInfo.compareTo(Binfo) == 0) {
                        
                        //3. makes a new edge to be put as firstEdge of A Vertex
                        Edge<T, N> currentEdgeA = Avertex.firstEdge;    //highlight the first edge of the A vertex
                        Edge<T, N> newEdgeA = new Edge<>(Bvertex, weight, currentEdgeA);   //attach currentEdge(firstEdge of A Vertex) as next edge, now newEdgeA be the firstEdge
                        
                        Avertex.firstEdge = newEdgeA;   //make newEdge as firstEdge of A Vertex
                        Avertex.outdeg++;
                        Bvertex.indeg++;
                        
                        //4. makes a new edge to attached as firstEdge of B Vertex
                        Edge<T, N> currentEdgeB = Bvertex.firstEdge;    //highlight the first edge of the B vertex
                        Edge<T, N> newEdgeB = new Edge<>(Avertex, weight, currentEdgeB);   //attach currentEdge(firstEdge of B Vertex) as next edge, now newEdgeB be the firstEdge
                        
                        Bvertex.firstEdge = newEdgeB;   //make newEdgeB as firstEdge of B Vertex
                        Bvertex.outdeg++;
                        Avertex.indeg++;
                        
                        return true;    //return true when mission succeeded
                    }
                    
                    Bvertex = Bvertex.nextVertex; //part of traversing to find destVertex
                }
            }
            
            Avertex = Avertex.nextVertex; //part of traversing to find sourceVertex
        }
        return false;   //when mission failed successfully, returning false hope to my country
    }
    
    public boolean removeEdge(T sourceInfo, T destInfo){
        if (head == null)   //if graph is empty
            return false;   //cus nothing to be checked
        
        //1. traversing through the vertices to set pointer on sourceVertex
        Vertex<T, N> sourceVertex = head;  //establish a pointer to traverse to desired sourceVertex
        while (sourceVertex != null) { //traverse the vertex nodes...
            if (sourceVertex.vertexInfo.compareTo(sourceInfo) == 0) {   //...until it reaches the desired sourceVertex
                
                //2. traversing the edges of the targeted vertex
                Edge<T, N> currentEdge = sourceVertex.firstEdge;    //set a pointer to find the targeted edge
                while (currentEdge != null) {   //traverse through all the edges of the vertex
                    if (currentEdge.toVertex.vertexInfo.compareTo(destInfo) == 0) { //identify whether it is desired edge based on dest info
                        //3.removing the edge by deleting the toVertex of the edge
                        currentEdge.toVertex = null;
                        
                        return true;    //mission accomplished
                    }
                    
                    currentEdge = currentEdge.nextEdge; //part of edge traverse
                }
                
                return false;   //when there is no edge based on dest info
            }
            
            sourceVertex = sourceVertex.nextVertex; //part of traversing to find sourceVertex
        }
        
        return false;   //when source vertex not found
    }
    
    public ArrayList<T> getNeighbours(T vertexInfo) {
        if (!hasVertex(vertexInfo))
            return null;

        ArrayList<T> neighbours = new ArrayList<>();

        Vertex<T, N> currentVertex = head;
        while (currentVertex != null) {
            if (currentVertex.vertexInfo.compareTo(vertexInfo) == 0) {
                // traverse until given vertex
                Edge<T, N> currentEdge = currentVertex.firstEdge;

                while (currentEdge != null) {
                    // go through all of the edges (outDegs) from the given vertex
                    neighbours.add(currentEdge.toVertex.vertexInfo);
                    
                    currentEdge = currentEdge.nextEdge;
                }
                return neighbours;
            }
////            currentVertex = currentVertex.nextVertex;
        }
        return null;
    }
}