package knewin;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Inicia {
	protected static ArrayList<String> lista = new ArrayList<String>();
	protected static String url = "https://www.infomoney.com.br/mercados/";
	protected static Integer carrega = 2;
	protected static Integer pagnum = 1;
	protected static String urlXpath;
	protected static String tituloXpath = "page-title-1";
	protected static String subXpath = "article-lead";
	protected static String autorXpath = "author-name";
	protected static String conteudoXpath = "col-md-9";
	protected static String dataXpath = "entry-date";
	protected static Integer numero = 1;
	public static WebDriver driver;

	public static void main(String[] args) {




		Navega req = new Navega();

		System.setProperty("webdriver.chrome.driver", ".\\libs\\chromedriver.exe");

		Map<String, Object> prefs = new HashMap<String, Object>(); ////////////////
		prefs.put("profile.default_content_setting_values.cookies", 2); // Este //
		prefs.put("network.cookie.cookieBehavior", 2); // bloco //
		prefs.put("profile.block_third_party_cookies", 1); // desabilita //
		ChromeOptions options = new ChromeOptions(); // cookies //
		options.setExperimentalOption("prefs", prefs); ////////////////

		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get(url);
		Navega.Espera();
		while (carrega < 4) {
			req.Carrega();
		}
		
		carrega=1;
		while (carrega<3) {
			req.Salva();
		}
		
		pagnum = 0;
		System.out.println("Links salvos, aguarde ate que a extracao das informacoes de todos os links \n seja concluida, pode demorar alguns minutos \n ");
		
		// irÃ¡ criar um arquivo txt na pasta do projeto /Bot__Selenium,
		// onde serÃ£o armazenados todas as informaÃ§Ãµes que seriam impressas no console
		File file = new File("out.txt");
		PrintStream printStream = null;

		try {
			printStream = new PrintStream(new FileOutputStream(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.setOut(printStream);
		while (pagnum <= lista.size() - 1) {
			req.Navegacao();

		}
		driver.quit();
		PrintStream consoleStream = new PrintStream(new FileOutputStream(FileDescriptor.out));
		System.setOut(consoleStream);

		System.out.println("Processo concluido, verificar arquivo out.txt na pasta \\Bot__Selenium");
	}
}
