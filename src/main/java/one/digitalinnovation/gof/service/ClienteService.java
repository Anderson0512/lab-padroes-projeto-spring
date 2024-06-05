package one.digitalinnovation.gof.service;

import one.digitalinnovation.gof.exception.BusinessException;
import one.digitalinnovation.gof.model.Cliente;
import one.digitalinnovation.gof.model.dto.ClientsResponseDTO;

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

	void inserir(Cliente cliente);

	void atualizar(Long id, Cliente cliente);

	void deletar(Long id);

}
