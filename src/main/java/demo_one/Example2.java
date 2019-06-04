package demo_one;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

public class Example2 {

	public static void main(String [] args) {
		// TODO Auto-generated method stub
		Injector injector = Guice.createInjector(new Example2Moudle());
		Apache2Bike apacheBike = injector.getInstance(Apache2Bike.class);
		
		apacheBike.goRace();
	}

}

class Example2Moudle extends AbstractModule{

	@Override
	protected void configure() {
		// TODO Auto-generated method stub
		bind(Racing2Bike.class).to(Apache2RR310.class);
	
		bind(Apache2RR310.class).to(Apache2RR200.class);
		
	}
	
}

class Apache2Bike {
	private Racing2Bike racingBike;
	
	@Inject 
	public Apache2Bike(Racing2Bike racingBike) {
		this.racingBike = racingBike;
	}
	
	public void goRace() {
		racingBike.begin();
		System.out.println("The winner is : "+racingBike.getClass().toString());
	}
}

interface Racing2Bike{
	public void begin();
}

class Apache2RR310 implements Racing2Bike{
	public void begin() {
		System.out.println("Vrooooommm... and here RR310 goes");
	}
}

class Apache2RR200 extends Apache2RR310{
	public void begin() {
		super.begin();
		System.out.println("Vrooooommm... and here RR200 goes");
	}
}

class Apache2RR160 implements Racing2Bike{
	public void begin() {
		System.out.println("Vrooooommm... and here RR160 goes");
	}
}