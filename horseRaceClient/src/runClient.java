import java.util.Scanner;

public class runClient {
	
	public static User my;
	public static User[] users = null;
	public static int userNum = 0;
	public static ClientGUI gui;
	
	public static void main(String[] args) throws InterruptedException {
		Scanner scan = new Scanner(System.in);
		
		System.out.print("Choose your Nickname:");
		String name = scan.nextLine();
		my = new User(name, 0);
		
		System.out.print("host address:");
		String host = scan.nextLine();
		ClientNetwork c = new ClientNetwork(host);
		
		c.send(name);
		
		System.out.println("Name Sent");
		
		while (c.flag == 0) {
			System.out.println("Loading...");
		}
		System.out.println("Load Completed");
		
		gui = new ClientGUI(users, userNum);
		
		while (true) {
			while (true) {
				
				if (c.flag == 1) {
					gui.showCard(c.card);
				}
				else if (c.flag == 2) {
					gui.updateScreen(c.symbol, c.curPosition, c.movePosition);
				}
				else if (c.flag == 3 || c.flag == 4) {
					break;
				}
			}
			if (c.flag == 3) {
				break;
			}
		}
		
		scan.close();
	}

}
