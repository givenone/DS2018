public class linked{
	// dummy head
	public node1 head1;
	public Node head2; // 2 types of dummy head
	private String genre;
	
	int numItems = 0;
	
	public linked(int i)
	{
		if( i == 1)
		{
			head2 = new Node();
		}
		else
		{ // for second
			head1 = new node1();
		}
	}
	public String getgenrename()
	{
		return genre;
	}
	public void setgenrename(String g)
	{
		this.genre = g;
	}


    
	public boolean isEmpty() {
		if(head2 == null)
		{
			return head1.getNext() == null;
		}
		else
		{
			return head2.getNext() == null;
		}
	}
	
	
	public int size() {
		return numItems;
	}

	
	public void add(Node item) {
		
		Node last = head2;
		
		
		while (last.getNext() != null ) {
			
			if(last.getNext().getlinked().getgenrename().compareTo(item.getgenre()) > 0)
			{// 첫번째 링크드리스트에서 장르 이름이 사전 순으로 더 클 경우 -> 새롭게 만들어야 함. / 노드의 리스트의 첫 노드도 세팅 // 새로운 장르가 생기는 경우임.
				break;
			}
			
			else if (last.getNext().getlinked().getgenrename().compareTo(item.getgenre()) == 0)
			{// 장르가 같을 경우 -> 그 링크드(last의 next) 리스트에 넣어줌
				
				node1 newlast = last.getNext().getlinked().head1;
				while(newlast.getNext() != null)
				{
					if(newlast.getNext().getname().compareTo(item.getname()) > 0)
					{
						break; // 사전순으로 다음이 커지는 순간.
					}
					newlast = newlast.getNext();
					
				}
				newlast.insertNext(new node1(item.getgenre(),item.getname()));
				last.getNext().getlinked().numItems++;
				
				return;
			

			}
			
			last = last.getNext();
		}
		
		last.insertNext(item);
		item.getlinked().head1.insertNext(new node1(item.getgenre(),item.getname()));
		item.getlinked().numItems++;
		item.getlinked().setgenrename(item.getgenre());
		numItems += 1;
	}

	public void removeAll() {
		if(head2 != null)
		{
			head2.setNext(null);
		}
		else
			head1.setNext(null);
	}
	
	public boolean findsame(Node item)
	{ // 이미 같은 것 있으면 true 반환
		Node last = head2.getNext();
		
		while(last != null)
		{
			if (last.getlinked().getgenrename().compareTo(item.getgenre()) == 0)
			{
				node1 newlast = last.getlinked().head1.getNext(); //genre를 담고 있는 linked list 안의 name과 관련된 linked list!
			
				while(newlast != null)
				{
					if(item.getname().compareTo(newlast.getname()) == 0)
					{
						return true;
					}
					newlast = newlast.getNext();
					
				}
			}
			last = last.getNext();
		}
		return false;
	}
	
	public void deletenode(Node item)
	{
		Node last = head2;
		
		while(last.getNext() != null)
		{
			node1 newlast = last.getNext().getlinked().head1;
	// linked 안의 linked에서 검색.
			
			while(newlast.getNext() != null)
			{
				if(item.getgenre().compareTo(newlast.getNext().getgenre()) == 0 && item.getname().compareTo(newlast.getNext().getname()) == 0)
				{
					newlast.removeNext();
					last.getNext().getlinked().numItems --;
					
					if (last.getNext().getlinked().numItems == 0 )
					{// name list가 비었을 경우 그 노드를 삭제!
						last.removeNext();
					}
					return;
				}
				
				newlast = newlast.getNext(); // name의 list
			}
			
			last = last.getNext();// genre의 list
			
		}
	}
	
	public void searchword(String a)
	{
		boolean flag = false;
		
		Node last = head2.getNext();
		
		while(last != null)
		{
			node1 newlast = last.getlinked().head1.getNext();
			while(newlast != null)
			{

				if(newlast.getname().contains(a)) {
					newlast.printNode();
					flag = true;
				}
				
				newlast = newlast.getNext();
			}
			last = last.getNext();
		}
		
		if(!flag)
			System.out.println("EMPTY"); //검색어에 해당하는 영화가 없으면 엠티 출력.
	}
	
	public void printall()
	{
		Node last = head2;
		
		if(isEmpty())
		{ // genre에 대한 linkedlist가 비어있을 경우에만 empty출력
			System.out.println("EMPTY");
			return;
		}
		
		while(last.getNext() != null)
		{
			node1 newnode = last.getNext().getlinked().head1;
			//first linked list
			while(newnode.getNext()!=null)
			{//second linked list
				newnode.getNext().printNode();
				newnode = newnode.getNext();
				
			}
			
			last = last.getNext();
		}
	}
}
