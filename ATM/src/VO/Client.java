package VO;

// 회원
public class Client {
	public int clientNo; // 1001부터 자동증가
	public String id;
	public String pw;
	public String name;

	public Client(int clientNo, String id, String pw, String name) {
		this.clientNo = clientNo;
		this.id = id;
		this.pw = pw;
		this.name = name;
	}

	@Override
	public String toString() {
		return clientNo + "\t" + id + "\t" + pw + "\t" + name + "\n";
	}

	public String saveToData() {
		return "%d/%s/%s/%s\n".formatted(clientNo, id, pw, name);
	}
}