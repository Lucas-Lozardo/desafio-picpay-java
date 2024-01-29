package lozardo.picpaydesafio.domain.transactions;

import jakarta.persistence.*;
import lombok.*;
import lozardo.picpaydesafio.domain.users.User;
import lozardo.picpaydesafio.dtos.TransactionDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "transactions")
@Table(name = "transactions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "PAYER_ID")
    private User payer;

    @ManyToOne
    @JoinColumn(name = "PAYEE_ID")
    private User payee;

    private LocalDateTime transactionTime;
}
