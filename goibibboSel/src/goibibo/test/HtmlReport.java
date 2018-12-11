package goibibo.test;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class HtmlReport {
	
	File htmlTempFile;
	String htmlString;
	
	public void generateInitReport()
	{
		 htmlTempFile = new File("./Configure/HTMLTemplate.html");
		try{
			htmlString=FileUtils.readFileToString(htmlTempFile);
		}
		catch(IOException e)
		{
			System.out.println("Error to Generate HTML file");
			e.printStackTrace();
		}
	}
	public void generateReport()
	{
		try
		{
			generateReportBody();
			File htmlReport=new File("./Configure/HTMLReport.html");
			FileUtils.writeStringToFile(htmlReport, htmlString);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void generateReportBody()
	{
		String title="Test Report";
		htmlString=htmlString.replace("$Title",title);
		htmlString=htmlString.replace("$Test_Id",title);
	//	htmlString=htmlString.replace("$Title",title);

	}

}
