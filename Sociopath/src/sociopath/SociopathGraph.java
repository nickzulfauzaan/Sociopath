/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sociopath;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lidros
 */
public class SociopathGraph{
    
    public class Vertex{    //T usually in String (to store name), N usually in integer-Weight (to store weight of the vertex)
        private Student vertexInfo;
        private int indeg,outdeg;
        private Vertex nextVertex;
        private Edge firstEdge;
        private boolean visited;
        private List<Vertex> neighbourList;

        public Vertex(Student vertexInfo, Vertex nextVertex) {
            this.vertexInfo = vertexInfo;
            this.nextVertex = nextVertex;
            this.neighbourList = new ArrayList<>();
        }

        public Vertex(Student vertexInfo, Vertex nextVertex, Edge firstEdge) {
            this.vertexInfo = vertexInfo;
            this.nextVertex = nextVertex;
            this.firstEdge = firstEdge;
            this.neighbourList = new ArrayList<>();
        }
        
        public boolean isVisited(){
            return visited;
        }
        
        public void setVisited(boolean visited){
            this.visited = visited;
        }
        
        public void addNeighbour(Vertex vertex){
            this.neighbourList.add(vertex);
        }
        
        public List<Vertex> getNeighbourList(){
            return this.neighbourList;
        }
        
        public String toString(){
            return vertexInfo.getName();
        }
    }
    
    private class Edge{
        private Vertex toVertex;   //the vertex the edge points onto
        private Integer weight;   //the weight of the edge
        private Edge nextEdge; //the next edge node (of this vertex) linked with this current edge
        
        public Edge(Vertex toVertex, Integer weight, Edge nextEdge) {
            this.toVertex = toVertex;
            this.weight = weight;
            this.nextEdge = nextEdge;
        }

        public Integer getWeight() {
            return weight;
        }

        public void addWeight(Integer increment) {
            weight = weight + increment;
        }
    }
    
    protected Vertex head;   //the head of the graph
    protected int size;   //size of the graph
    public SociopathGraph() {
    }
    
    public int getSize() {
        //returns the number of vertices in the graph
        return size;
    }

    public Vertex getHead() {
        return head;
    }
    
    public Student getHeadInfo() {
        //return the info(name) of the vertex
        return head.vertexInfo;
    }
    
    //kena overload/override byk method
    /**
     * checking the presence of student with the name of 'studentName'
     * this is similar to hasVertex method in parent class
     * @param studentName
     * @return the presence of the student with studentName in the graph
     */
    public boolean hasVertex(String studentName) {
        //checks if the vertex exists (based on the vertexInfo)
        if (head == null)   //if graph is empty
            return false;
        
        Vertex currentVertex = head;  //establish a pointer to traverse throughout vertices
        while (currentVertex != null) { //traverse the vertex nodes
            if (currentVertex.vertexInfo.getName().compareTo(studentName) == 0) //if current name of vertex same as requested, return true
                return true;
            currentVertex = currentVertex.nextVertex;   //pointer pointing to next vertex
        }
        return false;   //return false when there's no requested vertex name in the graph
    }
    
    public boolean isFriends(String fromInfo, String toInfo) {
        if (head == null){   //if graph is empty
            return false;   //cus nothing to be checked
        }
        
        //traversing through the vertices, through all the vertices' edges
        Vertex currentVertex = head;  //establish a pointer to traverse throughout vertices
        while (currentVertex != null) { //traverse the vertex nodes...
            if (currentVertex.vertexInfo.getName().compareTo(fromInfo) == 0) {    //...until it reaches the vertex (origin of edge)
                Edge currentEdge = currentVertex.firstEdge;   //establish a pointer to traverse througout the edges of currentVertex
                while (currentEdge != null) {
                    if (currentEdge.toVertex.vertexInfo.getName().compareTo(toInfo) == 0) {
                        return true;    //return true if the 'to' is the right destination
                    }
                    currentEdge = currentEdge.nextEdge;    //pointer pointing to next edge
                }
            }
            currentVertex = currentVertex.nextVertex;   //pointer pointing to next vertex
        }
        return false;   //return false when there's no requested vertex name in the graph
    }
    
    public int getIndex(String studentName){
        if (head == null)
            return -1;
        
        Vertex currentVertex = head;    //establish a pointer to traverse throughout vertices
        int count = 0;
        while (currentVertex != null) { //traverse the vertex nodes...
            if (currentVertex.vertexInfo.getName().compareTo(studentName) == 0) {  //...until it reaches the desired vertex
                return count;
            }
            count++;
            currentVertex = currentVertex.nextVertex;
        }
        return -1;
    }
    
