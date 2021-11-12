package es.uva.inf.tds.pr2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Tests de secuencia de Noticia
 * 
 * @author aleniet
 * @author davmele
 */
@Tag("Noticia")
@Tag("Sequence")
class NoticiaSequenceTest {

	@Test
	void testSecuenciaAleatoria() {
		String titular = "Los";
		String url = "https://www.elperiodico.com/es/politica/20191113/gobierno-coalicion-psoe-podemos-apoyos-sanchez-investidura-7734615";
		String fuente = "elperiodico";
		LocalDate fecha = LocalDate.of(2019, 11, 15);
		Categoria categoria = Categoria.NACIONAL;
		
		Noticia noticia = new Noticia(titular, fecha, fuente, url, categoria);
		noticia.similarTo(noticia);
		noticia.getFuente();
		noticia.compareTo(noticia);
		noticia.getTitular();
		noticia.getFecha();
		noticia.getCategoria();
		noticia.equals(noticia);
		noticia.getURL();
		
		assertEquals(fuente, noticia.getFuente());
		assertEquals(titular, noticia.getTitular());
		assertEquals(fecha, noticia.getFecha());
		assertEquals(categoria, noticia.getCategoria());
		assertEquals(url, noticia.getURL());
	}
}
