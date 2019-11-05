package models.entities;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Lenovo
 */
public class Cash {

    private Cashier cashier;
    private boolean available;

    private ArrayList<Payment> payments;

    /**
     * Queue for Cash payments only
     */
    private Queue<Payment> cashPaymentsQueue;
    
    /**
     * Queue for Credit Card Payments only
     */
    private Queue<Payment> creditCardPaymentsQueue;

    public Cash(Cashier cashier) {
        this.cashier = cashier;
        this.available = true;
        
        payments = new ArrayList<>();
        cashPaymentsQueue = new LinkedList<>();
        creditCardPaymentsQueue = new LinkedList<>();
    }

    public Payment generatePayment(Client client, double orderTotal, PaymentStrategy paymentStrategy) {
        return new Payment(client, orderTotal, paymentStrategy);
    }
    
    /**
     * Add a payment to the general payments queue
     * @param payment 
     */
    public void addPayment(Payment payment) {
        this.payments.add(payment);
        if(payment.getPaymentType().equals(PaymentType.C)){
            cashPaymentsQueue = new LinkedList<>();
        }else{
            creditCardPaymentsQueue = new LinkedList<>();
        }
    }
    
    /**
     * 
     * @return Waiter service rate according to the service time
     */
    public int generateWaiterServiceRate(){
        //TODO
        return 0;
    }

    public Cashier getCashier() {
        return cashier;
    }

    public void setCashier(Cashier cashier) {
        this.cashier = cashier;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public ArrayList<Payment> getPayments() {
        return payments;
    }

    public void setPayments(ArrayList<Payment> payments) {
        this.payments = payments;
    }

    public Queue<Payment> getCashPaymentsQueue() {
        return cashPaymentsQueue;
    }

    public void setCashPaymentsQueue(Queue<Payment> cashPaymentsQueue) {
        this.cashPaymentsQueue = cashPaymentsQueue;
    }

    public Queue<Payment> getCreditCardPaymentsQueue() {
        return creditCardPaymentsQueue;
    }

    public void setCreditCardPaymentsQueue(Queue<Payment> creditCardPaymentsQueue) {
        this.creditCardPaymentsQueue = creditCardPaymentsQueue;
    }
    
    
}
