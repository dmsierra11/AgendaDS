package example.danielsierraf.agendads.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by danielsierraf on 10/30/16.
 */

public class ContactList {
    public static List<Contact> contacts = new ArrayList<>();
    public static HashMap<Integer, Contact> contactMap = new HashMap<>();
    private static int COUNT = 25;

    static {
        for (int i = 0; i < COUNT ; i++){
            addItem(new Contact(i, "Persona "+i, "12345678"+i));
        }
    }

    private static void addItem(Contact contact){
        contacts.add(contact);
        contactMap.put(contact.getId(), contact);
    }
}
