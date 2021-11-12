package es.uva.inf.tds.pr2;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Tests TDD de la clase Boletin
 * 
 * @author aleniet
 * @author davmele
 */
@Tag("Boletin")
@Tag("TDD")
class BoletinTDDTest {
	private Noticia noticia;
	
	@BeforeEach
	public void inicializarNoticia1() {
		String titular = "Los apoyos de Sánchez para su investidura";
		String url = "https://www.elperiodico.com/es/politica/20191113/gobierno-coalicion-psoe-podemos-apoyos-sanchez-investidura-7734615";
		String fuente = "elperiodico";
		LocalDate fecha = LocalDate.of(2019, 11, 15);
		Categoria categoria = Categoria.NACIONAL;
		noticia = new Noticia(titular, fecha, fuente, url, categoria);
	}
	
	@Test
	void testInicializacionValida() {
		Boletin boletin = new Boletin();
		assertNotNull(boletin);
		assertEquals(0, boletin.getNumNoticias());
		
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
		
		Noticia[] noticias = {noticia1, noticia2};
		Boletin boletin2 = new Boletin(noticias);
		assertNotNull(boletin2);
		assertEquals(2, boletin2.getNumNoticias());
		assertArrayEquals(noticias, boletin2.getNoticiasOrdenCronologico());
	}
	
