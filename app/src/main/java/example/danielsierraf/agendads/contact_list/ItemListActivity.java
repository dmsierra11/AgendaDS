package example.danielsierraf.agendads.contact_list;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import example.danielsierraf.agendads.contact_detail.ItemDetailActivity;
import example.danielsierraf.agendads.contact_detail.ItemDetailFragment;
import example.danielsierraf.agendads.R;
import example.danielsierraf.agendads.contact_form.ContactFormActivity;
import example.danielsierraf.agendads.data.Contact;
import example.danielsierraf.agendads.data.ContactList;
import example.danielsierraf.agendads.data.FileHandler;

/**
 * An activity representing a list of Items. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ItemDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class ItemListActivity extends AppCompatActivity implements AdapterDelegate {

    private static final String TAG = "ItemListActivity";
    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    private RecyclerView recyclerView;
    private Contact mSelectedContact;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        EventBus.getDefault().register(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ItemListActivity.this, ContactFormActivity.class);
                intent.putExtra(ContactFormActivity.ARG_NEW_CONTACT_KEY, true);
                startActivity(intent);
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.item_list);
        assert recyclerView != null;
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(this));

        if (findViewById(R.id.item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        registerForContextMenu(recyclerView);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onStart();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo)menuInfo;
        SimpleItemRecyclerViewAdapter adapter = (SimpleItemRecyclerViewAdapter) recyclerView.getAdapter();
        mSelectedContact = adapter.getItem();
        menu.setHeaderTitle(mSelectedContact.getName());
        inflater.inflate(R.menu.menu_context_lista, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()) {
            // Se selecciona la opción 1 de menú contextual de la etiqueta
            case R.id.edit_contact:
                Intent intent = new Intent(this, ContactFormActivity.class);
                intent.putExtra(ContactFormActivity.ARG_ITEM_ID, mSelectedContact.getId());
                intent.putExtra(ContactFormActivity.ARG_NEW_CONTACT_KEY, false);
                startActivity(intent);
                return true;
            // Se selecciona la opción 2 de menú contextual de la etiqueta
            case R.id.remove_contact:
                Log.d(TAG, new FileHandler().readFile());
                new FileHandler().deleteContact(
                        ((SimpleItemRecyclerViewAdapter) recyclerView.getAdapter()).getPosition()
                );
                updateList(true);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    public void updateView(Contact item){
        if (mTwoPane) {
            Bundle arguments = new Bundle();
            arguments.putInt(ItemDetailActivity.ARG_ITEM_ID, item.getId());
            ItemDetailFragment fragment = new ItemDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.item_detail_container, fragment)
                    .commit();
        } else {
            Intent intent = new Intent(this, ItemDetailActivity.class);
            intent.putExtra(ItemDetailActivity.ARG_ITEM_ID, item.getId());

            startActivity(intent);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateList(Boolean flag){
        ContactList.fillContactList(new FileHandler().readFile());
        ((SimpleItemRecyclerViewAdapter)recyclerView.getAdapter()).setValues(ContactList.contacts);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                recyclerView.getAdapter().notifyDataSetChanged();
            }
        });
    }
}
