public class Node {
    private int i, j;
    private String sub;
    private Node LeftChild = null , RightChild = null, Next = null;
    private int height;

    public Node(int i, int j, String substring)
    {
        this.i = i;
        this.j = j;
        sub = substring;
        height = 1;
    }

    public  String getString()
    { return sub;}

    public Node getLeft()
    { return  LeftChild; }

    public Node getRight()
    {  return RightChild; }

    public Node getNext()
    { return Next;}

    public int getHeight()
    {   return height;}

    public void setNext(Node a)
    { this.Next = a;}

    public int geti()
    { return i;}

    public int getj()
    { return j;}

    public void setHeight(int newheight)
    {this.height = newheight;}

    public void setLeft(Node Left)
    { LeftChild = Left;}

    public void setRight(Node Right)
    { RightChild = Right; }

    public void insert(Node newone)
    {// Node 안의 linked list를 만들어서 이어줌.(linked list)
        Node head = this;
        while(head.getNext() != null)
            head = head.getNext();
        head.setNext(newone);
    }

    public boolean find(int i, int j)
    {
        Node head = this;
        while (head != null)
        {
            if(head.geti() == i && head.getj() == j)
                return true;
            head = head.getNext();
        }
        return false;
    }
}
