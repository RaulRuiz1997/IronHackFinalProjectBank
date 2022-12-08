package com.IronHackRaulRuiz.FinalProjectRaulRuiz;

import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.accounts.Checking;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.accounts.CreditCard;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.accounts.Savings;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.embeddable.Address;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.enums.StatusAccount;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.roles.Role;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.users.AccountHolder;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.users.Admin;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.users.ThirdParty;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.users.User;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.repositories.accounts.CheckingRepository;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.repositories.accounts.CreditCardRepository;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.repositories.accounts.SavingsRepository;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.repositories.users.AccountHolderRepository;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.repositories.users.RoleRepository;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.repositories.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootApplication
public class FinalProjectRaulRuizApplication implements CommandLineRunner {

    @Autowired
    AccountHolderRepository accountHolderRepository;

    @Autowired
    SavingsRepository savingsRepository;

    @Autowired
    CreditCardRepository creditCardRepository;

    @Autowired
    CheckingRepository checkingRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(FinalProjectRaulRuizApplication.class, args);
    }

    @Override
    public void run(String... args) {

        Address address = new Address("Avenida Edimburgo", 12, "Edimburgo", 99456);
        Address addressSavings = new Address("C/ Falsa", 123, "BCN", 8100);
        Address addressCreditCard = new Address("Street Oporto", 45, "SANT FRANCISCO", 449982);
        Address addressChecking = new Address("Grove Street", 24, "LOS SANTOS", 11923);

        Admin adminRaul = userRepository.save(new Admin("admin", passwordEncoder.encode("12345")));

        AccountHolder userJaume = userRepository.save(new AccountHolder("Jaume", passwordEncoder.encode("abcd"), LocalDate.of(1985, 05, 15), address, null));
        AccountHolder userJose = userRepository.save(new AccountHolder("Jose", passwordEncoder.encode("jose"), LocalDate.of(2001, 12, 06), address, null));

        AccountHolder accountHolderSavingsAccount = userRepository.save(new AccountHolder("Peter", passwordEncoder.encode("peter"), LocalDate.of(1997, 12, 19), addressSavings, null));
        AccountHolder accountHolderCreditCardAccount = userRepository.save(new AccountHolder("John", passwordEncoder.encode("john"), LocalDate.of(1968, 06, 25), addressCreditCard, null));
        AccountHolder accountHolderCheckingAccount = userRepository.save(new AccountHolder("Phillip", passwordEncoder.encode("phillip"), LocalDate.of(1982, 02, 14), addressChecking, null));

        ThirdParty userAlex = userRepository.save(new ThirdParty("Alex", passwordEncoder.encode("alex"), "HK-4561172"));
        ThirdParty userCristian = userRepository.save(new ThirdParty("Cristian", passwordEncoder.encode("cristian"), "HK-771882"));

        roleRepository.save(new Role("ADMIN", adminRaul));

        roleRepository.save(new Role("ACCOUNT-HOLDER", userJaume));
        roleRepository.save(new Role("ACCOUNT-HOLDER", userJose));
        roleRepository.save(new Role("ACCOUNT-HOLDER", accountHolderSavingsAccount));
        roleRepository.save(new Role("ACCOUNT-HOLDER", accountHolderCreditCardAccount));
        roleRepository.save(new Role("ACCOUNT-HOLDER", accountHolderCheckingAccount));

        roleRepository.save(new Role("THIRD-PARTY-USER", userAlex));
        roleRepository.save(new Role("THIRD-PARTY-USER", userCristian));

        Savings savingAccount = adminRaul.createSavingAccount(new BigDecimal("21500.00"), accountHolderSavingsAccount, accountHolderCreditCardAccount, StatusAccount.ACTIVE, new BigDecimal("999.0"), "c1n90n8", new BigDecimal("0.2"));

        CreditCard creditCardAccount = adminRaul.createCreditCardAccount(new BigDecimal("914214.2"), accountHolderCreditCardAccount, null, StatusAccount.ACTIVE, 89523, new BigDecimal("0.015"));

        Checking checkingAccount = adminRaul.createCheckingAccount(new BigDecimal("7500.2"), accountHolderCheckingAccount, null, StatusAccount.FROZEN, 2, new BigDecimal("0.2"),  "01101010100H");

        //accountHolderRepository.save(accountHolderSavingsAccount);
        //accountHolderRepository.save(accountHolderCreditCardAccount);
        //accountHolderRepository.save(accountHolderCheckingAccount);

        savingsRepository.save(savingAccount);

        creditCardRepository.save(creditCardAccount);

        checkingRepository.save(checkingAccount);

    }

}
