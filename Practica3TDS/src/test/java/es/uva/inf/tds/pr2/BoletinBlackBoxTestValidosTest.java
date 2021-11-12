package es.uva.inf.tds.pr2;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Tests de caja negra validos de Boletin
 * 
 * @author aleniet
 * @author davmele
 */
@Tag("Boletin")
@Tag("BlackBox")
class BoletinBlackBoxTestValidosTest {
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
	void testGetNoticiasOrdenCategoriaMismaCategoria() {
		String titular2 = "Los apoyos de Sánchez para su investidura";
		String url2 = "https://www.eldiarioes.com/es/politica/20191113/gobierno-coalicion-psoe-podemos-apoyos-sanchez-investidura-7734615";
		String fuente2 = "eldiarioes";
		LocalDate fecha2 = LocalDate.of(2019, 11, 19);
		Categoria categoria2 = Categoria.NACIONAL;
		Noticia noticia2 = new Noticia(titular2, fecha2, fuente2, url2, categoria2);
		
		Categoria categoria3 = Categoria.DEPORTE;
		Noticia noticia3 = new Noticia(titular2, fecha2, fuente2, url2, categoria3);
		
		Boletin b = new Boletin();
		b.addNoticia(noticia2);
		b.addNoticia(noticia);
		b.addNoticia(noticia3);
		
		Noticia[] noticiasOrdenCategoria = {noticia, noticia2, noticia3};
		
		assertArrayEquals(noticiasOrdenCategoria, b.getNoticiasOrdenCategoria());
	}
	
	@Test
	void testGetNoticiasOrdenCronologicoFechasCoincidentes() {
		String titular2 = "Los apoyos de Sánchez para su investidura";
		String url2 = "https://www.eldiarioes.com/es/politica/20191113/gobierno-coalicion-psoe-podemos-apoyos-sanchez-investidura-7734615";
		String fuente2 = "eldiarioes";
		LocalDate fecha2 = LocalDate.of(2019, 11, 15);
		Categoria categoria2 = Categoria.NACIONAL;
		Noticia noticia2 = new Noticia(titular2, fecha2, fuente2, url2, categoria2);
		
		Boletin b = new Boletin();
		b.addNoticia(noticia);
		b.addNoticia(noticia2);
		
		Noticia[] noticiasOrdenCronologico = {noticia, noticia2};
		
		assertArrayEquals(noticiasOrdenCronologico, b.getNoticiasOrdenCronologico());
	}
	
	@Test
	void testGetNoticiasSimilaresNoHayNingunaSimilarExtremos() {
		String titular2 = "Los apoyos de Sánchez para su investidura";
		String url2 = "https://www.eldiarioes.com/es/politica/20191113/gobierno-coalicion-psoe-podemos-apoyos-sanchez-investidura-7734615";
		String fuente2 = "eldiarioes";
		LocalDate fecha2 = LocalDate.of(2019, 11, 9);
		Categoria categoria2 = Categoria.NACIONAL;
		Noticia noticia2 = new Noticia(titular2, fecha2, fuente2, url2, categoria2);
		
		String titular3 = "Los apoyos de Sánchez para su investidura";
		String url3 = "https://www.elconfidencial.com/es/politica/20191113/gobierno-coalicion-psoe-podemos-apoyos-sanchez-investidura-7734615";
		String fuente3 = "confidencial";
		LocalDate fecha3 = LocalDate.of(2019, 3, 12);
		Categoria categoria3 = Categoria.NACIONAL;
		Noticia noticia3 = new Noticia(titular3, fecha3, fuente3, url3, categoria3);
		
		Boletin b = new Boletin();
		b.addNoticia(noticia);
		b.addNoticia(noticia2);
		
		assertNull(b.getNoticiasSimilares(noticia3));
	}
	
	@Test
	void testGetBoletinPorFechaNingunaNoticiaEnLaFecha() {
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
		
		assertEquals(boletinPorFechas, b.getBoletinPorFecha(LocalDate.of(1995, 5, 12)));
	}
	
