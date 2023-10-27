package com.example.customer.controller;



import com.example.customer.domain.CartEntity;
import com.example.customer.domain.Customer;
import com.example.customer.domain.Table;
import com.example.customer.entity.OrderEntity;
import com.example.customer.entity.OrderHistoryEntity;
import com.example.customer.remote.TableAPI;
import com.example.customer.service.*;
import com.example.customer.validator.CustomerValidator;
import com.example.customer.validator.TableValidator;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @Autowired
    private CartEntity cartEntity;

    @Autowired
    private TableService tableService;

    @Autowired
    private TableValidator tableValidator;

    @Autowired
    private CustomerValidator customerValidator;



    @GetMapping("success/{orderCode}")
    @Transactional
    public String showSuccess(@PathVariable String orderCode, @PathVariable String tb, Model model, HttpSession session){
        Long orderId = Long.parseLong(orderCode);
        Long tableId = tableValidator.validateTable(tb);
        customerValidator.checkSession(session);
        OrderEntity orderEntity = orderService.getOrderById(orderId);
        orderEntity = orderService.setStatus(orderEntity);
        customerService.setPoint(orderEntity);
        OrderHistoryEntity orderHistoryEntity = orderService.saveOrderHistory(orderEntity);
        orderDetailService.saveOrderDetailHistory(orderEntity, orderHistoryEntity);
        cartEntity.reset();
        tableService.setTableTrue(Long.parseLong(tb));
        model.addAttribute("tb", tableId);
        return "Success";
    }

    @GetMapping("failed")
    @Transactional
    public String showFailed(@PathVariable String tb, Model model, HttpSession session){
        Long tableId = tableValidator.validateTable(tb);
        customerValidator.checkSession(session);
        model.addAttribute("tb", tableId);
        return "Failed";
    }

}
