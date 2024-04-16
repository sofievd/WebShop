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
 * @version 0.1
 * <h2>EmailService</h2>
 * @date 2024-04-04
 */

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    public void sendOrderConfirmation(User user, List<CartItem> listOfCartItems, Order order) {
        StringBuilder emailMessage = new StringBuilder();
        emailMessage.append("\n-------------------------------------- Order Details ---------------------------------------");

        emailMessage.append("\n\nProduct" + "\t\t\t" + "Price (kr/pc)" + "\t\t\t" + "Quantity" + "\t\t\t" + "TotalPrice (kr)");

        for(CartItem item : listOfCartItems){
            emailMessage.append("\n\n");
            emailMessage.append(item.getProductName() + "\t\t\t\t" + item.getPrice() + "\t\t\t\t" +
                                    item.getQuantity() + "\t\t\t\t\t" + item.getTotalPrice());
        }
        emailMessage.append("\n\nTotal price: " + order.getTotalAmount() + " kr");
        emailMessage.append("\n\nThank you for placing your order!");

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Order Confirmation for Order #" + order.getId());
        message.setText(emailMessage.toString());
        emailSender.send(message);
    }
}
