package one.digitalinnovation.gof.model.mapper;

import one.digitalinnovation.gof.model.Cliente;
import one.digitalinnovation.gof.model.dto.ClientsDTO;
import one.digitalinnovation.gof.model.dto.ClientsResponseDTO;
import org.apache.commons.collections4.IterableUtils;

import java.util.ArrayList;
import java.util.List;

public class GetAllClientesResponseMapper {

  private final ClientsResponseDTO response = new ClientsResponseDTO();
  public GetAllClientesResponseMapper(Iterable<Cliente> clientes) {

    List<ClientsDTO> clientsDTOList = new ArrayList<>(IterableUtils.size(clientes));
    for (Cliente item : clientes) {
      ClientsDTO clientsDTO = new ClientsDTO();
      clientsDTO.setId(String.valueOf(item.getId()));
      clientsDTO.setName(item.getNome());
      clientsDTOList.add(clientsDTO);
    }

    response.setContent(clientsDTOList);
  }

  public ClientsResponseDTO getResponse() {
    return response;
  }
}
