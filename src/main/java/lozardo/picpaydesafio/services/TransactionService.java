package lozardo.picpaydesafio.services;

import lozardo.picpaydesafio.domain.transactions.Transaction;
import lozardo.picpaydesafio.domain.users.User;
import lozardo.picpaydesafio.dtos.TransactionDTO;
import lozardo.picpaydesafio.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class TransactionService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UserService userService;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private NotificationService notificationService;


    public Transaction createTransaction(TransactionDTO transactionDTO) throws Exception {

        User sender = userService.findUserById(transactionDTO.senderId());
        User receiver = userService.findUserById(transactionDTO.receiverId());

        userService.validateTransaction(sender, transactionDTO.value());
        userService.findUserById(receiver.getId());

        boolean isAuthorize = authorizeTransaction(sender, transactionDTO.value());
        if(!isAuthorize){
            throw new Exception("Transação não autorizada!");
        }

        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(transactionDTO.value());
        newTransaction.setPayer(sender);
        newTransaction.setPayee(receiver);
        newTransaction.setTransactionTime(LocalDateTime.now());

        sender.setBalance(sender.getBalance().subtract(transactionDTO.value()));
        receiver.setBalance(receiver.getBalance().add(transactionDTO.value()));

        transactionRepository.save(newTransaction);
        userService.saveUser(sender);
        userService.saveUser(receiver);
        notificationService.sendNotification(sender, "Transação realizada com sucesso!");
        notificationService.sendNotification(receiver, "Transação recebida com sucesso!");

        return newTransaction;

    }

    public boolean authorizeTransaction(User sender, BigDecimal value){
        ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity("https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc", Map.class);

        if(authorizationResponse.getStatusCode() == HttpStatus.OK){
            String message = (String) authorizationResponse.getBody().get("message");
            return "Autorizado".equalsIgnoreCase(message);
        }else return false;
    }
}
