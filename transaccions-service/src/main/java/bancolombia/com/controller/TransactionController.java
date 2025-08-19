package bancolombia.com.controller;

import bancolombia.com.model.Transaction;
import bancolombia.com.service.TransactionService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    private final TransactionService service;

    public TransactionController(TransactionService transactionService) {
        this.service = transactionService;
    }

    @GetMapping()
    public Flux<Transaction> getAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Transaction> getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping("/deposito")
    public Mono<Transaction> createDeposito(@RequestBody Transaction transaction){
        return service.createDeposito(transaction);
    }

    @PostMapping("/retiro")
    public Mono<Transaction> createRetiro(@RequestBody Transaction transaction){
        return service.createRetiro(transaction);
    }

    @PostMapping("/transfer")
    public Mono<Transaction> createTransfer(@RequestBody Transaction transaction){
        return service.crearTransferenciaMismoBanco(transaction);
    }


}
