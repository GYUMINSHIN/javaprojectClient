
public class Card {
	public int symbol;
	public int number;
	
	public Card(int s, int n) {
		this.symbol = s;
		this.number = n;
	}
	
	public String toString() {
		String result = "";
		
		switch (symbol)
		{
		case 0:
			result = "��";
			break;
		case 1:
			result = "��";
			break;
		case 2:
			result = "��";
			break;
		case 3:
			result = "��";
			break;
		}
		
		switch (number)
		{
		case 1:
			result = result.concat("A");
			break;
		case 11:
			result = result.concat("J");
			break;
		case 12:
			result = result.concat("Q");
			break;
		case 13:
			result = result.concat("K");
			break;
		default:
			result = result.concat(Integer.toString(number));
			break;
		}
		
		return result;
	}
}
