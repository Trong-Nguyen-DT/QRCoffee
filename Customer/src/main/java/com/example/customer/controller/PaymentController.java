package com.example.customer.controller;



import com.example.customer.domain.CartEntity;
import com.example.customer.domain.Customer;
import com.example.customer.entity.OrderEntity;
import com.example.customer.entity.OrderHistoryEntity;
import com.example.customer.service.CustomerService;
import com.example.customer.service.OrderDetailService;
import com.example.customer.service.OrderService;
import com.example.customer.service.ProductService;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("payment/{tb}")
public class PaymentController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private CustomerService customerService;



    @GetMapping("success/{orderCode}")
    @Transactional
    public String showSuccess(@PathVariable String orderCode, @PathVariable String tb, RedirectAttributes redirectAttributes){
        Long orderId = Long.parseLong(orderCode);
        Integer banValue = Integer.parseInt(tb);

        redirectAttributes.addAttribute("tb", banValue);
        OrderEntity orderEntity = orderService.getOrderById(orderId);
        orderEntity = orderService.setStatus(orderEntity);
        customerService.setPoint(orderEntity);
        OrderHistoryEntity orderHistoryEntity = orderService.saveOrderHistory(orderEntity);
        orderDetailService.saveOrderDetailHistory(orderEntity, orderHistoryEntity);
        return "Success";
    }

    @GetMapping("failed")
    @Transactional
    public String showFailed(@PathVariable String tb, RedirectAttributes redirectAttributes){
        Integer banValue = Integer.parseInt(tb);
        redirectAttributes.addAttribute("tb", banValue);
        return "Failed";
    }

}
