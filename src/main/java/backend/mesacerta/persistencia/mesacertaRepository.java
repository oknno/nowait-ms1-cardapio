package backend.mesacerta.persistencia;

import java.util.List;
import java.util.UUID;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import backend.mesacerta.entidades.Cardapio;

/**
 * Esta classe estende o padrão CrudRepository 
 * @author jhcru
 *
 */
@EnableScan()
public interface mesacertaRepository extends CrudRepository<Cardapio, UUID> {
	
	List<Cardapio> findByNome(String nome);
	
}