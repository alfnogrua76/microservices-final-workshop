package bancolombia.com.controller;

import bancolombia.com.model.Bank;
import bancolombia.com.service.BankService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/bank")
public class BankController {
    private final BankService service;

    public BankController(BankService service) {
        this.service = service;
    }

    @GetMapping()
    public Flux<Bank> getAll(){
        return service.findAll();
    }

    @GetMapping("/{bankId}")
    public Mono<Bank> getById(@PathVariable Long bankId) {
        return service.findById(bankId);
    }

    @PostMapping()
    public Mono<Bank> crate(@RequestBody Bank bank){
        return service.create(bank);
    }

    @PostMapping("/{bankId}")
    public Mono<Bank> update(@PathVariable Long bankId, @RequestBody Bank bank){

        return service.update(bankId, bank);
    }

}
