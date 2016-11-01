package example.danielsierraf.agendads.contact_form;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import example.danielsierraf.agendads.R;

public class ContactFormActivity extends AppCompatActivity {

    public static final String ARG_ITEM_ID = "contact_id";
    public static final String ARG_NEW_CONTACT_KEY = "new_contact";
    private ContactFormFragment mFragment;
    private boolean is_new_contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_form);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        is_new_contact = intent.getBooleanExtra(ARG_NEW_CONTACT_KEY, true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFragment = (ContactFormFragment) getSupportFragmentManager().
                        findFragmentById(R.id.contact_form_container);
                mFragment.saveContact(is_new_contact);
            }
        });

        if (savedInstanceState == null) {
            mFragment = new ContactFormFragment();
            if (getIntent().hasExtra(ARG_ITEM_ID)){
                Bundle arguments = new Bundle();
                arguments.putInt(ARG_ITEM_ID, getIntent().getIntExtra(ARG_ITEM_ID, 0));
                mFragment.setArguments(arguments);
            }
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contact_form_container, mFragment).commit();
        }
    }

}
