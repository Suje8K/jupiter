package in.sujeetk.jupiter.service.impl;

import in.sujeetk.jupiter.repository.auth.RegClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegisteredClientServiceImpl implements RegisteredClientRepository {

    final RegClientRepository regClientRepository;

    public RegisteredClientServiceImpl(RegClientRepository regClientRepository) {
        this.regClientRepository = regClientRepository;
    }

    @Override
    public void save(RegisteredClient oauth2RegisteredClient) {
        regClientRepository.save(oauth2RegisteredClient);
    }

    @Override
    public RegisteredClient findById(String id) {
        return null;
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        Optional<RegisteredClient> registeredClient = regClientRepository.findAll().filter(data ->
            data.getClientId().equals(clientId)
        ).next().blockOptional();
        return registeredClient.orElse(null);
    }
}
