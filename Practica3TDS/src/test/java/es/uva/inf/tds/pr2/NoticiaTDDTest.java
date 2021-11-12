package es.uva.inf.tds.pr2;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Tests TDD de la clase Noticia
 * 
 * @author aleniet
 * @author davmele
 */
@Tag("Noticia")
@Tag("TDD")
class NoticiaTDDTest {
	
	@Test
	void testInicializacionTitularUnaPalabra() {
		String titular = "Los";
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
	void testInicializacionTitularVacio() {
		String titular = "";
		String url = "https://www.elperiodico.com/es/politica/20191113/gobierno-coalicion-psoe-podemos-apoyos-sanchez-investidura-7734615";
		String fuente = "elperiodico";
		LocalDate fecha = LocalDate.of(2019, 11, 15);
		Categoria categoria = Categoria.NACIONAL;
		
		assertThrows(IllegalArgumentException.class, () -> {
			@SuppressWarnings("unused")
			Noticia noticia = new Noticia(titular, fecha, fuente, url, categoria);
		});
	}

	@Test
	void testCompareToThisConFechaAnterior() {
		String titular = "Los apoyos de Sánchez para su investidura";
		String url = "https://www.elperiodico.com/es/politica/20191113/gobierno-coalicion-psoe-podemos-apoyos-sanchez-investidura-7734615";
		String fuente = "elperiodico";
		LocalDate fecha1 = LocalDate.of(2019, 11, 15);
		Categoria categoria = Categoria.NACIONAL;
		Noticia noticia1 = new Noticia(titular, fecha1, fuente, url, categoria);
		LocalDate fecha2 = LocalDate.of(2019, 11, 16);
		Noticia noticia2 = new Noticia(titular, fecha2, fuente, url, categoria);
		
		assertEquals(-1, noticia1.compareTo(noticia2));
	}

	@Test
	void testCompareToConNull() {
		String titular = "Los apoyos de Sánchez para su investidura";
		String url = "https://www.elperiodico.com/es/politica/20191113/gobierno-coalicion-psoe-podemos-apoyos-sanchez-investidura-7734615";
		String fuente = "elperiodico";
		LocalDate fecha = LocalDate.of(2019, 11, 15);
		Categoria categoria = Categoria.NACIONAL;
		Noticia noticia1 = new Noticia(titular, fecha, fuente, url, categoria);
		
		assertThrows(IllegalArgumentException.class, () -> {
			noticia1.compareTo(null);
		});
	}
	
	@Test
	void testSimilarToTrueConDosDiasDeDiferenciaFecha() {
		String titular = "Los apoyos de Sánchez para su investidura";
		String url = "https://www.elperiodico.com/es/politica/20191113/gobierno-coalicion-psoe-podemos-apoyos-sanchez-investidura-7734615";
		String fuente = "elperiodico";
		LocalDate fecha1 = LocalDate.of(2019, 11, 15);
		Categoria categoria = Categoria.NACIONAL;
		Noticia noticia1 = new Noticia(titular, fecha1, fuente, url, categoria);
		
		LocalDate fecha2 = LocalDate.of(2019, 11, 17);
		String fuente2 = "eldiarioes";
		String url2 = "https://www.eldiarioes.com/es/politica/20191113/gobierno-coalicion-psoe-podemos-apoyos-sanchez-investidura-7734615";
		Noticia noticia2 = new Noticia(titular, fecha2, fuente2, url2, categoria);
		
		assertTrue(noticia1.similarTo(noticia2));
	}
	
	@Test
	void testSimilarToNoticiaNull() {
		String titular = "Los apoyos de Sánchez para su investidura";
		String url = "https://www.elperiodico.com/es/politica/20191113/gobierno-coalicion-psoe-podemos-apoyos-sanchez-investidura-7734615";
		String fuente = "elperiodico";
		LocalDate fecha1 = LocalDate.of(2019, 11, 15);
		Categoria categoria = Categoria.NACIONAL;
		Noticia noticia1 = new Noticia(titular, fecha1, fuente, url, categoria);
		
		assertThrows(IllegalArgumentException.class, () -> {
			noticia1.similarTo(null);
		});
	}

	@Test
	void testEqualsTrue() {
		String titular = "Los apoyos de Sánchez para su investidura";
		String url = "https://www.elperiodico.com/es/politica/20191113/gobierno-coalicion-psoe-podemos-apoyos-sanchez-investidura-7734615";
		String fuente = "elperiodico";
		LocalDate fecha = LocalDate.of(2019, 11, 15);
		Categoria categoria = Categoria.NACIONAL;
		Noticia noticia1 = new Noticia(titular, fecha, fuente, url, categoria);
		Noticia noticia2 = new Noticia(titular, fecha, fuente, url, categoria);
		
		assertTrue(noticia1.equals(noticia2));
	}

	@Test
	void testEqualsFalsePorNull() {
		String titular = "Los apoyos de Sánchez para su investidura";
		String url = "https://www.elperiodico.com/es/politica/20191113/gobierno-coalicion-psoe-podemos-apoyos-sanchez-investidura-7734615";
		String fuente = "elperiodico";
		LocalDate fecha = LocalDate.of(2019, 11, 15);
		Categoria categoria = Categoria.NACIONAL;
		Noticia noticia1 = new Noticia(titular, fecha, fuente, url, categoria);
		
		assertFalse(noticia1.equals(null));
	}
}