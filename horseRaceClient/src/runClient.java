import java.util.Scanner;

public class runClient {
	
	public static User my;
	public static User[] users = null;
	public static int userNum = 0;
	public static ClientGUI gui;
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.print("Choose your Nickname:");
		String name = scan.nextLine();
		my = new User(name, 0);
		
		System.out.print("host address:");
		String host = scan.nextLine();
		ClientNetwork c = new ClientNetwork(host);
		
		c.send(name);
		
		while (!c.flag)
		{
			
		}
		
		gui = new ClientGUI(users, userNum);
		
		scan.close();
	}

}
