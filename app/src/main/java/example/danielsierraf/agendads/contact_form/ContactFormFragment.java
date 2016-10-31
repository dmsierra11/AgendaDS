package example.danielsierraf.agendads.contact_form;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

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

    public ContactFormFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null && getArguments().containsKey(ContactFormActivity.ARG_ITEM_ID)) {
            mItem = ContactList.contactMap.get(getArguments().getInt(ContactFormActivity.ARG_ITEM_ID));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_contact_form, container, false);
        if (mItem != null) {
            name = (EditText) rootView.findViewById(R.id.contact_name);
            name.setText(mItem.getName());

            phone_number = (EditText) rootView.findViewById(R.id.phone_number);
            phone_number.setText(mItem.getPhone_number());


            if (!mItem.getLast_name().equals("")) {
                last_name = (EditText) rootView.findViewById(R.id.last_name);
                last_name.setText(mItem.getLast_name());
            }

            if (!mItem.getEmail().equals("")) {
                email = (EditText) rootView.findViewById(R.id.email);
                email.setText(mItem.getEmail());
            }

            if (!mItem.getAddress().equals("")) {
                address = (EditText) rootView.findViewById(R.id.address);
                address.setText(mItem.getAddress());
            }

            if (!mItem.getTone().equals("")) {
                tone = (EditText) rootView.findViewById(R.id.tono);
                tone.setText(mItem.getTone());
            }
        }
        return rootView;
    }

    @Override
    public void saveContact() {
        if (name.getText() != null && phone_number.getText() != null)
            new FileHandler(getActivity()).writeToFile(
                    new Contact(1, name.getText().toString(), phone_number.getText().toString()));
    }
}
