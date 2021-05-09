package ge.alpin.javakhet.milkfactory.repository;


import ge.alpin.javakhet.milkfactory.model.CollectorAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CollectorAccountRepository extends JpaRepository<CollectorAccount, Integer> {

    Optional<CollectorAccount> getByLoginAndPassword(String login, String password);


}
