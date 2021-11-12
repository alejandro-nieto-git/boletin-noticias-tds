package es.uva.inf.tds.pr2;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Tests de caja negra validos de Noticia
 * 
 * @author aleniet
 * @author davmele
 */
@Tag("Noticia")
@Tag("BlackBox")
class NoticiaBlackBoxTestValidosTest {

	@Test
	void testInicializacionTitularDocePalabras() {
		String titular = "una dos tres cuatro cinco seis siete ocho nueve diez once doce";
		String url = "https://www.elperiodico.com/es/politica/20191113/gobierno-coalicion-psoe-podemos-apoyos-sanchez-investidura-7734615";
		String fuente = "elperiodico";
		LocalDate fecha = LocalDate.of(2019, 11, 15);
		Categoria categoria = Categoria.NACIONAL;
		Noticia noticia = new Noticia(titular, fecha, fuente, url, categoria);
		
		assertNotNull(noticia);
		assertEquals(titular, noticia.getTitular());
		assertEquals(url, noticia.getURL());
		assertEquals(fuente, noticia.getFuente());
		assertEquals(fecha, noticia.getFecha());
		assertEquals(categoria, noticia.getCategoria());
	}
	
	@Test
	void testCompareToThisConMismaFecha() {
		String titular = "Los apoyos de Sánchez para su investidura";
		String url = "https://www.elperiodico.com/es/politica/20191113/gobierno-coalicion-psoe-podemos-apoyos-sanchez-investidura-7734615";
		String fuente = "elperiodico";
		LocalDate fecha1 = LocalDate.of(2019, 11, 16);
		Categoria categoria = Categoria.NACIONAL;
		Noticia noticia1 = new Noticia(titular, fecha1, fuente, url, categoria);
		LocalDate fecha2 = LocalDate.of(2019, 11, 16);
		Noticia noticia2 = new Noticia(titular, fecha2, fuente, url, categoria);
		
		assertEquals(0, noticia1.compareTo(noticia2));
	}
	
	@Test
	void testCompareToThisConFechaPosterior() {
		String titular = "Los apoyos de Sánchez para su investidura";
		String url = "https://www.elperiodico.com/es/politica/20191113/gobierno-coalicion-psoe-podemos-apoyos-sanchez-investidura-7734615";
		String fuente = "elperiodico";
		LocalDate fecha1 = LocalDate.of(2019, 11, 17);
		Categoria categoria = Categoria.NACIONAL;
		Noticia noticia1 = new Noticia(titular, fecha1, fuente, url, categoria);
		LocalDate fecha2 = LocalDate.of(2019, 11, 16);
		Noticia noticia2 = new Noticia(titular, fecha2, fuente, url, categoria);
		
		assertEquals(1, noticia1.compareTo(noticia2));
	}
	
	@Test
	void testSimilarToFalsePorTitularDistinto() {
		String titular1 = "Los apoyos de Sánchez para su investidura";
		String url = "https://www.elperiodico.com/es/politica/20191113/gobierno-coalicion-psoe-podemos-apoyos-sanchez-investidura-7734615";
		String fuente = "elperiodico";
		LocalDate fecha1 = LocalDate.of(2019, 11, 15);
		Categoria categoria = Categoria.NACIONAL;
		Noticia noticia1 = new Noticia(titular1, fecha1, fuente, url, categoria);
		
		String titular2 = "Este es distinto";
		LocalDate fecha2 = LocalDate.of(2019, 11, 15);
		String fuente2 = "eldiarioes";
		String url2 = "https://www.eldiarioes.com/es/politica/20191113/gobierno-coalicion-psoe-podemos-apoyos-sanchez-investidura-7734615";
		Noticia noticia2 = new Noticia(titular2, fecha2, fuente2, url2, categoria);
		
		assertFalse(noticia1.similarTo(noticia2));
	}
	
	@Test
	void testSimilarToFalsePorCategoriaDistinta() {
		String titular = "Los apoyos de Sánchez para su investidura";
		String url = "https://www.elperiodico.com/es/politica/20191113/gobierno-coalicion-psoe-podemos-apoyos-sanchez-investidura-7734615";
		String fuente = "elperiodico";
		LocalDate fecha1 = LocalDate.of(2019, 11, 15);
		Categoria categoria1 = Categoria.NACIONAL;
		Noticia noticia1 = new Noticia(titular, fecha1, fuente, url, categoria1);
		
		Categoria categoria2 = Categoria.SOCIEDAD;
		LocalDate fecha2 = LocalDate.of(2019, 11, 13);
		String fuente2 = "eldiarioes";
		String url2 = "https://www.eldiarioes.com/es/politica/20191113/gobierno-coalicion-psoe-podemos-apoyos-sanchez-investidura-7734615";
		Noticia noticia2 = new Noticia(titular, fecha2, fuente2, url2, categoria2);
		
		assertFalse(noticia1.similarTo(noticia2));
	}
	
