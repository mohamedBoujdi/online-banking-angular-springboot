package me.boujdi.ebankbackend;

import com.github.javafaker.Currency;
import com.github.javafaker.Faker;
import me.boujdi.ebankbackend.entities.*;
import me.boujdi.ebankbackend.enums.AccountStatus;
import me.boujdi.ebankbackend.enums.OperationType;
import me.boujdi.ebankbackend.repositories.AccountOperationRepository;
import me.boujdi.ebankbackend.repositories.BankAccountRepository;
import me.boujdi.ebankbackend.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class EbankBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(EbankBackendApplication.class, args);
    }

   @Bean
    CommandLineRunner start(CustomerRepository customerRepository,
                            BankAccountRepository bankAccountRepository,
                            AccountOperationRepository operationRepository){

        return args ->{
            Faker  faker=new Faker();
            Stream.of("Hassan","tarik","Naaima").forEach(name->{
                Customer customer=new Customer();
                customer.setId(UUID.randomUUID().toString());
                customer.setName(name);
                customer.setEmail(name+"@gmail.com");
                customerRepository.save(customer);
                    });
            customerRepository.findAll().forEach(customer -> {

                List<AccountStatus> list=Arrays.stream(AccountStatus.values()).collect(Collectors.toList());
                Random random=new Random();

                CurrentAccount currentAccount=new CurrentAccount();
                        currentAccount.setId(UUID.randomUUID().toString());
                        currentAccount.setBalance(Double.parseDouble(new DecimalFormat("#.##").format(faker.random().nextDouble()*10000)));
                        currentAccount.setAccStatus(list.get(random.nextInt(list.size())));
                        currentAccount.setCurrency(faker.currency().name());
                        currentAccount.setCreatedAt(new Date());
                        currentAccount.setCustomer(customer);
                        currentAccount.setOverDraft(9900);
                        bankAccountRepository.save(currentAccount);
                SavingAccount savingAccount=new SavingAccount();
                        savingAccount.setId(UUID.randomUUID().toString());
                        savingAccount.setBalance(Double.parseDouble(new DecimalFormat("#.###").format(faker.random().nextDouble()*10000)));
                        savingAccount.setCustomer(customer);
                        savingAccount.setCurrency(faker.currency().name());
                        savingAccount.setCreatedAt(new Date());
                        savingAccount.setAccStatus(list.get(random.nextInt(list.size())));
                        savingAccount.setInterestRate(0.3);
                        bankAccountRepository.save(savingAccount);



            });
            List<OperationType> list2=Arrays.stream(OperationType.values()).collect(Collectors.toList());
            Random random=new Random();
            bankAccountRepository.findAll().forEach(bankAccount -> {
                        for(int i=0;i<10;i++){
                          AccountOperation operation=new AccountOperation();
                          operation.setOperationType(list2.get(random.nextInt(list2.size())));
                          operation.setAmount(faker.random().nextDouble()*10000);
                          operation.setBankAccount(bankAccount);
                          operation.setDate(new Date());
                          operationRepository.save(operation);
                        }

                    }
            );
        };
    }

}
