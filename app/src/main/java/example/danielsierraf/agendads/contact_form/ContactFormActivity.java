package example.danielsierraf.agendads.contact_form;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import example.danielsierraf.agendads.R;

public class ContactFormActivity extends AppCompatActivity {

    public static final String ARG_ITEM_ID = "contact_id";
    private ContactFormFragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_form);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFragment = (ContactFormFragment) getSupportFragmentManager().
                        findFragmentById(R.id.contact_form_container);
                mFragment.saveContact();
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
