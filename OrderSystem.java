import java.util.*;
class Product{
	protected int id;
	protected String name;
	protected double price;
	
	Product(int id,String name,double price){
		this.id=id;
		this.name=name;
		this.price=price;
	}
	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id=id;
	}
	
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name=name;
	}
	
	public double getPrice(){
		return this.price;
	}
	public void setPrice(double price){
		this.price=price;
	}
	
	public void displayDetails(){
		System.out.println("\nProduct ID: "+this.id);
		System.out.println("Product Name: "+this.name);
		System.out.println("Product Price: "+this.price+"\n");	
	}
}

class ShoppingCart{
	private ArrayList <Product> items; 
	
	ShoppingCart(){
		this.items=new ArrayList <>();
	}
	public void addItem(Product product){
		items.add(product);
		System.out.println("Product added to cart successfully.");
	}
	public void removeItem(Product product){
		boolean isThere=false;
		for(int i=0;i<items.size();i++){
			if(items.get(i).getId()==product.getId()){
				items.remove(i);
				System.out.println("\nProduct removed from cart successfully.\n");
				isThere=true;
				
			}
		}
		if(isThere==false){
			System.out.println("\nProduct not found in the cart.");
		}	
	}
	public void viewCart(){
		System.out.println("\n==Items in Cart==\n");
		double totalAmount=0.0;
		for(Product item : items){
			System.out.print("- ");
			item.displayDetails();
			totalAmount=totalAmount+item.getPrice();
			System.out.println();
		}
		System.out.printf("\nTotal Price: "+totalAmount);
	}
	
	public boolean isEmpty(){
        return items.isEmpty();
    }
	
	public void clearCart(){
        items.clear();
    }
}

class Customer{
	private String customerName;
	private ShoppingCart cart;
	
	Customer(String customerName){
		this.customerName=customerName;
		this.cart= new ShoppingCart();
	}
	public String getCusName(){
		return this.customerName;
	}
	public ShoppingCart getCart(){
		return this.cart;
	}
}

public class OrderSystem{
	public static void main(String args []){
		Scanner inputs = new Scanner(System.in);
		Customer customer = new Customer("Null");
		
		ArrayList <Product> availableProducts = new ArrayList<>();
		availableProducts.add(new Product(101,"Laptop",999.99));
		availableProducts.add(new Product(102,"Smart Phone",599.99));
		availableProducts.add(new Product(103,"Mouse",99.99));
		availableProducts.add(new Product(104,"Tab",785.99));
		availableProducts.add(new Product(105,"Key Board",80.99));
		
		int x=0;
		while(x==0){
			System.out.println("\n--- Online Shopping System ---");
            System.out.println("1. Browse Products & Add to Cart");
            System.out.println("2. Remove Product from Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Place Order");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
			
			int choice =inputs.nextInt();
			inputs.nextLine();
			
			switch(choice){
				case 1:
					System.out.println("\n==Available Products==");
					for(Product p: availableProducts){
						System.out.print("- ");
						p.displayDetails();
					}
					System.out.println("--------------------");
					
					System.out.print("Product ID: ");
					int addId =inputs.nextInt();
					inputs.nextLine();
					
					boolean isAvailable=false;
					for(Product p: availableProducts){
						if(p.getId()==addId){
							System.out.println("Product Name: "+p.getName());
							System.out.println("Price: "+p.getPrice());
							customer.getCart().addItem(p);
							isAvailable=true;
							break;
						}
					}
					if(isAvailable==false){
						System.out.println("Product ID not found in catelog.");
					}
					break;
				case 2:
					System.out.print("Product ID: ");
					int removeId=inputs.nextInt();
					inputs.nextLine();
					
					customer.getCart().removeItem(new Product(removeId,"",0.0));
					break;
				
				case 3:
					customer.getCart().viewCart();
					break;
				
				case 4:
					if(customer.getCart().isEmpty()){
						System.out.println("Your Cart is Empty.");
					}
					else{
						System.out.println("Order placed Successfully.");
						customer.getCart().clearCart();
					}
					break;
					
				case 5:
					System.out.println("Thanks for visiing! GoofBye.");
					x=1;
					break;
					
				default:
					System.out.println("Invalid choice!");
					break;
			}
		}
	inputs.close();
	}
}
