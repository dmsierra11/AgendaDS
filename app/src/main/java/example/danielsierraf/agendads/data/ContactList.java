package example.danielsierraf.agendads.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by danielsierraf on 10/30/16.
 */

public class ContactList {
    public static List<Contact> contacts = new ArrayList<>();
    private static int COUNT = 25;

    static {
        for (int i = 0; i < COUNT ; i++){
            addItem(new Contact("Persona "+i, "12345678"+i));
        }
    }

    private static void addItem(Contact contact){
        contacts.add(contact);
    }
}
