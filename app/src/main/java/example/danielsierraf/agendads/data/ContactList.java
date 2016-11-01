package example.danielsierraf.agendads.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import example.danielsierraf.agendads.Constant;

/**
 * Created by danielsierraf on 10/30/16.
 */

public class ContactList {
    public static List<Contact> contacts = new ArrayList<>();
    public static HashMap<Integer, Contact> contactMap = new HashMap<>();

    public static void fillContactList(String data){
        contacts = new ArrayList<>();
        String[] contacts_split = data.split(" ");
        for (int i = 0; i < contacts_split.length; i++){
            if (!contacts_split[i].equals(""))
                addItem(i, readFile(i, contacts_split[i]));
        }
    }

    public static void addItem(int id, Contact contact){
        contacts.add(contact);
        contactMap.put(id, contact);
    }

    public static Contact updateItem(int id, Contact contact){
        return contacts.set(contacts.indexOf(contactMap.get(id)), contact);
    }

    public static void removeItem(int id){
        contacts.remove(contacts.indexOf(contactMap.get(id)));
    }

    public static Contact readFile(int id, String output){
        Contact contact = new Contact(id);
//        String output = new FileHandler().readFile();
        if (!output.equals("")){
            String[] contact_details = output.split(";");
            for (int i = 0; i < contact_details.length ; i++){
                String attribute = contact_details[i];
                if (!attribute.equals("")){
                    String[] detail = attribute.split(":");
                    if (detail.length > 1){
                        String key = detail[0];
                        String value = detail[1];
                        if (!value.equals("")){
                            switch (key) {
                                case Constant.CONTACT_NAME_KEY:
                                    contact.setName(value);
                                    break;
                                case Constant.CONTACT_LAST_NAME_KEY:
                                    contact.setLast_name(value);
                                    break;
                                case Constant.CONTACT_PHONE_NUMBER_KEY:
                                    contact.setPhone_number(value);
                                    break;
                                case Constant.CONTACT_ADDRESS_KEY:
                                    contact.setAddress(value);
                                    break;
                                case Constant.CONTACT_EMAIL_KEY:
                                    contact.setEmail(value);
                                    break;
                                case Constant.CONTACT_TONE_KEY:
                                    contact.setTone(value);
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                }
            }
        }
        return contact;
    }
}