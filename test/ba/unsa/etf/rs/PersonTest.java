package ba.unsa.etf.rs;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    void getId() {
        Person p = new Person(1, "Neko", "Nekić", 7, LocalDate.now());
        assertEquals(1, p.getId());
    }

    @Test
    void getName() {
        Person p = new Person(1, "Neko", "Nekić", 7, LocalDate.now());
        assertEquals("Neko", p.getName());
    }

    @Test
    void getSurname() {
        Person p = new Person(1, "Neko", "Nekić", 7, LocalDate.now());
        assertEquals("Nekić", p.getSurname());
    }

    @Test
    void getNumberPoints() {
        Person p = new Person(1, "Neko", "Nekić", 7, LocalDate.now());
        assertEquals(7, p.getNumberPoints());
    }

    @Test
    void getDate() {
        Person p = new Person(1, "Neko", "Nekić", 7, LocalDate.now());
        assertEquals(LocalDate.now(), p.getDate());
    }

    @Test
    void setDate() {
        Person p = new Person(1, "Neko", "Nekić", 7, LocalDate.of(2021, 7, 11));
        assertEquals(LocalDate.of(2021, 7, 11), p.getDate());
    }
}
