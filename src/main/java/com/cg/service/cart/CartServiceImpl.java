package com.cg.service.cart;

import com.cg.exception.DataInputException;
import com.cg.exception.UnauthorizedException;
import com.cg.model.Cart;
import com.cg.model.CartDetail;
import com.cg.model.Product;
import com.cg.model.User;
import com.cg.model.dto.cart.CartReqDTO;
import com.cg.repository.CartDetailRepository;
import com.cg.repository.CartRepository;
import com.cg.repository.ProductRepository;
import com.cg.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class CartServiceImpl implements ICartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartDetailRepository cartDetailRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Cart> findAll() {
        return null;
    }

    @Override
    public Optional<Cart> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void addToCart(CartReqDTO cartReqDTO, String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> {
            throw new UnauthorizedException("Please login");
        });

        Product product = productRepository.findById(cartReqDTO.getProductId()).orElseThrow(() -> {
            throw new DataInputException("Product not found");
        });

        BigDecimal productPrice = product.getPrice();
        long productQuantity = 1L;


        Optional<Cart> cartOptional = cartRepository.findByUser(user);

        if (cartOptional.isEmpty()) {
            Cart cart = new Cart();
            cart.setUser(user);
            cart.setTotalAmount(BigDecimal.ZERO);
            cartRepository.save(cart);

            BigDecimal productAmount = productPrice.multiply(BigDecimal.valueOf(productQuantity));

            CartDetail cartDetail = new CartDetail();
            cartDetail.setCart(cart);
            cartDetail.setProduct(product);
            cartDetail.setQuantity(productQuantity);
            cartDetail.setAmount(productAmount);
            cartDetailRepository.save(cartDetail);

            cart.setTotalAmount(productAmount);
            cartRepository.save(cart);
        }
        else {
            Cart cart = cartOptional.get();

            Optional<CartDetail> cartDetailOptional = cartDetailRepository.findByCartAndProduct(cart, product);

            if (cartDetailOptional.isEmpty()) {
                BigDecimal productAmount = productPrice.multiply(BigDecimal.valueOf(productQuantity));

                CartDetail cartDetail = new CartDetail();
                cartDetail.setCart(cart);
                cartDetail.setProduct(product);
                cartDetail.setQuantity(productQuantity);
                cartDetail.setAmount(productAmount);
                cartDetailRepository.save(cartDetail);
            }
            else {
                CartDetail cartDetail = cartDetailOptional.get();
                long currentQuantity = cartDetail.getQuantity();
                long newQuantity = currentQuantity + 1;
                BigDecimal productAmount = productPrice.multiply(BigDecimal.valueOf(newQuantity));
                cartDetail.setQuantity(newQuantity);
                cartDetail.setAmount(productAmount);
                cartDetailRepository.save(cartDetail);
            }

            BigDecimal totalAmount = cartDetailRepository.getSumAmountByCart(cart);
            cart.setTotalAmount(totalAmount);
            cartRepository.save(cart);
        }
    }

    @Override
    public void save(Cart cart) {

    }

    @Override
    public void deleteById(Long id) {

    }
}
