package es.uva.inf.tds.pr2;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Tests de secuencia de Boletin
 * 
 * @author aleniet
 * @author davmele
 */
@Tag("Boletin")
@Tag("Sequence")
class BoletinSequenceTest {

	@Test
	void testSecuenciaValida() {
		String titular = "Los";
		String url = "https://www.elperiodico.com/es/politica/20191113/gobierno-coalicion-psoe-podemos-apoyos-sanchez-investidura-7734615";
		String fuente = "elperiodico";
		LocalDate fecha = LocalDate.of(2019, 11, 15);
		Categoria categoria = Categoria.NACIONAL;
		Noticia noticia = new Noticia(titular, fecha, fuente, url, categoria);
		LocalDate fecha1 = LocalDate.of(2019, 12, 1);
		LocalDate fecha2 = LocalDate.of(2019, 12, 2);
		LocalDate fecha3 = LocalDate.of(2019, 10, 1);
		
		Boletin b = new Boletin();
		
		b.getBoletinIntervaloFechas(fecha1, fecha2);
		assertEquals(0, b.getNumNoticias());
		
		b.getBoletinPorCategoria(Categoria.CULTURA);
		assertEquals(0, b.getNumNoticias());
		
		b.getBoletinPorFecha(fecha1);
		assertEquals(0, b.getNumNoticias());
		
		b.getBoletinPorFechaYCategoria(fecha1, Categoria.SOCIEDAD);
		assertEquals(0, b.getNumNoticias());
		
		b.getBoletinPorIntervaloYCategoria(fecha1, fecha2, Categoria.ECONOMIA);
		assertEquals(0, b.getNumNoticias());
	
		assertNull(b.getNoticiasSimilares(noticia));
		assertEquals(0, b.getNumNoticias());
		
		b.addNoticia(noticia);
		assertEquals(1, b.getNumNoticias());
		
		Noticia noticia2 = new Noticia(titular, fecha, fuente, url, Categoria.CULTURA);
		
		b.addNoticia(noticia2);
		assertEquals(2, b.getNumNoticias());
		
		b.getBoletinIntervaloFechas(fecha3, fecha2);
		assertEquals(2, b.getNumNoticias());
		
		b.getBoletinPorCategoria(Categoria.NACIONAL);
		assertEquals(2, b.getNumNoticias());

		b.getBoletinPorFecha(fecha);
		assertEquals(2, b.getNumNoticias());

		b.getBoletinPorFechaYCategoria(fecha, Categoria.NACIONAL);
		assertEquals(2, b.getNumNoticias());
		
		b.getBoletinPorIntervaloYCategoria(fecha3, fecha2, Categoria.NACIONAL);
		assertEquals(2, b.getNumNoticias());
		
		b.getNoticiasSimilares(noticia);
		assertEquals(2, b.getNumNoticias());
		
		b.getNoticiasOrdenCronologico();
		assertEquals(2, b.getNumNoticias());
		
		b.getNoticiasOrdenCategoria();
		assertEquals(2, b.getNumNoticias());
		
		b.getFechaNoticiasMasAntiguas();
		assertEquals(2, b.getNumNoticias());

		
		b.getFechaNoticiasMasRecientes();
		assertEquals(2, b.getNumNoticias());
	}
	
	@Test
	void testGetFechaNoticiasMasAntiguasEstadoVacio() {
		Boletin b = new Boletin();
		
		assertThrows(IllegalStateException.class, () -> {
			b.getFechaNoticiasMasAntiguas();
		});
	}
	
	@Test
	void testGetFechaNoticiasMasRecientesEstadoVacio() {
		Boletin b = new Boletin();
		
		assertThrows(IllegalStateException.class, () -> {
			b.getFechaNoticiasMasRecientes();
		});
	}
	
	@Test
	void testGetNoticiasOrdenCronologicoEstadoVacio() {
		Boletin b = new Boletin();
		
		assertThrows(IllegalStateException.class, () -> {
			b.getNoticiasOrdenCronologico();
		});	
	}
	
	@Test
	void testGetNoticiasOrdenCategoriaEstadoVacio() {
		Boletin b = new Boletin();
		
		assertThrows(IllegalStateException.class, () -> {
			b.getNoticiasOrdenCategoria();
		});	
	}
	
	@Test
	void testAddNoticiaRepetida() {
		String titular = "Los";
		String url = "https://www.elperiodico.com/es/politica/20191113/gobierno-coalicion-psoe-podemos-apoyos-sanchez-investidura-7734615";
		String fuente = "elperiodico";
		LocalDate fecha = LocalDate.of(2019, 11, 15);
		Categoria categoria = Categoria.NACIONAL;
		Noticia noticia = new Noticia(titular, fecha, fuente, url, categoria);
		
		Boletin b = new Boletin();
		b.addNoticia(noticia);
		
		assertThrows(IllegalArgumentException.class, () -> {
			b.addNoticia(noticia);
		});	
	}
}