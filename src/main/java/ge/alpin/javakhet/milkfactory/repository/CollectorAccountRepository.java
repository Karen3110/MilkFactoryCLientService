package ge.alpin.javakhet.milkfactory.repository;


import ge.alpin.javakhet.milkfactory.model.CollectorAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollectorAccountRepository extends JpaRepository<CollectorAccount, Integer> {

    CollectorAccount findAllByLoginAndPassword(String login, String password);

}
