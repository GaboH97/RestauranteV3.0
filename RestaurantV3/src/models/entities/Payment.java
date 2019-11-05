/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.entities;

/**
 *
 * @author Lenovo
 */
public class Payment {

    private Client client;
    private PaymentStrategy paymentStrategy;
    private double orderTotal;
    private PaymentType paymentType;

    public Payment(Client client, double orderTotal, PaymentStrategy paymentStrategy) {
        this.client = client;
        this.orderTotal = orderTotal;
        this.paymentStrategy = paymentStrategy;
        this.paymentType = (Math.random() > 0.5) ? PaymentType.C : PaymentType.CC;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(double orderTotal) {
        this.orderTotal = orderTotal;
    }

    public PaymentStrategy getPaymentStrategy() {
        return paymentStrategy;
    }

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }
}
