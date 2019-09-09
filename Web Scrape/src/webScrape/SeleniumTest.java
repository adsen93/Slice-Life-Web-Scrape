package webScrape;

import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

    import org.openqa.selenium.By;
    import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

    public class SeleniumTest{
    	public SeleniumTest() {
    		System.setProperty("webdriver.chrome.driver", "C:\\Users\\ahmed\\Downloads\\chromedriver.exe");
            @SuppressWarnings("unused")
            WebDriver driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.get("https://slicelife.com/pizza-delivery/all-cities");
    	}
        public static void viewMore() {
	        System.setProperty("webdriver.chrome.driver", "C:\\Users\\ahmed\\Downloads\\chromedriver.exe");
	        @SuppressWarnings("unused")
	        WebDriver driver = new ChromeDriver();
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	        driver.get("https://slicelife.com/pizza-delivery/all-cities");
	        
	        PrintStream out = null;
			try {
				out = new PrintStream(new FileOutputStream("allCitieshref.txt"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    //    System.setOut(out);
	        List<WebElement> list = new ArrayList<WebElement>(); 
	        list = driver.findElements(By.className("f1o0e1cp"));
	      //  System.out.println("ArrayList : " + list.toString()); 
	
	     //   System.out.println(driver.findElements(By.className("f1o0e1cp")).size());
	        for (WebElement more : list)
	            more.click();
	        
	        List<WebElement> list2 = new ArrayList<WebElement>();
	        list2 = driver.findElements(By.className("fmiu1k6"));
	        
	        for (WebElement city : list2) {
	        	System.out.println(city.getAttribute("href"));
	        	out.println(city.getAttribute("href"));
	        }
        }
        
        public static String viewMenu() throws IOException {
        //	System.setProperty("webdriver.chrome.driver", "C:\\Users\\ahmed\\Downloads\\chromedriver.exe");
	     //   @SuppressWarnings("unused")
	      //  WebDriver driver = new ChromeDriver();
	       // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	        File file = new File("C:\\Users\\ahmed\\eclipse-workspace\\Web Scrape\\allCitieshref.txt");  
	        BufferedReader br = new BufferedReader(new FileReader(file)); 
	        List<WebElement> list = new ArrayList<WebElement>();
	        String st; 
	       // while ((st = br.readLine()) != null) 
	        //  System.out.println(st); 
	        st = br.readLine();
	        //driver.get(st);
	        return st;
	   //     list = driver.findElements(By.className("a.f1jago7f"));
	    //    System.out.println(list.get(list.size() - 1));
        }
    }
