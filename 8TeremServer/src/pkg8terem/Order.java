/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg8terem;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javafx.util.Pair;
import pkg8terem.Main.*;

/**
 *
 * @author polga
 */
public class Order implements Serializable{
    
    private int orderID;
    private int batchID;
    private int courierID;
    private int guestID;
    private int noregistrationGuestID;
    private int subsum;
    private int mealID;
    private String mealName;
    private int quantity;
    private Map<Meal, Integer> mealsOrdered;
    private int restaurantID;
    private String restaurantName;
    private String guestAddress;
    private String guestName;
    private String guestPNumber;
    private Timestamp orderDate;
    private Timestamp estimatedDeliveryDate;
    private int paymentMethod;  
    private int orderStatus;
    private Timestamp orderDoneDate;
    private int estimated_time;
    private int sum;


    
    public Order()
    {
        this.mealsOrdered = new HashMap<>();
    }
    
    public Order(int _batchID,int _courierID,String _restaurantName, String _guestAddress,String _guestName, String _guestPNumber,int _sum, int _paymentMethod, int _orderStatus, int _restaurantID)
    {
        this.mealsOrdered = new HashMap<>();
        this.batchID=_batchID;
        this.restaurantName=_restaurantName;
        this.guestAddress=_guestAddress;
        this.guestName=_guestName;
        this.guestPNumber=_guestPNumber;
        this.courierID=_courierID;
        this.sum=_sum;
        this.paymentMethod=_paymentMethod;
        this.orderStatus=_orderStatus;
        this.restaurantID=_restaurantID;
    }
    
    public Order(int _orderID,int _batchID,int _courierID,int _guestID, int _subsum, int _foodID, String _foodName,
            int _quantity, int _restaurantID, Timestamp _orderTime, Timestamp _estimatedDeliveryTime,
            int _paymentMethod, int _orderStatus,Timestamp _orderDoneTime)
    {
        this.mealsOrdered = new HashMap<>();
        this.orderID=_orderID;
        this.batchID=_batchID;
        this.courierID=_courierID;
        this.guestID=_guestID;
        this.subsum=_subsum;
        this.mealID=_foodID;
        this.mealName=_foodName;
        this.quantity=_quantity;
        this.restaurantID=_restaurantID;
        this.orderDate=_orderTime;
        this.estimatedDeliveryDate=_estimatedDeliveryTime;
        this.paymentMethod=_paymentMethod;
        this.orderStatus=_orderStatus;
        this.orderDoneDate=_orderDoneTime;
        
    }
    
    public Order(int _restaurantID,int _guestID, Map<Meal,Integer>_orderMap, int _paymentMethod)
    {
        this.mealsOrdered = new HashMap<>();
        this.restaurantID = _restaurantID;
        this.guestID = _guestID;
        this.mealsOrdered=_orderMap;
        this.paymentMethod = _paymentMethod;
    }
    
    public void orderAccepted(Order order) throws IOException
    {
        order.setOrderStatus(1);
        Main.datas = new Pair<>(order,1);
        Main.objectOutputStream.writeObject(Main.datas);
        Main.objectOutputStream.flush();
        Main.objectOutputStream.reset();
    }
    
    public void setDeliveryStatusToDelivered(Order order) throws IOException
    {
        order.setOrderStatus(3);
        Main.datas=new Pair<>(order,1);
        Main.objectOutputStream.writeObject(Main.datas);
        Main.objectOutputStream.flush();
        Main.objectOutputStream.reset();
        
    }
    
    public void underDelivery(Order order) throws IOException
    {
        order.setOrderStatus(2);
        Main.datas = new Pair<>(order,1);
        Main.objectOutputStream.writeObject(Main.datas);
        Main.objectOutputStream.flush();
        Main.objectOutputStream.reset();
    }
    
    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getBatchID() {
        return batchID;
    }

    public void setBatchID(int batchID) {
        this.batchID = batchID;
    }

    public int getCourierID() {
        return courierID;
    }

    public void setCourierID(int courierID) {
        this.courierID = courierID;
    }

    public int getGuestID() {
        return guestID;
    }

    public void setGuestID(int guestID) {
        this.guestID = guestID;
    }

    public int getNoregistrationGuestID() {
        return noregistrationGuestID;
    }

    public void setNoregistrationGuestID(int noregistrationGuestID) {
        this.noregistrationGuestID = noregistrationGuestID;
    }

    public int getSubsum() {
        return subsum;
    }

    public void setSubsum(int subsum) {
        this.subsum = subsum;
    }

    public int getMealID() {
        return mealID;
    }

    public void setMealID(int mealID) {
        this.mealID = mealID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getRestaurantID() {
        return restaurantID;
    }

    public void setRestaurantID(int restaurantID) {
        this.restaurantID = restaurantID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public Date getEstimatedDeliveryDate() {
        return estimatedDeliveryDate;
    }

    public void setEstimatedDeliveryDate(Timestamp estimatedDeliveryDate) {
        this.estimatedDeliveryDate = estimatedDeliveryDate;
    }

    public int getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(int paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Date getOrderDoneDate() {
        return orderDoneDate;
    }

    public void setOrderDoneDate(Timestamp orderDoneDate) {
        this.orderDoneDate = orderDoneDate;
    }
       public Map<Meal, Integer> getMealsOrdered() {
        return mealsOrdered;
    }

    public void setMealsOrdered(Map<Meal, Integer> mealsOrdered) {
        this.mealsOrdered = mealsOrdered;
    }
    
    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }
    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }
    
    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getGuestAddress() {
        return guestAddress;
    }

    public void setGuestAddress(String guestAddress) {
        this.guestAddress = guestAddress;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public String getGuestPNumber() {
        return guestPNumber;
    }

    public void setGuestPNumber(String guestPNumber) {
        this.guestPNumber = guestPNumber;
    }
    
    public int getEstimated_time() {
        return estimated_time;
    }

    public void setEstimated_time(int estimated_time) {
        this.estimated_time = estimated_time;
    }
        public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}