    public int getInDeg(String vertexInfo) {
        if (head == null)   //if graph is empty
            return -1;   //return -1 as a sign of no presence
        
        Vertex currentVertex = head;  //establish a pointer to traverse throughout vertices
        while (currentVertex != null) { //traverse the vertex nodes...
            if (currentVertex.vertexInfo.getName().compareTo(vertexInfo) == 0) {  //...until it reaches the desired vertex
                return currentVertex.indeg;
            }
            currentVertex = currentVertex.nextVertex;
        }
        
        return -1;   //return -1 as a sign of no presence
    }
    
    public int getOutDeg(String vertexInfo) {
        if (head == null)   //if graph is empty
            return -1;   //return -1 as a sign of no presence
        
        Vertex currentVertex = head;  //establish a pointer to traverse throughout vertices
        while (currentVertex != null) { //traverse the vertex nodes...
            if (currentVertex.vertexInfo.getName().compareTo(vertexInfo) == 0) {  //...until it reaches the desired vertex
                return currentVertex.outdeg;
            }
            currentVertex = currentVertex.nextVertex;
        }
        return -1;   //return -1 as a sign of no presence
    }
    
    public Student getStudent(int position){   //getting the info of vertex at a position
        if (position > size-1 || position < 0) {
            return null;
        }
        Vertex currentVertex = head; //pointer
        for (int i = 0; i < position; i++) {    //traverse to the position
            currentVertex = currentVertex.nextVertex;
        }
        return currentVertex.vertexInfo;
    }
    
    public Vertex getVertex(String studentName){
        if (head == null) {
            return null;  //not found
        }
        
        //traversing through the vertices, through all the vertices' edges
        Vertex currentVertex = head;  //establish a pointer to traverse throughout vertices
        while (currentVertex != null) { //traverse the vertex nodes...
            if (currentVertex.vertexInfo.getName().compareTo(studentName) == 0) {
                return currentVertex;
            }
            currentVertex = currentVertex.nextVertex;   //pointer pointing to next vertex
        }
        return null;   //return null when there's no requested vertex name in the graph
    }
    
    public int getEdge(String rated, String rater){
        if (head == null) {
            return -100;  //not found
        }
        
        //traversing through the vertices, through all the vertices' edges
        Vertex currentVertex = head;  //establish a pointer to traverse throughout vertices
        while (currentVertex != null) { //traverse the vertex nodes...
            if (currentVertex.vertexInfo.getName().compareTo(rated) == 0) {    //...until it reaches the vertex (origin of edge)
                Edge currentEdge = currentVertex.firstEdge;   //establish a pointer to traverse througout the edges of currentVertex
                while (currentEdge != null) {
                    if (currentEdge.toVertex.vertexInfo.getName().compareTo(rater) == 0) {
                        return currentEdge.getWeight();    //return true if the 'to' is the right destination
                    }
                    currentEdge = currentEdge.nextEdge;    //pointer pointing to next edge
                }
            }
            currentVertex = currentVertex.nextVertex;   //pointer pointing to next vertex
        }
        return -100;   //return -1 when there's no requested vertex name in the graph
    }
    
