package be.odisee.citymesh.controller;

import be.odisee.citymesh.service.CitymeshAiService;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/ai")
@CrossOrigin(origins = "*")
public class AiController {

    private final CitymeshAiService aiService;

    public AiController(CitymeshAiService aiService) {
        this.aiService = aiService;
    }


    @PostMapping("/ask")
    public Map<String, String> ask(@RequestBody Map<String, String> payload) {
        String question = payload.get("question");
        String answer = aiService.askAboutDrones(question);
        return Map.of("answer", answer);
    }
}