import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
 
public class Sample {
 
  public static void main(String[] args) {
 
	Sample obj = new Sample();
	obj.run();
 
  }
 
  public void run() {
 
	String csvFile = "/Users/Andreas/Documents/College/3rdYear/2ndSemester/CompSci250/cs250s2015-grebes15/lab7/Part1/cTIM_core_align.fa";
	BufferedReader br = null;
	String line = "";
	String cvsSplitBy = "\n";
 
	try {
 
		br = new BufferedReader(new FileReader(csvFile));
		while ((line = br.readLine()) != null) {
 
		        // use comma as separator
			String[] country = line.split(cvsSplitBy);
 
			System.out.println(country[0]);
 
		}
 
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		if (br != null) {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
 
	System.out.println("Done");
  }
 
}