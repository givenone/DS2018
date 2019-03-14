public class Tree { // AVL Tree

    private Node root;

    public Tree()
    {
        root = null;
    }

    public Node getRoot()
    { return root; }

    public static int max(int a, int b)
    { return (a > b) ? a : b ;}

    public Node Search(String substring)
    { return Search(this.root, substring);} // 외부 호출용

    private Node Search(Node root, String substring)
    {
        if(root == null)
            return null;
        else if(substring.compareTo(root.getString()) == 0)
            return root;
        else if(root.getString().compareTo(substring) > 0)
            return Search(root.getLeft(), substring);
        else
            return Search(root.getRight(), substring);
    }

    private Node RightR(Node n)
    {
        Node left = n.getLeft();
        Node right = left.getRight();

        left.setRight(n);
        n.setLeft(right);

        n.setHeight(max(height(n.getLeft()) , height(n.getRight())) + 1);
        left.setHeight(max(height(left.getLeft()) , height(left.getRight())) + 1);

        return left;
    }
    private Node LeftR(Node n)
    {
        Node right = n.getRight();
        Node left = right.getLeft();

        right.setLeft(n);
        n.setRight(left);

        n.setHeight(max(height(n.getLeft()), height(n.getRight())) + 1);
        right.setHeight(max(height(right.getLeft()), height(n.getRight())) + 1);

        return right;
    }

    public void insert( Node newone)
    { this.root = insert(root, newone); } // 외부 호출용

    private Node insert(Node root, Node newone)
    { // node를 만들어서 넣어줘야 한다.(parameter) // rotation도 해줘야함

        if(root == null)
        {
            return newone; // height = 1
        }
        else if(newone.getString().compareTo(root.getString()) == 0) // 같을 때는 Node에서 linkedlist로 매단다.
        {
            root.insert(newone);
            newone.setHeight(root.getHeight());//height는 root와 동일
            return root;
        }
        else if(root.getString().compareTo(newone.getString()) < 0)
        {
            root.setRight(insert(root.getRight(), newone));
        }
        else
        {
            root.setLeft(insert(root.getLeft(), newone));
        }
        // insertion이 끝난 후
        root.setHeight(max(height(root.getLeft()) , height(root.getRight())) + 1); // height 재설정
        int differenceh =  diffheight(root); // difference of height
        if(differenceh > 1)
        {
            if(newone.getString().compareTo(root.getLeft().getString()) > 0 )
            { // left + right
                root.setLeft(LeftR(root.getLeft()));
                return RightR(root);
            }
            else
            { // left + left
                return RightR(root);
            }
        }
        else if(differenceh < -1)
        {
            if(newone.getString().compareTo(root.getRight().getString()) > 0)
            { // right + right
                return LeftR(root);
            }
            else
            { // right + left
                root.setRight(RightR(root.getRight()));
                return LeftR(root);
            }
        }
        return root; // which is not changed
    }

    public int diffheight(Node n)
    { // 왼쪽 subtree와 오른쪽 subtree의 높이차
        return height(n.getLeft()) - height(n.getRight());
    }

    public int height(Node n)
    {
        if(n == null)
            return 0;
        else
            return n.getHeight();
    }

    public String Traversal(Node root)
    {//preorder traversal -> 같은 key 있을 수 있음.
        if(root == null)
            return null; // base case

        if( root.getLeft() == null && root.getRight() == null)
            return root.getString();
        else if(root.getLeft() == null)
            return root.getString() + " " + Traversal(root.getRight());
        else if(root.getRight() == null)
            return root.getString() + " " + Traversal(root.getLeft());
        else
             return   root.getString()+ " " + Traversal(root.getLeft()) + " " + Traversal(root.getRight());
    }
}
