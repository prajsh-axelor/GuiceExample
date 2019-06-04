package demo_one;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

public class Example {

	public static void main(String [] args) {
		// TODO Auto-generated method stub
		Injector injector = Guice.createInjector(new ExampleMoudle());
		ApacheBike apacheBike = injector.getInstance(ApacheBike.class);
		
		apacheBike.goRace();
	}

}

class ExampleMoudle extends AbstractModule{

	@Override
	protected void configure() {
		// TODO Auto-generated method stub
		bind(RacingBike.class).to(ApacheRR310.class);
//		bind(ApacheRR310.class).to(ApacheRR200.class);
		
	}
	
}

class ApacheBike {
	private RacingBike racingBike;
	
	@Inject 
	public ApacheBike(RacingBike racingBike) {
		this.racingBike = racingBike;
	}
	
	public void goRace() {
		System.out.println("Hey the max speed attained in this race is : "+racingBike.begin());
	}
}

interface RacingBike{
	public int begin();
}

class ApacheRR310 implements RacingBike{
	public int begin() {
		System.out.println("Vrooooommm... and here RR310 goes");
		return 231;
	}
}

class ApacheRR200 implements RacingBike{
	public int begin() {
		System.out.println("Vrooooommm... and here RR200 goes");
		return 196;
	}
}

class ApacheRR160 implements RacingBike{
	public int begin() {
		System.out.println("Vrooooommm... and here RR160 goes");
		return 145;
	}
}