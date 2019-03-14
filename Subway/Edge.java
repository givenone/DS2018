public class Edge {
    private String From;
    private Vertex To;
    private int Weight;

    public Edge(String From, Vertex To, int Weight)
    {
        this.From = From;
        this.To = To;
        this.Weight = Weight;
    }
    public Vertex getTo()
    { return To;}

    public int getWeight()
    { return Weight;}
    public void setWeight(int a)
    { this.Weight = a;}
}
