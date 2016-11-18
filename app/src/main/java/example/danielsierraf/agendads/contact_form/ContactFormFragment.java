package example.danielsierraf.agendads.contact_form;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

//import example.danielsierraf.agendads.Constant;
import example.danielsierraf.agendads.R;
import example.danielsierraf.agendads.data.Contact;
import example.danielsierraf.agendads.data.ContactosContract;
//import example.danielsierraf.agendads.data.FileHandler;

public class ContactFormFragment extends Fragment implements ContactFormDelegate{

    private Contact mItem = new Contact();
    private EditText name;
    private EditText last_name;
    private EditText phone_number;
    private EditText address;
    private EditText email;
    private EditText tone;
    private int mPosition;

    public ContactFormFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null && getArguments().containsKey(ContactFormActivity.CONTACT_DETAILS)) {
//            mPosition = getArguments().getInt(ContactFormActivity.ARG_ITEM_ID);
//            mItem = ContactList.contactMap.get(mPosition);
            mItem = new Contact(getArguments().getString(ContactFormActivity.CONTACT_DETAILS));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_contact_form, container, false);

        name = (EditText) rootView.findViewById(R.id.contact_name);
        phone_number = (EditText) rootView.findViewById(R.id.phone_number);
        last_name = (EditText) rootView.findViewById(R.id.last_name);
        email = (EditText) rootView.findViewById(R.id.email);
        address = (EditText) rootView.findViewById(R.id.address);
        tone = (EditText) rootView.findViewById(R.id.tono);

        if (mItem != null) {
            name.setText(mItem.getName());

            phone_number.setText(mItem.getPhone_number());

            if (!mItem.getLast_name().equals(""))
                last_name.setText(mItem.getLast_name());

            if (!mItem.getEmail().equals(""))
                email.setText(mItem.getEmail());

            if (!mItem.getAddress().equals(""))
                address.setText(mItem.getAddress());

            if (!mItem.getTone().equals(""))
                tone.setText(mItem.getTone());
        }
        return rootView;
    }

    @Override
    public void saveContact(boolean is_new_contact) {
        Contact contact = mItem;
        if (name.getText() != null)
            contact.setName(name.getText().toString());
        if (phone_number.getText() != null)
            contact.setPhone_number(phone_number.getText().toString());
        if (last_name.getText() != null)
            contact.setLast_name(last_name.getText().toString());
        if (address.getText() != null)
            contact.setAddress(address.getText().toString());
        if (email.getText() != null)
            contact.setEmail(email.getText().toString());
        if (tone.getText() != null)
            contact.setTone(tone.getText().toString());

//        String old_file = "";
//        if (mItem != null)
//            old_file = Constant.CONTACTS_FOLDER + mItem.getName()+ mItem.getPhone_number();
//        new FileHandler().writeToFile(old_file, contact, is_new_contact);

        saveOnContentResolver(is_new_contact, contact.getId(), contact.getName(),
                contact.getLast_name(), contact.getPhone_number(), contact.getAddress(),
                contact.getEmail());

        getActivity().finish();
    }

    public void saveOnContentResolver(boolean is_new_contact, long id, String nombre, String apellido,
                                      String telefono, String address, String email){
        Uri contactosUri = ContactosContract.Contacto.CONTENT_URI;
        ContentResolver resolver = getActivity().getContentResolver();
        ContentValues values = new ContentValues();
        values.put(ContactosContract.Contacto.COL_NOMBRE, nombre);
        values.put(ContactosContract.Contacto.COL_APELLIDO, apellido);
        values.put(ContactosContract.Contacto.COL_TELEFONO, telefono);
        values.put(ContactosContract.Contacto.COL_DIRECCION, address);
        values.put(ContactosContract.Contacto.COL_EMAIL, email);
        if (is_new_contact)
            resolver.insert(contactosUri, values);
        else
            resolver.update(contactosUri, values, ContactosContract.Contacto._ID + " = ? ",
                    new String[]{String.valueOf(id)});
    }
}
