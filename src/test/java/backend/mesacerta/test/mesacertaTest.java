package backend.mesacerta.test;

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
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

import backend.mesacerta.entidades.Cardapio;
import backend.mesacerta.persistencia.mesacertaRepository;

/**
 * Classe de testes para a entidade Cardapio.
 *  <br>
 * Para rodar, antes sete a seguinte variável de ambiente: -Dspring.config.location=C:/Users/jhcru/sdm/
 *  <br>
 * Neste diretório, criar um arquivo application.properties contendo as seguitnes variáveis:
 * <br>
 * amazon.aws.accesskey=<br>
 * amazon.aws.secretkey=<br>
 * @author jhcru
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PropertyPlaceholderAutoConfiguration.class, mesacertaTest.DynamoDBConfig.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class mesacertaTest {

    private static Logger LOGGER = LoggerFactory.getLogger(mesacertaTest.class);
    private SimpleDateFormat df = new SimpleDateFormat("dd/mm/yyyy");
	    
    @Configuration
	@EnableDynamoDBRepositories(basePackageClasses = mesacertaRepository.class)
	public static class DynamoDBConfig {

		@Value("${amazon.aws.accesskey}")
		private String amazonAWSAccessKey;

		@Value("${amazon.aws.secretkey}")
		private String amazonAWSSecretKey;

		public AWSCredentialsProvider amazonAWSCredentialsProvider() {
			return new AWSStaticCredentialsProvider(amazonAWSCredentials());
		}

		@Bean
		public AWSCredentials amazonAWSCredentials() {
			return new BasicAWSCredentials(amazonAWSAccessKey, amazonAWSSecretKey);
		}

		@Bean
		public AmazonDynamoDB amazonDynamoDB() {
			return AmazonDynamoDBClientBuilder.standard().withCredentials(amazonAWSCredentialsProvider())
					.withRegion(Regions.US_EAST_1).build();
		}
	}
    
	@Autowired
	private mesacertaRepository repository;

	@Test
	public void teste1Criacao() throws ParseException {
		LOGGER.info("Criando objetos...");
		Cardapio c1 = new Cardapio("ETH_TESTE", "Ethereum", "Ethereum é um sistema de blockchain descentralizado e open-source, que possui sua própria criptomoeda, o Ether. O ETH funciona como uma plataforma para várias outras criptomoedas, bem como para a execução de smart contracts descentralizados.", 
				df.parse("30/06/2015"));
		repository.save(c1);

		Cardapio c2 = new Cardapio("ADA_TESTE", "Cardano", "Cardano é uma plataforma blockchain proof-of-stake que afirma ter o objetivo de permitir que “agentes de mudança, inovadores e visionários” tragam mudanças globais positivas.", 
				df.parse("02/10/2017"));
		repository.save(c2);

		Cardapio c3 = new Cardapio("LINK_TESTE", "Chainlink", "Chainlink (LINK) é uma rede oracle descentralizada com o objetivo de conectar smart contracts com dados do mundo inteiro. A Chainlink foi desenvolvida por Sergey Nazarov, com Steve Ellis como cofundador. Foi realizado o ICO em setembro de 2017, arrecadando U$32 milhões, com o fornecimento total de 1 bilhão de tokens LINK.", 
				df.parse("21/09/2017"));
		repository.save(c3);
		LOGGER.info("Pesquisado todos");
		Iterable<Cardapio> lista = repository.findAll();
		assertNotNull(lista.iterator());
		for (Cardapio cardapio : lista) {
			LOGGER.info(cardapio.toString());
		}
		LOGGER.info("Pesquisado um objeto");
		List<Cardapio> result = repository.findByNome("Cardano");
		assertEquals(result.size(), 1);
		LOGGER.info("Encontrado: {}", result.get(0).getCodigo());
	}
	
	
	@Test
	public void teste2Exclusao() throws ParseException {
		LOGGER.info("Excluindo objetos...");
		Iterable<Cardapio> lista = repository.findAll();
		assertNotNull(lista.iterator());
		for (Cardapio cardapio : lista) {
			repository.delete(cardapio);
			LOGGER.info("Apagando "+cardapio.toString());
		}
		List<Cardapio> result = repository.findByNome("Cardano");
		assertEquals(result.size(), 0);
		LOGGER.info("Exclusão feita com sucesso");
		
	}
	
	
}