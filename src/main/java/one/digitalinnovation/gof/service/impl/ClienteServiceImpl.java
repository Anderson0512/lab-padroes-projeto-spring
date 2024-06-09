package one.digitalinnovation.gof.service.impl;

import java.util.Optional;

import one.digitalinnovation.gof.exception.BusinessException;
import one.digitalinnovation.gof.exception.CepException;
import one.digitalinnovation.gof.model.dto.ClientsDTO;
import one.digitalinnovation.gof.model.dto.ClientsResponseDTO;
import one.digitalinnovation.gof.model.dto.CreateClientDTO;
import one.digitalinnovation.gof.model.mapper.GetAllClientesResponseMapper;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import one.digitalinnovation.gof.model.Cliente;
import one.digitalinnovation.gof.model.ClienteRepository;
import one.digitalinnovation.gof.model.Endereco;
import one.digitalinnovation.gof.model.EnderecoRepository;
import one.digitalinnovation.gof.service.ClienteService;
import org.springframework.web.client.RestTemplate;

/**
 * Implementação da <b>Strategy</b> {@link ClienteService}, a qual pode ser
 * injetada pelo Spring (via {@link Autowired}). Com isso, como essa classe é um
 * {@link Service}, ela será tratada como um <b>Singleton</b>.
 */
@Service
public class ClienteServiceImpl implements ClienteService {

  // Singleton: Injetar os componentes do Spring com @Autowired.
  private final ClienteRepository clienteRepository;
  private final EnderecoRepository enderecoRepository;
  private final RestTemplate restTemplate;
  private final String url;

  @Autowired
  public ClienteServiceImpl(@Value("${url.viaCep}") String url, ClienteRepository clienteRepository, EnderecoRepository enderecoRepository, RestTemplate restTemplate) {
    this.url = url;
    this.clienteRepository = clienteRepository;
    this.enderecoRepository = enderecoRepository;
    this.restTemplate = restTemplate;
  }

  @Override
  public ClientsResponseDTO buscarTodos() throws BusinessException {
    // Buscar todos os Clientes e faz o filtro para retornar apenas dados necessários p apresentação em lista.

    try {
      Iterable<Cliente> clientes = clienteRepository.findAll();
      if (IterableUtils.isEmpty(clientes))
        throw new BusinessException("Sem dados na base");
      return new GetAllClientesResponseMapper(clientes).getResponse();
    } catch (Exception e) {
      throw new BusinessException(e.getMessage(), e);
    }
  }

  @Override
  public Cliente buscarPorId(Long id) throws BusinessException {
    // Buscar Cliente por ID.

    try {
      Optional<Cliente> cliente = Optional.ofNullable(clienteRepository.findById(id).orElseThrow(() ->
              new RuntimeException("Id não Encontrado")));
      Cliente client = new Cliente();
      if (cliente.isPresent()) {
        client = cliente.get();
      }
      return client;
    } catch (RuntimeException e) {
      throw new BusinessException(e.getMessage(), e.getLocalizedMessage(), "", e);
    }
  }

  @Override
  public ClientsDTO insert(CreateClientDTO client) throws BusinessException {
    try {

      Cliente cliente = new Cliente();
      cliente.setNome(client.getName());
      cliente.setProfissao(client.getProfession());
      cliente.setIdade(client.getAge());
      Endereco endereco = new Endereco();
      endereco.setCep(client.getZipCode());
      cliente.setEndereco(endereco);

      salvarClienteComCep(cliente);

      ClientsDTO result = new ClientsDTO();
      result.setName(client.getName());
      return result;
    } catch (CepException e) {
      throw new BusinessException(e.getErroCode(), e.getMessage(), e.getDetails(), e);
    }
  }

  @Override
  public ClientsDTO update(Long id, CreateClientDTO client) throws BusinessException {
    // Buscar Cliente por ID, caso exista:
    ClientsDTO clientsResponse = new ClientsDTO();

    clienteRepository.findById(id)
            .map(clientBase -> {
              clientBase.setId(id);
              clientBase.setNome(client.getName());
              clientBase.setProfissao(client.getProfession());
              clientBase.setIdade(client.getAge());
              clientBase.getEndereco().setCep(client.getZipCode());
              try {
                salvarClienteComCep(clientBase);
                clientsResponse.setId(String.valueOf(clientBase.getId()));
                clientsResponse.setName(clientBase.getNome());
              } catch (CepException e) {
                try {
                  throw new BusinessException(e.getErroCode(), e.getMessage(), e.getDetails(),e);
                } catch (BusinessException ex) {
                  throw new RuntimeException(ex);
                }
              }
              return clientBase;
            })
            .orElseThrow(() -> new BusinessException("Id inválido/Cliente não encontrado"));

    return clientsResponse;
  }

  @Override
  public void deletar(Long id) throws BusinessException {
    // Deletar Cliente por ID.
    try {
      clienteRepository.deleteById(id);
    } catch (Exception e) {
      throw new BusinessException("Ocorreu um erro ao deletar", e);
    }
  }

  private void salvarClienteComCep(Cliente cliente) throws CepException {
    // Verificar se o Endereco do Cliente já existe (pelo CEP).
    String cep = cliente.getEndereco().getCep();
    if (8 != cep.length()) {
      throw new CepException("400", "CEP inválido", "CEP");
    }
    Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
      String urlViaCep = url.concat(cep).concat("/json/");
      Endereco newAddress = restTemplate.getForEntity(urlViaCep, Endereco.class).getBody();
      assert newAddress != null;
      enderecoRepository.save(newAddress);
      return newAddress;
    });
    cliente.setEndereco(endereco);
    // Inserir Cliente, vinculando o Endereco (novo ou existente).
    clienteRepository.save(cliente);
  }
}
