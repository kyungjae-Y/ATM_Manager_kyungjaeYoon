package Util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import DAO.AccountDAO;
import DAO.ClientDAO;

public class FileManager {
//	account.txt , client.txt
//	데이터 클래스에서 가져온다
	public void saveToFile(AccountDAO aDAO, ClientDAO cDAO) {
		String aData = aDAO.saveAsFileData();
		String cData = cDAO.saveAsFileData();

		saveData("account.txt", aData);
		saveData("client.txt", cData);
	}

//	데이터 저장
	void saveData(String fileName, String data) {
		try (FileWriter fw = new FileWriter("src//ATM//" + fileName)) {
			fw.write(data);
			System.out.println(fileName + "저장 성공");
		} catch (IOException e) {
			System.out.println(fileName + "저장 실패");
			e.printStackTrace();
		}
	}

//	데이터 클래스에 전달
	public void loadFromFile(AccountDAO aDAO, ClientDAO cDAO) {
		String aData = loadData("account.txt");
		String cData = loadData("client.txt");

		aDAO.addAccountFromData(aData);
		cDAO.addClientFromData(cData);
	}

//	데이터 불러오기
	String loadData(String fileName) {
		String data = "";
		try (FileReader fr = new FileReader("src//ATM//" + fileName); BufferedReader br = new BufferedReader(fr)) {
			while (true) {
				String line = br.readLine();
				if (line == null) {
					break;
				}
				data += line + "\n";
			}
			data = data.substring(0, data.length() - 1);
			System.out.println(fileName + "로드 완료");
		} catch (IOException e) {
			System.out.println(fileName + "로드 실패");
			e.printStackTrace();
		}
		return data;
	}

	public void tempData(AccountDAO aDAO, ClientDAO cDAO) {
		String userdata = "1001/test01/pw1/김철수\n";
		userdata += "1002/test02/pw2/이영희\n";
		userdata += "1003/test03/pw3/신민수\n";
		userdata += "1004/test04/pw4/최상민\n";
		cDAO.addClientFromData(userdata);

		String accountdata = "test01/1111-1111-1111/8000\n";
		accountdata += "test02/2222-2222-2222/5000\n";
		accountdata += "test01/3333-3333-3333/11000\n";
		accountdata += "test03/4444-4444-4444/9000\n";
		accountdata += "test01/5555-5555-5555/5400\n";
		accountdata += "test02/6666-6666-6666/1000\n";
		accountdata += "test03/7777-7777-7777/1000\n";
		accountdata += "test04/8888-8888-8888/1000\n";
		aDAO.addAccountFromData(accountdata);
	}
}
