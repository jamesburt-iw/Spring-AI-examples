package uk.me.jamesburt.hellollm.config;

import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uk.me.jamesburt.hellollm.HelloService;

@Configuration
public class HelloConfiguration {

    @Bean
    public HelloService helloService(OpenAiChatClient aiClient) {
        return new HelloService(aiClient);
    }

}
