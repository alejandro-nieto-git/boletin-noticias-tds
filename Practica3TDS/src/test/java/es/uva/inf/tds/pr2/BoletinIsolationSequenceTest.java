 package es.uva.inf.tds.pr2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.easymock.EasyMock;

/**
 * Tests de secuencia en aislamiento de Boletin
 * 
 * @author aleniet
 * @author davmele
 */
@Tag("Boletin")
@Tag("Isolation")
public class BoletinIsolationSequenceTest {
	
	@Test
	void testSecuenciaValida() {
		LocalDate fecha = LocalDate.of(2019, 11, 15);
		LocalDate fecha1 = LocalDate.of(2019, 12, 1);
		LocalDate fecha2 = LocalDate.of(2019, 12, 2);
		LocalDate fecha3 = LocalDate.of(2019, 10, 1);
		
		Noticia noticia = EasyMock.createMock(Noticia.class);
		Noticia noticia2 = EasyMock.createMock(Noticia.class);
		
		EasyMock.expect(noticia.getFecha()).andStubReturn(LocalDate.of(2019, 11, 15));
		EasyMock.expect(noticia.getCategoria()).andStubReturn(Categoria.NACIONAL);
		EasyMock.expect(noticia.similarTo(noticia)).andReturn(true);
		
		EasyMock.expect(noticia2.getFecha()).andStubReturn(LocalDate.of(2019, 11, 15));
		EasyMock.expect(noticia2.getCategoria()).andStubReturn(Categoria.CULTURA);
		EasyMock.expect(noticia2.similarTo(noticia)).andReturn(false);

		EasyMock.replay(noticia, noticia2);
		
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
		Noticia noticia = EasyMock.createMock(Noticia.class);
		
		EasyMock.expect(noticia.getFecha()).andReturn(LocalDate.of(2019, 11, 15));

		EasyMock.replay(noticia);
		
		Boletin b = new Boletin();
		b.addNoticia(noticia);
		
		assertThrows(IllegalArgumentException.class, () -> {
			b.addNoticia(noticia);;
		});	
	}
}
