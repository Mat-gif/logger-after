package com.myapi.logger.controller;
import com.myapi.logger.dto.ProductDTO;
import com.myapi.logger.dto.UserDTO;
import com.myapi.logger.exception.InvalidProductException;
import com.myapi.logger.exception.ProductAlreadyExistsException;
import com.myapi.logger.exception.ProductNotFoundException;
import com.myapi.logger.service.ProductService;
import com.myapi.logger.service.UserService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
public class MyController {
    private static DateTimeFormatter dateTimeFormatter;

    private static LocalDateTime localDateTime;

    private static LogManager logManager;

    private static final Logger logger = logManager.getLogger("MyController");;

    private final ProductService productService;

    private final UserService userService;

    // Endpoint to add a new user
    @PostMapping("user")
    public ResponseEntity<?> addUser(@RequestBody
    UserDTO userDTO) {
        logger.warn(" [POST] , Method : addUser , userDTO :  [{}]" , userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.addUser(userDTO));
    }

    // Endpoint to display all products
    @GetMapping("products/{id_user}")
    public ResponseEntity<?> getAllProducts(@PathVariable
    Long id_user) {
        logger.warn(" [GET] , Method : getAllProducts , id_user :  [{}]" , id_user);
        return ResponseEntity.ok(productService.getAllProducts());
    }

    // Endpoint to fetch a product by ID
    @GetMapping("products/{id}/user/{id_user}")
    public ResponseEntity<?> getProductById(@PathVariable
    Long id, @PathVariable
    Long id_user) {
        logger.warn(" [GET] , Method : getProductById , id :  [{}] , id_user :  [{}]" , id,id_user);
        try {
            return ResponseEntity.ok(productService.getProductById(id));
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // Endpoint to add a new product
    @PostMapping("products")
    public ResponseEntity<?> addProduct(@RequestBody
    ProductDTO productDTO) {
        logger.warn(" [POST] , Method : addProduct , productDTO :  [{}]" , productDTO);
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(productService.addProduct(productDTO));
        } catch (ProductAlreadyExistsException | InvalidProductException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Endpoint to delete a product by ID
    @DeleteMapping("products/{id}/user/{id_user}")
    public ResponseEntity<?> deleteProduct(@PathVariable
    Long id, @PathVariable
    Long id_user) {
        logger.warn(" [DELETE] , Method : deleteProduct , id :  [{}] , id_user :  [{}]" , id,id_user);
        try {
            productService.deleteProductById(id);
            return ResponseEntity.noContent().build();
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // Endpoint to update a product's info
    @PutMapping("products/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable
    Long id, @RequestBody
    ProductDTO productDTO) {
        logger.warn(" [PUT] , Method : updateProduct , id :  [{}] , productDTO :  [{}]" , id,productDTO);
        try {
            return ResponseEntity.ok(productService.updateProduct(id, productDTO));
        } catch (ProductNotFoundException | InvalidProductException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}