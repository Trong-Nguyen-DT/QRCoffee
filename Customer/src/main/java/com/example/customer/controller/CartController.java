package com.example.customer.controller;


import com.example.customer.domain.CartEntity;
import com.example.customer.domain.Customer;
import com.example.customer.domain.Order;
import com.example.customer.entity.OrderEntity;
import com.example.customer.remote.PaymentAPI;
import com.example.customer.service.OrderDetailService;
import com.example.customer.service.OrderService;
import com.example.customer.service.ProductService;
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


    @GetMapping()
    public String showErrorQR(Model model) {
        return "ErrorQR";
    }

    @GetMapping("/{tb}")
    public String showProductByCart(Model model, HttpSession session, @PathVariable String tb) {
        try {
            Integer banValue = Integer.parseInt(tb);
            Customer customer = (Customer) session.getAttribute("customer");
            if (customer != null) {
                model.addAttribute("customer", customer);
                model.addAttribute("orderDetails", cartEntity.getAllCartItems());
                model.addAttribute("products", productService.getAllProduct());
                model.addAttribute("totalPrice", cartEntity.getTotalPrice());
                model.addAttribute("orderEntity", cartEntity.getOrderEntity());
                return "Cart";
            } else {
                return "redirect:/login";
            }
        } catch (NumberFormatException e) {
            return "ErrorQR";
        }
    }

    @GetMapping("/{tb}/removeItem")
    public String removeItem(@RequestParam Long productId, @PathVariable String tb, RedirectAttributes redirectAttributes, HttpSession session) {
        try {
            Integer banValue = Integer.parseInt(tb);
            Customer customer = (Customer) session.getAttribute("customer");
            if (customer != null) {
                cartEntity.remove(productId);
                redirectAttributes.addAttribute("tb", banValue);
                return "redirect:/cart/{tb}";
            } else {
                return "redirect:/login";
            }
        } catch (NumberFormatException e) {
            return "ErrorQR";
        }
    }

    @GetMapping("/{tb}/emptyItem")
    public String emptyItem(@PathVariable String tb, RedirectAttributes redirectAttributes, HttpSession session) {
        try {
            Integer banValue = Integer.parseInt(tb);
            Customer customer = (Customer) session.getAttribute("customer");
            if (customer != null) {
                cartEntity.emptyCart();
                redirectAttributes.addAttribute("tb", banValue);
                return "redirect:/cart/{tb}";
            } else {
                return "redirect:/login";
            }
        } catch (NumberFormatException e) {
            return "ErrorQR";
        }
    }

    @GetMapping("/{tb}/checkout")
    @Transactional
    public String checkout(@PathVariable String tb, @ModelAttribute OrderEntity cartOrderEntity, HttpSession session, RedirectAttributes redirectAttributes){
        try {
            Integer banValue = Integer.parseInt(tb);
            Customer customer = (Customer) session.getAttribute("customer");
            if (customer != null) {
                if (cartOrderEntity.getPoint() <= customer.getPoint() && cartOrderEntity.getPoint() <= cartEntity.getTotalPrice()) {
                    OrderEntity orderEntity = orderService.saveOrder(banValue, customer, cartEntity.getTotalPrice(), cartOrderEntity.getPoint());
                    orderDetailService.saveOrderDetail(orderEntity, cartEntity.getAllCartItems());

                    if (orderEntity.getAmount() > 0) {
                        Order order = orderService.setOrder(orderEntity);
                        order.setItems(cartEntity.getAllCartItems());

//                        String url = paymentAPI.getQrFromOtherClient(order);
//                        return "redirect:" + url;
                        return "Menu";
                    } else {
                        redirectAttributes.addAttribute("tb", banValue);
                        return "redirect:/payment/{tb}/success/" + orderEntity.getId();
                    }
                }
                return "ErrorQR"; // 1 trang nhap diem loi
            } else {
                return "redirect:/login";
            }
        } catch (NumberFormatException e) {
            return "ErrorQR";
        }
    }


}
