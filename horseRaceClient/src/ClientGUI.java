import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class ClientGUI extends JFrame {
	Font font = new Font("¸¼Àº °íµñ", Font.PLAIN, 25);
	String colNames[] = {"Start", " ", " ", " ", " ", " ", " ", "Goal"};
	Object datas[][] =
			{
					{ '¢¼', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
					{ '¡ß', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
					{ '¢¾', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
					{ '¢À', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
			};
	JPanel scorePanel = new JPanel();
	JLabel[] scoreLabel = new JLabel[4];
	
	JPanel boardPanel = new JPanel();
	JTable table = new JTable(datas, colNames);
	JScrollPane jscp = new JScrollPane(table);

	JPanel cardPanel = new JPanel();
	JLabel cardLabel = new JLabel();
	
	User[] users;
	int userNum;
	
	ClientGUI(User[] users, int userNum) {
		super("Horse Race!");
		this.setLocation(0, 0);
		this.setSize(400, 400);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.users = users;
		this.userNum = userNum;
		
		scorePanel.setLayout(new GridLayout(1, 4));
		for (int i = 0; i < userNum; i++) {
			scoreLabel[i] = new JLabel();
			scoreLabel[i].setText(users[i].name + ": " + users[i].score);
			scoreLabel[i].setFont(font);
			scorePanel.add(scoreLabel[i]);
		}
		
//		table.getTableHeader().setFont(font);
		table.setFont(font);
		table.setRowHeight(50);
		cardLabel.setFont(font);
		
		boardPanel.add(jscp);
		cardPanel.add(cardLabel);
		
		this.add(scorePanel, BorderLayout.PAGE_START);
		this.add(boardPanel, BorderLayout.CENTER);
		this.add(cardPanel, BorderLayout.PAGE_END);
		
		this.pack();
		this.setVisible(true);
	}
	
	public void showCard(String card) {
		cardLabel.setText("»ÌÀº Ä«µå: " + card);
		cardLabel.repaint();
	}
	
	public void updateScreen(int symbol, int curPosition, int movePosition) throws InterruptedException {
		Object obj = datas[symbol][curPosition];
		datas[symbol][curPosition] = ' ';
		datas[symbol][movePosition] = obj;
		table.repaint();
		Thread.sleep(1000);
	}
	
	public void reset()
	{
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 8; j++) {
				datas[i][j] = ' ';
			}
		}
		datas[0][0] = '¢¼';
		datas[1][0] = '¡ß';
		datas[2][0] = '¢¾';
		datas[3][0] = '¢À';
		
		for (int i = 0; i < userNum; i++) {
			scoreLabel[i].setText(users[i].name + ": " + users[i].score);
		}
		
		table.repaint();
		scorePanel.repaint();
	}
}