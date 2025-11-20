package backend.mesa_certa.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import backend.mesa_certa.service.CardapioService;
import backend.mesacerta.entidades.Cardapio;

@RestController
@RequestMapping("/cardapios")
public class CardapioController {

    private final CardapioService service;

    public CardapioController(CardapioService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Cardapio>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Cardapio> buscarPorId(@PathVariable String codigo) {
        return service.buscarPorId(codigo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/buscar-por-nome")
    public ResponseEntity<List<Cardapio>> buscarPorNome(@RequestParam String nome) {
        return ResponseEntity.ok(service.buscarPorNome(nome));
    }

    @PostMapping
    public ResponseEntity<Cardapio> criar(@RequestBody Cardapio cardapio) {
        Cardapio salvo = service.salvar(cardapio);
        return ResponseEntity.ok(salvo);
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> deletar(@PathVariable String codigo) {
        service.deletar(codigo);
        return ResponseEntity.noContent().build();
    }
}
