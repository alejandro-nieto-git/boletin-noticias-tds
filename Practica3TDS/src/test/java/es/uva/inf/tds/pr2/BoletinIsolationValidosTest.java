package es.uva.inf.tds.pr2;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import org.easymock.EasyMock;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Tests en aislamiento validos de Boletin
 * 
 * @author aleniet
 * @author davmele
 */
@Tag("Boletin")
@Tag("Isolation")
public class BoletinIsolationValidosTest {
	
	@Test
	void testGetNoticiasOrdenCategoriaMismaCategoria() {
		Noticia noticia = EasyMock.createMock(Noticia.class);
		Noticia noticia2 = EasyMock.createMock(Noticia.class);
		Noticia noticia3 = EasyMock.createMock(Noticia.class);
		
		EasyMock.expect(noticia.getCategoria()).andStubReturn(Categoria.NACIONAL);
		EasyMock.expect(noticia.getFecha()).andStubReturn(LocalDate.of(2019, 11, 15));
		EasyMock.replay(noticia);
		
		EasyMock.expect(noticia2.getCategoria()).andStubReturn(Categoria.NACIONAL);
		EasyMock.expect(noticia2.getFecha()).andStubReturn(LocalDate.of(2019, 11, 19));
		EasyMock.replay(noticia2);
		
		EasyMock.expect(noticia3.getCategoria()).andStubReturn(Categoria.DEPORTE);
		EasyMock.expect(noticia3.getFecha()).andStubReturn(LocalDate.of(2019, 11, 19));
		EasyMock.replay(noticia3);
		
		Boletin b = new Boletin();
		b.addNoticia(noticia2);
		b.addNoticia(noticia);
		b.addNoticia(noticia3);
		
		Noticia[] noticiasOrdenCategoria = {noticia, noticia2, noticia3};
		
		assertArrayEquals(noticiasOrdenCategoria, b.getNoticiasOrdenCategoria());
		EasyMock.verify(noticia);
		EasyMock.verify(noticia2);
		EasyMock.verify(noticia3);
	}
	
	@Test
	void testGetNoticiasOrdenCronologicoFechasCoincidentes() {
		Noticia noticia = EasyMock.createMock(Noticia.class);
		Noticia noticia2 = EasyMock.createMock(Noticia.class);
		
		EasyMock.expect(noticia.getFecha()).andStubReturn(LocalDate.of(2019, 11, 15));
		EasyMock.replay(noticia);
		
		EasyMock.expect(noticia2.getFecha()).andStubReturn(LocalDate.of(2019, 11, 15));
		EasyMock.replay(noticia2);
		
		Boletin b = new Boletin();
		b.addNoticia(noticia);
		b.addNoticia(noticia2);
		
		Noticia[] noticiasOrdenCronologico = {noticia, noticia2};
		
		assertArrayEquals(noticiasOrdenCronologico, b.getNoticiasOrdenCronologico());
		EasyMock.verify(noticia);
		EasyMock.verify(noticia2);
	}
	
	@Test
	void testGetNoticiasSimilaresNoHayNingunaSimilarExtremos() {
		Noticia noticia = EasyMock.createMock(Noticia.class);
		Noticia noticia2 = EasyMock.createMock(Noticia.class);
		Noticia noticia3 = EasyMock.createMock(Noticia.class);
		
		EasyMock.expect(noticia.getFecha()).andStubReturn(LocalDate.of(2019, 11, 15));
		EasyMock.expect(noticia.similarTo(noticia3)).andReturn(false).once();
		EasyMock.replay(noticia);
		
		EasyMock.expect(noticia2.getFecha()).andStubReturn(LocalDate.of(2019, 11, 9));
		EasyMock.expect(noticia2.similarTo(noticia3)).andReturn(false).once();
		EasyMock.replay(noticia2);
		
		Boletin b = new Boletin();
		b.addNoticia(noticia);
		b.addNoticia(noticia2);
		
		assertNull(b.getNoticiasSimilares(noticia3));
		EasyMock.verify(noticia);
		EasyMock.verify(noticia2);
	}
	
