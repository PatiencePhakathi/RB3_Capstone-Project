/*
Author: Patience Phakathi (222228431)
Date: 26/08/2025
 */

package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.CartItem;
import za.ac.cput.repository.CartItemRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository repository;

    @Autowired
    public CartItemServiceImpl(CartItemRepository repository) {

        this.repository = repository;
    }


    @Override
    public CartItem create(CartItem cartItem) {
        return this.repository.save(cartItem);
    }

    @Override
    public CartItem read(String s) {
        return this.repository.findById(s).orElse(null);
    }

    @Override
    public CartItem update(CartItem cartItem) {
        return this.repository.save(cartItem);
    }
    @Override
    public List<CartItem> getAll() {
        return this.repository.findAll();
    }

    @Override
    public boolean delete(String id) {
        this.repository.deleteById(id);
        return true;
    }
}
