package es.uva.inf.tds.pr2;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Tests de caja blanca de Boletin
 * 
 * @author aleniet
 * @author davmele
 */
@Tag("WhiteBox")
@Tag("Boletin")
class BoletinWhiteBoxTest {

	@Test
	void testGetBoletinPorIntervaloYCategoriaPrimeraFechaNull() {
		Boletin b = new Boletin();
		
		assertThrows(IllegalArgumentException.class, () -> {
			b.getBoletinPorIntervaloYCategoria(null, LocalDate.of(2019, 12, 19), Categoria.DEPORTE);
		});
	}
	
	@Test
	void testGetBoletinPorIntervaloYCategoriaSegundaFechaNull() {
		Boletin b = new Boletin();
		
		assertThrows(IllegalArgumentException.class, () -> {
			b.getBoletinPorIntervaloYCategoria(LocalDate.of(2019, 12, 19), null, Categoria.CULTURA);
		});
	}
}