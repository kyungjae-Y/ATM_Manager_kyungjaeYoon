package DAO;

import Util.InputManager;
import VO.Client;

public class ClientDAO {
	Client[] cList;
	int cnt;
	int maxClientNo;

//	생성자
	public ClientDAO() {
		cList = null;
		cnt = 0;
		maxClientNo = 1001;
	}

//	회원 가입
	public void createClient() {
		String id = InputManager.getValue("ID : ");
//		중복 확인
		int idIdx = idIndex(id);
		if (idIdx != -1) {
			System.err.println("중복 ID 입니다");
			return;
		}
		String pw = InputManager.getValue("PW : ");
		String name = InputManager.getValue("이름 : ");
		Client add = new Client(maxClientNo++, id, pw, name);
		createClient(add, -1, 1, "가입");
	}

//	회원 탈퇴 - 관리자용
	public void adminDeleteClient(AccountDAO aDAO) {
		String id = InputManager.getValue("ID : ");
//		중복 확인
		int idIdx = idIndex(id);
		if (idIdx == -1)
			return;
		createClient(null, idIdx, -1, "탈퇴");
//		계좌도 삭제해야되니 id 값 보낸다
		aDAO.delClientAcc(id);
	}

//	회원 탈퇴 - 사용자용
	public void deleteClient(String id, AccountDAO aDAO) {
		String pw = InputManager.getValue("PW : ");
		int idIdx = idIndex(id); // 중복 확인
		int pwIdx = idIndex(pw); // 중복 확인
		if (idIdx != pwIdx) {
			System.out.println("비밀번호 틀림");
			return;
		}
		createClient(null, idIdx, -1, "탈퇴");
		aDAO.delClientAcc(id);
	}

	void createClient(Client cl, int idx, int num, String msg) {
		Client[] copy = cList;
		cList = new Client[cnt + num];
		int index = 0;
		for (int i = 0; i < cnt; i += 1) {
			if (idx != i) {
				cList[index++] = copy[i];
			}
		}
		if (num != -1) {
			cList[cnt] = cl;
			System.out.println(cList[cnt]);
		}
		cnt += num;
		System.out.println(msg + "완료");
	}

//	회원 수정
	public void updateClient() {
		String id = InputManager.getValue("ID : ");
		int idx = idIndex(id);
		if (idx == -1) {
			System.err.println("ID가 존재하지 않습니다");
			return;
		}
		cList[idx].pw = InputManager.getValue("변경할 PW : ");
		cList[idx].name = InputManager.getValue("변경할 이름 : ");
		System.out.println("변경완료");
		System.out.println(cList[idx]);
	}

//	회원 출력
	public void printClient() {
		System.out.println("==============================");
		System.out.printf("No \tid \tpw \tname\n");
		System.out.println("------------------------------");
		for (int i = 0; i < cnt; i += 1) {
			System.out.print(cList[i]);
		}
		System.out.println("------------------------------");
	}

//	id 중복 참이면 방번호 거짓이면 -1
	int idIndex(String id) {
		for (int i = 0; i < cnt; i += 1) {
			if (id.equals(cList[i].id)) {
				return i;
			}
		}
		return -1;
	}

//	id 중복 참이면 방번호 거짓이면 -1
	int pwIndex(String pw) {
		for (int i = 0; i < cnt; i += 1) {
			if (pw.equals(cList[i].pw)) {
				return i;
			}
		}
		System.out.println("비밀번호가 틀렸습니다");
		return -1;
	}

//	사용자 메뉴 - 로그인 체크
	public String loginCheck() {
		String id = InputManager.getValue("ID : ");
		String pw = InputManager.getValue("pw : ");
		int idIdx = idIndex(id);
		if (idIdx == -1) { // 중복 확인
			System.out.println("id가 존재하지 않습니다");
			return "";
		}
		int pwIdx = pwIndex(pw);
		if (idIdx != pwIdx) {
			System.out.println("id와 비밀번호 불일치");
			return "";
		}
		return id;
	}

//	파일 불러 왔을 때 회원 번호가 제일 높은 멤버로 갱신
	void maxClientNo() {
		maxClientNo = 1001;
		for (int i = 0; i < cnt; i += 1) {
			if (maxClientNo < cList[i].clientNo) {
				maxClientNo = cList[i].clientNo;
			}
		}
		maxClientNo += 1;
	}

//	파일 저장위해 문자열로 만들어 보내기
	public String saveAsFileData() {
		if (cnt == 0)
			return "";
		String data = "";
		for (Client c : cList) {
			data += c.saveToData();
		}
		return data;
	}

//	파일에서 데이터 뽑아오기
	public void addClientFromData(String cData) {
		String[] temp = cData.split("\n");
		cList = new Client[temp.length];
		cnt = cList.length;

		for (int i = 0; i < cnt; i += 1) {
			String[] info = temp[i].split("/");
			cList[i] = new Client(Integer.parseInt(info[0]), info[1], info[2], info[3]);
		}
		maxClientNo();
	}
}