	@Test
	void testGetBoletinPorFechaNingunaNoticiaEnLaFecha() {
		Noticia noticia = EasyMock.createMock(Noticia.class);
		Noticia noticia2 = EasyMock.createMock(Noticia.class);
		Noticia noticia3 = EasyMock.createMock(Noticia.class);

		EasyMock.expect(noticia.getFecha()).andStubReturn(LocalDate.of(2019, 11, 15));
		EasyMock.replay(noticia);
		
		EasyMock.expect(noticia2.getFecha()).andStubReturn(LocalDate.of(2019, 11, 9));
		EasyMock.replay(noticia2);
		
		EasyMock.expect(noticia3.getFecha()).andStubReturn(LocalDate.of(2019, 11, 9));
		EasyMock.replay(noticia3);
		
		Boletin b = new Boletin();
		b.addNoticia(noticia);
		b.addNoticia(noticia2);
		b.addNoticia(noticia3);
		
		Boletin boletinPorFechas = new Boletin();
		
		assertEquals(boletinPorFechas, b.getBoletinPorFecha(LocalDate.of(1995, 5, 12)));
		EasyMock.verify(noticia);
		EasyMock.verify(noticia2);
		EasyMock.verify(noticia3);
	}
	
	@Test
	void testGetBoletinIntervaloFechasNingunaNoticiaEntreFechas() {
		Noticia noticia = EasyMock.createMock(Noticia.class);
		Noticia noticia2 = EasyMock.createMock(Noticia.class);
		Noticia noticia3 = EasyMock.createMock(Noticia.class);

		EasyMock.expect(noticia.getFecha()).andStubReturn(LocalDate.of(2019, 11, 15));
		EasyMock.replay(noticia);

		EasyMock.expect(noticia2.getFecha()).andStubReturn(LocalDate.of(2019, 11, 17));
		EasyMock.replay(noticia2);
		
		EasyMock.expect(noticia3.getFecha()).andStubReturn(LocalDate.of(2019, 11, 19));
		EasyMock.replay(noticia3);
		
		Boletin b = new Boletin();
		b.addNoticia(noticia);
		b.addNoticia(noticia2);
		b.addNoticia(noticia3);
		
		Boletin boletinIntervalico = new Boletin();
		
		assertEquals(boletinIntervalico, b.getBoletinIntervaloFechas(LocalDate.of(1999, 11, 10), LocalDate.of(1999, 11, 18)));
		EasyMock.verify(noticia);
		EasyMock.verify(noticia2);
		EasyMock.verify(noticia3);
	}
	
	@Test
	void testGetBoletinIntervaloFechasNingunaNoticiaEntreFechasPorBoletinVacio() {
		Boletin b = new Boletin();
		
		Boletin boletinIntervalico = new Boletin();
		
		assertEquals(boletinIntervalico, b.getBoletinIntervaloFechas(LocalDate.of(1999, 11, 10), LocalDate.of(1999, 11, 18)));
	}
	
	@Test
	void testGetBoletinPorCategoriaNingunaNoticiaDeLaCategoria() {
		Noticia noticia = EasyMock.createMock(Noticia.class);
		Noticia noticia2 = EasyMock.createMock(Noticia.class);
		Noticia noticia3 = EasyMock.createMock(Noticia.class);

		EasyMock.expect(noticia.getFecha()).andStubReturn(LocalDate.of(2019, 11, 15));
		EasyMock.expect(noticia.getCategoria()).andStubReturn(Categoria.NACIONAL);
		EasyMock.replay(noticia);

		EasyMock.expect(noticia2.getFecha()).andStubReturn(LocalDate.of(2019, 11, 17));
		EasyMock.expect(noticia2.getCategoria()).andStubReturn(Categoria.NACIONAL);
		EasyMock.replay(noticia2);
		
		EasyMock.expect(noticia3.getFecha()).andStubReturn(LocalDate.of(2019, 11, 19));
		EasyMock.expect(noticia3.getCategoria()).andStubReturn(Categoria.DEPORTE);
		EasyMock.replay(noticia3);
		
		Boletin b = new Boletin();
		b.addNoticia(noticia);
		b.addNoticia(noticia2);
		b.addNoticia(noticia3);
		
		Boletin bCategoria = new Boletin();
		
		assertEquals(bCategoria, b.getBoletinPorCategoria(Categoria.ECONOMIA));
		EasyMock.verify(noticia);
		EasyMock.verify(noticia2);
		EasyMock.verify(noticia3);
	}
	
