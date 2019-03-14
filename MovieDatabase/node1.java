
public class node1 {
	// 두번째 링크드리스트 안의 노드
	
    private String genre;
    private String name;
    private node1 next;

    public node1()
    {
    	this.next = null;
    }
    public node1(String a, String b) {
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
    
    public final void setNext(node1 next) {
    	this.next = next;
    }
    
    public node1 getNext() {
    	return this.next;
    }
    
    public final void insertNext(node1 obj) {
    	
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
