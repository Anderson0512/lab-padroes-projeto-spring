package one.digitalinnovation.gof.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

/**
 * Os atributos desse modelo foram gerados automaticamente pelo site
 * jsonschema2pojo.org. Para isso, usamos o JSON de retorno da API do ViaCEP.
 * 
 * @see <a href="https://www.jsonschema2pojo.org">jsonschema2pojo.org</a>
 * @see <a href="https://viacep.com.br">ViaCEP</a>
 * 
 * @author falvojr
 */
@Entity
@Getter
@Setter
public class Endereco {

	@Id
	@JsonProperty
	private String cep;
	@JsonProperty
	private String logradouro;
	@JsonProperty
	private String complemento;
	@JsonProperty
	private String bairro;
	@JsonProperty
	private String localidade;
	@JsonProperty
	private String uf;
	@JsonProperty
	private String ibge;
	@JsonProperty
	private String gia;
	@JsonProperty
	private String ddd;
	@JsonProperty
	private String siafi;
}
