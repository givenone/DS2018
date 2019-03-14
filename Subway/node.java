public class node implements Comparable<node> {
    public long Distance;
    public Vertex the;

    public node(long dist, Vertex ve)
    {
        Distance = dist;
        the = ve;
    }

    public int compareTo(node target)
    {
        if(this.Distance > target.Distance)
            return 1;
        else if(this.Distance < target.Distance)
            return  -1;
        else
            return 0;
    }
}