	@Test
	void testInicializacionInvalidaPorNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			@SuppressWarnings("unused")
			Boletin b = new Boletin(null);
		});
	}

	@Test
	void testAgregacionDeNoticias() {
		Boletin b = new Boletin();
		assertEquals(0, b.getNumNoticias());
		b.addNoticia(noticia);
		assertEquals(1, b.getNumNoticias());
		assertEquals(noticia, b.getNoticiasOrdenCronologico()[0]);
	}
	
	@Test
	void testAddNoticiaRepetida() {
		Boletin b = new Boletin();
		b.addNoticia(noticia);
		assertThrows(IllegalArgumentException.class, () -> {
			b.addNoticia(noticia);
		});
	}
	
	@Test
	void testGetFechaNoticiasMasRecientes() {
		String titular2 = "Los apoyos de Sánchez para su investidura";
		String url2 = "https://www.eldiarioes.com/es/politica/20191113/gobierno-coalicion-psoe-podemos-apoyos-sanchez-investidura-7734615";
		String fuente2 = "eldiarioes";
		LocalDate fecha2 = LocalDate.of(2019, 11, 19);
		Categoria categoria2 = Categoria.NACIONAL;
		Noticia noticia2 = new Noticia(titular2, fecha2, fuente2, url2, categoria2);
		
		Boletin b = new Boletin();
		b.addNoticia(noticia);
		b.addNoticia(noticia2);
		
		assertEquals(fecha2, b.getFechaNoticiasMasRecientes());
	}
	
	@Test
	void testGetFechaNoticiasMasRecientesSinNoticias() {
		Boletin b = new Boletin();
		
		assertThrows(IllegalStateException.class, () -> {
			b.getFechaNoticiasMasRecientes();
		});
	}
	
	@Test
	void testGetFechaNoticiasMasAntiguas() {
		String titular2 = "Los apoyos de Sánchez para su investidura";
		String url2 = "https://www.eldiarioes.com/es/politica/20191113/gobierno-coalicion-psoe-podemos-apoyos-sanchez-investidura-7734615";
		String fuente2 = "eldiarioes";
		LocalDate fecha2 = LocalDate.of(2019, 11, 19);
		Categoria categoria2 = Categoria.NACIONAL;
		Noticia noticia2 = new Noticia(titular2, fecha2, fuente2, url2, categoria2);
		
		Boletin b = new Boletin();
		b.addNoticia(noticia);
		b.addNoticia(noticia2);
		
		assertEquals(LocalDate.of(2019, 11, 15), b.getFechaNoticiasMasAntiguas());
	}
	
	@Test
	void testGetFechaNoticiasMasAntiguasSinNoticias() {
		Boletin b = new Boletin();
		
		assertThrows(IllegalStateException.class, () -> {
			b.getFechaNoticiasMasAntiguas();
		});
	}
	
	@Test
	void testGetNoticiasOrdenCronologico() {
		String titular = "Los apoyos de Sánchez para su investidura";
		String url = "https://www.elperiodico.com/es/politica/20191113/gobierno-coalicion-psoe-podemos-apoyos-sanchez-investidura-7734615";
		String fuente = "elperiodico";
		LocalDate fecha = LocalDate.of(2019, 11, 19);
		Categoria categoria = Categoria.NACIONAL;
		Noticia noticia = new Noticia(titular, fecha, fuente, url, categoria);
		
		String titular2 = "Los apoyos de Sánchez para su investidura";
		String url2 = "https://www.eldiarioes.com/es/politica/20191113/gobierno-coalicion-psoe-podemos-apoyos-sanchez-investidura-7734615";
		String fuente2 = "eldiarioes";
		LocalDate fecha2 = LocalDate.of(2019, 11, 15);
		Categoria categoria2 = Categoria.NACIONAL;
		Noticia noticia2 = new Noticia(titular2, fecha2, fuente2, url2, categoria2);
		
		Boletin b = new Boletin();
		b.addNoticia(noticia);
		b.addNoticia(noticia2);
		
		Noticia[] noticiasOrdenCronologico = {noticia2, noticia};
		
		assertArrayEquals(noticiasOrdenCronologico, b.getNoticiasOrdenCronologico());
	}
	
	@Test
	void testGetNoticiasOrdenCronologicoBoletinVacio() {
		Boletin b = new Boletin();
		
		assertThrows(IllegalStateException.class, () -> {
			b.getNoticiasOrdenCronologico();
		});
	}

	@Test
	void testGetNoticiasOrdenCategoria() {
		String titular2 = "Los apoyos de Sánchez para su investidura";
		String url2 = "https://www.eldiarioes.com/es/politica/20191113/gobierno-coalicion-psoe-podemos-apoyos-sanchez-investidura-7734615";
		String fuente2 = "eldiarioes";
		LocalDate fecha2 = LocalDate.of(2019, 11, 19);
		Categoria categoria2 = Categoria.DEPORTE;
		Noticia noticia2 = new Noticia(titular2, fecha2, fuente2, url2, categoria2);
		
		Boletin b = new Boletin();
		b.addNoticia(noticia2);
		b.addNoticia(noticia);
		
		Noticia[] noticiasOrdenCategoria = {noticia, noticia2};
		
		assertArrayEquals(noticiasOrdenCategoria, b.getNoticiasOrdenCategoria());
	}
	
	@Test
	void testGetNoticiasOrdenCategoriaBoletinVacio() {
		Boletin b = new Boletin();
		
		assertThrows(IllegalStateException.class, () -> {
			b.getNoticiasOrdenCategoria();
		});
	}
	
	@Test
	void testGetNoticiasSimilares() {
		String titular2 = "Los apoyos de Sánchez para su investidura";
		String url2 = "https://www.eldiarioes.com/es/politica/20191113/gobierno-coalicion-psoe-podemos-apoyos-sanchez-investidura-7734615";
		String fuente2 = "eldiarioes";
		LocalDate fecha2 = LocalDate.of(2019, 11, 19);
		Categoria categoria2 = Categoria.NACIONAL;
		Noticia noticia2 = new Noticia(titular2, fecha2, fuente2, url2, categoria2);
		
		String titular3 = "Los apoyos de Sánchez para su investidura";
		String url3 = "https://www.elconfidencial.com/es/politica/20191113/gobierno-coalicion-psoe-podemos-apoyos-sanchez-investidura-7734615";
		String fuente3 = "confidencial";
		LocalDate fecha3 = LocalDate.of(2019, 11, 17);
		Categoria categoria3 = Categoria.NACIONAL;
		Noticia noticia3 = new Noticia(titular3, fecha3, fuente3, url3, categoria3);
		
		Boletin b = new Boletin();
		b.addNoticia(noticia);
		b.addNoticia(noticia2);
		
		Noticia[] noticiasSimilares = {noticia, noticia2};
		
		assertArrayEquals(noticiasSimilares, b.getNoticiasSimilares(noticia3));
	}
	
	@Test
	void testGetNoticiasSimilaresNoticiaNull() {
		Boletin b = new Boletin();
		
		assertThrows(IllegalArgumentException.class, () ->{
			b.getNoticiasSimilares(null);
		});
	}
	
	@Test
	void testGetBoletinPorFecha() {
		String titular2 = "Los apoyos de Sánchez para su investidura";
		String url2 = "https://www.eldiarioes.com/es/politica/20191113/gobierno-coalicion-psoe-podemos-apoyos-sanchez-investidura-7734615";
		String fuente2 = "eldiarioes";
		LocalDate fecha2 = LocalDate.of(2019, 11, 19);
		Categoria categoria2 = Categoria.NACIONAL;
		Noticia noticia2 = new Noticia(titular2, fecha2, fuente2, url2, categoria2);
		
		String titular3 = "Los apoyos de Sánchez para su investidura";
		String url3 = "https://www.elconfidencial.com/es/politica/20191113/gobierno-coalicion-psoe-podemos-apoyos-sanchez-investidura-7734615";
		String fuente3 = "confidencial";
		LocalDate fecha3 = LocalDate.of(2019, 11, 19);
		Categoria categoria3 = Categoria.NACIONAL;
		Noticia noticia3 = new Noticia(titular3, fecha3, fuente3, url3, categoria3);
		
		Boletin b = new Boletin();
		b.addNoticia(noticia);
		b.addNoticia(noticia2);
		b.addNoticia(noticia3);
		
		Boletin boletinPorFechas = new Boletin();
		boletinPorFechas.addNoticia(noticia2);
		boletinPorFechas.addNoticia(noticia3);
		
		assertEquals(boletinPorFechas, b.getBoletinPorFecha(fecha2));
	}
	
	@Test
	void testBoletinPorFechaNull() {
		Boletin b = new Boletin();
		
		assertThrows(IllegalArgumentException.class, () -> {
			b.getBoletinPorFecha(null);
		});
	}

	@Test
	void testGetBoletinIntervaloFechas() {	
		String titular2 = "Los apoyos de Sánchez para su investidura";
		String url2 = "https://www.eldiarioes.com/es/politica/20191113/gobierno-coalicion-psoe-podemos-apoyos-sanchez-investidura-7734615";
		String fuente2 = "eldiarioes";
		LocalDate fecha2 = LocalDate.of(2019, 11, 17);
		Categoria categoria2 = Categoria.NACIONAL;
		Noticia noticia2 = new Noticia(titular2, fecha2, fuente2, url2, categoria2);
		
		String titular3 = "Los apoyos de Sánchez para su investidura";
		String url3 = "https://www.elconfidencial.com/es/politica/20191113/gobierno-coalicion-psoe-podemos-apoyos-sanchez-investidura-7734615";
		String fuente3 = "confidencial";
		LocalDate fecha3 = LocalDate.of(2019, 11, 19);
		Categoria categoria3 = Categoria.NACIONAL;
		Noticia noticia3 = new Noticia(titular3, fecha3, fuente3, url3, categoria3);
		
		LocalDate fecha4 = LocalDate.of(2019, 11, 14);
		Noticia noticia4 = new Noticia(titular3, fecha4, fuente3, url3, categoria3);
		
		LocalDate fecha5 = LocalDate.of(2019, 11, 20);
		Noticia noticia5 = new Noticia(titular3, fecha5, fuente3, url3, categoria3);
		
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
		
		assertEquals(boletinIntervalico, b.getBoletinIntervaloFechas(LocalDate.of(2019, 11, 15), fecha3));
	}

	@Test
	void testGetBoletinIntervaloFechasNull() {
		Boletin b = new Boletin();
		
		assertThrows(IllegalArgumentException.class, () -> {
			b.getBoletinIntervaloFechas(null, null);
		});
	}
	
	@Test
	void testGetBoletinIntervaloFechasSegundaFechaAnterior() {
		Boletin b = new Boletin();
		
		assertThrows(IllegalArgumentException.class, () -> {
			b.getBoletinIntervaloFechas(LocalDate.of(2019, 11, 29), LocalDate.of(2019, 11, 28));
		});
	}
	
	@Test
	void testGetBoletinPorCategoria() {
		String titular2 = "Los apoyos de Sánchez para su investidura";
		String url2 = "https://www.eldiarioes.com/es/politica/20191113/gobierno-coalicion-psoe-podemos-apoyos-sanchez-investidura-7734615";
		String fuente2 = "eldiarioes";
		LocalDate fecha2 = LocalDate.of(2019, 11, 17);
		Categoria categoria2 = Categoria.NACIONAL;
		Noticia noticia2 = new Noticia(titular2, fecha2, fuente2, url2, categoria2);
		
		String titular3 = "Los apoyos de Sánchez para su investidura";
		String url3 = "https://www.elconfidencial.com/es/politica/20191113/gobierno-coalicion-psoe-podemos-apoyos-sanchez-investidura-7734615";
		String fuente3 = "confidencial";
		LocalDate fecha3 = LocalDate.of(2019, 11, 19);
		Categoria categoria3 = Categoria.DEPORTE;
		Noticia noticia3 = new Noticia(titular3, fecha3, fuente3, url3, categoria3);
		
		Boletin b = new Boletin();
		b.addNoticia(noticia);
		b.addNoticia(noticia2);
		b.addNoticia(noticia3);
		
		Boletin bCategoria = new Boletin();
		bCategoria.addNoticia(noticia);
		bCategoria.addNoticia(noticia2);
		
		assertEquals(bCategoria, b.getBoletinPorCategoria(Categoria.NACIONAL));
	}

	@Test
	void testGetBoletinPorCategoriaNull() {
		Boletin b = new Boletin();
		
		assertThrows(IllegalArgumentException.class, () -> {
			b.getBoletinPorCategoria(null);
		});
	}
	
	@Test
	void testGetBoletinPorFechaYCategoria() {
		String titular2 = "Los apoyos de Sánchez para su investidura";
		String url2 = "https://www.eldiarioes.com/es/politica/20191113/gobierno-coalicion-psoe-podemos-apoyos-sanchez-investidura-7734615";
		String fuente2 = "eldiarioes";
		LocalDate fecha2 = LocalDate.of(2019, 11, 17);
		Categoria categoria2 = Categoria.ECONOMIA;
		Noticia noticia2 = new Noticia(titular2, fecha2, fuente2, url2, categoria2);
		
		String titular3 = "Los apoyos de Sánchez para su investidura";
		String url3 = "https://www.elconfidencial.com/es/politica/20191113/gobierno-coalicion-psoe-podemos-apoyos-sanchez-investidura-7734615";
		String fuente3 = "confidencial";
		LocalDate fecha3 = LocalDate.of(2019, 11, 17);
		Categoria categoria3 = Categoria.ECONOMIA;
		Noticia noticia3 = new Noticia(titular3, fecha3, fuente3, url3, categoria3);
		
		Boletin b = new Boletin();
		b.addNoticia(noticia);
		b.addNoticia(noticia2);
		b.addNoticia(noticia3);
		
		Boletin b2 = new Boletin();
		b2.addNoticia(noticia2);
		b2.addNoticia(noticia3);
		
		assertEquals(b2, b.getBoletinPorFechaYCategoria(fecha2, Categoria.ECONOMIA));
	}
	
	@Test
	void testGetBoletinPorFechaNullYCategoriaNull() {
		Boletin b = new Boletin();
		
		assertThrows(IllegalArgumentException.class, () -> {
			b.getBoletinPorFechaYCategoria(null, null);
		});
	}
	
	@Test
	void testGetBoletinPorIntervaloYCategoria() {	
		String titular2 = "Los apoyos de Sánchez para su investidura";
		String url2 = "https://www.eldiarioes.com/es/politica/20191113/gobierno-coalicion-psoe-podemos-apoyos-sanchez-investidura-7734615";
		String fuente2 = "eldiarioes";
		LocalDate fecha2 = LocalDate.of(2019, 11, 17);
		Categoria categoria2 = Categoria.ECONOMIA;
		Noticia noticia2 = new Noticia(titular2, fecha2, fuente2, url2, categoria2);
		
		String titular3 = "Los apoyos de Sánchez para su investidura";
		String url3 = "https://www.elconfidencial.com/es/politica/20191113/gobierno-coalicion-psoe-podemos-apoyos-sanchez-investidura-7734615";
		String fuente3 = "confidencial";
		LocalDate fecha3 = LocalDate.of(2019, 11, 19);
		Categoria categoria3 = Categoria.ECONOMIA;
		Noticia noticia3 = new Noticia(titular3, fecha3, fuente3, url3, categoria3);
		
		Boletin b = new Boletin();
		b.addNoticia(noticia);
		b.addNoticia(noticia2);
		b.addNoticia(noticia3);
		
		Boletin b2 = new Boletin();
		b2.addNoticia(noticia2);
		b2.addNoticia(noticia3);
		
		assertEquals(b2, b.getBoletinPorIntervaloYCategoria(fecha2, fecha3, Categoria.ECONOMIA));
	}
	
	@Test
	void testGetBoletinPorIntervaloNullYCategoriaNull() {
		Boletin b = new Boletin();
		
		assertThrows(IllegalArgumentException.class, () -> {
			b.getBoletinPorIntervaloYCategoria(null, null, null);
		});
	}
	
	@Test
	void testGetBoletinPorIntervaloYCategoriaSegundaFechaAnterior() {
		Boletin b = new Boletin();
		
		assertThrows(IllegalArgumentException.class, () -> {
			b.getBoletinPorIntervaloYCategoria(LocalDate.of(2019, 11, 29), LocalDate.of(2019, 11, 28), Categoria.DEPORTE);
		});
	}
	
	@Test
	void testEquals() {
		Boletin b = new Boletin();
		Boletin b2 = new Boletin();
		
		assertTrue(b.equals(b2));
		
		b.addNoticia(noticia);
		
		assertFalse(b.equals(b2));
		
		b2.addNoticia(noticia);

		assertTrue(b.equals(b2));
		
		assertFalse(b.equals(null));
	}
	
	@Test
	void testGetPorcentajeSimilitud() {
		Boletin b = new Boletin();
		Boletin b2 = new Boletin();
		
		String titular2 = "Los apoyos de Sánchez para su investidura";
		String url2 = "https://www.eldiarioes.com/es/politica/20191113/gobierno-coalicion-psoe-podemos-apoyos-sanchez-investidura-7734615";
		String fuente2 = "eldiarioes";
		LocalDate fecha2 = LocalDate.of(2019, 11, 19);
		Categoria categoria2 = Categoria.ECONOMIA;
		Noticia noticia2 = new Noticia(titular2, fecha2, fuente2, url2, categoria2);
		
		String titular3 = "Los apoyos de Sánchez para su investidura";
		String url3 = "https://www.elconfidencial.com/es/politica/20191113/gobierno-coalicion-psoe-podemos-apoyos-sanchez-investidura-7734615";
		String fuente3 = "confidencial";
		LocalDate fecha3 = LocalDate.of(2019, 11, 19);
		Categoria categoria3 = Categoria.ECONOMIA;
		Noticia noticia3 = new Noticia(titular3, fecha3, fuente3, url3, categoria3);
		
		b.addNoticia(noticia);
		b.addNoticia(noticia2);
		b.addNoticia(noticia3);
		
		b2.addNoticia(noticia2);
		b2.addNoticia(noticia3);
		
		assertEquals(66.6666, b.getPorcentajeSimilitud(b2), 0.0001);
	}
	
	@Test
	void testGetPorcentajeSimilitudBoletinNull() {
		Boletin b = new Boletin();
		
		assertThrows(IllegalArgumentException.class, () -> {
			b.getPorcentajeSimilitud(null);
		});
	}
}
