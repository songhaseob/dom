package test;

import java.io.File;
 
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
 
public class XmlParsering {
 
	public static void main(String[] args) {
 
		try {
			
			// 파일을 읽어옴
			File file = new File("D:/D_Other/book3.xml");

			// 문서를 읽기위한 공장(Factory)를 만들어줌
			DocumentBuilderFactory docBuildFact = DocumentBuilderFactory.newInstance();

			// 빌더를 생성
			DocumentBuilder docBuild = docBuildFact.newDocumentBuilder();

			// 생성된 빌더를 통해서 xml문서를 Document객체로 파싱해 가져옴
			Document doc = docBuild.parse(file);

			// dom tree가 XML문서의 구조대로 완성해줌
			doc.getDocumentElement().normalize();
			
			// 노드의 이름을 출력
			System.out.println("루트 요소 : " + doc.getDocumentElement().getNodeName());
			System.out.println();
 
			// person엘리먼트 리스트
			// person 안의 정보들 (각 person 마다 안에있는 name의정보, tel의 정보,
			// address의 정보들을 personlist에 담아줌)
			NodeList personlist = doc.getElementsByTagName("person");
			
			// 반복문을 실행하여 노드의 정보를 얻어옴
			for (int i = 0; i < personlist.getLength(); i++) {
 
				System.out.println("-----------------"+ (i+1) +"번 고객 정보 ------------------");
 				//personlist의 태그 내용(노드)을 personNode에 담아줌
				Node personNode = personlist.item(i);
 				//personNode의 노드 타입이 노드의 요소와 같으면 해당결과를 출력해준다.
				if (personNode.getNodeType() == Node.ELEMENT_NODE) {
					// person엘리먼트 
					// personNode를 Element로 형변환 시켜 준 뒤 personElmnt 객체에 담아줌
					Element personElmnt = (Element) personNode;
 
					// name 태그의 정보 출력
					// 태그안의 이름이 name이면 그 정보를 nameList에 담아준다.
					NodeList nameList= personElmnt.getElementsByTagName("name");
					Element nameElmnt = (Element) nameList.item(0);
					Node name = nameElmnt.getFirstChild();
					// 노드 안의 데이터를 출력해준다
					System.out.println("name    : " + name.getNodeValue());
 
					// tel 태그의 정보출력
					// 태그안의 이름이 tel이면 그 정보를 telList에 담아준다.
					NodeList telList= personElmnt.getElementsByTagName("tel");
					Element telElmnt = (Element) telList.item(0);
					Node tel = telElmnt.getFirstChild();
					// 노드 안의 데이터를 출력해준다
					System.out.println("tel     : " + tel.getNodeValue());
 
					// address 태그의 정보 출력
					// 태그안의 이름이 address이면 그 정보를 addressList에 담아준다.
					NodeList addressList= personElmnt.getElementsByTagName("address");
					Element addressElmnt = (Element) addressList.item(0);
					Node address = addressElmnt.getFirstChild();
					// 노드 안의 데이터를 출력해준다
					System.out.println("address : " + address.getNodeValue());
				}
 
				System.out.println("---------------------------------------------");
				System.out.println();
			}
 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