	@Test
	void testGetBoletinPorCategoriaNingunaNoticiaDeLaCategoriaPorBoletinVacio() {
		Boletin b = new Boletin();
		
		Boletin bCategoria = new Boletin();
		
		assertEquals(bCategoria, b.getBoletinPorCategoria(Categoria.SOCIEDAD));
	}
	
	@Test
	void testGetBoletinPorFechaYCategoriaNingunaNoticiaPorCategoria() {
		Noticia noticia = EasyMock.createMock(Noticia.class);
		Noticia noticia2 = EasyMock.createMock(Noticia.class);
		Noticia noticia3 = EasyMock.createMock(Noticia.class);

		EasyMock.expect(noticia.getFecha()).andStubReturn(LocalDate.of(2019, 11, 15));
		EasyMock.expect(noticia.getCategoria()).andStubReturn(Categoria.NACIONAL);
		EasyMock.replay(noticia);

		EasyMock.expect(noticia2.getFecha()).andStubReturn(LocalDate.of(2019, 11, 17));
		EasyMock.expect(noticia2.getCategoria()).andStubReturn(Categoria.ECONOMIA);
		EasyMock.replay(noticia2);
		
		EasyMock.expect(noticia3.getFecha()).andStubReturn(LocalDate.of(2019, 11, 17));
		EasyMock.expect(noticia3.getCategoria()).andStubReturn(Categoria.ECONOMIA);
		EasyMock.replay(noticia3);
		
		Boletin b = new Boletin();
		b.addNoticia(noticia);
		b.addNoticia(noticia2);
		b.addNoticia(noticia3);
		
		Boletin b2 = new Boletin();
		
		assertEquals(b2, b.getBoletinPorFechaYCategoria(LocalDate.of(2019, 11, 17), Categoria.CULTURA));
		EasyMock.verify(noticia);
		EasyMock.verify(noticia2);
		EasyMock.verify(noticia3);
	}
	
	@Test
	void testGetBoletinPorFechaYCategoriaNingunaNoticiaPorFecha() {
		Noticia noticia = EasyMock.createMock(Noticia.class);
		Noticia noticia2 = EasyMock.createMock(Noticia.class);
		Noticia noticia3 = EasyMock.createMock(Noticia.class);

		EasyMock.expect(noticia.getFecha()).andStubReturn(LocalDate.of(2019, 11, 15));
		EasyMock.expect(noticia.getCategoria()).andStubReturn(Categoria.NACIONAL);
		EasyMock.replay(noticia);

		EasyMock.expect(noticia2.getFecha()).andStubReturn(LocalDate.of(2019, 11, 17));
		EasyMock.expect(noticia2.getCategoria()).andStubReturn(Categoria.ECONOMIA);
		EasyMock.replay(noticia2);
		
		EasyMock.expect(noticia3.getFecha()).andStubReturn(LocalDate.of(2019, 11, 17));
		EasyMock.expect(noticia3.getCategoria()).andStubReturn(Categoria.ECONOMIA);
		EasyMock.replay(noticia3);
		
		Boletin b = new Boletin();
		b.addNoticia(noticia);
		b.addNoticia(noticia2);
		b.addNoticia(noticia3);
		
		Boletin b2 = new Boletin();
		
		assertEquals(b2, b.getBoletinPorFechaYCategoria(LocalDate.of(1875, 1, 5), Categoria.ECONOMIA));
		EasyMock.verify(noticia);
		EasyMock.verify(noticia2);
		EasyMock.verify(noticia3);
	}
	
	@Test
	void testGetBoletinPorFechaYCategoriaNingunaNoticiaPorBoletinVacio() {
		Boletin b = new Boletin();
		
		Boletin b2 = new Boletin();
		
		assertEquals(b2, b.getBoletinPorFechaYCategoria(LocalDate.of(1875, 1, 5), Categoria.ECONOMIA));
	}
	
