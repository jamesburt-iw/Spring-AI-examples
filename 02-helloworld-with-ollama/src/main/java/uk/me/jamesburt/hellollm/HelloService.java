package uk.me.jamesburt.hellollm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.Generation;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.messages.UserMessage;

import java.util.List;

public class HelloService {

    private static final Logger logger = LoggerFactory.getLogger(HelloService.class);

    private final ChatClient aiClient;

    public HelloService(ChatClient aiClient) {
        this.aiClient = aiClient;
    }

    public Generation sendMessage(String message) {

        UserMessage userMessage = new UserMessage(message);
        logger.info("Asking AI model to reply to question.");
         Prompt prompt = new Prompt(List.of(userMessage));
        logger.info(prompt.toString());
        ChatResponse response = aiClient.call(prompt);
        logger.info("AI responded.");
        logger.info(response.getResult().toString());
        return response.getResult();
    }

}