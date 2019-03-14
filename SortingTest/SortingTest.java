import java.io.*;
import java.util.*;

public class SortingTest
{
	public static void main(String args[])
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		try
		{
			boolean isRandom = false;	// 입력받은 배열이 난수인가 아닌가?
			int[] value;	// 입력 받을 숫자들의 배열
			String nums = br.readLine();	// 첫 줄을 입력 받음
			if (nums.charAt(0) == 'r')
			{
				// 난수일 경우
				isRandom = true;	// 난수임을 표시

				String[] nums_arg = nums.split(" ");

				int numsize = Integer.parseInt(nums_arg[1]);	// 총 갯수
				int rminimum = Integer.parseInt(nums_arg[2]);	// 최소값
				int rmaximum = Integer.parseInt(nums_arg[3]);	// 최대값

				Random rand = new Random();	// 난수 인스턴스를 생성한다.

				value = new int[numsize];	// 배열을 생성한다.
				for (int i = 0; i < value.length; i++)	// 각각의 배열에 난수를 생성하여 대입
					value[i] = rand.nextInt(rmaximum - rminimum + 1) + rminimum;
			}
			else
			{
				// 난수가 아닐 경우
				int numsize = Integer.parseInt(nums);

				value = new int[numsize];	// 배열을 생성한다.
				for (int i = 0; i < value.length; i++)	// 한줄씩 입력받아 배열원소로 대입
					value[i] = Integer.parseInt(br.readLine());
			}

			// 숫자 입력을 다 받았으므로 정렬 방법을 받아 그에 맞는 정렬을 수행한다.
			while (true)
			{
				int[] newvalue = (int[])value.clone();	// 원래 값의 보호를 위해 복사본을 생성한다.

				String command = br.readLine();

				long t = System.currentTimeMillis();
				switch (command.charAt(0))
				{
					case 'B':	// Bubble Sort
						newvalue = DoBubbleSort(newvalue);
						break;
					case 'I':	// Insertion Sort
						newvalue = DoInsertionSort(newvalue);
						break;
					case 'H':	// Heap Sort
						newvalue = DoHeapSort(newvalue);
						break;
					case 'M':	// Merge Sort
						newvalue = DoMergeSort(newvalue);
						break;
					case 'Q':	// Quick Sort
						newvalue = DoQuickSort(newvalue);
						break;
					case 'R':	// Radix Sort
						newvalue = DoRadixSort(newvalue);
						break;
					case 'X':
						return;	// 프로그램을 종료한다.
					default:
						throw new IOException("잘못된 정렬 방법을 입력했습니다.");
				}
				if (isRandom)
				{
					// 난수일 경우 수행시간을 출력한다.
					System.out.println((System.currentTimeMillis() - t) + " ms");
				}
				else
				{
					// 난수가 아닐 경우 정렬된 결과값을 출력한다.
					for (int i = 0; i < newvalue.length; i++)
					{
						System.out.println(newvalue[i]);
					}
				}

			}
		}
		catch (IOException e)
		{
			System.out.println("입력이 잘못되었습니다. 오류 : " + e.toString());
		}
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////
	private static int[] DoBubbleSort(int[] value)
	{
		for(int i=0; i<value.length; i++)
		{
			for(int j=i+1; j<value.length; j++)
			{
				if(value[i] > value[j])
					value[i] = value[i]^value[j]^(value[j] = value[i]);
			}
		}
		// TODO : Bubble Sort 를 구현하라.
		// value는 정렬안된 숫자들의 배열이며 value.length 는 배열의 크기가 된다.
		// 결과로 정렬된 배열은 리턴해 주어야 하며, 두가지 방법이 있으므로 잘 생각해서 사용할것.
		// 주어진 value 배열에서 안의 값만을 바꾸고 value를 다시 리턴하거나
		// 같은 크기의 새로운 배열을 만들어 그 배열을 리턴할 수도 있다.
		return (value);
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////
	private static int[] DoInsertionSort(int[] value)
	{
		for(int i=1; i<value.length; i++)
		{
			for(int j=i-1; j>=0 ; j--)
			{
				if(value[j] > value[j+1])
					value[j] = value[j] ^ value[j+1] ^(value[j+1] = value[j]);
			}
		}
		// TODO : Insertion Sort 를 구현하라.
		return (value);
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////
	private static int[] DoHeapSort(int[] value)
	{
		int length = value.length;
		for(int i=length/2 -1; i>=0 ; i--)
		{
			Percolate(value, i, length);
		}

		for( int i = length -1 ; i >=0 ; i--)
		{// swqp & percolate
			value[i] = value[i] ^ value[0] ^(value[0] = value[i]);
			Percolate(value,0, i);
		}
		// TODO : Heap Sort 를 구현하라.
		return (value);
	}

	private  static void Percolate(int[] value, int i, int n)
	{
		int leftchild = 2*i + 1;
		int rightchild = 2*i + 2;

		if(leftchild < n)
		{
			if( (rightchild <n) && (value[leftchild] < value[rightchild]))
				leftchild = rightchild;
			if( value[i] < value[leftchild])
			{
				value[i] = value[i] ^ value[leftchild] ^(value[leftchild] = value[i]);
				Percolate(value,leftchild,n);
			}
		}
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////
	private static int[] DoMergeSort(int[] value)
	{
		if(value.length <= 1 )
			return value;
		else
		{
			return	Merge(DoMergeSort(Arrays.copyOfRange(value,0, value.length/2)),DoMergeSort(Arrays.copyOfRange(value,value.length/2,value.length)));
		}
		// TODO : Merge Sort 를 구현하라.

	}

	public static int[] Merge(int[] value1, int[] value2)
	{
		int length = value1.length + value2.length;
		int[] res = new int[length];
		int i = 0,j =0;
		while(i+j <length)
		{
			if(i == value1.length){
				while(j < value2.length)
					res[i+j] = value2[j++];
				break;
			}
			else if(j == value2.length){
				while (i<value1.length)
					res[i+j] = value1[i++];
				break;
			}

			if(value1[i] < value2[j])
				res[i+j] = value1[i++];
			else
				res[i+j] = value2[j++];
		}

		return res;
	}


	////////////////////////////////////////////////////////////////////////////////////////////////////
	private static int[] DoQuickSort(int[] value)
	{
		Quicksort(value,0,value.length -1);
		// TODO : Quick Sort 를 구현하라.
		return (value);
	}

	private static void Quicksort(int[] value,int low, int high)
	{
		if(low < high)
		{
			int pivot = partition(value, low, high);

			Quicksort(value, low, pivot-1);
			Quicksort(value, pivot+1, high);
		}
	}
	private  static int partition(int[] value, int low, int high)
	{//array -> reference!
		int pi = value[low];
		int finger1 = low;
		int finger2 = low+1;

		while(finger2 <= high)
		{
			if(pi >= value[finger2])
			{
				value[finger1+1] = value[finger1+1] ^ value[finger2] ^(value[finger2] = value[finger1+1]);
				finger1++;
				finger2++;
			}
			else
			{
				finger2++;
			}
		}
		value[low] = value[low] ^ value[finger1] ^(value[finger1] = value[low]); // pivot의 위치 알맞게

		return finger1; // pivot의 index
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////
	private static int[] DoRadixSort(int[] value)
	{ // -9 ~ 9
		int maxnum = getmaxdigit(value);
		for(int digit = 1;  maxnum / digit  > 0 ; digit *= 10)
			stablesort(value,digit);
		// TODO : Radix Sort 를 구현하라.
		return (value);
	}
	public static void stablesort(int[] value, int digit)
	{
		int n = value.length;
		int[] temp = new int[n];
		int[] count = new int[19]; // -9 ~ 9
		for( int i = 0 ; i<n; i++)
			count[(value[i] / digit) % 10 + 9]++;
		for(int i=1; i<19; i++)
			count[i] += count[i-1] ; // 위치 array

		for(int i=n-1; i>=0; i--) // stable 하게 하기 위함
		{
			temp[ count[((value[i] / digit) % 10 )+ 9 ] - 1] = value[i];
			count[(value[i] /digit) % 10 + 9]--;
		}
		for(int i=0; i<n ; i++)// value semantic
			value[i] = temp[i];
	}
	public static int getmaxdigit(int[] value)
	{
		int max = abs(value[0]);
		for(int i= 1; i<value.length; i++)
		{
			if(max < abs(value[i]))
				max = abs(value[i]);
		}

		return max;

	}

	public static int abs (int a) {
		if(a >= 0)
			return a;
		else
			return  -a;
	}
}
