package br.com.fiap.pagamentos.domain.service;

import br.com.fiap.pagamentos.api.model.CartaoDTO;
import br.com.fiap.pagamentos.domain.exception.ServiceUnavailableResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class CartaoService {


    private final RestTemplate restTemplate;

    @Autowired
    public CartaoService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public CartaoDTO validarCartao(String cpf) {
        String url = "http://hackaton-cartao-app:8082/api/cartao/consultar/"+cpf;
        try {
            ResponseEntity<CartaoDTO> response = restTemplate.getForEntity(url, CartaoDTO.class);
            return response.getBody();
        } catch (ServiceUnavailableResponse ex) {
            throw new ServiceUnavailableResponse("Erro ao consultar cartão nossos sistemas estão temporariamente indisponíveis no momento. Por favor, tente novamente mais tarde.");
        }
    }

    public CartaoDTO update(CartaoDTO cartao) {
        String url = "http://hackaton-cartao-app:8082/api/cartao/update";
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<CartaoDTO> entity = new HttpEntity<>(cartao, headers);
            ResponseEntity<CartaoDTO> response = restTemplate.exchange(url, HttpMethod.PUT, entity, CartaoDTO.class);
            return response.getBody();
        } catch (ServiceUnavailableResponse ex) {
            throw new ServiceUnavailableResponse("Erro ao salvar cartão nossos sistemas estão temporariamente indisponíveis no momento. Por favor, tente novamente mais tarde.");
        }
    }

}
