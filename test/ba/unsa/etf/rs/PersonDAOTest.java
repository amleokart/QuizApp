package ba.unsa.etf.rs;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PersonDAOTest {

    @Test
    void testSetCurrentPerson() {
        PersonDAO person = PersonDAO.getInstance();
        person.defaultData();
        Person k = person.getPersons().get(0);
        person.setCurrentPerson(k);
        assertEquals(k, person.getCurrentPerson());
    }

    @Test
    void testDeletePerson() {
        PersonDAO person = PersonDAO.getInstance();
        person.clearAll();
        person.defaultData();
        Person k = person.getPersons().get(0);
        person.setCurrentPerson(k);
        person.deletePerson();
        boolean test1 = false;
        for (Person p : person.getPersons()) {
            if (p.getName().equals("Elma")) test1 = true;
            if (p.getSurname().equals("Trako")) test1 = true;
        }
        assertFalse(test1);
        assertNull(person.getCurrentPerson());
    }

    @Test
    void testAddDeleteCurrentPerson() {
        PersonDAO person = PersonDAO.getInstance();
        person.clearAll();
        Person p = new Person(1, "Selma", "Selmić", 10, LocalDate.now());
        person.addPerson(p);
        person.defaultData();
        person.setCurrentPerson(p);
        assertEquals(2, person.getPersons().size());
        assertSame(p, person.getCurrentPerson());
        person.deletePerson();
        assertNull(person.getCurrentPerson());
        person.deletePerson();
        assertEquals(1, person.getPersons().size());
    }
}