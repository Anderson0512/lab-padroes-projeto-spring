package one.digitalinnovation.gof.service;

import one.digitalinnovation.gof.exception.BusinessException;
import one.digitalinnovation.gof.model.Cliente;
import one.digitalinnovation.gof.model.dto.ClientsDTO;
import one.digitalinnovation.gof.model.dto.ClientsResponseDTO;
import one.digitalinnovation.gof.model.dto.CreateClientDTO;

/**
 * Interface que define o padrão <b>Strategy</b> no domínio de cliente. Com
 * isso, se necessário, podemos ter multiplas implementações dessa mesma
 * interface.
 * 
 * @author falvojr
 */
public interface ClienteService {

	ClientsResponseDTO buscarTodos() throws BusinessException;

	Cliente buscarPorId(Long id) throws BusinessException;

	ClientsDTO insert(CreateClientDTO cliente) throws BusinessException;

	ClientsDTO update(Long id, Cliente cliente) throws BusinessException;

	void deletar(Long id) throws BusinessException;

}
