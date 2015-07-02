package de.hrw.swep.biblio.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import de.hrw.swep.biblio.persistence.DAO;
import de.hrw.swep.biblio.service.benutzer.Benutzer;
import de.hrw.swep.biblio.service.gegenstaende.Buch;

/**
 * Testet die Bibliotheksklasse mit der echten Datenbank.
 * 
 * @author M. Friedrich
 *
 */
public class BibliothekIntegrationsTest {

	Bibliothek bib;
	DAO dao;

	/**
	 * Setup
	 */
	@Before
	public void setup() {
		bib = new Bibliothek();
		dao = new DAO();
		bib.setDb(dao);
	}

	/**
	 * Testet, ob ein Buch fuer einen gegebenen Titel gefunden wird.
	 */
	@Test
	public void testSucheBuchNachTitel() {
		Set<Buch> bookset = bib.sucheBuchNachTitel("Klatsch");
		assertEquals(1, bookset.size());
		for (Buch b : bookset) {
			assertEquals("Klatsch", b.getTitel());
			assertEquals("Malte Mohn", b.getAutor());
			assertEquals("de.hrw.swep.biblio.service.gegenstaende.Ausgeliehen",
					b.getState().getClass().getName());
		}
	}

	/**
	 * Testet, ob ein Buch fuer einen gegebenen Autor gefunden wird.
	 */
	@Test
	public void testSucheBuchNachAutor() {
		Set<Buch> bookset = bib.sucheBuchNachAutor("Malte Mohn");
		assertEquals(1, bookset.size());
		for (Buch b : bookset) {
			assertEquals("Klatsch", b.getTitel());
			assertEquals("Malte Mohn", b.getAutor());
			assertEquals("de.hrw.swep.biblio.service.gegenstaende.Ausgeliehen", b
					.getState().getClass().getName());
		}
	}

	/**
	 * Testet, ob ein Benutzer mit einem gegebenen Namen gefunden wird.
	 */
	@Test
	public void testSucheBenutzerNachName() {
		Set<Benutzer> bset = bib.sucheBenutzerNachName("Adalbert Alt");
		assertEquals(1, bset.size());
		for (Benutzer b : bset) {
			assertEquals(1, b.getId());
			assertEquals("de.hrw.swep.biblio.service.benutzer.Normal", b
					.getStatus().getClass().getName());
		}
	}

	/**
	 * Testet, ob ein Benutzer mit einer gegebenen ID gefunden wird.
	 */
	@Test
	public void testSucheBenutzerNachId() {
		Benutzer b = bib.sucheBenutzerNachId(1);
		assertEquals("Adalbert Alt", b.getName());
		assertEquals("de.hrw.swep.biblio.service.benutzer.Normal", b
				.getStatus().getClass().getName());
	}
}
