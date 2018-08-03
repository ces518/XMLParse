package sax;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class CustomHandler extends DefaultHandler {

	// VO와 VO를 담을 리스트
	private List<EmployeeVO> empList = null;
	private EmployeeVO emp = null;

	// EmployeeVO 리스트 getter 
	public List<EmployeeVO> getEmpList() {
		return empList;
	}

	// 읽은데이터가 나이인지 , 이름인지 , 성별인지 , ROLE 인지 판별하는 FLAG변수 
	boolean bAge = false;
	boolean bName = false;
	boolean bGender = false;
	boolean bRole = false;

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		// 엘리먼트의 이름이 Employee라면 ( 엘리먼트를 여는시점 )
		if (qName.equalsIgnoreCase("Employee")) {
			String id = attributes.getValue("id");
			emp = new EmployeeVO();
			emp.setId(Integer.parseInt(id));
			if (empList == null)
				empList = new ArrayList<EmployeeVO>();
		} else if (qName.equalsIgnoreCase("name")) {
			bName = true;
		} else if (qName.equalsIgnoreCase("age")) {
			bAge = true;
		} else if (qName.equalsIgnoreCase("gender")) {
			bGender = true;
		} else if (qName.equalsIgnoreCase("role")) {
			bRole = true;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		// 엘리먼트의 이름이 Employee라면 ( 엘리먼트를 닫는시점 )
		if (qName.equalsIgnoreCase("Employee")) {
			empList.add(emp);
		}
	}

	@Override
	public void characters(char ch[], int start, int length) throws SAXException {
		//읽은 데이터를 flag변수를 활용하여 VO에 세팅해준다.
		if (bAge) {
			emp.setAge(Integer.parseInt(new String(ch, start, length)));
			bAge = false;
		} else if (bName) {
			emp.setName(new String(ch, start, length));
			bName = false;
		} else if (bRole) {
			emp.setRole(new String(ch, start, length));
			bRole = false;
		} else if (bGender) {
			emp.setGender(new String(ch, start, length));
			bGender = false;
		}
	}
}
