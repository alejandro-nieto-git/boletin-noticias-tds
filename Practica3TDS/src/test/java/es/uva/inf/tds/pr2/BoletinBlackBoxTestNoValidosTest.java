package es.uva.inf.tds.pr2;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Tests de caja negra no validos de Boletin
 * 
 * @author aleniet
 * @author davmele
 */
@Tag("Boletin")
@Tag("BlackBox")
class BoletinBlackBoxTestNoValidosTest {

	@Test
	void testInicializacionInvalidaPorNoticiaNull() {
		String titular = "Los apoyos de Sánchez para su investidura";
		String url = "https://www.elperiodico.com/es/politica/20191113/gobierno-coalicion-psoe-podemos-apoyos-sanchez-investidura-7734615";
		String fuente = "elperiodico";
		LocalDate fecha = LocalDate.of(2019, 11, 19);
		Categoria categoria = Categoria.NACIONAL;
		Noticia noticia1 = new Noticia(titular, fecha, fuente, url, categoria);
		
		String titular2 = "Los apoyos de Sánchez para su investidura";
		String url2 = "https://www.eldiarioes.com/es/politica/20191113/gobierno-coalicion-psoe-podemos-apoyos-sanchez-investidura-7734615";
		String fuente2 = "eldiarioes";
		LocalDate fecha2 = LocalDate.of(2019, 11, 19);
		Categoria categoria2 = Categoria.NACIONAL;
		Noticia noticia2 = new Noticia(titular2, fecha2, fuente2, url2, categoria2);
		
		Noticia[] noticias = {noticia1, noticia2, null};
		
		assertThrows(IllegalArgumentException.class, () -> {
			@SuppressWarnings("unused")
			Boletin b = new Boletin(noticias);
		});
	}
	
	@Test
	void testInicializacionInvalidaPorNoticiaRepetida() {
		String titular = "Los apoyos de Sánchez para su investidura";
		String url = "https://www.elperiodico.com/es/politica/20191113/gobierno-coalicion-psoe-podemos-apoyos-sanchez-investidura-7734615";
		String fuente = "elperiodico";
		LocalDate fecha = LocalDate.of(2019, 11, 19);
		Categoria categoria = Categoria.NACIONAL;
		Noticia noticia1 = new Noticia(titular, fecha, fuente, url, categoria);
		
		Noticia[] noticias = {noticia1, noticia1};
		
		assertThrows(IllegalArgumentException.class, () -> {
			@SuppressWarnings("unused")
			Boletin b = new Boletin(noticias);
		});
	}

	@Test
	void testAddNoticiaNull() {
		Boletin b = new Boletin();
		
		assertThrows(IllegalArgumentException.class, () -> {
			b.addNoticia(null);
		});
	}
	
	@Test
	void testGetBoletinIntervaloPrimeraFechaNullSegundaBien() {
		Boletin b = new Boletin();
		
		assertThrows(IllegalArgumentException.class, () -> {
			b.getBoletinIntervaloFechas(null, LocalDate.of(2019, 12, 1));
		});
	}
	
	@Test
	void testGetBoletinIntervaloPrimeraFechaBienSegundaNull() {
		Boletin b = new Boletin();
		
		assertThrows(IllegalArgumentException.class, () -> {
			b.getBoletinIntervaloFechas(LocalDate.of(2019, 12, 1), null);
		});
	}
	
	@Test
	void testGetBoletinPorFechaNullYCategoria() {
		Boletin b = new Boletin();
		
		assertThrows(IllegalArgumentException.class, () -> {
			b.getBoletinPorFechaYCategoria(null, Categoria.CULTURA);
		});
	}
	
	@Test
	void testGetBoletinPorFechaYCategoriaNull() {
		Boletin b = new Boletin();
		
		assertThrows(IllegalArgumentException.class, () -> {
			b.getBoletinPorFechaYCategoria(LocalDate.of(2020, 1, 1), null);
		});
	}
	
	@Test
	void testGetBoletinPorIntervaloYCategoriaNull() {
		Boletin b = new Boletin();
		
		assertThrows(IllegalArgumentException.class, () -> {
			b.getBoletinPorIntervaloYCategoria(LocalDate.of(2019, 11, 30), LocalDate.of(2019, 12, 1), null);
		});
	}
	
	@Test
	void testGetBoletinPorIntervaloNullYCategoria() {
		Boletin b = new Boletin();
		
		assertThrows(IllegalArgumentException.class, () -> {
			b.getBoletinPorIntervaloYCategoria(null, null, Categoria.SOCIEDAD);
		});
	}
}
