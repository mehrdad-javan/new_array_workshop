package se.lexicon;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NameRepositoryTest {
    private String[] testNames = {
            "John Doe",
            "Jane Smith",
            "Michael Johnson",
            "Alice Brown",
            "Robert White"
    };

    @BeforeEach
    public void setup() {
        NameRepository.setNames(testNames);
    }

    @Test
    public void testGetSize() {
        assertEquals(testNames.length, NameRepository.getSize());
    }

    @Test
    public void testClear() {
        NameRepository.clear();
        assertEquals(0, NameRepository.getSize());
    }

    @Test
    public void testFindAll() {
        String[] allNames = NameRepository.findAll();
        assertArrayEquals(testNames, allNames);
    }

    @Test
    public void testFindExistingName() {
        String existingName = "John Doe";
        assertEquals(existingName, NameRepository.find(existingName));
    }

    @Test
    public void testFindNonExistingName() {
        String nonExistingName = "Not Present";
        assertNull(NameRepository.find(nonExistingName));
    }

    @Test
    public void testAddNewName() {
        String newName = "Chris Brown";
        boolean result = NameRepository.add(newName);
        assertTrue(result);
        assertEquals(testNames.length + 1, NameRepository.getSize());
    }

    @Test
    public void testAddExistingName() {
        String existingName = "John Doe";
        boolean result = NameRepository.add(existingName);
        assertFalse(result);
        assertEquals(testNames.length, NameRepository.getSize());
    }

    @Test
    public void testFindByFirstName() {
        String firstName = "Michael";
        String[] expected = {"Michael Johnson"};
        String[] result = NameRepository.findByFirstName(firstName);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testFindByLastName() {
        String lastName = "Brown";
        String[] expected = {"Alice Brown"};
        String[] result = NameRepository.findByLastName(lastName);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testUpdateExistingName() {
        String originalName = "John Doe";
        String updatedName = "John Smith";
        boolean result = NameRepository.update(originalName, updatedName);
        assertTrue(result);
        assertEquals(updatedName, NameRepository.find(updatedName));
    }

    @Test
    public void testUpdateNonExistingName() {
        String originalName = "Not Present";
        String updatedName = "New Name";
        boolean result = NameRepository.update(originalName, updatedName);
        assertFalse(result);
    }

    @Test
    public void testRemoveExistingName() {
        String nameToRemove = "Jane Smith";
        boolean result = NameRepository.remove(nameToRemove);
        assertTrue(result);
        assertNull(NameRepository.find(nameToRemove));
        assertEquals(testNames.length - 1, NameRepository.getSize());
    }

    @Test
    public void testRemoveNonExistingName() {
        String nameToRemove = "Not Present";
        boolean result = NameRepository.remove(nameToRemove);
        assertFalse(result);
        assertEquals(testNames.length, NameRepository.getSize());
    }
}