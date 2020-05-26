package Application.Repositories;

import Application.Models.CartItem;
import Application.Models.CartItemId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICartItemRepository extends CrudRepository<CartItem, CartItemId> {
}
