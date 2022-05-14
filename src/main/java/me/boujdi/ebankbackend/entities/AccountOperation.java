package me.boujdi.ebankbackend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.boujdi.ebankbackend.enums.OperationType;
import org.springframework.data.jpa.repository.Temporal;

import javax.persistence.*;
import java.util.Date;
@Entity
@Data@NoArgsConstructor@AllArgsConstructor
public class AccountOperation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private double amount;
    @ManyToOne
    private  BankAccount bankAccount;
    @Enumerated(value = EnumType.STRING)
    private OperationType operationType;
}
