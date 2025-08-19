package bancolombia.com.controllers;

import bancolombia.com.dto.AccountDto;
import bancolombia.com.models.Account;
import bancolombia.com.services.AccountService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/account")
public class AccountController {
    private final AccountService service;

    public AccountController(AccountService service) {
        this.service = service;
    }

    @GetMapping()
    public Flux<Account> getAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Account> getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping("/number/{number}")
    public Mono<Account> getByNumber(@PathVariable Long number) {
        return service.findBynumber(number);
    }

    @PostMapping()
    public Mono<Account> crate(@RequestBody Account account){
        return service.create(account);
    }

    @PostMapping("/{id}")
    public Mono<Account> update(@PathVariable Long id, @RequestBody AccountDto account){
        return service.update(id, account);
    }


}
