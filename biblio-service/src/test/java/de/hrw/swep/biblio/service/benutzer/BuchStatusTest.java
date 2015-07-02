package de.hrw.swep.biblio.service.benutzer;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.hrw.swep.biblio.service.IllegalStateTransition;
import de.hrw.swep.biblio.service.gegenstaende.Ausgeliehen;
import de.hrw.swep.biblio.service.gegenstaende.Buch;
import de.hrw.swep.biblio.service.gegenstaende.Frei;
import de.hrw.swep.biblio.service.gegenstaende.Verloren;

/**
 * Testklasse fuer Statusuebergaenge des Bibliothek-Buecher
 * 
 * @author J. Riedel
 *
 */

public class BuchStatusTest {

	/**
	 * Ein Ausgeliehenes Buch wird ausgeliehen: 
	 * Erwartet:IllegalStateTransition;
	 */
	@Test(expected = IllegalStateTransition.class)
	public void ausgeliehenausleihen() {
		Benutzer testuser = new Benutzer(1, "Hüüüüüllomann");
		Buch b = new Buch("Hobbit", "Tolkien");
		b.setState(new Ausgeliehen(b));
		b.ausleihen(testuser);
	}

	/**
	 * Ein Freies Buch wird zurückgegeben: 
	 * Erwartet: IllegalStateTransition;
	 */
	@Test(expected = IllegalStateTransition.class)
	public void freizurueckgeben() {
		Buch b = new Buch("Hobbit1", "Tolkien1");
		b.setState(new Frei(b));
		b.zurueckgeben();
	}

	/**
	 * Ein Verlorenes Buch wird ausgeliehen: 
	 * Erwartet: IllegalStateTransition;
	 */
	@Test(expected = IllegalStateTransition.class)
	public void verlorenAusleihen() {
		Buch b = new Buch("Hobbit2", "Tolkien2");
		b.setState(new Verloren(b));
		b.verloren();
	}

	/**
	 * Ein Verlorenes Buch wird verloren: 
	 * Erwartet: IllegalStateTransition;
	 */
	@Test(expected = IllegalStateTransition.class)
	public void verlorenVerlieren() {
		Buch b = new Buch("Hobbit3", "Tolkien3");
		b.setState(new Verloren(b));
		b.verloren();
	}
		
		/**
		 * Ein Verlorenes Buch wird uzurückgegeben: 
		 * Teste Übergang verloren -> frei
		 */
		@Test
		public void verlorenZurueck() {
			Buch b = new Buch("Hobbit3", "Tolkien3");
			b.setState(new Verloren(b));
			b.zurueckgeben();
			
			assertEquals("de.hrw.swep.biblio.service.gegenstaende.Frei", b.getState().getClass().getName());
	}


}
