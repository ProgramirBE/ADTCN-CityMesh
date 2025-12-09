package be.odisee.citymesh;

import be.odisee.citymesh.service.CitymeshAiService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.wiremock.spring.ConfigureWireMock;
import org.wiremock.spring.EnableWireMock;

import java.io.IOException;
import java.nio.charset.Charset;

@EnableWireMock(@ConfigureWireMock(baseUrlProperties = "openai.base.url"))
@SpringBootTest(properties = "spring.ai.openai.base-url=${openai.base.url}")
class CitymeshAiServiceTest {

    @Value("classpath:/canned/citymesh-response.json")
    Resource responseResource;

    @Autowired
    ChatClient.Builder chatClientBuilder;

    @BeforeEach
    void setup() throws IOException {
        String jsonBody = responseResource.getContentAsString(Charset.defaultCharset());
        WireMock.stubFor(WireMock.post("/v1/chat/completions")
                .willReturn(ResponseDefinitionBuilder.okForJson(new ObjectMapper().readTree(jsonBody))));
    }

    @Test
    void testAskAboutDrones() {
        CitymeshAiService service = new CitymeshAiService(chatClientBuilder);

        String reponse = service.askAboutDrones("Test vraag");

        // CORRECTIE: We checken nu op de Nederlandse tekst uit je JSON
        Assertions.assertThat(reponse).contains("Om de batterij");
    }
}