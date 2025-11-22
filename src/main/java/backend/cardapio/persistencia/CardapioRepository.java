package backend.cardapio.persistencia;

import java.util.List;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import backend.cardapio.entidades.Cardapio;

@EnableScan
public interface CardapioRepository extends CrudRepository<Cardapio, String> {

    List<Cardapio> findByNome(String nome);

}
