package uk.me.jamesburt.image.config;

import org.springframework.ai.image.ImageClient;
import org.springframework.ai.openai.OpenAiImageClient;
import org.springframework.ai.openai.api.OpenAiImageApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ImageConfiguration {

    @Bean
    public ImageClient imageClient(@Value("${spring.ai.openai.api-key}") String apiKey) {
        return new OpenAiImageClient(new OpenAiImageApi(apiKey));
    }

}