package knewin;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

public class Navega extends Inicia {

	public void Carrega() {
		try {

			driver.findElement(By.xpath("/html/body/div[4]/div[4]/div/div/div[" + "" + pagnum + "" + "]/span/button"))
					.click();
			System.out.println();
			System.out.println("Página "+""+carrega+""+" carregada");
			System.out.println();
			carrega = carrega + 1;
		} catch (Exception e4) {
			try {
				String urlXpath = driver
						.findElement(By.xpath(
								"/html/body/div[4]/div[4]/div/div/div[" + "" + pagnum + "" + "]/div[2]/span[2]/a"))
						.getAttribute("href");
				lista.add(urlXpath);
				System.out.println("Noticia "+""+pagnum+""+" salva");
				pagnum = pagnum + 1;
				
			} catch (Exception e5) {
				carrega = 1;
				pagnum = 1;
				lista.clear();
				System.out.println("Erro ao encontrar link, processo será refeito do início");
				driver.get(url);
			}
		}

	}

	public static void Espera() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	public void Navegacao() {

		if (pagnum > lista.size() - 1) {
			driver.quit();

		}
		try {

			Navega.Espera();

			System.out.println("A url da noticia " + "" + numero + "" + " é : \n " + lista.get(pagnum));

			Navega.Espera();

			driver.get(lista.get(pagnum));
			Navega.Espera();
			// Extrai o titulo

			String tituloUm = driver.findElement(By.className(tituloXpath)).getText();
			System.out.println("O titulo " + "" + numero + "" + " é : \n " + tituloUm);

			System.out.println();
			System.out.println();
			Navega.Espera();
			// Extrai o subtitulo

			String subUm = driver.findElement(By.className(subXpath)).getText();
			System.out.println("O subtitulo " + "" + numero + "" + " é : \n " + subUm);
			System.out.println();
			System.out.println();
			Navega.Espera();
			// Extrai o Autor

			String autorUm = driver.findElement(By.className(autorXpath)).getText();
			System.out.println("O autor da noticia " + "" + numero + "" + " é : \n " + autorUm);
			System.out.println();
			System.out.println();
			Navega.Espera();
			// Extrai a data

			String dataUm = driver.findElement(By.xpath(dataXpath)).getAttribute("datetime");
			dataUm = dataUm.substring(0, dataUm.length() - 6) + "Z";

			DateTimeFormatter originalFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
			DateTimeFormatter targetFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

			LocalDateTime dateTime = LocalDateTime.parse(dataUm, originalFormat);

			dataUm = dateTime.format(targetFormat);

			System.out.println("A data da noticia " + "" + numero + "" + " é : \n " + dataUm);
			System.out.println();
			System.out.println();
			Navega.Espera();
			// Extrai o conteúdo da noticia
			try {
				String conteudoUm = driver.findElement(By.className(conteudoXpath)).getText();
				System.out.println("O conteúdo da noticia " + "" + numero + "" + " é : \n " + conteudoUm);

			} catch (Exception e) {

			}
			Navega.Espera();

			numero = numero + 1;

			System.out.println();
			System.out.println();
			System.out.println("_______________________________________________________");
			System.out.println();
			System.out.println();
			pagnum = pagnum + 1;

		} catch (Exception e2) {
			System.out.println("Elemento indisponível na página");
			pagnum = pagnum +1;
			numero = numero + 1;

		}

	}

}
