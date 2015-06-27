import java.net.URL;
import java.util.Scanner;

public class Main {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception 
	{
		for(String arg: args)
			System.out.println(args);
		Main.blogTest(true,true,false);
		//System.out.println(Main.ack(1, 2));
		//Main.assignTest(2, 5);
		//Main.inputTest();
	}
	
	public static void blogTest(boolean mail, boolean ping, boolean twitter) throws Exception
	{
		
		Blog b = Blog.getBlog();
		
		if(mail)
			new MailNotification(b, "test@test.ts");

		else if(ping)
			new PingNotification(b, new URL("http://pingservi.ce/ping"));
		
		else if(twitter)
			new TwitterNotification(b, "blogTester", "empty");
		
		b.createNewEntry("First Entry", "First Test!");		
		
		b.setBackend(new XMLBackend());
		
		b.createNewEntry("Next Entry", "Some more text");
				
		b.createNewComment( 1 , "Tony Tester", "Test comment");
		
		System.out.println(b.render());
		
	}
	
	public static int ack(int n, int m)
	{
		if(n == 0)
			return m + 1;
		if(m == 0)
			return ack(n -1 , 1);
		return ack(n-1, ack(n, m-1));
	}
	
	public static void assignTest(int a, int b)
	{
		if(a > 3)
			a = b;
		if(a > b)
			a = b;
		if(a < 2)
			System.out.println("2");		
	}
	
	public static void inputTest()
	{
		System.out.println("Enter text:");
		String input = new Scanner(System.in).nextLine();
		System.out.println("inputTest: " + input);
		symbInput();
	}
	
	private static void symbInput()
	{
		System.out.println("Enter text:");
		String input = new Scanner(System.in).nextLine();
		System.out.println("symbInput: " + input);
	}

}
