import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContactTest {

    @Test
    void testValidContactCreation() {
        Contact c = new Contact("01", "Robert", "De  Niroo", "1234567890", "100 Bayview Dr");
        assertEquals("01",        c.getID());
        assertEquals("Robert",       c.getFirstName());
        assertEquals("De  Niroo",       c.getLastName());
        assertEquals("1234567890",  c.getPhone());
        assertEquals("100 Bayview Dr", c.getAddress());
    }

    // contact Id

    @Test
    void testContactIdNull() {
        assertThrows(IllegalArgumentException.class, () ->
            new Contact(null, "Robert", "De  Niroo", "1234567890", "100 Bayview Dr"));
    }

    @Test
    void testContactIdTooLong() {
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("12345678901", "Robert", "De  Niroo", "1234567890", "100 Bayview Dr"));
    }

    @Test
    void testContactIdExactly10Characters() {
        assertDoesNotThrow(() ->
            new Contact("1234567890", "Robert", "De  Niroo", "1234567890", "100 Bayview Dr"));
    }

    //first Name

    @Test
    void testFirstNameNull() {
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("01", null, "De  Niroo", "1234567890", "100 Bayview Dr"));
    }

    @Test
    void testFirstNameTooLong() {
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("01", "RobertLongName", "De  Niroo", "1234567890", "100 Bayview Dr"));
    }

    @Test
    void testFirstNameExactly10Characters() {
        assertDoesNotThrow(() ->
            new Contact("01", "Alexandria", "De  Niroo", "1234567890", "100 Bayview Dr"));
    }

    // last Name

    @Test
    void testLastNameNull() {
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("01", "Robert", null, "1234567890", "100 Bayview Dr"));
    }

    @Test
    void testLastNameTooLong() {
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("01", "Robert", "De  NirooJohnson", "1234567890", "100 Bayview Dr"));
    }

    @Test
    void testLastNameExactly10Characters() {
        assertDoesNotThrow(() ->
            new Contact("01", "Robert", "De  Niroso", "1234567890", "100 Bayview Dr"));
    }

    //phone

    @Test
    void testPhoneNull() {
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("01", "Robert", "De  Niroo", null, "100 Bayview Dr"));
    }

    @Test
    void testPhoneTooShort() {
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("01", "Robert", "De  Niroo", "123456789", "100 Bayview Dr"));
    }

    @Test
    void testPhoneTooLong() {
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("01", "Robert", "De  Niroo", "12345678901", "100 Bayview Dr"));
    }

    @Test
    void testPhoneContainsLetters() {
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("01", "Robert", "De  Niroo", "123456789A", "100 Bayview Dr"));
    }

    @Test
    void testPhoneExactly10Digits() {
        assertDoesNotThrow(() ->
            new Contact("01", "Robert", "De  Niroo", "0987654321", "100 Bayview Dr"));
    }

    // address

    @Test
    void testAddressNull() {
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("01", "Robert", "De  Niroo", "1234567890", null));
    }

    @Test
    void testAddressTooLong() {
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("01", "Robert", "De  Niroo", "1234567890", "1234 Very Long Address Name Here!"));
    }

    @Test
    void testAddressExactly30Characters() {
        assertDoesNotThrow(() ->
            new Contact("01", "Robert", "De  Niroo", "1234567890", "123456789012345678901234567890"));
    }

    // setters
    @Test
    void testSetFirstNameValid() {
        Contact c = new Contact("01", "Robert", "De  Niroo", "1234567890", "100 Bayview Dr");
        c.setFirstName("Bob");
        assertEquals("Bob", c.getFirstName());
    }

    @Test
    void testSetFirstNameNull() {
        Contact c = new Contact("01", "Robert", "De  Niroo", "1234567890", "100 Bayview Dr");
        assertThrows(IllegalArgumentException.class, () -> c.setFirstName(null));
    }

    @Test
    void testSetFirstNameTooLong() {
        Contact c = new Contact("01", "Robert", "De  Niroo", "1234567890", "100 Bayview Dr");
        assertThrows(IllegalArgumentException.class, () -> c.setFirstName("RobertLongName"));
    }

    @Test
    void testSetLastNameValid() {
        Contact c = new Contact("01", "Robert", "De  Niroo", "1234567890", "100 Bayview Dr");
        c.setLastName("Jones");
        assertEquals("Jones", c.getLastName());
    }

    @Test
    void testSetLastNameNull() {
        Contact c = new Contact("01", "Robert", "De  Niroo", "1234567890", "100 Bayview Dr");
        assertThrows(IllegalArgumentException.class, () -> c.setLastName(null));
    }

    @Test
    void testSetPhoneValid() {
        Contact c = new Contact("01", "Robert", "De  Niroo", "1234567890", "100 Bayview Dr");
        c.setPhone("0987654321");
        assertEquals("0987654321", c.getPhone());
    }

    @Test
    void testSetPhoneInvalid() {
        Contact c = new Contact("01", "Robert", "De  Niroo", "1234567890", "100 Bayview Dr");
        assertThrows(IllegalArgumentException.class, () -> c.setPhone("123"));
    }

    @Test
    void testSetAddressValid() {
        Contact c = new Contact("01", "Robert", "De  Niroo", "1234567890", "100 Bayview Dr");
        c.setAddress("456 Oak Ave");
        assertEquals("456 Oak Ave", c.getAddress());
    }

    @Test
    void testSetAddressNull() {
        Contact c = new Contact("01", "Robert", "De  Niroo", "1234567890", "100 Bayview Dr");
        assertThrows(IllegalArgumentException.class, () -> c.setAddress(null));
    }
}
