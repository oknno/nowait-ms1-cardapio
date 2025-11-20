package backend.mesacerta.persistencia;

import java.util.List;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import backend.mesacerta.entidades.Cardapio;

@EnableScan
public interface CardapioRepository extends CrudRepository<Cardapio, String> {

    List<Cardapio> findByNome(String nome);

}
