package ro.unibuc.hello.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;
import ro.unibuc.hello.data.product.ProductDTO;
import ro.unibuc.hello.data.transaction.TransactionDTO;
import ro.unibuc.hello.data.transaction.TransactionEntity;
import ro.unibuc.hello.service.TransactionService;

@Controller
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping("/api/transactions")
    @ResponseBody
    public void postProduct(@RequestBody TransactionDTO transaction){
        try {
            transactionService.createTransaction(transaction);
        }
        catch (Exception e) {
            if (e.getMessage().equals(HttpStatus.NOT_FOUND.toString())){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "A product could not be found");
            }
            else if (e.getMessage().equals(HttpStatus.BAD_REQUEST.toString()))
            {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not enough products in stock");
            }
            else {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Service not available");
            }
        }

    }
}
