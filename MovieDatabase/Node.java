public class Node{
// 첫번째 링크드리스트의 노드	
	private linked genredata = new linked(0);
	
	public linked getlinked()
	{
		return genredata;
	}

	
    private String genre;
    private String name;
    private Node next;

    public Node()
    {
    	this.next = null;
    }
    public Node(String a, String b) {
        this.genre = a;
        this.name = b;
    }
    
     
    public final String getgenre()
    {
    	return genre;
    }
    
    public final String getname() {
    	return name;
    }
    
    public final void setNext(Node next) {
    	this.next = next;
    }
    
    public Node getNext() {
    	return this.next;
    }
    
    public final void insertNext(Node obj) {
    	
    	obj.next = this.next;
    	this.next = obj;
    	
    }
    
    public final void removeNext() {
    	
    	this.next = this.next.next;
    }
    
    public final void printNode()
    {
    	System.out.println("("+this.getgenre()+", "+this.getname()+")");
    }

}