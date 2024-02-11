package uk.me.jamesburt.hellollm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private final HelloService ragService;

    @Autowired
    public HelloController(HelloService ragService) {
        this.ragService = ragService;
    }

    @GetMapping("/hello")
    public String generate(@RequestParam(value = "message") String message) {
        return ragService.sendMessage(message).toString();
    }
}
