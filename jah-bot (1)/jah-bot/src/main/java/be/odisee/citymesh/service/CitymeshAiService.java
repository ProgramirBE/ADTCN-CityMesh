package be.odisee.citymesh.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class CitymeshAiService {

    private final ChatClient chatClient;

    public CitymeshAiService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    public String askAboutDrones(String question) {
        return chatClient.prompt()

                .system("Je bent een expert-assistent voor het bedrijf Citymesh. " +
                        "Je helpt dronepiloten en technici. " +
                        "Antwoord altijd in het Nederlands, kort en professioneel. " +
                        // AJOUT CRUCIAL (GUARDRAIL) :
                        "BELANGRIJK: Beantwoord ALLEEN vragen over drones, techniek en Citymesh. " +
                        "Als de gebruiker een vraag stelt die hier niets mee te maken heeft (zoals recepten, sport of algemene kennis), " +
                        "weiger dan beleefd en zeg dat je enkel technische vragen over Citymesh beantwoordt.")
                // -------------------------------------

                .user(question)
                .call()
                .content();
    }
}