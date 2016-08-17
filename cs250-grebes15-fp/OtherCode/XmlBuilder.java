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
  String outputPath = "person.xml";  
  generateXml(outputPath);  
}  
public static void generateXml(String outputPath) {  
  try {  
	
   Person [] arr = new Person[]{new Person("jinbo",20), new Person("gameboy",25)};  
   List<Person> list = Arrays.asList(arr);   
   Document doc = generateXml(list);
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
public static Document generateXml(List<Person> list){  
  Document doc = null;  
  Element root = null;  
  try {  
   DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
   DocumentBuilder builder = factory.newDocumentBuilder();  
   doc = builder.newDocument();  
   root = doc.createElement("student");  
   doc.appendChild(root);  
  } catch (Exception e) {  
   e.printStackTrace();  
   return null;  
  }  
    
  int len = list.size() ;  
  Element element ;  
  for (int i = 0; i < len; i++) {  
   Person person = list.get(i);  
   element = doc.createElement("person"+(i+1)); 
   element.appendChild(createChild(doc,element, "age",""+person.getAge()));
   element.appendChild(createChild(doc, element, "name", person.getName()));   
   root.appendChild(element);  
  }  
  return doc;  
} 
 private static Node createChild(Document doc, Element element, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }

} 
