public class MovieDB
{
	private linked linkedlist = new linked(1);
	
	public void insert(Node a)
	{
		if(linkedlist.findsame(a) == true)
		{

			return;

		}

		else
			linkedlist.add(a);
	}
	
	public void delete(Node a)
	{
		linkedlist.deletenode(a);
	}
	
	public void SEARCH(String a)
	{
		linkedlist.searchword(a);
	}
	
	public void printall()
	{
		linkedlist.printall();
	}
}