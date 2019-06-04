package demo_one;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provides;

public class ExampleProvidesAnnotation {
	public static void main(String [] args) {
		Injector injector = Guice.createInjector(new TEModule());
		TE editor = injector.getInstance(TE.class);
		editor.makeSpellCheck();
	}

}

class TE {
	private SC spellChecker;
	
	@Inject
	public TE(SC spellChecker) {
		this.spellChecker = spellChecker;
	}
	
	public void makeSpellCheck() {
		spellChecker.checkSpelling();
	}
}

class TEModule extends AbstractModule {
	@Override
	protected void configure() {}
	
	@Provides
	public SC provideSpellChecker() {
		String dbUrl = "jdbc:mysql://localhost:5326/emp";
		String user = "user";
		int timeout = 100;
		
		SC spellChecker = new SCImpl(dbUrl, user, timeout);
		return spellChecker;
	}
}

interface SC{
	public void checkSpelling();
}

class SCImpl implements SC {
	private String dbUrl;
	private String user;
	private Integer timeout;
	
	@Inject
	public SCImpl(String dbUrl, String user, Integer timeout) {
		this.dbUrl = dbUrl;
		this.user = user;
		this.timeout = timeout;
	}
	
	@Override
	public void checkSpelling() {
		System.out.println("Inside checkingSpelling");
		System.out.println(dbUrl);
		System.out.println(user);
		System.out.println(timeout);
	}
}
