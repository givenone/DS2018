import java.util.*;

public class Graph implements Cloneable {

    private HashMap<String, ArrayList<Vertex>> byName = new HashMap<>();
    private HashMap<String, Vertex> byID = new HashMap<>();

    private final int transfer = 5;

    public void addVertex(Vertex newone)
    {
        if(byName.containsKey(newone.getName()))
        {
            ArrayList<Vertex> samename = byName.get(newone.getName());
            for(Vertex temp : samename)
            {
                if(temp.getLine() != newone.getLine()) {
                    temp.addEdge(new Edge(temp.getID(), newone, transfer));
                    newone.addEdge(new Edge(newone.getID(), temp, transfer));
                }
            }
            samename.add(newone);
            byID.put(newone.getID(), newone);
        }
        else
        {
            ArrayList<Vertex> _new = new ArrayList<>();
            _new.add(newone);
            byName.put(newone.getName(), _new);
            byID.put(newone.getID(), newone);
        }
    }

    public Vertex getVertex(String ID)
    {
        return byID.get(ID);
    }

    public ArrayList<Vertex> getVertexbyName(String Name)
    {
        return byName.get(Name);
    }

    public void insertEdge(String fromID, String ToID, int Weight)
    {
        Vertex from = getVertex(fromID);
        Vertex to = getVertex(ToID);
        from.addEdge(new Edge(fromID,to,Weight));
    }

    public Object[] Dijkstra(String from, String To, boolean type)
    {
        setWeight(type); // Weight와 Distance 초기화.

        PriorityQueue<node> Q = new PriorityQueue<>();
        ArrayList<Vertex> start = getVertexbyName(from);

        for( Vertex _temp : start)
        {
            _temp.setDistance(0);
            Q.add(new node(0,_temp));
        }
        Vertex _start = start.get(0);
        Vertex U = _start;
        while ( !(U.getName().equals(To) || Q.isEmpty()))
        {
            U = Q.poll().the; // least element
            U.visit();
            for(Edge e : U.getEdges())
            {
                if(!e.getTo().getvisited()){

                    if(e.getTo().getDistance() > U.getDistance() + e.getWeight())
                    {
                        e.getTo().setDistance(U.getDistance() + e.getWeight());
                        e.getTo().setPrevious(U);
                        Q.add(new node(e.getTo().getDistance(), e.getTo()));//add to PQ
                    }

                }

            }
        }
        ArrayList<String> res = new ArrayList<>();
        res.add(Long.toString(U.getDistance()));
        res.add(U.getName());
        while (! U.getName().equals(_start.getName()))
        {
            U = U.getPrevious();
            res.add(U.getName());
        }
        Collections.reverse(res); // return : from start ~ finish names + distance ( as string)
        return res.toArray();
    }

    private void setWeight(boolean flag)
    {
        if(flag)
        {
            Iterator<String> it = byID.keySet().iterator();
            while (it.hasNext())
            {
                Vertex a = byID.get(it.next());
                a.unvisit();
                a.setDistance(Long.MAX_VALUE);
                for(Edge b : a.getSamename())
                { b.setWeight(Integer.MAX_VALUE); }
            }

        }
        else
        {
            Iterator<String> it = byID.keySet().iterator();
            while (it.hasNext())
            {
                Vertex a = byID.get(it.next());
                a.unvisit();
                a.setDistance(Long.MAX_VALUE);
                for(Edge b : a.getSamename())
                { b.setWeight(transfer); }
            }
        }


    }

}
