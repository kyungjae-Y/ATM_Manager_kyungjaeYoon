package VO;

// 통장 
// 한 회원 마다 계죄 3개까지 만들 수 있음
public class Account {
	public String clientId;
	public String accNumber;
	public int money;

	public Account(String clientId, String accNumber, int money) {
		this.clientId = clientId;
		this.accNumber = accNumber;
		this.money = money;
	}

	@Override
	public String toString() {
		return clientId + " " + accNumber + " " + money + "원\n";
	}

	public String saveToData() {
		return "%s/%s/%d\n".formatted(clientId, accNumber, money);
	}
}