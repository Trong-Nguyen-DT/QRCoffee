package com.example.customer.controller;


import com.example.customer.domain.CartEntity;
import com.example.customer.domain.Customer;
import com.example.customer.domain.Order;
import com.example.customer.entity.OrderEntity;
import com.example.customer.remote.PaymentAPI;
import com.example.customer.service.OrderDetailService;
import com.example.customer.service.OrderService;
import com.example.customer.service.ProductService;
import com.example.customer.validator.CustomerValidator;
import com.example.customer.validator.TableValidator;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("cart")
public class CartController {

    @Autowired
    private CartEntity cartEntity;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private PaymentAPI paymentAPI;

    @Autowired
    private TableValidator tableValidator;

    @Autowired
    private CustomerValidator customerValidator;


    @GetMapping()
    public String showErrorQR(Model model) {
        return "ErrorCustomer";
    }

    @GetMapping("/{tb}")
    public String showProductByCart(Model model, HttpSession session, @PathVariable String tb) {
        Long tableId = tableValidator.validateTable(tb);
        Customer customer = customerValidator.checkSession(session);
        model.addAttribute("customer", customer);
        model.addAttribute("orderDetails", cartEntity.getAllCartItems());
        model.addAttribute("products", productService.getAllProduct());
        model.addAttribute("totalPrice", cartEntity.getTotalPrice());
        model.addAttribute("orderEntity", cartEntity.getOrderEntity());
        return "Cart";
    }

    @GetMapping("/{tb}/removeItem")
    public String removeItem(@RequestParam Long productId, @PathVariable String tb, RedirectAttributes redirectAttributes, HttpSession session) {
        Long tableId = tableValidator.validateTable(tb);
        customerValidator.checkSession(session);
        cartEntity.remove(productId);
        redirectAttributes.addAttribute("tb", tableId);
        return "redirect:/cart/{tb}";
    }

    @GetMapping("/{tb}/emptyItem")
    public String emptyItem(@PathVariable String tb, RedirectAttributes redirectAttributes, HttpSession session) {
        Long tableId = tableValidator.validateTable(tb);
        customerValidator.checkSession(session);
        cartEntity.emptyCart();
        redirectAttributes.addAttribute("tb", tableId);
        return "redirect:/cart/{tb}";
    }

    @GetMapping("/{tb}/checkout")
    @Transactional
    public String checkout(@PathVariable String tb, @ModelAttribute OrderEntity cartOrderEntity, HttpSession session, RedirectAttributes redirectAttributes){
        Long tableId = tableValidator.validateTable(tb);
        Customer customer = customerValidator.checkSession(session);
        int orderPoints = (cartOrderEntity.getPoint() != null) ? cartOrderEntity.getPoint() : 0;
        if ((orderPoints <= customer.getPoint()) && orderPoints <= cartEntity.getTotalPrice()) {
            OrderEntity orderEntity = orderService.saveOrder(Integer.parseInt(tb), customer, cartEntity.getTotalPrice(), orderPoints);
            orderDetailService.saveOrderDetail(orderEntity, cartEntity.getAllCartItems());

            if (orderEntity.getAmount() > 0) {
                Order order = orderService.setOrder(orderEntity);
                order.setItems(cartEntity.getAllCartItems());

//                String url = paymentAPI.getQrFromOtherClient(order);
//                return "redirect:" + url;
                return "redirect:/payment/{tb}/success/" + orderEntity.getId();
            } else {
                redirectAttributes.addAttribute("tb", tableId);
                return "redirect:/payment/{tb}/success/" + orderEntity.getId();
            }
        }
        return "ErrorQR";
    }


}
