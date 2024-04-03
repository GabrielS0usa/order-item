package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import entities.Client;
import entities.Order;
import entities.OrderItem;
import entities.Product;
import entities.enums.OrderStatus;

public class Program {

	public static void main(String[] args) throws ParseException {
		SimpleDateFormat sdt = new SimpleDateFormat("dd/MM/yyyy");
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter client data");
		System.out.print("Name: ");
		String name = sc.nextLine();
		System.out.print("Email: ");
		String email = sc.nextLine();
		System.out.print("Birth date (dd/MM/yyyy): ");
		Date birthDate = sdt.parse(sc.next());
		Client client = new Client(name, email, birthDate);
		
		System.out.println("Enter order data: ");
		System.out.print("Status: ");
		String status = sc.next();
		System.out.print("How many items to this order? ");
		int c = sc.nextInt();
		OrderItem[] oi = new OrderItem[c];
		
		for (int i = 0; i < c; i++) {
			System.out.println("Enter #" + (i + 1) + " item data:");
			System.out.print("Product name: ");
			sc.nextLine();
			String pName = sc.nextLine();
			System.out.print("Product price: ");
			double pPrice = sc.nextDouble();
			System.out.print("Product quantity: ");
			int pQuantity = sc.nextInt();
			Product p = new Product(pName, pPrice); 
			oi[i] = new OrderItem(pQuantity, pPrice, p);
		}
		Date moment = new Date();
		Order order = new Order(moment, OrderStatus.valueOf(status),client);
		
		for (OrderItem p : oi) {
			order.addOrderItem(p);
		}
		
		System.out.println();
		System.out.println(order);
		
		sc.close();
	}

}
