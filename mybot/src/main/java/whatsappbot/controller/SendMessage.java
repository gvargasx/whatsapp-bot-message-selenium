package whatsappbot.controller;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import whatsappbot.model.Message;

@RestController
@RequestMapping(value = "/zap-zap")
@Slf4j
public class SendMessage {

    @Autowired
    private WebDriver webDriver;

    @PostMapping
    public void receberMensagem(@RequestBody Message message) {
        for (String contato : message.getContatos()) {
            try {
                WebElement elementoContato = findContato(contato);
                elementoContato.click();

                WebElement caixaMensagem = findCaixaTexto();
                caixaMensagem.sendKeys(message.getConteudo());
                caixaMensagem.sendKeys(Keys.RETURN);
            } catch (Exception e) {
                log.error("Não foi possível enviar a message para o contato {}", contato, e);
            }
        }
    }

    private WebElement findContato(String nomeContato) {
        String xPathContato = "//*[@id=\"pane-side\"]/*//span[@title='" + nomeContato + "']";
        return webDriver.findElement(By.xpath(xPathContato));
    }

    private WebElement findCaixaTexto() {
        String xPathCaixaTexto = "//*[@id=\"main\"]/footer//*[@role='textbox']";
        return webDriver.findElement(By.xpath(xPathCaixaTexto));
    }

}
