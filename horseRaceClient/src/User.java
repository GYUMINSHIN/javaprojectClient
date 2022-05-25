import java.net.SocketAddress;

public class User {
	public String name;
	public int score;
	public int symbol;
	public SocketAddress adress;
	
	public User(String name, int score) {
		this.name = name;
		this.score = score;
	}
		
	public void addScore(int value) {
		this.score += value;
	}
}
