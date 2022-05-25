import java.util.Scanner;

public class runClient {
	
	public static User my = new User();
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.print("Choose your Nickname:");
		String name = scan.nextLine();
		my.name = name;
		
		System.out.print("host address:");
		String host = scan.nextLine();
		ClientNetwork c = new ClientNetwork(host);

	}

}