	@Test
	void testSimilarToFalseConTresDiasDeDiferenciaFecha() {
		String titular = "Los apoyos de Sánchez para su investidura";
		String url = "https://www.elperiodico.com/es/politica/20191113/gobierno-coalicion-psoe-podemos-apoyos-sanchez-investidura-7734615";
		String fuente = "elperiodico";
		LocalDate fecha1 = LocalDate.of(2019, 11, 15);
		Categoria categoria = Categoria.NACIONAL;
		Noticia noticia1 = new Noticia(titular, fecha1, fuente, url, categoria);
		
		LocalDate fecha2 = LocalDate.of(2019, 11, 18);
		String fuente2 = "eldiarioes";
		String url2 = "https://www.eldiarioes.com/es/politica/20191113/gobierno-coalicion-psoe-podemos-apoyos-sanchez-investidura-7734615";
		Noticia noticia2 = new Noticia(titular, fecha2, fuente2, url2, categoria);
		
		assertFalse(noticia1.similarTo(noticia2));
	}
	
	@Test
	void testEqualsFalsePorTitular() {
		String titular1 = "Los apoyos de Sánchez para su investidura";
		String titular2 = "Este es distinto";
		String url = "https://www.elperiodico.com/es/politica/20191113/gobierno-coalicion-psoe-podemos-apoyos-sanchez-investidura-7734615";
		String fuente = "elperiodico";
		LocalDate fecha = LocalDate.of(2019, 11, 15);
		Categoria categoria = Categoria.NACIONAL;
		Noticia noticia1 = new Noticia(titular1, fecha, fuente, url, categoria);
		Noticia noticia2 = new Noticia(titular2, fecha, fuente, url, categoria);
		
		assertFalse(noticia1.equals(noticia2));
	}
	
	@Test
	void testEqualsFalsePorFecha() {
		String titular = "Los apoyos de Sánchez para su investidura";
		String url = "https://www.elperiodico.com/es/politica/20191113/gobierno-coalicion-psoe-podemos-apoyos-sanchez-investidura-7734615";
		String fuente = "elperiodico";
		LocalDate fecha1 = LocalDate.of(2019, 11, 15);
		LocalDate fecha2 = LocalDate.of(2019, 11, 18);
		Categoria categoria = Categoria.NACIONAL;
		Noticia noticia1 = new Noticia(titular, fecha1, fuente, url, categoria);
		Noticia noticia2 = new Noticia(titular, fecha2, fuente, url, categoria);
		
		assertFalse(noticia1.equals(noticia2));
	}
	
	@Test
	void testEqualsFalsePorFuente() {
		String titular = "Los apoyos de Sánchez para su investidura";
		String url = "https://www.elperiodico.com/es/politica/20191113/gobierno-coalicion-psoe-podemos-apoyos-sanchez-investidura-7734615";
		String fuente1 = "elperiodico";
		String fuente2 = "lafuente";
		LocalDate fecha = LocalDate.of(2019, 11, 15);
		Categoria categoria = Categoria.NACIONAL;
		Noticia noticia1 = new Noticia(titular, fecha, fuente1, url, categoria);
		Noticia noticia2 = new Noticia(titular, fecha, fuente2, url, categoria);
		
		assertFalse(noticia1.equals(noticia2));
	}
	
	@Test
	void testEqualsFalsePorURL() {
		String titular = "Los apoyos de Sánchez para su investidura";
		String url1 = "https://www.elperiodico.com/es/politica/20191113/gobierno-coalicion-psoe-podemos-apoyos-sanchez-investidura-7734615";
		String url2 = "otra url";
		String fuente = "elperiodico";
		LocalDate fecha = LocalDate.of(2019, 11, 15);
		Categoria categoria = Categoria.NACIONAL;
		Noticia noticia1 = new Noticia(titular, fecha, fuente, url1, categoria);
		Noticia noticia2 = new Noticia(titular, fecha, fuente, url2, categoria);
		
		assertFalse(noticia1.equals(noticia2));
	}

	@Test
	void testEqualsFalsePorCategoria() {
		String titular = "Los apoyos de Sánchez para su investidura";
		String url = "https://www.elperiodico.com/es/politica/20191113/gobierno-coalicion-psoe-podemos-apoyos-sanchez-investidura-7734615";
		String fuente = "elperiodico";
		LocalDate fecha = LocalDate.of(2019, 11, 15);
		Categoria categoria1 = Categoria.NACIONAL;
		Categoria categoria2 = Categoria.SOCIEDAD;
		Noticia noticia1 = new Noticia(titular, fecha, fuente, url, categoria1);
		Noticia noticia2 = new Noticia(titular, fecha, fuente, url, categoria2);
		
		assertFalse(noticia1.equals(noticia2));
	}
}
