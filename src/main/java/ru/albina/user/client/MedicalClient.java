package ru.albina.user.client;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.albina.backlib.configuration.WebConstants;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Component
public class MedicalClient {

    private final WebClient webClient;

    public MedicalClient(WebClient.Builder libWebClientBuilder) {
        this.webClient = libWebClientBuilder
                .baseUrl(Optional.ofNullable(System.getenv("MEDICAL_SERVICE_HOST")).orElse("http://localhost:8081"))
                .build();
    }


    public void createDoctor(UUID doctorId) {
        this.webClient.post()
                .uri(WebConstants.FULL_PRIVATE + "/doctors")
                .bodyValue(
                        Map.of(
                                "userId", doctorId
                        )
                )
                .retrieve()
                .toBodilessEntity()
                .block();
    }

    public void deleteDoctor(UUID doctorId) {
        this.webClient.delete()
                .uri(WebConstants.FULL_PRIVATE + "/doctors/{id}", uriBuilder -> uriBuilder.build(doctorId))
                .retrieve()
                .toBodilessEntity()
                .block();
    }
}
