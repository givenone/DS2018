import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Table  {

    private Tree[] table = new Tree[100]; // Avl Tree
    int k = 6;

    public Tree getTable(int i) throws IndexOutOfBoundsException
    { return table[i]; }

    Table(String input) throws FileNotFoundException
    {
        for(int i=0; i<100; i++)
            table[i] = new Tree();
        Scanner in = new Scanner(new File(input));
        int i = 0;
        while (in.hasNextLine())
        {
            i++;
            String now =  in.nextLine();
            for(int j=0; j<= now.length() - k; j++)
            {
                String sub = now.substring(j, j+k);
                table[hashfunction(sub)].insert(new Node(i,j + 1,sub)); // i,j 값을 가진 linkedlist Node를 hash값 table에 넣어줌.
            }
        }
    }

    private int hashfunction(String input)
    {
        int i=0;
        for(int a=0; a<input.length() ; a++ )
            i += input.charAt(a);
        return i % 100;
    }

    public Tree getTarget(String tohash)
    { return this.table[hashfunction(tohash)]; }
}
