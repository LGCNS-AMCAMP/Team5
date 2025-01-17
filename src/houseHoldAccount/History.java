package houseHoldAccount;

public class History {
	String date;
	String source;
	int money;
	public History(String date, String source, int money, String check, String memo) {
		super();
		this.date = date;
		this.source = source;
		this.money = money;
		this.check = check;
		this.memo = memo;
	}
	@Override
	public String toString() {
		return "History [date=" + date + ", source=" + source + ", money=" + money + ", check=" + check + ", memo="
				+ memo + "]";
	}
	String check;
	public String getDate() {
		return date;
	}
	public String getSource() {
		return source;
	}
	public int getMoney() {
		return money;
	}
	public String getCheck() {
		return check;
	}
	public String getMemo() {
		return memo;
	}
	String memo;
}

