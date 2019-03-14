import java.io.*;
import java.util.ArrayList;

public class Subway {

    private static Graph data = new Graph();

    public static void main(String args[]) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        constructGraph(args[0]);

        while (true)
        {
            try
            {
                String input = br.readLine();
                if (input.compareTo("QUIT") == 0)
                    break;

                command(input);
            }
            catch (IOException e)
            {
                System.out.println("입력이 잘못되었습니다. 오류 : " + e.toString());
            }
        }
    }

    private static void command(String input)  throws IOException
    {
        String[] Str = input.split("\\s");
        Object[] res = null;
        if(Str.length == 2)
            res = data.Dijkstra(Str[0], Str[1], false);

        else if(Str.length == 3 && Str[2].equals("!"))// 최소환승경로
            res = data.Dijkstra(Str[0], Str[1], true);
        StringBuilder finalres = new StringBuilder();
        int count = 0;
        if(res != null)
        {
            for(int i=0; i<res.length -1 ; i++ )
            {
                boolean flag = false;
                while (((String)res[i]).compareTo((String)res[i+1]) == 0)
                {
                    i++;
                    flag = true;
                }
                if(flag){
                    count++;
                    finalres.append("[").append(res[i]).append("] ");
                }
                else
                    finalres.append(res[i]).append(" ");
            }
        }

        System.out.println(finalres.toString().trim());
        if(Str.length == 3 && Str[2].equals("!"))
            System.out.println(Long.parseLong((String)res[res.length-1]) - (count * (long)Integer.MAX_VALUE) + 5 *count);
        else
        System.out.println(res[res.length -1]);
    }

    private static void constructGraph(String path) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader(path));
        String input;
        String[] str;
        while (true)
        {
            input = in.readLine();
            if(input == null || input.compareTo("") == 0)
                break;
            else
            {
                str = input.split("\\s");
                if(str.length != 3)
                    break;
                data.addVertex(new Vertex(str[0], str[1], str[2]));
            }
        }
        while (true)
        {
            input = in.readLine();
            if(input == null || input.compareTo("") == 0)
                break;
            else
            {
                str = input.split("\\s");
                if(str.length != 3)
                    break;
                data.insertEdge(str[0], str[1], Integer.parseInt(str[2]));
            }
        }
    }
}
