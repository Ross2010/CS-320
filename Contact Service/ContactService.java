import java.util.HashMap;
import java.util.Map;

public class ContactService {

    private final Map<String, Contact> contacts = new HashMap<>();

    //adds a new contact, throws if the ID already exists.
    public void addContact(Contact contact) {
        if (contact == null) {
            throw new IllegalArgumentException("Contact must not be null.");
        }
        if (contacts.containsKey(contact.getID())) {
            throw new IllegalArgumentException("A contact with ID '" + contact.getID() + "' already exists.");
        }
        contacts.put(contact.getID(), contact);
    }

    //deletes contact by ID. Throws if the ID is not found.
    public void deleteContact(String ID) {
        if (!contacts.containsKey(ID)) {
            throw new IllegalArgumentException("No contact found with ID '" + ID + "'.");
        }
        contacts.remove(ID);
    }

    //updates the first name of the contact with the given ID.
    public void updateFirstName(String ID, String firstName) {
        getExistingContact(ID).setFirstName(firstName);
    }

    //updates the last name of the contact with the given ID.
    public void updateLastName(String ID, String lastName) {
        getExistingContact(ID).setLastName(lastName);
    }

    //updates the phone number of the contact with the given ID.
    public void updatePhone(String ID, String phone) {
        getExistingContact(ID).setPhone(phone);
    }

    //updates the address of the contact with the given ID.
    public void updateAddress(String ID, String address) {
        getExistingContact(ID).setAddress(address);
    }

    //returns the contact for the given ID, or null if not found.
    public Contact getContact(String ID) {
        return contacts.get(ID);
    }

    //helper

    private Contact getExistingContact(String ID) {
        Contact contact = contacts.get(ID);
        if (contact == null) {
            throw new IllegalArgumentException("No contact found with ID '" + ID + "'.");
        }
        return contact;
    }
}
