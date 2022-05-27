import java.awt.datatransfer.SystemFlavorMap;
import java.util.Scanner;

public class runClient {
	
	public static User my;
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
//			System.out.println("Loading...");
			Thread.sleep(10);
		}
		System.out.println("Load Completed");
		
		while (true) {
			gui = new ClientGUI(c.users, userNum);
			
			while (true) {
//				System.out.println("Checking Flag");
				Thread.sleep(10);
				if (c.flag == 1) {
					System.out.println("Show Card");
					gui.showCard(c.card);
					Thread.sleep(20);
					c.flag = 0;
				}
				else if (c.flag == 2) {
					System.out.println("Update Screen");
					gui.updateScreen(c.symbol, c.curPosition, c.movePosition);
					Thread.sleep(20);
					c.flag = 0;
				}
				else if (c.flag == 3 || c.flag == 4) {
					c.flag = 0;
					break;
				}
			}
			Thread.sleep(20);
			if (c.flag == 3) {
				gui.setVisible(false);
			}
			else if (c.flag == 4) {
				break;
			}
		}
		
		scan.close();
	}

}
