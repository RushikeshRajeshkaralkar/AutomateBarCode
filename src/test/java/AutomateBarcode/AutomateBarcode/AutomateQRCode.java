package AutomateBarcode.AutomateBarcode;

import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

/**
 * Hello world!
 *
 */
public class AutomateQRCode {

	public static WebDriver driver;

	public static void main(String[] args) throws Exception {

		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Rushikesh\\Desktop\\ChromeDriver79\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.qoo10.sg/gmkt.inc/Login/Login.aspx");
		String barCodeUrl = driver.findElement(By.xpath("//div[@id='captcha_src']//div//div//img")).getAttribute("src");
		System.out.println(barCodeUrl);

		URL url = new URL(barCodeUrl);

		BufferedImage bufferedImage = ImageIO.read(url);

		LuminanceSource luminanceSource = new BufferedImageLuminanceSource(bufferedImage);
		BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(luminanceSource));
		Result result = new MultiFormatReader().decode(binaryBitmap);

		System.out.println("Barcode Is : " + result.getText());

		driver.close();

	}

}
