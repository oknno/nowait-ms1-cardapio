package backend.cardapio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import backend.cardapio.entidades.Cardapio;
import backend.cardapio.persistencia.CardapioRepository;

@Service
public class CardapioService {

    private final CardapioRepository repository;

    public CardapioService(CardapioRepository repository) {
        this.repository = repository;
    }

    public List<Cardapio> listarTodos() {
        return (List<Cardapio>) repository.findAll();
    }

    public Optional<Cardapio> buscarPorId(String codigo) {
        return repository.findById(codigo);
    }

    public List<Cardapio> buscarPorNome(String nome) {
        return repository.findByNome(nome);
    }

    public Cardapio salvar(Cardapio cardapio) {
        return repository.save(cardapio);
    }

    public void deletar(String codigo) {
        repository.deleteById(codigo);
    }
}
