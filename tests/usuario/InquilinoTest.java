package usuario;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sitioWeb.SitioWeb;

class InquilinoTest {

	private Inquilino juan;
	private SitioWeb sitio;
	
	@BeforeEach
	void setUp() throws Exception {
		juan = new Inquilino("Juan Perez" , "juan@gmail.com", "234234234");
		sitio = new SitioWeb();
	}

	@Test
	void testConsolidarReserva() {
		fail("Not yet implemented");
	}

}
