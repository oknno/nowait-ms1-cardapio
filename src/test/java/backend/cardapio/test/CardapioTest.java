package backend.cardapio.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import backend.cardapio.entidades.Cardapio;
import backend.cardapio.persistencia.CardapioRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "file:C:/Users/okn/Documents/noWait/Credenciais/application.properties")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CardapioTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(CardapioTest.class);

    @Autowired
    private CardapioRepository repository;

    // ================================
    // TESTE 1 – CRIAÇÃO
    // ================================
    @Test
    public void teste1Criacao() {
        LOGGER.info("Criando objetos...");

        Cardapio c1 = new Cardapio(
                "SANDUBA",
                "Sanduiche",
                "Sanduiche de carne com tomate, alface, pao e queijo.",
                25.90
        );
        repository.save(c1);

        Cardapio c2 = new Cardapio(
                "MASSA",
                "Pizza",
                "Massa fina com calabresa e molho de tomate com borda recheada de catupiry.",
                59.90
        );
        repository.save(c2);

        Cardapio c3 = new Cardapio(
                "REFRI",
                "Kuat",
                "Refrigerante gelado sabor guaraná.",
                6.50
        );
        repository.save(c3);

        LOGGER.info("Pesquisando todos os registros...");
        Iterable<Cardapio> lista = repository.findAll();
        assertNotNull(lista.iterator());

        for (Cardapio cardapio : lista) {
            LOGGER.info(cardapio.toString());
        }

        LOGGER.info("Pesquisando objeto 'Pizza'...");
        List<Cardapio> result = repository.findByNome("Pizza");
        assertEquals(1, result.size());
        LOGGER.info("Encontrado: {}", result.get(0).getCodigo());
    }

    // ================================
    // TESTE 2 – EXCLUSÃO
    // ================================
    @Test
    public void teste2Exclusao() {
        LOGGER.info("Excluindo objetos...");

        Iterable<Cardapio> lista = repository.findAll();
        assertNotNull(lista.iterator());

        for (Cardapio cardapio : lista) {
            LOGGER.info("Apagando {}", cardapio);
            repository.delete(cardapio);
        }

        List<Cardapio> result = repository.findByNome("Pizza");
        assertEquals(0, result.size());

        LOGGER.info("Exclusão finalizada com sucesso.");
    }
}
