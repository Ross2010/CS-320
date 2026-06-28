import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



public class ContactServiceTest {

    private ContactService service;

    @BeforeEach
    void setUp() {
        service = new ContactService();
    }

    // add Contact

    @Test
    void testAddContactSuccess() {
        Contact c = new Contact("01", "Robert", "Deniroo", "1234567890", "100 Bayview Dr");
        service.addContact(c);
        assertNotNull(service.getContact("01"));
        assertEquals("Robert", service.getContact("01").getFirstName());
    }

    @Test
    void testAddContactDuplicateIdThrows() {
        Contact c1 = new Contact("01", "Robert", "Deniroo", "1234567890", "100 Bayview Dr");
        Contact c2 = new Contact("01", "Bob",   "Jones", "0987654321", "456 Oak Ave");
        service.addContact(c1);
        assertThrows(IllegalArgumentException.class, () -> service.addContact(c2));
    }

    @Test
    void testAddNullContactThrows() {
        assertThrows(IllegalArgumentException.class, () -> service.addContact(null));
    }

    @Test
    void testAddMultipleContactsUniqueIds() {
        service.addContact(new Contact("01", "Robert", "Deniroo", "1234567890", "100 Bayview Dr"));
        service.addContact(new Contact("ID02", "Bob",   "Jones", "0987654321", "456 Oak Ave"));
        assertNotNull(service.getContact("01"));
        assertNotNull(service.getContact("ID02"));
    }

    // delete Contact

    @Test
    void testDeleteContactSuccess() {
        service.addContact(new Contact("01", "Robert", "Deniroo", "1234567890", "100 Bayview Dr"));
        service.deleteContact("01");
        assertNull(service.getContact("01"));
    }

    @Test
    void testDeleteNonExistentContactThrows() {
        assertThrows(IllegalArgumentException.class, () -> service.deleteContact("GHOST"));
    }

    @Test
    void testDeleteContactThenReAddSameId() {
        service.addContact(new Contact("01", "Robert", "Deniroo", "1234567890", "100 Bayview Dr"));
        service.deleteContact("01");
        // After deletion, the same ID should be reusable
        assertDoesNotThrow(() ->
            service.addContact(new Contact("01", "Charlie", "Brown", "5556667777", "Collins Ave")));
    }

    // update FirstName

    @Test
    void testUpdateFirstNameSuccess() {
        service.addContact(new Contact("01", "Robert", "Deniroo", "1234567890", "100 Bayview Dr"));
        service.updateFirstName("01", "Carol");
        assertEquals("Carol", service.getContact("01").getFirstName());
    }

    @Test
    void testUpdateFirstNameNotFoundThrows() {
        assertThrows(IllegalArgumentException.class, () ->
            service.updateFirstName("GHOST", "Carol"));
    }

    @Test
    void testUpdateFirstNameNullThrows() {
        service.addContact(new Contact("01", "Robert", "Deniroo", "1234567890", "100 Bayview Dr"));
        assertThrows(IllegalArgumentException.class, () ->
            service.updateFirstName("01", null));
    }

    @Test
    void testUpdateFirstNameTooLongThrows() {
        service.addContact(new Contact("01", "Robert", "Deniroo", "1234567890", "100 Bayview Dr"));
        assertThrows(IllegalArgumentException.class, () ->
            service.updateFirstName("01", "RobertLongName"));
    }

    // update LastName

    @Test
    void testUpdateLastNameSuccess() {
        service.addContact(new Contact("01", "Robert", "Deniroo", "1234567890", "100 Bayview Dr"));
        service.updateLastName("01", "Johnson");
        assertEquals("Johnson", service.getContact("01").getLastName());
    }

    @Test
    void testUpdateLastNameNotFoundThrows() {
        assertThrows(IllegalArgumentException.class, () ->
            service.updateLastName("GHOST", "Johnson"));
    }

    @Test
    void testUpdateLastNameNullThrows() {
        service.addContact(new Contact("01", "Robert", "Deniroo", "1234567890", "100 Bayview Dr"));
        assertThrows(IllegalArgumentException.class, () ->
            service.updateLastName("01", null));
    }

    // update Phone
    @Test
    void testUpdatePhoneSuccess() {
        service.addContact(new Contact("01", "Robert", "Deniroo", "1234567890", "100 Bayview Dr"));
        service.updatePhone("01", "5556667777");
        assertEquals("5556667777", service.getContact("01").getPhone());
    }

    @Test
    void testUpdatePhoneNotFoundThrows() {
        assertThrows(IllegalArgumentException.class, () ->
            service.updatePhone("GHOST", "5556667777"));
    }

    @Test
    void testUpdatePhoneInvalidThrows() {
        service.addContact(new Contact("01", "Robert", "Deniroo", "1234567890", "100 Bayview Dr"));
        assertThrows(IllegalArgumentException.class, () ->
            service.updatePhone("01", "123"));
    }

    @Test
    void testUpdatePhoneNullThrows() {
        service.addContact(new Contact("01", "Robert", "Deniroo", "1234567890", "100 Bayview Dr"));
        assertThrows(IllegalArgumentException.class, () ->
            service.updatePhone("01", null));
    }

    //update Address

    @Test
    void testUpdateAddressSuccess() {
        service.addContact(new Contact("01", "Robert", "Deniroo", "1234567890", "100 Bayview Dr"));
        service.updateAddress("01", "Collins Ave");
        assertEquals("Collins Ave", service.getContact("01").getAddress());
    }

    @Test
    void testUpdateAddressNotFoundThrows() {
        assertThrows(IllegalArgumentException.class, () ->
            service.updateAddress("GHOST", "Collins Ave"));
    }

    @Test
    void testUpdateAddressTooLongThrows() {
        service.addContact(new Contact("01", "Robert", "Deniroo", "1234567890", "100 Bayview Dr"));
        assertThrows(IllegalArgumentException.class, () ->
            service.updateAddress("01", "1234567890 Long Address Name Here"));
    }

    @Test
    void testUpdateAddressNullThrows() {
        service.addContact(new Contact("01", "Robert", "Deniroo", "1234567890", "100 Bayview Dr"));
        assertThrows(IllegalArgumentException.class, () ->
            service.updateAddress("01", null));
    }

    //contactId immutability

    @Test
    void testContactIdRemainsUnchangedAfterUpdates() {
        service.addContact(new Contact("01", "Robert", "Deniroo", "1234567890", "100 Bayview Dr"));
        service.updateFirstName("01", "Carol");
        service.updateLastName("01", "Jones");
        service.updatePhone("01",    "5556667777");
        service.updateAddress("01",  "Collins Ave");
        assertEquals("01", service.getContact("01").getID());
    }
}
