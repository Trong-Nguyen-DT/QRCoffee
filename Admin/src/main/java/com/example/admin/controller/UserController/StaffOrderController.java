package com.example.admin.controller.UserController;


import com.example.admin.domain.CartEntity;
import com.example.admin.domain.Customer;
import com.example.admin.domain.Order;
import com.example.admin.domain.User;
import com.example.admin.entity.CustomerEntity;
import com.example.admin.entity.OrderEntity;
import com.example.admin.entity.OrderHistoryEntity;
import com.example.admin.entity.ProductEntity;
import com.example.admin.remote.PaymentAPI;
import com.example.admin.service.*;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Objects;

@Controller
@RequestMapping("staff")
public class StaffOrderController {

    @Autowired
    private CartEntity cartEntity;

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private StaffService staffService;

    @Autowired
    private PaymentAPI paymentAPI;



    @GetMapping("/menu")
    public String showMenu(Model model){
        model.addAttribute("product", new ProductEntity());
        model.addAttribute("orderDetails", cartEntity.getAllCartItems());
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("categories", categoryService.getAllCategory());
        return "Staff/MenuStaff";
    }

    @GetMapping("/menu/addItem")
    public String addItem(@RequestParam Long productId) {
        ProductEntity product = productService.getProductEntityById(productId);
        cartEntity.addItem(product);
        return "redirect:/staff/menu";
    }

    @GetMapping("/menu/reduceItem")
    public String reduceItem(@RequestParam Long productId) {
        ProductEntity product = productService.getProductEntityById(productId);
        cartEntity.reduceItem(product);
        return "redirect:/staff/menu";
    }


    @GetMapping("/cart")
    public String showProductByCart(Model model, @RequestParam(value = "phone", required = false) String phoneNumber) {

        if (phoneNumber == null){
            phoneNumber = "null";
        }

        if (!phoneNumber.equals("null")){
            Customer customer = customerService.getCustomerByPhone(phoneNumber);
            if (customer != null){
                cartEntity.setCustomer(customerService.getCustomerEntityByCustomer(customer));
                model.addAttribute("customer", customer);
            } else {
                return "redirect:/staff/menu";
            }
        }
        model.addAttribute("orderDetails", cartEntity.getAllCartItems());
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("totalPrice", cartEntity.getTotalPrice());
        model.addAttribute("orderEntity", cartEntity.getOrderEntity());
        model.addAttribute("phone", phoneNumber);


        return "Staff/CartStaff";
    }

    @GetMapping("cart/removeItem")
    public String removeItem(@RequestParam Long productId) {
        cartEntity.remove(productId);

        return "redirect:/staff/cart";
    }

    @GetMapping("cart/emptyItem")
    public String emptyItem() {
        cartEntity.emptyCart();
        return "redirect:/staff/cart";
    }

    @GetMapping("/cart/checkout")
    @Transactional
    public String checkout(@ModelAttribute OrderEntity cartOrderEntity){
        Customer customer = cartEntity.getCustomer();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = staffService.getUserByUserName(authentication.getName());
        if (customer != null) {
            int orderPoints = (cartOrderEntity.getPoint() != null) ? cartOrderEntity.getPoint() : 0;
            if ((orderPoints <= customer.getPoint()) && orderPoints <= cartEntity.getTotalPrice()) {
                OrderEntity orderEntity = orderService.saveOrder(customer, cartEntity.getTotalPrice(), orderPoints, user);
                orderDetailService.saveOrderDetail(orderEntity, cartEntity.getAllCartItems());

                if (orderEntity.getAmount() > 0) {
                    Order order = orderService.setOrder(orderEntity);
                    order.setItems(cartEntity.getAllCartItems());

//                    String url = paymentAPI.getQrFromOtherClient(order);
//                    return "redirect:" + url;
                    return "redirect:/staff/payment-success/" + orderEntity.getId();
                } else {
                    return "redirect:/staff/payment/success/" + orderEntity.getId();
                }
            } else {
                return "Staff/Error";
            }
        } else {
            OrderEntity orderEntity = orderService.saveOrder(null, cartEntity.getTotalPrice(), 0, user);
            orderDetailService.saveOrderDetail(orderEntity, cartEntity.getAllCartItems());

            Order order = orderService.setOrder(orderEntity);
            order.setItems(cartEntity.getAllCartItems());

//            String url = paymentAPI.getQrFromOtherClient(order);
//            return "redirect:" + url;
            return "redirect:/staff/payment-success/" + orderEntity.getId();
        }
    }

    @GetMapping("payment-success/{id}")
    public String paymentSuccess(@PathVariable String id) {
        Long orderId = Long.parseLong(id);

        OrderEntity orderEntity = orderService.getOrderEntityById(orderId);
        orderEntity = orderService.setStatus(orderEntity);
        CustomerEntity customerEntity = cartEntity.getOrderEntity().getCustomerEntity();
        if (customerEntity != null) {
            customerService.setPoint(customerEntity, orderEntity);
        }
        OrderHistoryEntity orderHistoryEntity = orderService.saveOrderHistory(orderEntity);
        orderDetailService.saveOrderDetailHistory(orderEntity, orderHistoryEntity);
        cartEntity.reset();
        return "redirect:/staff";
    }

    @GetMapping("payment-failed")
    @Transactional
    public String showFailed(){
        return "Staff/Failed";
    }
}
