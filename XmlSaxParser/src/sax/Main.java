package sax;

import java.io.File;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class Main {
	public static void main(String[] args) {
		// sax팩토리 인스턴스생성
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		try {
			//sax parser 생성
			SAXParser saxParser = saxParserFactory.newSAXParser();
			//사용자 정의핸들러 클래스 생성
			CustomHandler handler = new CustomHandler();
			// 파서를 이용하여 xml파일과 handler등록 
			saxParser.parse(new File("D:\\eGovworkspace\\XmlSaxParser\\src\\employee.xml"), handler);

			List<EmployeeVO> empList = handler.getEmpList();
			// 읽은 데이터출력
			for (EmployeeVO emp : empList)
				System.out.println(emp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
