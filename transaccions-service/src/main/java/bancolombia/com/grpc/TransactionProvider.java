package bancolombia.com.grpc;

import bancolombia.com.repository.ITransactionRepository;
import bancolombia.com.service.TransactionService;
import io.grpc.stub.StreamObserver;
import org.springframework.grpc.server.service.GrpcService;
import reactor.core.publisher.Mono;

@GrpcService
public class TransactionProvider extends TransactionServiceGrpc.TransactionServiceImplBase{

    private final ITransactionRepository repository;
    private final TransactionService service;


    public TransactionProvider(ITransactionRepository repository, TransactionService service) {
        this.repository = repository;
        this.service = service;
    }

    @Override
    public void crearDepositoOtrosBanco(TransactionRequest request, StreamObserver<TransactionResponse> responseObserver) {
     /*
        repository.save(request)
                .switchIfEmpty(Mono.error(new RuntimeException("Transaction not Found")))
                .subscribe(transaction -> {
                    TransactionResponse response = TransactionResponse
                            .newBuilder()
                            .setId()


                })


      */

    }

    /*
    public void crearDepositoOtrosBanco(TransactionRequest request, StreamObserver<TransactionResponse> responseObserver) {
        repository.findById(request.)
                .switchIfEmpty(Mono.error(new RuntimeException("Transaction not found")))
                .subscribe(product -> {
                    ProductResponse response = ProductResponse
                            .newBuilder()
                            .setDescription(product.getDescription())
                            .setId(product.getId())
                            .setName(product.getName())
                            .setPrice(product.getPrice())
                            .setStock(product.getStock())
                            .build();

                    responseObserver.onNext(response);
                    responseObserver.onCompleted();
                }, responseObserver::onError).dispose();
    }

 */
}
