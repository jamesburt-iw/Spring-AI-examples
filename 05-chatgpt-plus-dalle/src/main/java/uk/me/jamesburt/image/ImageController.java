package uk.me.jamesburt.image;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.image.ImageClient;
import org.springframework.ai.image.ImageOptions;
import org.springframework.ai.image.ImageOptionsBuilder;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class ImageController {

    private static final Logger logger = LoggerFactory.getLogger(ImageController.class);

    private final OpenAiChatClient chatClient;
    private final ImageClient imageClient;

    @Value("classpath:/prompts/imagegen.st")
    private Resource imagePrompt;

    @Autowired
    public ImageController(ImageClient imageClient, OpenAiChatClient chatClient) {
        this.imageClient = imageClient;
        this.chatClient = chatClient;
    }

    @GetMapping("safeimagegen")
    public String restrictedImageGeneration(@RequestParam(name = "animal") String animal,
                                            @RequestParam(name = "activity") String activity,
                                            @RequestParam(name = "mood") String mood) {

        PromptTemplate promptTemplate = new PromptTemplate(imagePrompt);
        Message message = promptTemplate.createMessage(Map.of("animal", animal, "activity", activity, "mood", mood));

        Prompt prompt = new Prompt(List.of(message));

        logger.info(prompt.toString());
        ChatResponse response = chatClient.call(prompt);
        String generatedImagePrompt = response.getResult().toString();
        logger.info("AI responded.");
        logger.info(generatedImagePrompt);

        ImageOptions imageOptions = ImageOptionsBuilder.builder().withModel("dall-e-3").build();

        ImagePrompt imagePrompt = new ImagePrompt(generatedImagePrompt, imageOptions);
        ImageResponse imageResponse = imageClient.call(imagePrompt);
        String imageUrl = imageResponse.getResult().getOutput().getUrl();
        return "redirect:"+imageUrl;

    }
}
