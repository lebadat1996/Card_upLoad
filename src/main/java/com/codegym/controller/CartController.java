package com.codegym.controller;

import com.codegym.model.Cart;
import com.codegym.model.Product;
import com.codegym.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController {
    @Autowired
    IProductService productService;

    @GetMapping("addToCart/{id}")
    public ModelAndView addCart(@PathVariable Long id, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("product/cart");
        if (session.getAttribute("cart") == null) {
            List<Cart> carts = new ArrayList<>();
            Product product = productService.findOne(id);
            Cart cart = new Cart(product, 1);
            carts.add(cart);
            session.setAttribute("cart", carts);
        } else {
            List<Cart> carts = (List<Cart>) session.getAttribute("cart");
            for (int i = 0; i < carts.size(); i++) {
                if (carts.get(i).getProduct().getProductName().equals(productService.findOne(id).getProductName())) {
                    int quantity = carts.get(i).getQuantity() + 1;
                    carts.get(i).setQuantity(quantity);
                    return modelAndView;
                }
            }
            Product product = productService.findOne(id);
            Cart cart = new Cart(product, 1);
            carts.add(cart);
            session.setAttribute("cart", carts);
        }

        return modelAndView;
    }
}
