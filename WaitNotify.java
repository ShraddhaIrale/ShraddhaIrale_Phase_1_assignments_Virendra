package thread;

class Customer1{  
int amount=10000;  
  
	synchronized void withdraw(int amount){  
	System.out.println("going to withdraw...");  
	  
		if(this.amount<amount)
		{  
		System.out.println("Less balance; waiting for deposit...");  		
		try{
			wait();
		   }
		catch(Exception e){}  
		}  
		this.amount-=amount;  
		System.out.println("withdraw completed...the lefft over amount is"+ this.amount);  
	}  
  
	synchronized void deposit(int amount){  
		System.out.println("going to deposit...");  
		this.amount+=amount;  
		
		System.out.println("deposit completed... " + this.amount);  
		notify();  //unlocking of thread
	}  
}  

public class WaitNotify {

	public static void main(String[] args) {
		
		final Customer1 c=new Customer1();  
		
		new Thread(){                 // anonymous class
		  public void run()
		  {
			c.withdraw(15000);
		  }  
		}.start();  
		
		new Thread(){  
		  public void run()
		   {
			 c.deposit(20000);
		   }  
		}.start();
		
	}

}
