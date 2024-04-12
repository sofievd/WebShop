package se.iths.webshop.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.iths.webshop.dto.CartItem;
import se.iths.webshop.entity.OrderLine;
import se.iths.webshop.entity.Product;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CartItemServiceTest {
    CartItemService service;

   List<OrderLine> orderLineList;
   List<CartItem> cartItemList;

   @BeforeEach
   void setUp(){
       service = new CartItemService();
       OrderLine line1 = new OrderLine(1, new Product("test1", 12.5 ), 2);
       OrderLine line2 = new OrderLine(2, new Product("test2", 26.6), 4);
       OrderLine line3 = new OrderLine(3, new Product("test3", 133.4), 1);
       orderLineList = new ArrayList<>();
       orderLineList.add(line1);
       orderLineList.add(line2);
       orderLineList.add(line3);

       cartItemList= new ArrayList<>();
      cartItemList.add(new CartItem("test1", 12.5, 2, 25.0));
      cartItemList.add(new CartItem("test2", 26.6, 4,106.4));
      cartItemList.add(new CartItem("test3", 133.4, 1,133.4));
   }

    @Test
    void getCartItemsList() {
        List<CartItem> cartList = service.getCartItemsList(orderLineList);
        List<CartItem> list = new ArrayList<>();
        list.add(new CartItem("test1", 12.5, 2, 25.0));
        list.add(new CartItem("test2", 26.6, 4,106.4));
        list.add(new CartItem("test3", 133.4,1,133.4));
        assertTrue(list.size()==cartList.size());
        for(int i= 0; i< cartList.size(); i++){
            assertEquals(list.get(i).toString(), cartList.get(i).toString());
        }


    }

    @Test
    void getTotalNumberOfItemsInCart() {
       int totalNumOfItems = service.getTotalNumberOfItemsInCart(cartItemList);
       assertEquals(7, totalNumOfItems);
    }


}