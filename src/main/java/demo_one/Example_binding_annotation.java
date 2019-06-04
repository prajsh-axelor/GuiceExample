package demo_one;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.google.inject.AbstractModule;
import com.google.inject.BindingAnnotation;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;


@BindingAnnotation @Target({FIELD, PARAMETER, METHOD}) @Retention(RUNTIME)
@interface RaceBike {}

public class Example_binding_annotation {

	public static void main(String [] args) {
		// TODO Auto-generated method stub
		Injector injector = Guice.createInjector(new ExampleMoudleB());
		ApacheBikeB apacheBike = injector.getInstance(ApacheBikeB.class);
		
		apacheBike.goRace();
	}

}

class ExampleMoudleB extends AbstractModule{

	@Override
	protected void configure() {
		// TODO Auto-generated method stub
		bind(RacingBikeB.class).annotatedWith(RaceBike.class).to(ApacheRR200B.class);
		
	}
	
}

class ApacheBikeB {
	private RacingBikeB racingBike;
	
	@Inject 
	public ApacheBikeB(@RaceBike RacingBikeB racingBike) {
		this.racingBike = racingBike;
	}
	
	public void goRace() {
		System.out.println("Hey the max speed attained in this race is : "+racingBike.begin());
	}
}

interface RacingBikeB{
	public int begin();
}

class ApacheRR310B implements RacingBikeB{
	public int begin() {
		System.out.println("Vrooooommm... and here RR310 goes");
		return 231;
	}
}

class ApacheRR200B extends ApacheRR310B{
	public int begin() {
		System.out.println("Vrooooommm... and here RR200 goes");
		return 196;
	}
}



class ApacheRR160B implements RacingBikeB{
	public int begin() {
		System.out.println("Vrooooommm... and here RR160 goes");
		return 145;
	}
}