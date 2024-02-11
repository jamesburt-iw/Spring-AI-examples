package uk.me.jamesburt.rag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RagController {

    private final RagService ragService;

    @Autowired
    public RagController(RagService ragService) {
        this.ragService = ragService;
    }

    @GetMapping("/rag")
    public String generate(@RequestParam(value = "message" String message) {
        return ragService.retrieve(message).toString();
    }
}
