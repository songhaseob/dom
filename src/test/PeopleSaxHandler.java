package test;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;


//SAX를 통해 파싱을 하기 위해 먼저 DefaultHandler를 상속받는 Handler 클래스를 작성
public class PeopleSaxHandler extends DefaultHandler{
	
	//파싱한 사람객체를 넣을 리스트
	private List<Person> personList;
	//파싱한 사람 객체
	private Person person;
	//character 메소드에서 저장할 문자열 변수
	private String str;
	
	// 생성자 호출시 리스트 생성, test에서 호출
	public PeopleSaxHandler() {
		personList = new ArrayList<>();
	}
	
	//  startElement로 모든 이벤트를 차례대로 읽는다(people)
	public void startElement(String uri, String localName, String name, Attributes att) {
		//시작 태그를 만났을 때 발생하는 이벤트
		if(name.equals("person")) { //name(태그안에 이름이 person일 경우 )
					//person안의 정보를 담기위해 person객체를 생성한다
			person = new Person(); //빈 값
			personList.add(person);
		}
	}
	// 캐릭터메서드에 xml파일에 있는 모든 내용을 읽어온다 
	//int start라는 파라미터는 정보를 읽기위해 처음 읽어야할 시작점을 알려준다
	//int length는 얼만큼 읽어야하는지 길이를 나타낸다 ex)length가 2면 start로부터 두글자를 읽는다
	public void characters(char[] ch, int start, int length) {
		//태그와 태그 사이의 내용을 처리
		str = new String(ch,start,length);
	}
	//endElement는 끝태그를 만났을때 처리해주는 부분인데 
	// sax방식은 데이터를 메모리에 두는게 아니라 읽고 처리하는 
	// 것이기 때문에 읽을때 나온 자료를 변수에 저장해줘야한다.
	//파라미터 name은 태그안의 이름을 알려준다. 
	public void endElement(String uri, String localName, String name) {
		//끝 태그를 만났을 때,
		if(name.equals("age")) {  //태그안의 내용이 age일때 str의 값을 person에 age변수에 넣어준다.
			person.setAge(Integer.parseInt(str));
		}else if(name.equals("name")) {
			person.setName(str);
		}else if(name.equals("gender")) {
			person.setGender(str);
		}else if(name.equals("role")) {
			person.setRole(str);
		}
	}
    public List<Person> getPersonList(){
		return personList;
	}
	public void setPersonList(List<Person> personList) {
		this.personList=personList;
	}
}
