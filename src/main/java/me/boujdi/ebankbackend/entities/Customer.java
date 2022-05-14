package me.boujdi.ebankbackend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
@Entity
@Data@NoArgsConstructor@AllArgsConstructor
public class Customer {
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String name;
    private String email;
    //un client peut avoir un ou plusieur comptes
    @OneToMany(mappedBy ="customer")
    // mappedBy pour eviter de créer deux clé étrangére
    // danss le cas de l'association bidirectionelle
    private Collection<BankAccount> bankaccounts;
}
