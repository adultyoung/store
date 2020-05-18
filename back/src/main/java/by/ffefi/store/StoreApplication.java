package by.ffefi.store;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import viber4j.ViberBotManager;

@SpringBootApplication
public class StoreApplication implements ApplicationListener<ApplicationReadyEvent> {

	private static final Logger logger = LoggerFactory.getLogger(StoreApplication.class);
	@Value("${viber.token}")
	private String botToken;
	@Value("${viber.web-hook}")
	private String webHookUrl;
	@Value("${viber.media-source-url}")
	private String mediaSourceUrl;


	public static void main(String[] args) {
		SpringApplication.run(StoreApplication.class, args);
	}

	@Override
	public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
		logger.info("Web-hook registration for {}", webHookUrl);
		if (!ViberBotManager.viberBot(botToken).setWebHook(webHookUrl))
			logger.error("Web-hook registration failed!");
	}


}
