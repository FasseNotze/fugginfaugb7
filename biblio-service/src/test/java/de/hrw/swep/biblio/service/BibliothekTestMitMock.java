package de.hrw.swep.biblio.service;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import static org.mockito.Mockito.*;
import de.hrw.swep.biblio.persistence.DBInterface;
import de.hrw.swep.biblio.persistence.dto.BenutzerDTO;
import de.hrw.swep.biblio.persistence.dto.BuchDTO;
import de.hrw.swep.biblio.service.benutzer.Benutzer;
import de.hrw.swep.biblio.service.gegenstaende.Buch;

/**
 * Testet die Bibliotheks-Klasse mit einem Mock-Objekt
 * 
 * @author Sandra
 *
 */
public class BibliothekTestMitMock {

	Bibliothek bib;

	/**
	 * Mocksetup
	 */
	@Before
	public void setup() {
		DBInterface dbi = Mockito.mock(DBInterface.class);

		BenutzerDTO user = new BenutzerDTO(1, "Capt'n Joey", "Normal");
		Set<BenutzerDTO> userset = new HashSet<BenutzerDTO>();
		userset.add(user);

		BuchDTO book = new BuchDTO(1, "Tolkien", "Hobbit", "Frei");
		Set<BuchDTO> bookset = new HashSet<BuchDTO>();
		bookset.add(book);

		when(dbi.getBenutzerById(1)).thenReturn(user);
		when(dbi.getBenutzerByName("Capt'n Joey")).thenReturn(userset);
		when(dbi.getBuchByAutor("Tolkien")).thenReturn(bookset);
		when(dbi.getBuchByTitle("Hobbit")).thenReturn(bookset);

		bib = new Bibliothek();
		bib.setDb(dbi);

	}

	/**
	 * Testet, ob ein Buch mit gegebenem Titel gefunden wird.
	 */
	@Test
	public void testSucheBuchNachTitel() {
		Set<Buch> bookset = bib.sucheBuchNachTitel("Hobbit");
		assertEquals(1, bookset.size());
		for (Buch b : bookset) {
			assertEquals("Hobbit", b.getTitel());
			assertEquals("Tolkien", b.getAutor());
			assertEquals("de.hrw.swep.biblio.service.gegenstaende.Frei", b
					.getState().getClass().getName());
		}
	}

	/**
	 * Testet, ob ein Buch mit gegebenem Autor gefunden wird.
	 */
	@Test
	public void testSucheBuchNachAutor() {
		Set<Buch> bookset = bib.sucheBuchNachAutor("Tolkien");
		assertEquals(1, bookset.size());
		for (Buch b : bookset) {
			assertEquals("Hobbit", b.getTitel());
			assertEquals("Tolkien", b.getAutor());
			assertEquals("de.hrw.swep.biblio.service.gegenstaende.Frei", b
					.getState().getClass().getName());
		}

	}

	/**
	 * Testet, ob ein Benutzer mit gegebenem Namen gefunden wird.
	 */
	@Test
	public void testSucheBenutzerNachName() {
		Set<Benutzer> bset = bib.sucheBenutzerNachName("Capt'n Joey");
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
		assertEquals("Capt'n Joey", b.getName());
		assertEquals("de.hrw.swep.biblio.service.benutzer.Normal", b
				.getStatus().getClass().getName());
	}

}
