package Application.Repositories;

import Application.Models.ChargeRequest;
import Application.Models.ShoppingCart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITransactionRepository  extends CrudRepository<ChargeRequest, Long> {
}
