package backend.cardapio.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
@SpringBootTest   // Usa o Application + DynamoDBConfig da app principal
@TestPropertySource(locations = "file:C:/Users/okn/Documents/noWait/Credenciais/application.properties")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CardapioTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(CardapioTest.class);
    private final SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

    @Autowired
    private CardapioRepository repository;

    // ================================
    // TESTE 1 – CRIAÇÃO
    // ================================
    @Test
    public void teste1Criacao() throws ParseException {
        LOGGER.info("Criando objetos...");

        Cardapio c1 = new Cardapio("SANDUBA", "Sanduiche",
                "Sanduiche de carne com tomate, alface, pao e queijo.",
                df.parse("30/06/2015"));
        repository.save(c1);

        Cardapio c2 = new Cardapio("MASSA", "Pizza",
                "Massa fina com calabresa e molho de tomate com borda recheada de catupiry.",
                df.parse("02/10/2017"));
        repository.save(c2);

        Cardapio c3 = new Cardapio("REFRI", "Kuat",
                "Refrigerante gelado sabor guaraná.",
                df.parse("21/09/2017"));
        repository.save(c3);

        LOGGER.info("Pesquisando todos...");
        Iterable<Cardapio> lista = repository.findAll();
        assertNotNull(lista.iterator());

        for (Cardapio cardapio : lista) {
            LOGGER.info(cardapio.toString());
        }

        LOGGER.info("Pesquisando objeto 'Pizza'");
        List<Cardapio> result = repository.findByNome("Pizza");
        assertEquals(1, result.size());
        LOGGER.info("Encontrado: {}", result.get(0).getCodigo());
    }

    // ================================
    // TESTE 2 – EXCLUSÃO
    // ================================
    @Test
    public void teste2Exclusao() throws ParseException {
        LOGGER.info("Excluindo objetos...");

        Iterable<Cardapio> lista = repository.findAll();
        assertNotNull(lista.iterator());

        for (Cardapio cardapio : lista) {
            repository.delete(cardapio);
            LOGGER.info("Apagando {}", cardapio);
        }

        List<Cardapio> result = repository.findByNome("Pizza");
        assertEquals(0, result.size());

        LOGGER.info("Exclusão feita com sucesso");
    }
}
