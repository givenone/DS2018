import java.io.*;

public class Matching
{
	public static Table table ;
	static int k = 6; // substring 길이 6으로 관리
	public static void main(String args[])
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

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

	private static void command(String input) throws FileNotFoundException
	{
		char mission= input.charAt(0);
		switch (mission){
			case '<' :
				datainput(input);
				break;
			case '@' :
				dataoutput(input);
				break;
			case  '?' :
				pattern(input);
				break;
		}
// 정규식 -> 괄호 안 추출?
	}

	private static void datainput(String input) throws  FileNotFoundException
	{
		table = new Table(input.substring(2,input.length()));

	}
	private static void dataoutput(String input) throws IndexOutOfBoundsException
	{
		int index = Integer.parseInt(input.substring(2,input.length()));

		if(table.getTable(index).getRoot() == null)
		{
			System.out.println("EMPTY");
			return;
		}
		String res = table.getTable(index).Traversal(table.getTable(index).getRoot());
		System.out.println(res);
	}

	private static void pattern(String input)
	{
		String patt = input.substring(2,input.length());
		boolean fir = true;
		Node first = table.getTarget(patt.substring(0,k)).Search(patt.substring(0,k));
		boolean flag = false;
		while (first != null) { // 같은 key 값에 대해서
			int i = first.geti();
			int j = first.getj();
			boolean tempflag = true;
			for (int a = 0; a <= patt.length() - k; ) {
				Node temp = table.getTarget(patt.substring(a, a+k)).Search(patt.substring(a, a+k));
				if( temp == null || !temp.find(i, j+a))
					tempflag = false;
				if(a + k <= patt.length())
					a+=k;
				else
					a = patt.length() - k; // 마지막 index 처리용
			}
			if(tempflag) {
				if(fir) {
					System.out.printf("(%d, %d)", i, j);
					fir = false;
				}
				else
					System.out.printf(" (%d, %d)", i, j);
				flag = true;
			}
			first = first.getNext();
		}
		if(!flag) // 하나도 없음
			System.out.printf("(%d, %d)",0,0);
		System.out.println();
	}
}