	@Test
	void testGetBoletinPorIntervaloYCategoriaNingunaNoticiaPorCategoria() {
		Noticia noticia = EasyMock.createMock(Noticia.class);
		Noticia noticia2 = EasyMock.createMock(Noticia.class);
		Noticia noticia3 = EasyMock.createMock(Noticia.class);

		EasyMock.expect(noticia.getFecha()).andStubReturn(LocalDate.of(2019, 11, 15));
		EasyMock.expect(noticia.getCategoria()).andStubReturn(Categoria.NACIONAL);
		EasyMock.replay(noticia);

		EasyMock.expect(noticia2.getFecha()).andStubReturn(LocalDate.of(2019, 11, 17));
		EasyMock.expect(noticia2.getCategoria()).andStubReturn(Categoria.ECONOMIA);
		EasyMock.replay(noticia2);
		
		EasyMock.expect(noticia3.getFecha()).andStubReturn(LocalDate.of(2019, 11, 19));
		EasyMock.expect(noticia3.getCategoria()).andStubReturn(Categoria.ECONOMIA);
		EasyMock.replay(noticia3);
		
		Boletin b = new Boletin();
		b.addNoticia(noticia);
		b.addNoticia(noticia2);
		b.addNoticia(noticia3);
		
		Boletin b2 = new Boletin();
		
		assertEquals(b2, b.getBoletinPorIntervaloYCategoria(LocalDate.of(2019, 11, 15), LocalDate.of(2019, 11, 20), Categoria.INTERNACIONAL));
		EasyMock.verify(noticia);
		EasyMock.verify(noticia2);
		EasyMock.verify(noticia3);
	}
	
	@Test
	void testGetBoletinPorIntervaloYCategoriaNingunaNoticiaPorIntervalo() {
		Noticia noticia = EasyMock.createMock(Noticia.class);
		Noticia noticia2 = EasyMock.createMock(Noticia.class);
		Noticia noticia3 = EasyMock.createMock(Noticia.class);

		EasyMock.expect(noticia.getFecha()).andStubReturn(LocalDate.of(2019, 11, 15));
		EasyMock.expect(noticia.getCategoria()).andStubReturn(Categoria.NACIONAL);
		EasyMock.replay(noticia);

		EasyMock.expect(noticia2.getFecha()).andStubReturn(LocalDate.of(2019, 11, 17));
		EasyMock.expect(noticia2.getCategoria()).andStubReturn(Categoria.ECONOMIA);
		EasyMock.replay(noticia2);
		
		EasyMock.expect(noticia3.getFecha()).andStubReturn(LocalDate.of(2019, 11, 19));
		EasyMock.expect(noticia3.getCategoria()).andStubReturn(Categoria.ECONOMIA);
		EasyMock.replay(noticia3);
		
		Boletin b = new Boletin();
		b.addNoticia(noticia);
		b.addNoticia(noticia2);
		b.addNoticia(noticia3);
		
		Boletin b2 = new Boletin();
		
		assertEquals(b2, b.getBoletinPorIntervaloYCategoria(LocalDate.of(1990, 11, 30), LocalDate.of(1994, 12, 1), Categoria.ECONOMIA));
		EasyMock.verify(noticia);
		EasyMock.verify(noticia2);
		EasyMock.verify(noticia3);
	}
	
	@Test
	void testGetBoletinPorIntervaloYCategoriaNingunaNoticiaPorBoletinVacio() {
		Boletin b = new Boletin();
		
		Boletin b2 = new Boletin();
		
		assertEquals(b2, b.getBoletinPorIntervaloYCategoria(LocalDate.of(1990, 11, 30), LocalDate.of(1994, 12, 1), Categoria.ECONOMIA));
	}
	
	@Test
	void testGetNoticiasSimilaresNoHayNingunaSimilarPorBoletinVacio() {
		Noticia noticia = EasyMock.createMock(Noticia.class);
		EasyMock.replay(noticia);

		Boletin b = new Boletin();
		
		assertNull(b.getNoticiasSimilares(noticia));
		EasyMock.verify(noticia);
	}
	
