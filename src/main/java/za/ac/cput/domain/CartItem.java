/*CartItem.java
CartItem class
Author: Patience Phakathi (222228431)
Date: 11/05/2025
 */

package za.ac.cput.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;


@Entity
public class CartItem {

    @Id
    private String cartItemId;

    private String productId;
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    public CartItem() {
        // Default constructor for JPA
    }

    private CartItem(Builder builder) {
        this.cartItemId = builder.cartItemId;
        this.productId = builder.productId;
        this.quantity = builder.quantity;
        this.student = builder.student;
    }

    public String getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(String cartItemId) {
        this.cartItemId = cartItemId;
    }

    public String getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public Student getStudent() {
        return student;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "cartItemId='" + cartItemId + '\'' +
                ", productId='" + productId + '\'' +
                ", quantity=" + quantity +
           //     ", student=" + student +
                '}';
    }

    public void setQuantity(int i) {
        this.quantity = i;
    }

    public static class Builder {
        private String cartItemId;
        private String productId;
        private int quantity;
        private Student student;

        public Builder setCartItemId(String cartItemId) {
            this.cartItemId = cartItemId;
            return this;
        }

        public Builder setProductId(String productId) {
            this.productId = productId;
            return this;
        }

        public Builder setQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder setStudent(Student student) {
            this.student = student;
            return this;
        }

        public CartItem build() {
            return new CartItem(this);
        }

        public Builder copy(CartItem cartItem) {
            this.cartItemId = cartItem.cartItemId;
            this.productId = cartItem.productId;
            this.quantity = cartItem.quantity;
            this.student = cartItem.student;
            return this;
        }
    }
}
