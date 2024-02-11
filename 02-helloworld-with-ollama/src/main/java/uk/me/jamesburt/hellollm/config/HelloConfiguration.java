package uk.me.jamesburt.hellollm.config;

import org.springframework.ai.ollama.OllamaChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uk.me.jamesburt.hellollm.HelloService;

@Configuration
public class HelloConfiguration {

    @Bean
    public HelloService helloService(OllamaChatClient aiClient) {
        return new HelloService(aiClient);
    }

}