	@Test
	void testInicializacionValida() {
		Boletin boletin = new Boletin();
		assertNotNull(boletin);
		assertEquals(0, boletin.getNumNoticias());
		
		Noticia noticia1 = EasyMock.createMock(Noticia.class);
		Noticia noticia2 = EasyMock.createMock(Noticia.class);
		
		EasyMock.expect(noticia1.getFecha()).andStubReturn(LocalDate.of(2019, 11, 19));
		
		EasyMock.expect(noticia2.getFecha()).andStubReturn(LocalDate.of(2019, 11, 19));
		
		EasyMock.replay(noticia1, noticia2);
		
		Noticia[] noticias = {noticia1, noticia2};
		Boletin boletin2 = new Boletin(noticias);
		assertNotNull(boletin2);
		assertEquals(2, boletin2.getNumNoticias());
		assertArrayEquals(noticias, boletin2.getNoticiasOrdenCronologico());
		
		EasyMock.verify(noticia1, noticia2);
	}
	
	@Test
	void testAgregacionDeNoticias() {
		Noticia noticia = EasyMock.createMock(Noticia.class);
		EasyMock.replay(noticia);
		
		Boletin b = new Boletin();
		assertEquals(0, b.getNumNoticias());
		b.addNoticia(noticia);
		assertEquals(1, b.getNumNoticias());
		assertEquals(noticia, b.getNoticiasOrdenCronologico()[0]);
		
		EasyMock.verify(noticia);
	}
	
	@Test
	void testGetFechaNoticiasMasRecientes() {
		Noticia noticia = EasyMock.createMock(Noticia.class);
		Noticia noticia2 = EasyMock.createMock(Noticia.class);
		
		LocalDate fecha = LocalDate.of(2019, 11, 19);
		
		EasyMock.expect(noticia.getFecha()).andStubReturn(LocalDate.of(2019, 11, 15));
		
		EasyMock.expect(noticia2.getFecha()).andStubReturn(fecha);
		
		
		EasyMock.replay(noticia, noticia2);
		
		Boletin b = new Boletin();
		b.addNoticia(noticia);
		b.addNoticia(noticia2);
		
		assertEquals(fecha, b.getFechaNoticiasMasRecientes());
		
		EasyMock.verify(noticia, noticia2);
	}
	
	@Test
	void testGetFechaNoticiasMasAntiguas() {
		Noticia noticia = EasyMock.createMock(Noticia.class);
		Noticia noticia2 = EasyMock.createMock(Noticia.class);
		
		LocalDate fecha = LocalDate.of(2019, 11, 15);
		
		EasyMock.expect(noticia.getFecha()).andStubReturn(fecha);
		
		EasyMock.expect(noticia2.getFecha()).andStubReturn(LocalDate.of(2019, 11, 19));
		
		
		EasyMock.replay(noticia, noticia2);
		
		Boletin b = new Boletin();
		b.addNoticia(noticia);
		b.addNoticia(noticia2);
		
		assertEquals(fecha, b.getFechaNoticiasMasAntiguas());
		
		EasyMock.verify(noticia, noticia2);
	}
	
	@Test
	void testGetNoticiasOrdenCronologico() {
		Noticia noticia = EasyMock.createMock(Noticia.class);
		Noticia noticia2 = EasyMock.createMock(Noticia.class);
		
		EasyMock.expect(noticia.getFecha()).andStubReturn(LocalDate.of(2019, 11, 19));
		
		EasyMock.expect(noticia2.getFecha()).andStubReturn(LocalDate.of(2019, 11, 15));
		
		
		EasyMock.replay(noticia, noticia2);
		
		Boletin b = new Boletin();
		b.addNoticia(noticia);
		b.addNoticia(noticia2);
		
		Noticia[] noticiasOrdenCronologico = {noticia2, noticia};
		
		assertArrayEquals(noticiasOrdenCronologico, b.getNoticiasOrdenCronologico());
		
		EasyMock.verify(noticia, noticia2);
	}
	
	@Test
	void testGetNoticiasOrdenCategoria() {
		Noticia noticia = EasyMock.createMock(Noticia.class);
		Noticia noticia2 = EasyMock.createMock(Noticia.class);
		
		EasyMock.expect(noticia.getCategoria()).andStubReturn(Categoria.NACIONAL);
		EasyMock.expect(noticia.getFecha()).andStubReturn(LocalDate.of(2019, 11, 15));
		
		EasyMock.expect(noticia2.getCategoria()).andStubReturn(Categoria.DEPORTE);
		EasyMock.expect(noticia2.getFecha()).andStubReturn(LocalDate.of(2019, 11, 19));
		
		EasyMock.replay(noticia, noticia2);

		Boletin b = new Boletin();
		b.addNoticia(noticia2);
		b.addNoticia(noticia);
		
		Noticia[] noticiasOrdenCategoria = {noticia, noticia2};
		
		assertArrayEquals(noticiasOrdenCategoria, b.getNoticiasOrdenCategoria());
		
		EasyMock.verify(noticia, noticia2);
	}
	
