package webScrape;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
public class WebScrape {
	
	public static void main (String [] args) throws IOException {
	/*	Document dc = Jsoup.connect("https://www.wikihow.com/Adjust-Bass-on-a-Computer").timeout(6000).get();
		Elements body = dc.select("div#bodycontents");
		
		for (Element step : body) {
			String method = step.select("div.steps h3 div.altblock").text();
			System.out.println(method);
			String title = step.select("div.steps span.mw-headline").text();
			System.out.println(title);
			
		}
	*/
		//fmiu1k6
		//SeleniumTest chromeDriver = new SeleniumTest();
	//	SeleniumTest.viewMore();	//Open all view more buttons
	//	SeleniumTest.viewMenu();	//Get all 
		File file = new File("C:\\Users\\ahmed\\eclipse-workspace\\Web Scrape\\Ohio.txt");  //File with all of the city hrefs.
	
		@SuppressWarnings("resource")
		BufferedReader in = new BufferedReader(new FileReader(file));
		String str;

		List<String> list = new ArrayList<String>();
		while((str = in.readLine()) != null){
		    list.add(str);
		}

		String[] allCitiesHref = list.toArray(new String[0]);	//Used to store all city hrefs
		//Document dc = Jsoup.connect("https://slicelife.com/pizza-delivery/ak-anchorage").timeout(30 * 1000).get();;	//dc is used to connect to the href of a city.
		Document dc = null;
		Document pizzaPage = null;	//pizzaPage is used to connect to the specific pizza location of a city.
		int lastPage = 0;
		Elements body = null;
	//	Elements body = dc.select("div.f1l2u390 h3.f1ptf8hk > a");	//body is all of the buttons for the city hrefs.
		//Elements contactInfo;
	//	Element elmnt;
		int cntr = 1;
		boolean connectionEstablished = false;
		//string link = "";
		//String baseURL = "https://slicelife.com/";
		
		 PrintStream out = null;
			try {
				out = new PrintStream(new FileOutputStream("sliceLifeLocations3(Ohio cutoff).txt"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		for (int i = 0; i < allCitiesHref.length; i++) {
			
			
	            //	dc = Jsoup.connect(allCitiesHref[i]).timeout(30 * 1000).userAgent("Mozilla/5.0 (Windows NT 10.0;Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.132 Safari/537.36").get();
	            while(connectionEstablished == false) {
	            	try{
	            		dc = Jsoup.connect(allCitiesHref[i]).timeout(30 * 1000).userAgent("Mozilla/5.0 (Windows NT 10.0;Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.132 Safari/537.36").get();
	            		body = dc.select("div.f1l2u390 h3.f1ptf8hk > a");
	            		connectionEstablished = true;
	            	}catch(Exception e) {
	            		System.out.println("whoops");
	            		dc = connect(dc, allCitiesHref[i]);
	            		
	            	}
	            }
	            
	            connectionEstablished = false;
			
			//dc = Jsoup.connect(allCitiesHref[i]).timeout(30 * 1000).get();
			
			lastPage = getMaxPages(allCitiesHref[i]);
			do {
			    for (Element step : body) {   
		            String url = step.attr("href");
		            System.out.println(url);
		            
		            while(connectionEstablished == false) {
		            	try{
		            		pizzaPage = Jsoup.connect(url).timeout(30 * 1000).userAgent("Mozilla/5.0 (Windows NT 10.0;Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.132 Safari/537.36").get();
		            	//	body = dc.select("div.f1l2u390 h3.f1ptf8hk > a");
		            		connectionEstablished = true;
		            	}catch(Exception e) {
		            		System.out.println("whoops");
		            		pizzaPage = connect(pizzaPage, url);
		            		
		            	}
		            }
		            
		            connectionEstablished = false;
		           // 		connect(pizzaPage, url);
			           // 	pizzaPage = Jsoup.connect(url).timeout(30 * 1000).userAgent("Mozilla/5.0 (Windows NT 10.0;Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.132 Safari/537.36").get();
			            
		   //         connectionEstablished = false;
		          
		            
			          // 		String test = pizzaPage.selectFirst("div.f1xs6i5f").text();
			           //		System.out.println(test);
		     //       try {
			  //          	String contactInfo = pizzaPage.select("div.f7q9e1f h1.f13p7rsj").text() + ", " + pizzaPage.select("div.f19xeu2d h2.f1v8faom address.f1lfckhr").text() + ", " + pizzaPage.selectFirst("div.f19xeu2d button.f12gt8lx").text() ;
			   //         	System.out.println(contactInfo);
			    //        	out.println(contactInfo);
			     //       	connectionEstablished = true;
		          //  }catch (Exception e) {
		            	
		           // }
			            getInfo(pizzaPage, out, url);	
			            	
			            //pizzaPage = Jsoup.connect(url).timeout(30 * 1000).get();
			          
		            
		            connectionEstablished = false;
		       	}
			    cntr++;
			   // System.out.println("https://slicelife.com/pizza-delivery/ak-anchorage" + "?page=" + cntr);
			    
			    
			    while(connectionEstablished == false) {
	            	try{
	            		dc = Jsoup.connect(allCitiesHref[i] + "?page=" + cntr).timeout(30 * 1000).userAgent("Mozilla/5.0 (Windows NT 10.0;Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.132 Safari/537.36").get();
	            		body = dc.select("div.f1l2u390 h3.f1ptf8hk > a");
	            		connectionEstablished = true;
	            	}catch(Exception e) {
	            		System.out.println("whoops");
	            		dc = connect(dc, allCitiesHref[i] + "?page=" + cntr);
	            		
	            	}
	            }
	            
	            connectionEstablished = false;
			    
			    
			   // 	try{connect(dc, allCitiesHref[i] + "?page=" + cntr); 
			 //   	body = dc.select("div.f1l2u390 h3.f1ptf8hk > a");
			 //   	}catch (Exception e) {
			//    		System.out.println("whoops2");
			//    	}
		            //dc = Jsoup.connect(allCitiesHref[i] + "?page=" + cntr).timeout(30 * 1000).userAgent("Mozilla/5.0 (Windows NT 10.0;Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.132 Safari/537.36").get();
		            //	connectionEstablished = true;
		            
		     //   connectionEstablished = false;
		       
			}while(cntr <= lastPage);
		}
		/*	
			Elements elements = dc.getElementsByClass("fmiu1k6");
			for (Element element : elements) {
				String link = element.attributes().get("href");
				System.out.println(link);
			}
			
			Elements elements1 = dc.getElementsByClass("f1o0e1cp");
			for (Element element : elements1) {
				String link = element.attributes().get("href");
				System.out.println(link);
			}
			*/
			
		
		
		//String link = step.select("div.fx9lj4m > a").attr("abs:href");
			//System.out.println(link);
		//	String link = step.select("div.fx9lj4m div.f105sxl5 a href").text();
			//
		}
		
	
	public static int getMaxPages(String link) throws IOException {
		Document dc2 = null;;
		boolean connectionEstablished = false;
		 while(connectionEstablished == false) {
	            try{
	            	dc2 = Jsoup.connect(link).timeout(30 * 1000).get();
	            	connectionEstablished = true;
	            }catch(Exception e) {
	            	System.out.println("failed, trying again 5");
	            	connectionEstablished = connected(connectionEstablished, link, dc2);
	            //	dc = Jsoup.connect(allCitiesHref[i] + "?page=" + cntr).timeout(30 * 1000).get();
	            };
         }
	        connectionEstablished = false;
		Elements body2 = dc2.select("div.f12to336");
		String state2 = "";
		for (Element step2 : body2) {
			state2 = step2.select("a.f1jago7f").text();
			System.out.println(state2);
		}
		String s[] = state2.split(" ");
		int out[] = new int[s.length];
		for(int i = 0 ; i < s.length ; i++) { 
			if(s.length == 1) {
				return 1;
			}
				
		     out[i] = Integer.parseInt(s[i]);
		}
		System.out.println(out[out.length - 1]);
		return out[out.length - 1];
	}
	
	public static int getNumLinesTextFile(File file ) throws FileNotFoundException, IOException {
		try
		(
		   FileReader       input = new FileReader(file);
		   LineNumberReader count = new LineNumberReader(input);
		)
		{
		   while (count.skip(Long.MAX_VALUE) > 0)
		   {
		      // Loop just in case the file is > Long.MAX_VALUE or skip() decides to not read the entire file
		   }

		  int result = count.getLineNumber() + 1;                                    // +1 because line index starts at 0
		  return result;
		}
	}
	
	public static boolean connected(boolean connectionEstablished, String link, Document dc2) {
		 while(connectionEstablished == false) {
	            try{
	            	dc2 = Jsoup.connect(link).timeout(6000).get();
	            	connectionEstablished = true;
	            }catch(Exception e) {
	            	System.out.println("failed, trying again 6");
	            	connected(connectionEstablished, link, dc2);
	            //	dc = Jsoup.connect(allCitiesHref[i] + "?page=" + cntr).timeout(30 * 1000).get();
	            };
      }
		return true;
	}
	
	public static void getInfo(Document pizzaPage, PrintStream out, String url) throws IOException {
		 try {
         	String contactInfo = pizzaPage.select("div.f7q9e1f h1.f13p7rsj").text() + ", " + pizzaPage.select("div.f19xeu2d h2.f1v8faom address.f1lfckhr").text() + ", " + pizzaPage.selectFirst("div.f19xeu2d button.f12gt8lx").text() ;
         	System.out.println(contactInfo);
         	out.println(contactInfo);
              }catch (Exception e) {
            	  pizzaPage = Jsoup.connect(url).timeout(30 * 1000).get();
            	  getInfo(pizzaPage, out, url);
     }
	}
	
	public static Document connect(Document dc, String url) {
		try {
			dc = Jsoup.connect(url).timeout(30 * 1000).get();
			return dc;
		}
		catch (Exception e) {
			connect(dc, url);
		}
		return dc;
	}
	}

