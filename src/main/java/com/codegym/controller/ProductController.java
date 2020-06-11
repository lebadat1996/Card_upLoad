package com.codegym.controller;

import com.codegym.model.Cart;
import com.codegym.model.Product;
import com.codegym.model.ProductForm;
import com.codegym.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/")
public class ProductController {
    @Autowired
    IProductService productService;

    @Autowired
    Environment env;

    @GetMapping("list")
    public ModelAndView showList() {
        ModelAndView modelAndView = new ModelAndView("product/list");
        Iterable<Product> products = productService.findAll();
        modelAndView.addObject("product", products);
        return modelAndView;
    }

    @GetMapping()
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("product/index");
        modelAndView.addObject("productForm", new ProductForm());
        return modelAndView;
    }

    @PostMapping("product")
    public ModelAndView saveFile(@ModelAttribute ProductForm productForm) throws Exception {
        ModelAndView modelAndView = new ModelAndView("product/index");
        Product product = new Product(productForm.getProductName(), null, productForm.getPrice());
        MultipartFile multipartFile = productForm.getImage();
        String fileName = multipartFile.getOriginalFilename();
        String fileUpload = env.getProperty("file_upload").toString();
        try {
            FileCopyUtils.copy(productForm.getImage().getBytes(), new File(fileUpload + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        product.setImage(fileName);
        Product product1 = productService.save(product);
        if (product1 == null) {
            modelAndView.addObject("message", "errors");
        } else {
            modelAndView.addObject("message", "ok");
        }
        modelAndView.addObject("productForm", new ProductForm());
        return modelAndView;

    }
}
