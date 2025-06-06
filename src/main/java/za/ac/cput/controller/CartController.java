/* CartController.java
 * Cart Controller class
 * Author: Nobahle Vuyiswa Nzimande (222641533)
 * Date: 25 May 2025
 */
package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Cart;
import za.ac.cput.service.CartService;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    public ResponseEntity<Cart> create(@RequestBody Cart cart) {
        Cart createdCart = cartService.create(cart);
        return ResponseEntity.ok(createdCart);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cart> read(@PathVariable String id) {
        Cart cart = cartService.read(id);
        return cart != null ? ResponseEntity.ok(cart) : ResponseEntity.notFound().build();
    }

    @PutMapping
    public ResponseEntity<Cart> update(@RequestBody Cart cart) {
        Cart updatedCart = cartService.update(cart);
        return updatedCart != null ? ResponseEntity.ok(updatedCart) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        boolean deleted = cartService.delete(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Cart>> getAll() {
        List<Cart> carts = cartService.getAll();
        return ResponseEntity.ok(carts);
    }
}