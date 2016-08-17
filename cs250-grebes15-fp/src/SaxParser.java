import java.util.*;
import java.io.*;
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import javax.management.Attribute;

public class SaxParser extends DefaultHandler
{

	// All booleans track what element type we are in
	// pls make it stop

	// BASE ELEMENTS
	boolean isProf = false; 	//
	boolean isStudent = false; 	//
	boolean isClass = false; 	//
	boolean isRoom = false; 	//
	boolean isCourse = false; 	//
	// Prof
	boolean profID = false; 	//
	boolean profTaught = false; //
	boolean maxClasses = false; //
	//boolean profTeaches = false;
	// Stud
	boolean studCourse = false; //
	// Class
	boolean classProf = false; 	//
	boolean classCourse = false;//
	boolean classRoom = false; //
	// Room
	boolean roomID = false; 	//
	boolean roomName = false; 	//
	boolean seats = false; 		//
	// Course
	boolean courseID = false; 	//
	boolean courseName = false; //

	Professor prof;
	Student stud;
	Class aClass;
	Course course;
	Classroom room;

	public void startDocument() throws SAXException
	{
		// Do nothing
	}

	// Element events

	public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException
	{
		switch(localName){
			// "Base" Cases (Object complete)
			case "Professor":
				isProf = true;
				prof = new Professor();
				break;
			case "Student":
				isStudent = true;
				stud = new Student();
				break;
			case "Course":
				isCourse = true;
				course = new Course();
				break;
			case "Classes":
				isClass = true;
				aClass = new Class();
				break;
			case "Classroom":
				isRoom = true;
				room = new Classroom();	
				break;
				// ID
			case "ID":
				if(isProf){
					profID = true;
				}else if(isRoom){
					roomID = true;
				}else if(isCourse){
					courseID = true;
				}
				break;
				// References
			case "courseRef":
				if(isStudent){
					studCourse = true;
				}else if(isClass){
					classCourse = true;
				}
				break;
			case "profRef":
				classProf = true;
				break;
			case "roomRef":
				classRoom = true;
				break;
				// Professor (other)
			case "maxClasses":
				maxClasses = true;
				break;
			case "teaches":
				profTaught = true;
				break;
				// name
			case "name":
				if(isRoom){
					roomName = true;
				}else if(isCourse){
					courseName = true;
				}
				break;
				// Room (other)
			case "seats":
				seats = true;
				break;
				// Course is covered in previous sections (name, id)
		}
	}
	public void characters(char[] ch, int start, int length)
	{
		String data = new String(ch, start, length);

		//Prof
		if(!data.equals("")){
			if(profID){
				prof.setID(Integer.parseInt(data));
			}else if(profTaught){
				Course c = new Course();
				c.setID(Integer.parseInt(data));
				prof.addCourse(c);
			}else if(maxClasses){
				prof.setMaxClasses(Integer.parseInt(data));
				// Student
			}else if(studCourse){
				Course c = new Course();
				c.setID(Integer.parseInt(data));
				stud.addCourse(c);
				// Class
			}else if(classProf){
				Professor p = new Professor();
				p.setID(Integer.parseInt(data));
				aClass.setProfessor(p);
			}else if(classCourse){
				Course c = new Course();
				c.setID(Integer.parseInt(data));
				aClass.setCourse(c);
			}else if(classRoom){
				Classroom r = new Classroom();
				r.setID(Integer.parseInt(data));
				aClass.setRoom(r);
				//Room
			}else if(roomID){
				room.setID(Integer.parseInt(data));
			}else if(roomName){
				room.setName(data);
			}else if(seats){
				room.setSeats(Integer.parseInt(data));
				//Course
			}else if(courseID){
				course.setID(Integer.parseInt(data));
			}else if(courseName){
				course.setName(data);
			}else{
				System.out.println("Bad data...  Found: " + data);
				System.exit(-1);
			}
		}

	}

	public void endElement(String namespaceURI, String localName, String qName) throws SAXException
	{
		switch(localName){
			// "Base" Cases (Object complete)
			case "Professor":
				isProf = false;
				TimeTabler.addProf(prof);
				break;
			case "Student":
				isStudent = false;
				TimeTabler.addStudent(stud);
				break;
			case "Course":
				isCourse = false;
				TimeTabler.addCourse(course);
				break;
			case "Classes":
				isClass = false;
				TimeTabler.addClass(aClass);
				break;
			case "Classroom":
				isRoom = false;
				TimeTabler.addRoom(room);
				break;
				// ID
			case "ID":
				if(isProf){
					profID = false;
				}else if(isRoom){
					roomID = false;
				}else if(isCourse){
					courseID = false;
				}
				break;
				// References
			case "courseRef":
				if(isStudent){
					studCourse = false;
				}else if(isClass){
					classCourse = false;
				}
				break;
			case "profRef":
				classProf = false;
				break;
			case "roomRef":
				classRoom = false;
				break;
				// Professor (other)
			case "maxClasses":
				maxClasses = false;
				break;
			case "teaches":
				profTaught = false;
				break;
				// name
			case "name":
				if(isRoom){
					roomName = false;
				}else if(isCourse){
					courseName = false;
				}
				break;
				// Room (other)
			case "seats":
				seats = false;
				break;
				// Course is covered in previous sections (name, id)

		}
	}

	public void endDocument() throws SAXException
	{


	}

	public static String convertToFileURL(String filename)
	{
		String path = new File(filename).getAbsolutePath();
		if(File.separatorChar != '/')
		{
			path = path.replace(File.separatorChar, '/');
		}

		if (!path.startsWith("/"))
		{
			path = "/" + path;
		}

		path = "file:" + path;
		return path;
	}
}