	@Test
	void testGetNoticiasSimilares() {
		Noticia noticia = EasyMock.createMock(Noticia.class);
		Noticia noticia2 = EasyMock.createMock(Noticia.class);
		Noticia noticia3 = EasyMock.createMock(Noticia.class);
		
		EasyMock.expect(noticia.similarTo(noticia3)).andStubReturn(true);
		EasyMock.expect(noticia.getFecha()).andStubReturn(LocalDate.of(2019, 11, 15));

		EasyMock.expect(noticia2.similarTo(noticia3)).andStubReturn(true);
		EasyMock.expect(noticia2.getFecha()).andStubReturn(LocalDate.of(2019, 11, 19));
		
		EasyMock.replay(noticia, noticia2, noticia3);
		
		Boletin b = new Boletin();
		b.addNoticia(noticia);
		b.addNoticia(noticia2);
		
		Noticia[] noticiasSimilares = {noticia, noticia2};
		
		assertArrayEquals(noticiasSimilares, b.getNoticiasSimilares(noticia3));
		
		EasyMock.verify(noticia, noticia2, noticia3);
	}
	
	@Test
	void testGetBoletinPorFecha() {
		Noticia noticia = EasyMock.createMock(Noticia.class);
		Noticia noticia2 = EasyMock.createMock(Noticia.class);
		Noticia noticia3 = EasyMock.createMock(Noticia.class);
		
		LocalDate fecha = LocalDate.of(2019, 11, 19);
		
		EasyMock.expect(noticia.getFecha()).andStubReturn(LocalDate.of(2019, 11, 15));
		
		EasyMock.expect(noticia2.getFecha()).andStubReturn(fecha);
		
		EasyMock.expect(noticia3.getFecha()).andStubReturn(fecha);
		
		EasyMock.replay(noticia, noticia2, noticia3);
		
		Boletin b = new Boletin();
		b.addNoticia(noticia);
		b.addNoticia(noticia2);
		b.addNoticia(noticia3);
		
		Boletin boletinPorFechas = new Boletin();
		boletinPorFechas.addNoticia(noticia2);
		boletinPorFechas.addNoticia(noticia3);
		
		assertEquals(boletinPorFechas, b.getBoletinPorFecha(fecha));
		
		EasyMock.verify(noticia, noticia2, noticia3);
	}
	
	@Test
	void testGetBoletinIntervaloFechas() {
		Noticia noticia = EasyMock.createMock(Noticia.class);
		Noticia noticia2 = EasyMock.createMock(Noticia.class);
		Noticia noticia3 = EasyMock.createMock(Noticia.class);
		Noticia noticia4 = EasyMock.createMock(Noticia.class);
		Noticia noticia5 = EasyMock.createMock(Noticia.class);
		
		EasyMock.expect(noticia.getFecha()).andStubReturn(LocalDate.of(2019, 11, 15));
		EasyMock.replay(noticia);

		EasyMock.expect(noticia2.getFecha()).andStubReturn(LocalDate.of(2019, 11, 17));
		EasyMock.replay(noticia2);
		
		EasyMock.expect(noticia3.getFecha()).andStubReturn(LocalDate.of(2019, 11, 19));
		EasyMock.replay(noticia3);
		
		EasyMock.expect(noticia4.getFecha()).andStubReturn(LocalDate.of(2019, 11, 14));
		EasyMock.replay(noticia4);
		
		EasyMock.expect(noticia5.getFecha()).andStubReturn(LocalDate.of(2019, 11, 20));
		EasyMock.replay(noticia5);
		
		
		
		Boletin b = new Boletin();
		b.addNoticia(noticia);
		b.addNoticia(noticia2);
		b.addNoticia(noticia3);
		b.addNoticia(noticia4);
		b.addNoticia(noticia5);
		
		Boletin boletinIntervalico = new Boletin();
		boletinIntervalico.addNoticia(noticia);
		boletinIntervalico.addNoticia(noticia2);
		boletinIntervalico.addNoticia(noticia3);
		
		assertEquals(boletinIntervalico, b.getBoletinIntervaloFechas(LocalDate.of(2019, 11, 15), LocalDate.of(2019, 11, 19)));
		EasyMock.verify(noticia, noticia2, noticia3, noticia4, noticia5);
	}
	
