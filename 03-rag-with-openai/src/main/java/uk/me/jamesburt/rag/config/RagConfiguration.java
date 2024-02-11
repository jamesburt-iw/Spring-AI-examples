package uk.me.jamesburt.rag.config;

import org.springframework.ai.embedding.EmbeddingClient;
import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uk.me.jamesburt.rag.RagService;

@Configuration
public class RagConfiguration {

    @Bean
    public RagService ragService(OpenAiChatClient aiClient, EmbeddingClient embeddingClient) {
        return new RagService(aiClient, embeddingClient);
    }

}
