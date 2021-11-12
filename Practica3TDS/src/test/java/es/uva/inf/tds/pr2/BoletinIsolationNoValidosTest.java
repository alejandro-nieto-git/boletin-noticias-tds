package es.uva.inf.tds.pr2;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import org.easymock.EasyMock;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Tests de aislamiento no validos de Boletin
 * 
 * @author aleniet
 * @author davmele
 */
@Tag("Boletin")
@Tag("Isolation")
class BoletinIsolationNoValidosTest {

	@Test
	void testInicializacionInvalidaPorNoticiaNull() {
		Noticia noticia1 = EasyMock.createMock(Noticia.class);
		Noticia noticia2 = EasyMock.createMock(Noticia.class);
		
		Noticia[] noticias = {noticia1, noticia2, null};
		
		assertThrows(IllegalArgumentException.class, () -> {
			@SuppressWarnings("unused")
			Boletin b = new Boletin(noticias);
		});
	}
	
	@Test
	void testInicializacionInvalidaPorNoticiaRepetida() {
		Noticia noticia1 = EasyMock.createMock(Noticia.class);
		
		EasyMock.replay(noticia1);

		Noticia[] noticias = {noticia1, noticia1};
		
		assertThrows(IllegalArgumentException.class, () -> {
			@SuppressWarnings("unused")
			Boletin b = new Boletin(noticias);
		});
		
		EasyMock.verify(noticia1);
	}
	
	@Test
	void testAddNoticiaNull() {
		Boletin b = new Boletin();
		
		assertThrows(IllegalArgumentException.class, () -> {
			b.addNoticia(null);
		});
	}
	
	@Test
	void testGetBoletinIntervaloPrimeraFechaNullSegundaBien() {
		Boletin b = new Boletin();
		
		assertThrows(IllegalArgumentException.class, () -> {
			b.getBoletinIntervaloFechas(null, LocalDate.of(2019, 12, 1));
		});
	}
	
	@Test
	void testGetBoletinIntervaloPrimeraFechaBienSegundaNull() {
		Boletin b = new Boletin();
		
		assertThrows(IllegalArgumentException.class, () -> {
			b.getBoletinIntervaloFechas(LocalDate.of(2019, 12, 1), null);
		});
	}
	
	@Test
	void testGetBoletinPorFechaNullYCategoria() {
		Boletin b = new Boletin();
		
		assertThrows(IllegalArgumentException.class, () -> {
			b.getBoletinPorFechaYCategoria(null, Categoria.CULTURA);
		});
	}
	
	@Test
	void testGetBoletinPorFechaYCategoriaNull() {
		Boletin b = new Boletin();
		
		assertThrows(IllegalArgumentException.class, () -> {
			b.getBoletinPorFechaYCategoria(LocalDate.of(2020, 1, 1), null);
		});
	}
	
	@Test
	void testGetBoletinPorIntervaloYCategoriaNull() {
		Boletin b = new Boletin();
		
		assertThrows(IllegalArgumentException.class, () -> {
			b.getBoletinPorIntervaloYCategoria(LocalDate.of(2019, 11, 30), LocalDate.of(2019, 12, 1), null);
		});
	}
	
	@Test
	void testGetBoletinPorIntervaloNullYCategoria() {
		Boletin b = new Boletin();
		
		assertThrows(IllegalArgumentException.class, () -> {
			b.getBoletinPorIntervaloYCategoria(null, null, Categoria.SOCIEDAD);
		});
	}
	
	@Test
	void testInicializacionInvalidaPorNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			@SuppressWarnings("unused")
			Boletin b = new Boletin(null);
		});
	}
	
	@Test
	void testAddNoticiaRepetida() {
		Noticia noticia = EasyMock.createMock(Noticia.class);
		EasyMock.replay(noticia);
		
		Boletin b = new Boletin();
		b.addNoticia(noticia);
		assertThrows(IllegalArgumentException.class, () -> {
			b.addNoticia(noticia);
		});
		
		EasyMock.verify(noticia);
	}

	@Test
	void testGetFechaNoticiasMasRecientesSinNoticias() {
		Boletin b = new Boletin();
		
		assertThrows(IllegalStateException.class, () -> {
			b.getFechaNoticiasMasRecientes();
		});
	}
	
	@Test
	void testGetFechaNoticiasMasAntiguasSinNoticias() {
		Boletin b = new Boletin();
		
		assertThrows(IllegalStateException.class, () -> {
			b.getFechaNoticiasMasAntiguas();
		});
	}
	
	@Test
	void testGetNoticiasOrdenCronologicoBoletinVacio() {
		Boletin b = new Boletin();
		
		assertThrows(IllegalStateException.class, () -> {
			b.getNoticiasOrdenCronologico();
		});
	}
	
	@Test
	void testGetNoticiasOrdenCategoriaBoletinVacio() {
		Boletin b = new Boletin();
		
		assertThrows(IllegalStateException.class, () -> {
			b.getNoticiasOrdenCategoria();
		});
	}
	
	@Test
	void testGetNoticiasSimilaresNoticiaNull() {		
		Boletin b = new Boletin();
		
		assertThrows(IllegalArgumentException.class, () ->{
			b.getNoticiasSimilares(null);
		});
	}
	
	@Test
	void testBoletinPorFechaNull() {
		Boletin b = new Boletin();
		
		assertThrows(IllegalArgumentException.class, () -> {
			b.getBoletinPorFecha(null);
		});
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
	void testGetBoletinPorCategoriaNull() {
		Boletin b = new Boletin();
		
		assertThrows(IllegalArgumentException.class, () -> {
			b.getBoletinPorCategoria(null);
		});
	}
	
	@Test
	void testGetBoletinPorFechaNullYCategoriaNull() {
		Boletin b = new Boletin();
		
		assertThrows(IllegalArgumentException.class, () -> {
			b.getBoletinPorFechaYCategoria(null, null);
		});
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
	void testGetPorcentajeSimilitudBoletinNull() {
		Boletin b = new Boletin();
		
		assertThrows(IllegalArgumentException.class, () -> {
			b.getPorcentajeSimilitud(null);
		});
	}
}
