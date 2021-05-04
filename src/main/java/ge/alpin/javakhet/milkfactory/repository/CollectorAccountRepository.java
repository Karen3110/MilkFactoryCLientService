package ge.alpin.javakhet.milkfactory.repository;


import ge.alpin.javakhet.milkfactory.model.CollectorAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CollectorAccountRepository extends JpaRepository<CollectorAccount,Integer> {


    CollectorAccount getById(int id);

    CollectorAccount findAllByLoginAndPassword(String login, String password);

}
