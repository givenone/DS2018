import java.util.ArrayList;

public class Vertex implements Comparable<Vertex>{
    private String ID;
    private String Name;
    private String Line;
    private ArrayList<Edge> edges = new ArrayList<>();
    private ArrayList<Edge> samename = new ArrayList<>();
    private long Distance = Long.MAX_VALUE;
    private Vertex previous;
    private boolean visited = false;

    public Vertex(String ID, String Name, String Line)
    {
        this.ID = ID;
        this.Name = Name;
        this.Line = Line;
    }

    public String getID()
    { return ID;}
    public String getName()
    { return Name;}
    public String getLine()
    { return  Line;}

    public ArrayList<Edge> getSamename()
    { return samename;}

    public void addEdge(Edge newedge)
    {
        edges.add(newedge);
        if(newedge.getTo().getName().equals(Name))
            samename.add(newedge);
    }
    public void setDistance(long i)
    { Distance = i;}
    public long getDistance()
    { return Distance;}
    public void setPrevious(Vertex previous)
    { this.previous = previous;}
    public Vertex getPrevious()
    { return previous;}
    public ArrayList<Edge> getEdges()
    { return edges;}

    public int compareTo(Vertex target)
    {
        if(this.getDistance() > target.getDistance())
            return 1;
        else if(this.getDistance() < target.getDistance())
            return  -1;
        else
            return 0;
    }

    public void visit()
    { visited = true;}
    public void unvisit()
    { visited = false; }
    public boolean getvisited()
    { return visited; }



}
