import java.awt.datatransfer.SystemFlavorMap;
import java.util.Scanner;

public class runClient {
	
	public static User my;
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
//		System.out.println("Name Sent");
		
		System.out.println("Spade = 0");
		System.out.println("Diamond = 1");
		System.out.println("Heart = 2");
		System.out.println("Club = 3");
		
		while (c.flag == 0) {
//			System.out.println("Loading...");
			Thread.sleep(10);
		}
//		System.out.println("Load Completed");
		
		gui = new ClientGUI(c.users, c.userNum);
		while (true) {
			while (true) {
//				System.out.println("Checking Flag");
				Thread.sleep(10);
				if (c.flag == 1) {
//					System.out.println("Show Card");
					gui.showCard(c.card);
					Thread.sleep(20);
					c.flag = 0;
				}
				else if (c.flag == 2) {
//					System.out.println("Update Screen");
//					System.out.println(c.symbol + " " + c.curPosition + " " + c.movePosition);
					gui.updateScreen(c.symbol, c.curPosition, c.movePosition);
					Thread.sleep(20);
					c.flag = 0;
				}
				else if (c.flag == 3 || c.flag == 4) {
					break;
				}
			}
//			System.out.println("Round Ended");
			if (c.flag == 3) {
				gui.reset();
				c.flag = 0;
			}
			else if (c.flag == 4) {
				break;
			}
		}
		
//		System.out.println("Game Ended");
		int winner = 0;
		int winnerNum = 0;
		for (int i = 0; i < c.userNum; i++) {
			if (c.users[i].score > c.users[winner].score) {
				winner = i;
				winnerNum = 1;
			}
			else if (c.users[i].score == c.users[winner].score) {
				winnerNum++;
			}
		}
		
		if (winnerNum > 1) {
			System.out.println("Draw!");
		} else {
			System.out.println(c.users[winner].name + " wins!");
		}
		
		scan.close();
	}

}
