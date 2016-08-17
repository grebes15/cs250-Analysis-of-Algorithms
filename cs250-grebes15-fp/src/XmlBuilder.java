import java.io.FileOutputStream;   
import java.io.PrintWriter;  
import java.util.Arrays;  
import java.util.Collections;  
import java.util.List;  
import javax.xml.parsers.DocumentBuilder;  
import javax.xml.parsers.DocumentBuilderFactory;  
import javax.xml.parsers.ParserConfigurationException;  
import javax.xml.transform.OutputKeys;  
import javax.xml.transform.Transformer;  
import javax.xml.transform.TransformerFactory;  
import javax.xml.transform.dom.DOMSource;  
import javax.xml.transform.stream.StreamResult;  
import org.w3c.dom.Document;  
import org.w3c.dom.Element;  
import java.util.*;
import org.w3c.dom.Node;

  
public class XmlBuilder{  
public static void main(String[] args) {  
  String outputPath = "input.xml";  
  generateXml(outputPath);  
}  
public static void generateXml(String outputPath) {  
  try {  
	
   Student[] arr = Preferences.GeneratePreferences();
   Professor[] arr1= Preferences.GenerateProfessor();
   Classroom[] arr2 = Preferences.GenerateClassroom();
   Class[] arr3=Preferences.GenerateClass();

   List<Student> list = Arrays.asList(arr);
   List<Professor> list1 = Arrays.asList(arr1);
   List<Classroom> list2 = Arrays.asList(arr2);
   List<Class> list3 = Arrays.asList(arr3);

   Document doc = generateXml(list, list1, list2, list3);
	//Document doc1=generateXml1(list1);
  
   outputXml(doc, outputPath); 
  } catch (Exception e) {  
   System.err.println("Exception Happens");  
  }  
} 
private static void outputXml (Document doc, String fileName) throws Exception{  
  TransformerFactory tf = TransformerFactory.newInstance();  
  Transformer transformer = tf.newTransformer();  
  DOMSource source = new DOMSource(doc);  
  transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");  
  transformer.setOutputProperty(OutputKeys.INDENT, "yes");  
  PrintWriter pw = new PrintWriter(new FileOutputStream(fileName));  
  StreamResult result = new StreamResult(pw);  
  transformer.transform(source, result);  
  System.out.println("Sucessfully Generates the Xml file!");  
}  
public static Document generateXml(List<Student> list, List<Professor> list1, List<Classroom> list2, List<Class> list3){  
  Document doc = null;  
  Element root = null; 
 // Element root1 = null;
  try {  
   DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
   DocumentBuilder builder = factory.newDocumentBuilder();  
   doc = builder.newDocument();  
   root = doc.createElement("Data");  
   //root1 = doc.createElement("professor");

   doc.appendChild(root);
	//doc.appendChild(root1);
  } catch (Exception e) {  
   e.printStackTrace();  
   return null;  
  }  
  int len = list.size() ;  
  Element element ;  
  for (int i = 0; i < len; i++) {  
   Student person = list.get(i);  
   element = doc.createElement("Student"); 
   element.appendChild(createChild(doc,element, "Student_ID",""+person.getStudent_ID()));
	element.appendChild(createChild(doc, element, "CourseName", person.getCourses().get(0).getName())); 
   element.appendChild(createChild(doc, element, "CourseName", person.getCourses().get(1).getName())); 
   element.appendChild(createChild(doc, element, "CourseName", person.getCourses().get(2).getName()));
   element.appendChild(createChild(doc, element, "CourseName", person.getCourses().get(3).getName()));  
   root.appendChild(element);  
  } 
	int len1= list1.size();
  Element element1;
  for(int i=0; i<len1; i++){
  	Professor professor= list1.get(i);
  	element1=doc.createElement("professor");
  	element1.appendChild(createChild(doc, element1,"profID", ""+professor.getId()));
  	 element1.appendChild(createChild(doc, element1, "maxClasses", ""+professor.getMaxClasses()));
  	 element1.appendChild(createChild(doc, element1, "ProfTaught", professor.getCourses().get(0).getName()));
  	 element1.appendChild(createChild(doc, element1, "ProfTaught", professor.getCourses().get(1).getName()));
  	 element1.appendChild(createChild(doc, element1,"ProfTaught", professor.getCourses().get(2).getName()));
  	root.appendChild(element1);
  }
  int len2=list2.size();
  Element element2;
  for(int i=0; i<len2; i++){
  	Classroom cr=list2.get(i);
  	element2=doc.createElement("Classroom");
  	element2.appendChild(createChild(doc, element2, "roomID", ""+cr.getID()));
  	element2.appendChild(createChild(doc, element2, "roomName", cr.getName()));
  	element2.appendChild(createChild(doc, element2, "seats", ""+cr.getSeats()));
	root.appendChild(element2);
  }
  int len3=list3.size();
  Element element3;
  for(int i=0; i<len3; i++){
  	Class c= list3.get(i);
  	element3=doc.createElement("Class");
  	element3.appendChild(createChild(doc, element3, "classProf", ""+c.getProf().getId()));
  	element3.appendChild(createChild(doc, element3, "classCourse", c.getCourse().getName()));
  	element3.appendChild(createChild(doc, element3, "classRoom", c.getRoom().getName()));
  	root.appendChild(element3);
  }


  return doc;
  } 


 private static Node createChild(Document doc, Element element, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }

} 


