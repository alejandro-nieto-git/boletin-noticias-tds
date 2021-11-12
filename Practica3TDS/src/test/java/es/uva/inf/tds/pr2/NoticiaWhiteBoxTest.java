package es.uva.inf.tds.pr2;

import static org.junit.jupiter.api.Assertions.assertThrows;
import java.time.LocalDate;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Tests de caja blanca de Noticia
 * 
 * @author aleniet
 * @author davmele
 */
@Tag("WhiteBox")
@Tag("Noticia")
class NoticiaWhiteBoxTest {

	@Test
	void testInicializacionInvalidaPorFechaNull() {
		String titular = "Los";
		String url = "https://www.elperiodico.com/es/politica/20191113/gobierno-coalicion-psoe-podemos-apoyos-sanchez-investidura-7734615";
		String fuente = "elperiodico";
		Categoria categoria = Categoria.NACIONAL;
		assertThrows(IllegalArgumentException.class, () -> {
			@SuppressWarnings("unused")
			Noticia noticia = new Noticia(titular, null, fuente, url, categoria);	
		});
	}
	
	@Test
	void testInicializacionInvalidaPorFuenteNull() {
		String titular = "Los";
		String url = "https://www.elperiodico.com/es/politica/20191113/gobierno-coalicion-psoe-podemos-apoyos-sanchez-investidura-7734615";
		Categoria categoria = Categoria.NACIONAL;
		assertThrows(IllegalArgumentException.class, () -> {
			@SuppressWarnings("unused")
			Noticia noticia = new Noticia(titular, LocalDate.of(2019, 12, 20), null, url, categoria);	
		});
	}
	
	@Test
	void testInicializacionInvalidaPorUrlNull() {
		String titular = "Los";
		String fuente = "elperiodico";
		Categoria categoria = Categoria.NACIONAL;
		assertThrows(IllegalArgumentException.class, () -> {
			@SuppressWarnings("unused")
			Noticia noticia = new Noticia(titular, LocalDate.of(2019, 12, 20), fuente, null, categoria);	
		});
	}
	
	@Test
	void testInicializacionInvalidaPorCategoriaNull() {
		String titular = "Los";
		String fuente = "elperiodico";
		String url = "https://www.elperiodico.com/es/politica/20191113/gobierno-coalicion-psoe-podemos-apoyos-sanchez-investidura-7734615";
		assertThrows(IllegalArgumentException.class, () -> {
			@SuppressWarnings("unused")
			Noticia noticia = new Noticia(titular, LocalDate.of(2019, 12, 20), fuente, url, null);	
		});
	}
}
