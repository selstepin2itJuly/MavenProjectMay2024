package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

public class YAMLHelper {

	Yaml y = new Yaml();
	
	public Map<Object, Object> getYAMLData() throws FileNotFoundException
	{
		String file = "./src/test/resources/testdata/yaml/testdata.yaml";
		Map<Object,Object> readData = y.load(new FileInputStream(new File(file)));
		return readData;
	}
	
	public Map<Object, Object> getYAMLData(String fileName) throws FileNotFoundException
	{
		String file = "./src/test/resources/testdata/yaml/"+fileName+".yaml";
		Map<Object,Object> readData = y.load(new FileInputStream(new File(file)));
		return readData;
	}
	
	public Map<Object, Object> getTestcaseSpecificData(String testcase) throws FileNotFoundException
	{
		Map<Object, Object> temp = getYAMLData();
		System.out.println(temp);
		return temp;
		
	}
	public Map<Object, Object> getTestcaseSpecificData(String filename, String testcase) throws FileNotFoundException
	{
		Map<Object, Object> temp = getYAMLData(filename);
		System.out.println(temp);
		return temp;
	}
	
	public static void main(String[] arg) throws FileNotFoundException
	{
		YAMLHelper g = new YAMLHelper();
		g.getTestcaseSpecificData("");
	}
}
