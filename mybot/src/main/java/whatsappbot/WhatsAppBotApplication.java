package whatsappbot;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Slf4j
public class WhatsAppBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhatsAppBotApplication.class, args);
    }

    @Bean
    public WebDriver webDriver() {
        log.info("Instanciando o Seleniun webdriver");
        System.setProperty("webdriver.chrome.driver","SeuDiretorio");
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(false);
        WebDriver webDriver = new ChromeDriver(options);
        webDriver.get("https://web.whatsapp.com/");
        return webDriver;
    }
}
