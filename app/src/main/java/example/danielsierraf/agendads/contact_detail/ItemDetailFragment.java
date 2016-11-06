package example.danielsierraf.agendads.contact_detail;

import android.app.Activity;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import example.danielsierraf.agendads.R;
import example.danielsierraf.agendads.contact_list.ItemListActivity;
import example.danielsierraf.agendads.data.Contact;
import example.danielsierraf.agendads.data.FileHandler;

public class ItemDetailFragment extends Fragment {

    private Contact mItem;

    public ItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ItemDetailActivity.CONTACT_DETAILS)) {
            mItem = new Contact(getArguments().getString(ItemDetailActivity.CONTACT_DETAILS));

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.getName()+" "+mItem.getLast_name());
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.contact_detail_view, container, false);

        if (mItem != null) {
//            ((TextView) rootView.findViewById(R.id.item_detail)).setText(mItem.details);
            ( (TextView) rootView.findViewById(R.id.contact_name)).setText(mItem.getName());
            ( (TextView) rootView.findViewById(R.id.phone_number)).setText(mItem.getPhone_number());
            if (!mItem.getEmail().equals(""))
                ( (TextView) rootView.findViewById(R.id.email)).setText(mItem.getEmail());
            if (!mItem.getAddress().equals(""))
                ( (TextView) rootView.findViewById(R.id.address)).setText(mItem.getAddress());
            if (!mItem.getTone().equals(""))
                ( (TextView) rootView.findViewById(R.id.tono)).setText(mItem.getTone());
        }

        return rootView;
    }
}
