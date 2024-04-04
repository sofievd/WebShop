package se.iths.webshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import se.iths.webshop.dto.CartItem;
import se.iths.webshop.entity.Order;
import se.iths.webshop.entity.User;

import java.util.List;

/**
 * @author Depinder Kaur
 * @version <h2></h2>
 * @date 2024-04-04
 */

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    public void sendOrderConfirmation(User user, List<CartItem> listOfCartItems, Order order) {
        StringBuilder emailMessage = new StringBuilder();
        emailMessage.append("########## Order Details ##########");

        for(CartItem item : listOfCartItems){
            emailMessage.append("\n");
            emailMessage.append(item.getProductName() + "\t\t" + item.getQuantity() + "\t\t" + item.getTotalPrice() + " kr");
        }
        emailMessage.append("\nTotal price: " + order.getTotalAmount() + " kr");


        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Order Confirmation, Order No.: " + order.getId());
        message.setText(emailMessage.toString());
        emailSender.send(message);
    }
}