    public boolean addVertex(Student vertexInfo) {
        if (!hasVertex(vertexInfo.getName())) {
            
            Vertex newVertex = new Vertex(vertexInfo, null);
            
            if (head == null) {
                // if the graph is empty, the new vertex is the head of the graph
                head = newVertex;
            } else {
                // if the graph is not empty
                Vertex currentVertex = head; // starting from head vertex
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
    
    public boolean increaseRep(String rated, String rater, Integer increment){
        if (head == null)   //if graph is empty
            return false;   //cus nothing to be checked
        if (!isFriends(rated, rater)) {
            return false;
        }
        
        //traversing through the vertices, through all the vertices' edges
        Vertex currentVertex = head;  //establish a pointer to traverse throughout vertices
        while (currentVertex != null) { //traverse the vertex nodes...
            if (currentVertex.vertexInfo.getName().compareTo(rated) == 0) {    //...until it reaches the vertex (origin of edge)
                Edge currentEdge = currentVertex.firstEdge;   //establish a pointer to traverse througout the edges of currentVertex
                while (currentEdge != null) {
                    if (currentEdge.toVertex.vertexInfo.getName().compareTo(rater) == 0) {
                        //System.out.println("Current Weight: " + currentEdge.getWeight());
                        currentEdge.addWeight(increment);
                        //System.out.println("After increment: " + currentEdge.getWeight());
                        return true;
                    }
                    currentEdge = currentEdge.nextEdge;    //pointer pointing to next edge
                }
            }
            currentVertex = currentVertex.nextVertex;   //pointer pointing to next vertex
        }
        return false;   //return -1 when there's no requested vertex name in the graph
    }
    
    public boolean beFriends(String Ainfo, String Binfo, Integer ArateB, Integer BrateA) {
        if (head == null)   //if graph is empty
            return false;   //cus nothing to be checked
        if (isFriends(Ainfo, Binfo)) {
            return false;
        }
        
        //1. traversing through the vertices to set pointer on sourceVertex
        Vertex Avertex = head;  //establish a pointer to traverse to desired sourceVertex
        while (Avertex != null) { //traverse the vertex nodes...
            if (Avertex.vertexInfo.getName().compareTo(Ainfo) == 0) {   //...until it reaches the desired A Vertex
                
                //2. traversing through the vertices to set pointer on BVertex
                Vertex Bvertex = head; //establish pointer to traverse to desired B Vertex
                while (Bvertex != null) {
                    if (Bvertex.vertexInfo.getName().compareTo(Binfo) == 0) {
                        
                        //3. makes a new edge to be put as firstEdge of A Vertex
                        Edge currentEdgeA = Avertex.firstEdge;    //highlight the first edge of the A vertex
                        Edge newEdgeA = new Edge(Bvertex, BrateA, currentEdgeA);   //attach currentEdge(firstEdge of A Vertex) as next edge, now newEdgeA be the firstEdge
                        
                        Avertex.firstEdge = newEdgeA;   //make newEdge as firstEdge of A Vertex
                        Avertex.outdeg++;
                        Bvertex.indeg++;
                        Avertex.addNeighbour(Bvertex);
                        
                        //4. makes a new edge to attached as firstEdge of B Vertex
                        Edge currentEdgeB = Bvertex.firstEdge;    //highlight the first edge of the B vertex
                        Edge newEdgeB = new Edge(Avertex, ArateB, currentEdgeB);   //attach currentEdge(firstEdge of B Vertex) as next edge, now newEdgeB be the firstEdge
                        
                        Bvertex.firstEdge = newEdgeB;   //make newEdgeB as firstEdge of B Vertex
                        Bvertex.outdeg++;
                        Avertex.indeg++;
                        Bvertex.addNeighbour(Avertex);
                        
                        return true;    //return true when mission succeeded
                    }
                    
                    Bvertex = Bvertex.nextVertex; //part of traversing to find destVertex
                }
            }
            
            Avertex = Avertex.nextVertex; //part of traversing to find sourceVertex
        }
        return false;   //when mission failed successfully, returning false hope to my country
    }
    
    public ArrayList<Student> getFriends(String vertexInfo) {
        if (!hasVertex(vertexInfo))
            return null;

        ArrayList<Student> neighbours = new ArrayList<>();

        Vertex currentVertex = head;
        while (currentVertex != null) {
            if (currentVertex.vertexInfo.getName().compareTo(vertexInfo) == 0) {
                // traverse until given vertex
                Edge currentEdge = currentVertex.firstEdge;

                while (currentEdge != null) {
                    // go through all of the edges (outDegs) from the given vertex
                    neighbours.add(currentEdge.toVertex.vertexInfo);
                    
                    currentEdge = currentEdge.nextEdge;
                }
                return neighbours;
            }
            currentVertex = currentVertex.nextVertex;
        }
        return null;
    }
    
    public ArrayList<Vertex> getAllVertices(){
        if (head == null) {
            return null;
        }
        
        ArrayList<Vertex> toReturn = new ArrayList<>();
        Vertex currentVertex = head;
        while(currentVertex!=null){ //traversing the vertices
            toReturn.add(currentVertex);
            currentVertex = currentVertex.nextVertex;
        }
        
        return toReturn;
    }
    
    public boolean hasPath(String fromVertex, String toVertex) {
        if (head == null)   //if graph is empty
            return false;   //cus nothing to be checked
        if (isFriends(fromVertex, toVertex)) {
            return true;
        }
        
        //1. traversing through the vertices to set pointer on sourceVertex
        Vertex currentVertex = head;  //establish a pointer to traverse to desired sourceVertex
        while (currentVertex != null) { //traverse the vertex nodes...
            if (currentVertex.vertexInfo.getName().compareTo(fromVertex) == 0) {   //...until it reaches the desired A Vertex
                
                //2. traversing through the vertices to set pointer on BVertex
                Vertex currentToVertex = head; //establish pointer to traverse to desired B Vertex
                while (currentToVertex != null) {
                    if (currentToVertex.vertexInfo.getName().compareTo(toVertex) == 0) {
                        
                    }
                    
                    currentToVertex = currentToVertex.nextVertex; //part of traversing to find destVertex
                }
            }
            
            currentVertex = currentVertex.nextVertex; //part of traversing to find sourceVertex
        }
        return false;   //when mission failed successfully, returning false hope to my country
    }
}
