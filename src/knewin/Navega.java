package knewin;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

public class Navega extends Inicia {

	public void Carrega() {
		try {
			Navega.Espera();
			driver.findElement(By.xpath("//button[contains(text(),'Carregar mais')]")).click();
			System.out.println();
			System.out.println("PÃ¡gina "+""+carrega+""+" carregada");
			System.out.println();
			carrega = carrega + 1;
		} catch (Exception e4) {

				
		
				
			
				
				System.out.println("Erro ao encontrar botão, será tentado novamente");
				
			}
	}
	
	public void Salva() {
		
		
		try {
			Navega.Espera();
			String urlXpath = driver
					.findElement(By.xpath(
							"/html/body/div[4]/div[4]/div/div/div[" + "" + pagnum + "" + "]/div[2]/span[2]/a"))
					.getAttribute("href");
			lista.add(urlXpath);
			System.out.println("Noticia "+""+pagnum+""+" salva");
			pagnum = pagnum + 1;
		}
			catch(Exception e) {
				
				System.out.println("Erro ao salvar noticia "+""+pagnum+""+" , será tentado novamente");
				carrega=carrega+1;
				
			}
	}


	public static void Espera() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public void Navegacao() {

		if (pagnum > lista.size() - 1) {
			driver.quit();

		}
		try {

			Navega.Espera();

			System.out.println("A url da noticia " + "" + numero + "" + " Ã© : \n " + lista.get(pagnum));

			Navega.Espera();

			driver.get(lista.get(pagnum));
			Navega.Espera();
			// Extrai o titulo

			String tituloUm = driver.findElement(By.className(tituloXpath)).getText();
			System.out.println("O titulo " + "" + numero + "" + " Ã© : \n " + tituloUm);

			System.out.println();
			System.out.println();
			Navega.Espera();
			// Extrai o subtitulo

			String subUm = driver.findElement(By.className(subXpath)).getText();
			System.out.println("O subtitulo " + "" + numero + "" + " Ã© : \n " + subUm);
			System.out.println();
			System.out.println();
			Navega.Espera();
			// Extrai o Autor

			String autorUm = driver.findElement(By.className(autorXpath)).getText();
			autorUm = autorUm.substring(4, autorUm.length());
			System.out.println("O autor da noticia " + "" + numero + "" + " Ã© : \n " + autorUm);
			System.out.println();
			System.out.println();
			Navega.Espera();
			// Extrai a data

			String dataUm = driver.findElement(By.className(dataXpath)).getAttribute("datetime");
			dataUm = dataUm.substring(0, dataUm.length() - 6) + "Z";

			DateTimeFormatter originalFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
			DateTimeFormatter targetFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

			LocalDateTime dateTime = LocalDateTime.parse(dataUm, originalFormat);

			dataUm = dateTime.format(targetFormat);

			System.out.println("A data da noticia " + "" + numero + "" + " Ã© : \n " + dataUm);
			System.out.println();
			System.out.println();
			Navega.Espera();
			// Extrai o conteÃºdo da noticia
			try {
				String conteudoUm = driver.findElement(By.className(conteudoXpath)).getText();
				System.out.println("O conteÃºdo da noticia " + "" + numero + "" + " Ã© : \n " + conteudoUm);

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
			System.out.println("Elemento indisponÃ­vel na pÃ¡gina");
			pagnum = pagnum +1;
			numero = numero + 1;

		}

	}

}
