package demo_one;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.name.Named;
import com.google.inject.name.Names;

public class ExampleNamedBinding {

	public static void main(String [] args) {
		// TODO Auto-generated method stub
		Injector injector = Guice.createInjector(new ExampleMoudleN());
		ApacheBikeN apacheBike = injector.getInstance(ApacheBikeN.class);
		
		apacheBike.goRace();
	}

}

class ExampleMoudleN extends AbstractModule{

	@Override
	protected void configure() {
		// TODO Auto-generated method stub
		bind(RacingBikeN.class).annotatedWith(Names.named("RaceBike")).to(ApacheRR160N.class);
//		bind(ApacheRR310.class).to(ApacheRR200.class);
		
	}
	
}

class ApacheBikeN {
	private RacingBikeN racingBike;
	
	@Inject 
	public ApacheBikeN(@Named("RaceBike") RacingBikeN racingBike) {
		this.racingBike = racingBike;
	}
	
	public void goRace() {
		System.out.println("Hey the max speed attained in this race is : "+racingBike.begin());
	}
}

interface RacingBikeN{
	public int begin();
}

class ApacheRR310N implements RacingBikeN{
	public int begin() {
		System.out.println("Vrooooommm... and here RR310 goes");
		return 231;
	}
}

class ApacheRR200N implements RacingBikeN{
	public int begin() {
		System.out.println("Vrooooommm... and here RR200 goes");
		return 196;
	}
}

class ApacheRR160N implements RacingBikeN{
	public int begin() {
		System.out.println("Vrooooommm... and here RR160 goes");
		return 145;
	}
}