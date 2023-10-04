package com.example.customer.exception;

import com.example.customer.handler.CustomerEntityNotFoundException;
import com.example.customer.handler.CustomerSessionNotFoundException;
import com.example.customer.handler.TableEntityNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomerEntityNotFoundException.class)
    public String handleCustomerEntityNotFound(CustomerEntityNotFoundException ex, Model model) {
        model.addAttribute("errorMessage", "Khách hàng không tồn tại! Vui lòng Đăng ký hoặc nhập số điện thoại");
        return "LoginMember";
    }

    @ExceptionHandler(TableEntityNotFoundException.class)
    public String handleTableEntityNotFound(TableEntityNotFoundException ex) {
        return "ErrorTable";
    }

    @ExceptionHandler(CustomerSessionNotFoundException.class)
    public String handleCustomerSessionNotFound(CustomerSessionNotFoundException ex) {
        return "LoginMember";
    }
}