	@Test
	void testGetBoletinPorCategoria() {
		Noticia noticia = EasyMock.createMock(Noticia.class);
		Noticia noticia2 = EasyMock.createMock(Noticia.class);
		Noticia noticia3 = EasyMock.createMock(Noticia.class);
		
		EasyMock.expect(noticia.getFecha()).andStubReturn(LocalDate.of(2019, 11, 15));
		EasyMock.expect(noticia.getCategoria()).andStubReturn(Categoria.NACIONAL);
		EasyMock.replay(noticia);

		EasyMock.expect(noticia2.getFecha()).andStubReturn(LocalDate.of(2019, 11, 17));
		EasyMock.expect(noticia2.getCategoria()).andStubReturn(Categoria.NACIONAL);
		EasyMock.replay(noticia2);
		
		EasyMock.expect(noticia3.getFecha()).andStubReturn(LocalDate.of(2019, 11, 19));
		EasyMock.expect(noticia3.getCategoria()).andStubReturn(Categoria.DEPORTE);
		EasyMock.replay(noticia3);
		
		Boletin b = new Boletin();
		b.addNoticia(noticia);
		b.addNoticia(noticia2);
		b.addNoticia(noticia3);
		
		Boletin bCategoria = new Boletin();
		bCategoria.addNoticia(noticia);
		bCategoria.addNoticia(noticia2);
		
		assertEquals(bCategoria, b.getBoletinPorCategoria(Categoria.NACIONAL));
		EasyMock.verify(noticia, noticia2, noticia3);
	}
	
	@Test
	void testGetBoletinPorFechaYCategoria() {
		Noticia noticia = EasyMock.createMock(Noticia.class);
		Noticia noticia2 = EasyMock.createMock(Noticia.class);
		Noticia noticia3 = EasyMock.createMock(Noticia.class);
		
		EasyMock.expect(noticia.getFecha()).andStubReturn(LocalDate.of(2019, 11, 15));
		EasyMock.expect(noticia.getCategoria()).andStubReturn(Categoria.NACIONAL);
		EasyMock.replay(noticia);

		EasyMock.expect(noticia2.getFecha()).andStubReturn(LocalDate.of(2019, 11, 17));
		EasyMock.expect(noticia2.getCategoria()).andStubReturn(Categoria.ECONOMIA);
		EasyMock.replay(noticia2);
		
		EasyMock.expect(noticia3.getFecha()).andStubReturn(LocalDate.of(2019, 11, 17));
		EasyMock.expect(noticia3.getCategoria()).andStubReturn(Categoria.ECONOMIA);
		EasyMock.replay(noticia3);
		
		Boletin b = new Boletin();
		b.addNoticia(noticia);
		b.addNoticia(noticia2);
		b.addNoticia(noticia3);
		
		Boletin b2 = new Boletin();
		b2.addNoticia(noticia2);
		b2.addNoticia(noticia3);
		
		assertEquals(b2, b.getBoletinPorFechaYCategoria(LocalDate.of(2019, 11, 17), Categoria.ECONOMIA));
		EasyMock.verify(noticia, noticia2, noticia3);
	}
	
