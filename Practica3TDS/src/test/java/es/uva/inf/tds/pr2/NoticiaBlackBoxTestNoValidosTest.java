package es.uva.inf.tds.pr2;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Tests de caja negra no validos de Noticia
 * 
 * @author aleniet
 * @author davmele
 */
@Tag("Noticia")
@Tag("BlackBox")
class NoticiaBlackBoxTestNoValidosTest {

	@Test
	void testInicializacionArgumentosNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			@SuppressWarnings("unused")
			Noticia noticia = new Noticia(null, null, null, null, null);
		});
	}
	
	@Test
	void testInicializacionTitularTrecePalabras() {
		String titular = "una dos tres cuatro cinco seis siete ocho nueve diez once doce trece";
		String url = "https://www.elperiodico.com/es/politica/20191113/gobierno-coalicion-psoe-podemos-apoyos-sanchez-investidura-7734615";
		String fuente = "elperiodico";
		LocalDate fecha = LocalDate.of(2019, 11, 15);
		Categoria categoria = Categoria.NACIONAL;
		
		assertThrows(IllegalArgumentException.class, () -> {
			@SuppressWarnings("unused")
			Noticia noticia = new Noticia(titular, fecha, fuente, url, categoria);
		});
	}
}
