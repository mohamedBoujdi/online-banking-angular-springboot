package me.boujdi.ebankbackend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.boujdi.ebankbackend.enums.AccountStatus;
import org.hibernate.annotations.DiscriminatorOptions;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
@Entity(name = "Accounts")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Account_type",length = 2)
@Data@NoArgsConstructor@AllArgsConstructor
public abstract class BankAccount {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY) attention à ne pas mettre
    private String id;
    private Date createdAt;
    private double balance;
    @Enumerated(value = EnumType.STRING)
    private AccountStatus accStatus;
    private String currency;
    @ManyToOne
    private Customer customer;
    @OneToMany(mappedBy = "bankAccount")
    private  Collection<AccountOperation> operations;
    
}
