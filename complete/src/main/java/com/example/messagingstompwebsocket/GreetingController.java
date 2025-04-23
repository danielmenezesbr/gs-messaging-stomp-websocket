package com.example.messagingstompwebsocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.config.WebSocketMessageBrokerStats;
import org.springframework.web.util.HtmlUtils;

import javax.annotation.PostConstruct;

@Controller
public class GreetingController {

	@Autowired
	private WebSocketMessageBrokerStats brokerStats;

	@Value("${spring.application.version}")
	private String appVersion;

	@PostConstruct
	public void init() {
		System.out.println("brokerStats:");
		System.out.println(brokerStats);
	}


	@MessageMapping("/hello")
	@SendTo("/topic/greetings")
	public Greeting greeting(HelloMessage message) throws Exception {
		Thread.sleep(1000); // simulated delay
		System.out.println("Received message: " + message.getName());
		return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) +
				"! (hostname: %s, version: %s)".formatted(System.getenv("HOSTNAME"), appVersion));
	}

}
