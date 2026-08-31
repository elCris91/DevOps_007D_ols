package Anfri.Pedidos.WebClient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.Map;

@Component
public class ClienteClient {

    private final WebClient webClient;

    public ClienteClient(@Value("${cliente-service.url}") String urlServicio) {
        this.webClient = WebClient.builder().baseUrl(urlServicio).build();
    }

    public Map<String, Object> validarCliente(Integer id) {
        return this.webClient.get()
                .uri("/{id}", id)
                .retrieve()
                .onStatus(status -> status.is4xxClientError(),
                        response -> response.bodyToMono(String.class)
                                .map(body -> new RuntimeException("Cliente con ID " + id + " no existe")))
                .bodyToMono(Map.class)
                .block();
    }
}