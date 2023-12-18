package Util;

import java.util.Scanner;

public class InputManager {
	private static Scanner scan = new Scanner(System.in);

//	숫자 예외사항 체크 후 반환
	public static int getValue(String msg, int start, int end) {
		System.out.printf("%s[%d-%d] : ", msg, start, end);
		try {
			int num = scan.nextInt();
			scan.nextLine();
			if (num < start || num > end) {
				System.out.printf("[%d - %d] 사이값만 가능\n", start, end);
			}
			return num;
		} catch (Exception e) {
			scan.nextLine();
			System.out.println("숫자값을 입력하세요");
		}
		return -1;
	}

	public static String getValue(String msg) {
		System.out.print(msg);
		String data = scan.next();
		scan.nextLine();
		return data;
	}

//	종료 시 닫기
	public static void closeUtil() {
		scan.close();
	}
}