	@Test
	void testGetBoletinIntervaloFechasNingunaNoticiaEntreFechas() {
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
		
		Boletin b = new Boletin();
		b.addNoticia(noticia);
		b.addNoticia(noticia2);
		b.addNoticia(noticia3);
		
		Boletin boletinIntervalico = new Boletin();
		
		assertEquals(boletinIntervalico, b.getBoletinIntervaloFechas(LocalDate.of(1999, 11, 10), LocalDate.of(1999, 11, 18)));
	}
	
	@Test
	void testGetBoletinIntervaloFechasNingunaNoticiaEntreFechasPorBoletinVacio() {
		Boletin b = new Boletin();
		
		Boletin boletinIntervalico = new Boletin();
		
		assertEquals(boletinIntervalico, b.getBoletinIntervaloFechas(LocalDate.of(1999, 11, 10), LocalDate.of(1999, 11, 18)));
	}
	
	@Test
	void testGetBoletinPorCategoriaNingunaNoticiaDeLaCategoria() {
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
		
		assertEquals(bCategoria, b.getBoletinPorCategoria(Categoria.ECONOMIA));
	}
	
	@Test
	void testGetBoletinPorCategoriaNingunaNoticiaDeLaCategoriaPorBoletinVacio() {
		Boletin b = new Boletin();
		
		Boletin bCategoria = new Boletin();
		
		assertEquals(bCategoria, b.getBoletinPorCategoria(Categoria.SOCIEDAD));
	}
	
	@Test
	void testGetBoletinPorFechaYCategoriaNingunaNoticiaPorCategoria() {
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
		
		assertEquals(b2, b.getBoletinPorFechaYCategoria(fecha2, Categoria.CULTURA));
	}
	
	@Test
	void testGetBoletinPorFechaYCategoriaNingunaNoticiaPorFecha() {
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
		
		assertEquals(b2, b.getBoletinPorFechaYCategoria(LocalDate.of(1875, 1, 5), Categoria.ECONOMIA));
	}
	
	@Test
	void testGetBoletinPorFechaYCategoriaNingunaNoticiaPorBoletinVacio() {
		Boletin b = new Boletin();
		
		Boletin b2 = new Boletin();
		
		assertEquals(b2, b.getBoletinPorFechaYCategoria(LocalDate.of(1875, 1, 5), Categoria.ECONOMIA));
	}
	
	@Test
	void testGetBoletinPorIntervaloYCategoriaNingunaNoticiaPorCategoria() {
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
		
		assertEquals(b2, b.getBoletinPorIntervaloYCategoria(fecha2, fecha3, Categoria.INTERNACIONAL));
	}
	
	@Test
	void testGetBoletinPorIntervaloYCategoriaNingunaNoticiaPorIntervalo() {
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
		
		assertEquals(b2, b.getBoletinPorIntervaloYCategoria(LocalDate.of(1990, 11, 30), LocalDate.of(1994, 12, 1), Categoria.ECONOMIA));
	}
	
	@Test
	void testGetBoletinPorIntervaloYCategoriaNingunaNoticiaPorBoletinVacio() {
		Boletin b = new Boletin();
		
		Boletin b2 = new Boletin();
		
		assertEquals(b2, b.getBoletinPorIntervaloYCategoria(LocalDate.of(1990, 11, 30), LocalDate.of(1994, 12, 1), Categoria.ECONOMIA));
	}
	
	@Test
	void testGetNoticiasSimilaresNoHayNingunaSimilarPorBoletinVacio() {
		Boletin b = new Boletin();
		
		assertNull(b.getNoticiasSimilares(noticia));
	}
	
	@Test
	void testGetPorcentajeSimilitudNingunaSimilarOTodasSimilares() {
		Boletin b = new Boletin();
		Boletin b2 = new Boletin();
		
		assertEquals(0, b.getPorcentajeSimilitud(b2));
		
		String titular2 = "Los apoyos de Sánchez para su investidura";
		String url2 = "https://www.eldiarioes.com/es/politica/20191113/gobierno-coalicion-psoe-podemos-apoyos-sanchez-investidura-7734615";
		String fuente2 = "eldiarioes";
		LocalDate fecha2 = LocalDate.of(2019, 11, 17);
		Categoria categoria2 = Categoria.ECONOMIA;
		Noticia noticia2 = new Noticia(titular2, fecha2, fuente2, url2, categoria2);
		
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
	}
}
