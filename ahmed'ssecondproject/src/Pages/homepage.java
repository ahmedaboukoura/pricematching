package Pages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class homepage {
	String browserType;
    String homeurl;
    String excelfilepath;
	//following the factory page object model
	WebDriver driver;
	@FindBy(xpath=(".//*[@id='header']/div[2]/div/div/nav/div[1]/a"))
	WebElement signin;
	@FindBy(css="#search_query_top")
	WebElement searchBox;
	@FindBy(name="submit_search")
	WebElement searchButton;
	@FindBy(xpath=".//*[@id='homefeatured']/li[2]/div/div[1]/div")
	WebElement blouse;
	@FindBy(xpath=".//*[@id='homefeatured']/li[2]/div/div[1]/div/a[2]")
	WebElement quickView;
	@FindBy(className="icon-plus")
	WebElement plusIcon;
	@FindBy(xpath=".//*[@id='add_to_cart']/button")
	WebElement sumbitButton;
	@FindBy(id="group_1")
	WebElement sizeList;
	@FindBy(xpath=".//*[@id='layer_cart']/div[1]/div[2]/div[4]/a/span")
	WebElement proccedToCheckout;
	@FindBy(xpath=".//*[@id='layer_cart']/div[1]/div[1]/h2")
	WebElement heading;
	@FindBy(xpath=".//*[@id='homefeatured']/li[1]/div/div[2]/h5/a")
	WebElement firstProduct;
	@FindBy(xpath=".//*[@id='homefeatured']/li[2]/div/div[2]/h5/a")
	WebElement secondProduct;
	@FindBy(xpath=".//*[@id='homefeatured']/li[3]/div/div[2]/h5/a")
	WebElement thirdProduct;
	@FindBy(xpath=".//*[@id='homefeatured']/li[4]/div/div[2]/h5/a")
	WebElement fourthProduct;
	@FindBy(xpath=".//*[@id='homefeatured']/li[5]/div/div[2]/h5/a")
	WebElement fifthProduct;
	@FindBy(xpath=".//*[@id='homefeatured']/li[6]/div/div[2]/h5/a")
	WebElement sixthProduct;
	@FindBy(xpath=".//*[@id='homefeatured']/li[7]/div/div[2]/h5/a")
	WebElement seventhProduct;
	@FindBy(xpath=".//*[@id='homefeatured']/li[8]/div/div[2]/h5/a")
	WebElement eightsProduct;
	@FindBy(xpath=".//*[@id='homefeatured']/li[1]/div/div[1]/div/div[2]/span")
	WebElement firstPrice;
	@FindBy(xpath=".//*[@id='homefeatured']/li[2]/div/div[1]/div/div[2]/span")
	WebElement secondPrice;
	@FindBy(xpath=".//*[@id='homefeatured']/li[3]/div/div[1]/div/div[2]/span")
	WebElement thirdPrice;
	@FindBy(xpath=".//*[@id='homefeatured']/li[4]/div/div[1]/div/div[2]/span")
	WebElement fourthPrice;
	@FindBy(xpath=".//*[@id='homefeatured']/li[5]/div/div[1]/div/div[2]/span")
	WebElement fifthPrice;
	@FindBy(xpath=".//*[@id='homefeatured']/li[6]/div/div[1]/div/div[2]/span")
	WebElement sixthPrice;
	@FindBy(xpath=".//*[@id='homefeatured']/li[7]/div/div[1]/div/div[2]/span")
	WebElement seventhPrice;
	@FindBy(xpath=".//*[@id='homefeatured']/li[8]/div/div[1]/div/div[2]/span")
	WebElement eigthsPrice;
	
	//building the constructor 
	public homepage(WebDriver argDriver){
		this.driver=argDriver;
		PageFactory.initElements(driver, this);
	}
	public void loadprpfiles(){
		try {
			Properties prop = new Properties();
			FileInputStream fs;
			fs = new FileInputStream("C:\\Users\\ahmed aboukoura\\Desktop\\project\\ahmed'ssecondproject\\src\\Pages\\url&browser.properties");
			prop.load(fs);
			browserType=prop.getProperty("browser");
		      excelfilepath=prop.getProperty("excelfilepath");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    //
 // this method will be true  if the actual price and actual product matched the expected price and the expected product 
    // it will give true for all except for number 1 and 6 because i put incorrect data on the excel file ! 
	public boolean   DidThePriceAndProductMatch(int argProductNumber) throws InterruptedException{
		int y = argProductNumber - 1;
		WebElement apro = driver.findElement(By.xpath(".//*[@id='homefeatured']/li["+argProductNumber+"]/div/div[2]/h5/a"));
		WebElement apri = driver.findElement(By.xpath(".//*[@id='homefeatured']/li["+argProductNumber+"]/div/div[1]/div/div[2]/span"));
		WebElement priceBox = driver.findElement(By.xpath(".//*[@id='homefeatured']/li["+argProductNumber+"]/div/div[1]/div/a[1]/img"));
		Actions msover = new Actions(driver);
		msover.moveToElement(priceBox).perform();
		String actualPrice = apri.getText();
		String actualproduct = apro.getText();
		Xls_Reader reader =  new Xls_Reader("C:\\Users\\ahmed aboukoura\\Desktop\\prices of homepage.xlsx");
        String expectedProduct = reader.getCellData("sheet1", 0 , y+2);
        String expectedPrice = reader.getCellData("sheet1", 1 , y+2);
         if (expectedProduct.contains(actualproduct) && actualPrice.contains(expectedPrice)) {
        return true;
         }else{
        return false ;
        }		
		}
	}




