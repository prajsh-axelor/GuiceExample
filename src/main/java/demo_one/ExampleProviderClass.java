package demo_one;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provider;

public class ExampleProviderClass {

	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new TestEditorM());
		TextEdi editor = injector.getInstance(TextEdi.class);
		editor.makeSpellCheck();
	}

}


class TextEdi{
	private SpellCheck spellChecker;
	
	@Inject
	public TextEdi(SpellCheck spellChecker) {
		this.spellChecker = spellChecker;
	}
	
	public void makeSpellCheck() {
		spellChecker.checkSpelling();
	}
}

class TestEditorM extends AbstractModule{
	@Override
	protected void configure() {
		bind(SpellCheck.class).toProvider(SpellCheckerProvier.class);
	}
}

interface SpellCheck{
	public void checkSpelling();
}

class SpellCheckImpl implements SpellCheck {
	private String dbUrl;
	private String user;
	private String timeout;
	
	@Inject
	public SpellCheckImpl(String dbUrl, String user, Integer timeout0) {
		this.dbUrl = dbUrl;
		this.user = user;
		this.timeout = timeout;
	}
	
	@Override
	public void checkSpelling() {
		System.out.println("Inside the check spelling method!! Enjouy the show!!");
	}
}

class SpellCheckerProvier implements Provider<SpellCheck> {
	@Override
	public SpellCheck get() {
		String dbUrl = "this is jdbc url";
		String user = "Prajjwal singh";
		int timeout = 100;
		
		SpellCheck spellChecker = new SpellCheckImpl(dbUrl, user, timeout);
		return spellChecker;
	}
}