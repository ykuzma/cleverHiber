package by.kuzma.clever.hiber.mapper;

import by.kuzma.clever.hiber.dto.ClientDto;
import by.kuzma.clever.hiber.entity.Client;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper(uses = CarMapper.class, componentModel = "spring")
public interface ClientMapper {
    Client toEntity(ClientDto clientDto);
    ClientDto toDto(Client client);

    List<Client> toEntities(List<ClientDto> clientDtos);
    List<ClientDto> toDtos(List<Client> clients);
}
