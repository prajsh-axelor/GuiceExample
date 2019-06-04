package demo_one;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.name.Named;
import com.google.inject.name.Names;

public class ExConstantBinding {
	public static void main (String [] args) {
		Injector injector = Guice.createInjector(new TextEditorModuleCB());
		TextEditorCB editor = injector.getInstance(TextEditorCB.class);
		
		editor.makeConnection();
	}
}

class TextEditorCB {
	private String dbUrl;
	
	@Inject
	public TextEditorCB(@Named("JDBC") String dbUrl) {
		this.dbUrl = dbUrl;
	}
	
	public void makeConnection() {
		System.out.println(dbUrl);
	}
}

class TextEditorModuleCB extends AbstractModule {
	
	@Override
	protected void configure() {
		bind(String.class).annotatedWith(Names.named("JDBC")).toInstance("jdbc:mysql://localhost:5326/emp");
		
	}

}
