package example.danielsierraf.agendads.contact_form;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import example.danielsierraf.agendads.R;
import example.danielsierraf.agendads.data.Contact;
import example.danielsierraf.agendads.data.ContactList;
import example.danielsierraf.agendads.data.FileHandler;

/**
 * A placeholder fragment containing a simple view.
 */
public class ContactFormFragment extends Fragment implements ContactFormDelegate{

    private Contact mItem = null;
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

        if (getArguments() != null && getArguments().containsKey(ContactFormActivity.ARG_ITEM_ID)) {
            mPosition = getArguments().getInt(ContactFormActivity.ARG_ITEM_ID);
            mItem = ContactList.contactMap.get(mPosition);
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
        Contact contact = new Contact();
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

        if (is_new_contact)
            new FileHandler().writeToFile(contact);
        else new FileHandler().updateCurrent(mPosition, contact);

        EventBus.getDefault().post(true);
        getActivity().finish();
    }
}
