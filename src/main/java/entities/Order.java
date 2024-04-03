package entities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entities.enums.OrderStatus;

public class Order {
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); 
	
	private Date moment;
	private OrderStatus status;
	
	private Client client;
	private List<OrderItem> orderItem = new ArrayList<>();
	
	public Order() {
	}

	public Order(Date moment, OrderStatus status, Client client) {
		this.moment = moment;
		this.status = status;
		this.client = client;
	}
	
	public void addOrderItem(OrderItem orderitem) {
		this.orderItem.add(orderitem);
	}
	
	public void removeOrderItem(OrderItem orderitem) {
		this.orderItem.remove(orderitem);
	}
	
	public double total() {
		double sum = 0;
		for (OrderItem item : orderItem) {
			sum += item.subTotal();
		}
		return sum;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("ORDER SUMMARY: \n");
		sb.append("Order moment: " + sdf.format(moment) + "\n");
		sb.append("Order status: " + status + "\n");
		sb.append("Client: " + client.getName() + " - " + client.getEmail() + "\n");
		sb.append("Order items:\n");
		for (OrderItem item : orderItem) {
			double pro = item.getProduct().getPrice();
			sb.append(item.getProduct().getName() + ", " + String.format("$%.2f", pro) + item.getQuantity() + ", " + "Subtotal: " + String.format("$%.2f", item.subTotal()) + "\n");
		}
		sb.append("Total price: " + String.format("$%.2f", total()));
		return sb.toString();
	}
	
}