	@Test
	void testGetBoletinPorIntervaloYCategoria() {
		Noticia noticia = EasyMock.createMock(Noticia.class);
		Noticia noticia2 = EasyMock.createMock(Noticia.class);
		Noticia noticia3 = EasyMock.createMock(Noticia.class);
		
		EasyMock.expect(noticia.getFecha()).andStubReturn(LocalDate.of(2019, 11, 15));
		EasyMock.expect(noticia.getCategoria()).andStubReturn(Categoria.NACIONAL);
		EasyMock.replay(noticia);

		EasyMock.expect(noticia2.getFecha()).andStubReturn(LocalDate.of(2019, 11, 17));
		EasyMock.expect(noticia2.getCategoria()).andStubReturn(Categoria.ECONOMIA);
		EasyMock.replay(noticia2);
		
		EasyMock.expect(noticia3.getFecha()).andStubReturn(LocalDate.of(2019, 11, 19));
		EasyMock.expect(noticia3.getCategoria()).andStubReturn(Categoria.ECONOMIA);
		EasyMock.replay(noticia3);
		
		Boletin b = new Boletin();
		b.addNoticia(noticia);
		b.addNoticia(noticia2);
		b.addNoticia(noticia3);
		
		Boletin b2 = new Boletin();
		b2.addNoticia(noticia2);
		b2.addNoticia(noticia3);
		
		assertEquals(b2, b.getBoletinPorIntervaloYCategoria(LocalDate.of(2019, 11, 17), LocalDate.of(2019, 11, 19), Categoria.ECONOMIA));
		EasyMock.verify(noticia, noticia2, noticia3);
	}
	
	@Test
	void testGetPorcentajeSimilitud() {
		Boletin b = new Boletin();
		Boletin b2 = new Boletin();
		
		Noticia noticia = EasyMock.createMock(Noticia.class);
		Noticia noticia2 = EasyMock.createMock(Noticia.class);
		Noticia noticia3 = EasyMock.createMock(Noticia.class);
		
		EasyMock.expect(noticia.getFecha()).andStubReturn(LocalDate.of(2019, 11, 15));
		EasyMock.expect(noticia2.getFecha()).andStubReturn(LocalDate.of(2019, 11, 19));
		EasyMock.expect(noticia3.getFecha()).andStubReturn(LocalDate.of(2019, 11, 19));
		EasyMock.expect(noticia.similarTo(noticia2)).andStubReturn(false);
		EasyMock.expect(noticia.similarTo(noticia3)).andStubReturn(false);
		EasyMock.expect(noticia2.similarTo(noticia2)).andStubReturn(true);
		EasyMock.expect(noticia3.similarTo(noticia2)).andStubReturn(true);
		EasyMock.replay(noticia, noticia2, noticia3);
		
		b.addNoticia(noticia);
		b.addNoticia(noticia2);
		b.addNoticia(noticia3);
		
		b2.addNoticia(noticia2);
		b2.addNoticia(noticia3);
		
		assertEquals(66.6666, b.getPorcentajeSimilitud(b2), 0.0001);
		EasyMock.verify(noticia, noticia2, noticia3);
	}
	
	@Test
	void testGetPorcentajeSimilitudNingunaSimilarOTodasSimilares() {
		Boletin b = new Boletin();
		Boletin b2 = new Boletin();
		
		Noticia noticia = EasyMock.createMock(Noticia.class);
		Noticia noticia2 = EasyMock.createMock(Noticia.class);
		
		EasyMock.expect(noticia2.similarTo(noticia2)).andStubReturn(true);
		EasyMock.expect(noticia2.similarTo(noticia)).andStubReturn(false);
		EasyMock.expect(noticia.similarTo(noticia2)).andStubReturn(false);
		EasyMock.replay(noticia, noticia2);
		
		assertEquals(0, b.getPorcentajeSimilitud(b2));
		
		b2.addNoticia(noticia2);
		assertEquals(0, b.getPorcentajeSimilitud(b2));
		assertEquals(0, b2.getPorcentajeSimilitud(b));
		
		b.addNoticia(noticia2);
		assertEquals(100, b2.getPorcentajeSimilitud(b));
		assertEquals(100, b.getPorcentajeSimilitud(b2));
		
		Boletin b3 = new Boletin();
		b3.addNoticia(noticia);
		
		assertEquals(0, b.getPorcentajeSimilitud(b3));
		assertEquals(0, b3.getPorcentajeSimilitud(b));
		EasyMock.verify(noticia, noticia2);
	}
	
	@Test
	void testEquals() {
		Boletin b = new Boletin();
		Boletin b2 = new Boletin();
		
		assertTrue(b.equals(b2));
		
		Noticia noticia = EasyMock.createMock(Noticia.class);
		EasyMock.replay(noticia);
		
		b.addNoticia(noticia);
		
		assertFalse(b.equals(b2));
		
		b2.addNoticia(noticia);

		assertTrue(b.equals(b2));
		
		assertFalse(b.equals(null));
		EasyMock.verify(noticia);
	}
}