package com.example.customer.exception;

import com.example.customer.handler.CustomerEntityNotFoundException;
import com.example.customer.handler.CustomerSessionNotFoundException;
import com.example.customer.handler.TableEntityNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomerEntityNotFoundException.class)
    public String handleCustomerEntityNotFound(CustomerEntityNotFoundException ex, Model model) {
        model.addAttribute("errorMessage", "Không tồn tại khách hàng");
        return "LoginMember";
    }

    @ExceptionHandler(TableEntityNotFoundException.class)
    public String handleTableEntityNotFound(TableEntityNotFoundException ex) {
        return "ErrorTable";
    }

    @ExceptionHandler(CustomerSessionNotFoundException.class)
    public String handleCustomerSessionNotFound(CustomerSessionNotFoundException ex) {
        return "ErrorCustomer";
    }
}
