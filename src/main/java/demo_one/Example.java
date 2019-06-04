package demo_one;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.google.inject.AbstractModule;
import com.google.inject.BindingAnnotation;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provider;
import com.google.inject.Provides;
import com.google.inject.name.Names;



@BindingAnnotation @Target({FIELD, PARAMETER, METHOD}) @Retention(RUNTIME)
@interface ApacheRR {}
@BindingAnnotation @Target({FIELD, PARAMETER, METHOD}) @Retention(RUNTIME)
@interface ApacheRS {}


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
		bind(RacingBike.class).annotatedWith(ApacheRR.class).to(ApacheRR310.class);
		bind(RacingBike.class).annotatedWith(ApacheRS.class).to(ApacheRR200.class);
		bind(RacingBike.class).annotatedWith(Names.named("ApacheRT")).to(ApacheRR160.class);
		
		bind(String.class).annotatedWith(Names.named("Prajjwal Singh")).toInstance("This is Prajjwal Singh Welcoming you here");
		
//		bind(ApacheRR310.class).to(ApacheRR200.class);
		
	}
	
	//provides method @Provides
	@Provides
	RacingBike provideApache310() {
		ApacheRR310 apacheRR310 = new ApacheRR310();
		apacheRR310.setString("This belongs to Prajjwal Singh");
		System.out.println(apacheRR310.getString());
		return apacheRR310;
	}
	
}

class ApacheBike {
	private RacingBike racingBike;
	
	@Inject
	public ApacheBike(RacingBike racingBike) { //calls @Provides method injector  needs its object instance 
//	public ApacheBike(@Named("ApacheRT") RacingBike racingBike, @Named("Prajjwal Singh") String pj) { //this is instanceBinding
//	public ApacheBike(@Named("ApacheRT") RacingBike racingBike) { //this is bindingAnnotation using @Named
//	public ApacheBike(@ApacheRS RacingBike racingBike) { //this is bindingAnnotation using @BindingAnnotation
		this.racingBike = racingBike;
//		System.out.println(pj);
	}
	
	public void goRace() {
		System.out.println("Hey the max speed attained in this race is : "+racingBike.begin());
	}
}


//provider class implementation
class ProviderBikes implements Provider<RacingBike>{

	@Inject
	public ProviderBikes() {    //has dependencies of its own that's why constructot
										//with @Inject annotation
		// TODO Auto-generated constructor stub
		
		System.out.println("Inside the constructor of the provide class");
	}		
	
	@Override
	public RacingBike get() {
		// TODO Auto-generated method stub
		System.out.println("Inside get method of provider class!!!");
		return null;
	}
	
}


interface RacingBike{
	public int begin();
}

class ApacheRR310 implements RacingBike{
	private String str ;
	public void setString(String str) {
		this.str = str;
	}
	public String getString(){
		return str;
	}
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