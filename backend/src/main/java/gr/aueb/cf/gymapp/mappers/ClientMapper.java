package gr.aueb.cf.gymapp.mappers;

import gr.aueb.cf.gymapp.dto.client.ClientReadOnlyDTO;
import gr.aueb.cf.gymapp.model.Client;

public class ClientMapper {

    private ClientMapper() {}

    public static ClientReadOnlyDTO mapToDto(Client client) {
        ClientReadOnlyDTO dto = new ClientReadOnlyDTO();
        dto.setId(client.getId());
        dto.setUser(client.getUser());
        dto.setKg(client.getKg());
        dto.setAge(client.getAge());
        dto.setSessions(client.getSessions());
        return dto;
    }
}
