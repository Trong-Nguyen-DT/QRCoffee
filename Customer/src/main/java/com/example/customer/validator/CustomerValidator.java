package com.example.customer.validator;

import com.example.customer.domain.Customer;
import com.example.customer.entity.ProductEntity;
import com.example.customer.handler.CustomerEntityNotFoundException;
import jakarta.servlet.http.HttpSession;
import org.apache.catalina.session.StandardSession;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


@Component
public class CustomerValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return  Customer.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Customer customer = (Customer) target;
        if (!isValidPhoneNumber(customer.getPhone())) {
            errors.rejectValue("phone", "field.invalid", "Số điện thoại không hợp lệ. Vui lòng nhập 10 số.");
        }
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        // Loại bỏ tất cả các ký tự không phải là số
        String numericPhone = phoneNumber.replaceAll("[^0-9]", "");

        // Kiểm tra xem chuỗi số điện thoại sau khi loại bỏ ký tự không phải là số có đúng 10 chữ số hay không
        return numericPhone.length() == 10;
    }

    public Customer checkSession(HttpSession session) {
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer != null) {
            return customer;
        } else {
            throw new CustomerEntityNotFoundException("Không có khách hàng nào đang đặt món");
        }
    }
}
