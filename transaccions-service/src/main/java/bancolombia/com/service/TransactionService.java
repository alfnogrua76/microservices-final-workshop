package bancolombia.com.service;

import bancolombia.com.dto.AccountDto;
import bancolombia.com.grpc.TransactionProvider;
import bancolombia.com.grpc.TransactionRequest;
import bancolombia.com.model.Transaction;
import bancolombia.com.repository.ITransactionRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TransactionService {
    private  final ITransactionRepository repository;
    private final AccountService accountService;
    private final TransactionProvider transactionProvider;


    public TransactionService(ITransactionRepository repository, AccountService accountService, TransactionProvider transactionProvider) {
        this.repository = repository;
        this.accountService = accountService;
        this.transactionProvider = transactionProvider;
    }

    public Mono<Transaction> findById(Long id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Product not Found")));
    }

    public Flux<Transaction> findAll() {
        return repository.findAll()
                .switchIfEmpty(Mono.error(new RuntimeException("Products not Found")));
    }

    public Mono<Transaction> createDeposito(Transaction transaction) {
        return accountService.getAccount(transaction.getAccountP())
                .switchIfEmpty(Mono.error(new RuntimeException("Cuenta pagador no encontrado")))
                .flatMap(cuentaPagador->{
                    return accountService.getAccount(transaction.getAccountB())
                            .switchIfEmpty(Mono.error(new RuntimeException("Cuenta del beneficiario no encontrado")))
                            .flatMap(cuentaBeneficiario -> {

                                if (cuentaPagador.getBanco() != cuentaBeneficiario.getBanco()) {
                                   // crearDepositoInter(transaction);
                                    // Si el saldo es insuficiente, se detiene el flujo y se emite un error.
                                    return Mono.error(new RuntimeException("Las cuentas no pertenecen al mismo Banco"));
                                }
                                    AccountDto dep = new AccountDto();
                                    dep.setId(cuentaBeneficiario.getId());
                                    dep.setBanco(cuentaBeneficiario.getBanco());
                                    dep.setNumber(cuentaBeneficiario.getNumber());
                                    dep.setSaldo(cuentaBeneficiario.getSaldo() + transaction.getMonto());
                                    dep.setTitular(cuentaBeneficiario.getTitular());
                                    return accountService.updateAccount(dep);

                            })
                            .flatMap(trasaccion -> {
                                Transaction deposito = new Transaction();
                                deposito.setTid(transaction.getTid());
                                deposito.setType(transaction.getType()+ " DEPOSITO");
                                deposito.setPagador(transaction.getPagador());
                                deposito.setBeneficiary(transaction.getBeneficiary());
                                deposito.setAccountP(transaction.getAccountP());
                                deposito.setAccountB(transaction.getAccountB());
                                deposito.setMonto(transaction.getMonto());
                                deposito.setData(transaction.getData());
                                // otros campos...
                                return repository.save(deposito);
                            });
                });
    }

    public Mono<Transaction> createRetiro(Transaction transaction) {
        return accountService.getAccount(transaction.getAccountP())
                .switchIfEmpty(Mono.error(new RuntimeException("Cuenta no encontrado")))
                .flatMap(cuenta->{
                    if (cuenta.getSaldo() < transaction.getMonto()) {
                        // Si el saldo es insuficiente, se detiene el flujo y se emite un error.
                        return Mono.error(new RuntimeException("Saldo insuficiente en la cuenta"));
                    }
                    AccountDto dep = new AccountDto();
                    dep.setId(cuenta.getId());
                    dep.setBanco(cuenta.getBanco());
                    dep.setNumber(cuenta.getNumber());
                    dep.setSaldo(cuenta.getSaldo() - transaction.getMonto());
                    dep.setTitular(cuenta.getTitular());
                    return accountService.updateAccount(dep);
                })
                .flatMap(trasaccion -> {
                    Transaction deposito = new Transaction();
                    deposito.setTid(transaction.getTid());
                    deposito.setType(transaction.getType() + " RETIRO");
                    deposito.setPagador(transaction.getPagador());
                    deposito.setBeneficiary(transaction.getBeneficiary());
                    deposito.setAccountP(transaction.getAccountP());
                    deposito.setAccountB(transaction.getAccountB());
                    deposito.setMonto(transaction.getMonto());
                     deposito.setData(transaction.getData());
                    // otros campos...
                    return repository.save(deposito);
                });
    }
/*
    public Mono<Transaction> crearDepositoInter(TransactionRequest transaction){
        return transactionProvider.crearDepositoOtrosBanco(transaction,re);
    }
*/
    public Mono<Transaction> crearTransferenciaMismoBanco(Transaction transaction) {
                 return Mono.when(
                         createRetiro(transaction),
                                 createDeposito(transaction)
                         )
                         //.onErrorResume(error -> Mono.error(new RuntimeException("Transaccion erronea")))
                         .then(Mono.just(transaction));

    }

}