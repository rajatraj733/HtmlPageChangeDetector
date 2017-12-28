import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MainClass {
	public static void main(String[] args) {
		for(int i=0; i<60; i++)
		{
			displayResult();
			try {
				Thread.sleep(20000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	public static void displayResult()
	{
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet get = new HttpGet("http://cbseresults.nic.in/");
		HttpResponse response = null;
		try {
			 response = client.execute(get);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
		BufferedReader is ;
		StringBuffer wholeText;
		try {
			is = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			 wholeText = new StringBuffer();
			String line = "";
			while((line=is.readLine())!=null)
				wholeText.append("\n"+line);
		} catch (UnsupportedOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
			
		}
//		System.out.println(wholeText);
		Document doc  = Jsoup.parse(wholeText.toString());
		System.out.println(doc.title());
		Elements links = doc.select("a[href]");
		
		for(Element link: links)
		{
			if(link.text().equals("CBSE - Class X Examination"))
			{
				if(link.attr("href").equals("#"))
				{
					System.out.println("Not yet");
				}
				else
				{
					System.out.println("Result Declared");
				}
			}
				
		}
	}
}


