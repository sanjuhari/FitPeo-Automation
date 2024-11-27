package assignment;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class FitepoAssignment {

    public static void main(String[] args) {

        
        WebDriver driver = new ChromeDriver();

        try {
            // Create WebDriverWait instance
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Step 1: Navigate to FitPeo Homepage
            driver.get("https://www.fitpeo.com/");
            driver.manage().window().maximize();
            Thread.sleep(3000); // Allow time for page load

            // Step 2: Navigate to the Revenue Calculator Page
            WebElement revenueCalculatorLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'Revenue Calculator')]")));
            revenueCalculatorLink.click();
            Thread.sleep(3000); // Allow time for page load

            // Step 3: Scroll Down to the Slider Section
            WebElement sliderSection = driver.findElement(By.xpath("//span[@class='MuiSlider-root MuiSlider-colorPrimary MuiSlider-sizeMedium css-16i48op']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", sliderSection);
            Thread.sleep(3000); // Wait for the section to come into view

            // Step 4: Adjust the Slider to 820
            WebElement slider = driver.findElement(By.xpath("//input[@type='range']"));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].focus();", slider);
            js.executeScript("arguments[0].value = 820;", slider);
            js.executeScript("arguments[0].dispatchEvent(new Event('input'));", slider);

            // Verify that the value of the text field updates to 820
            WebElement textField = driver.findElement(By.xpath("//fieldset[@class='MuiOutlinedInput-notchedOutline css-igs3ac']"));
            String sliderValue = textField.getAttribute("value");
            if (sliderValue != null && sliderValue.equals("820")) {
                System.out.println("Slider successfully set to 820.");
            } else {
                System.out.println("Failed to set slider to 820. Current value: " + sliderValue);
            }

            // Adjust slider again to 560
            WebElement updatedSlider = driver.findElement(By.xpath("//input[@type='range']"));
            js.executeScript("arguments[0].value = 560;", updatedSlider);
            js.executeScript("arguments[0].dispatchEvent(new Event('input'));", updatedSlider);

            // Validate the Slider Value
            String updatedSliderValue = updatedSlider.getAttribute("value");
            if (updatedSliderValue.equals("560")) {
                System.out.println("Slider value updated to 560.");
            } else {
                System.out.println("Slider value is not updated. Current value: " + updatedSliderValue);
            }

            // Step 6: Select CPT Codes (checkboxes)
            List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@class='PrivateSwitchBase-input css-1m9pwf3' and @type='checkbox']"));
            
            	
            
            for (int i = 0; i < checkboxes.size(); i++) {
                if (i == 1 || i == 2 || i == 3 || i == 8) {
                    checkboxes.get(i).click();
                }
            }
            
            
           

            // Step 7: Validate Total Recurring Reimbursement
            WebElement totalReimbursement = driver.findElement(By.xpath("//p[contains(text(),'Total Recurring Reimbursement for all Patients Per')]"));
            String totalReimbursementValue = totalReimbursement.getText();
            if (totalReimbursementValue.contains("$75600")) {
                System.out.println("Total Recurring Reimbursement is correct: " + totalReimbursementValue);
            } else {
                System.out.println("Incorrect Total Recurring Reimbursement. Current value: " + totalReimbursementValue);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser
            driver.quit();
        }
    }
}

