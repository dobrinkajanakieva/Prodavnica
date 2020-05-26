package Application.Repositories;

import Application.Models.ShoppingCart;
import Application.Models.Status;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IShoppingCartRepository extends CrudRepository<ShoppingCart, Long> {
    ShoppingCart findByUserUsernameAndStatus(String username, Status status);
    boolean existsByUserUsernameAndStatus(String username, Status status);
}
