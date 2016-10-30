package example.danielsierraf.agendads.contact_list;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import example.danielsierraf.agendads.AdapterDelegate;
import example.danielsierraf.agendads.R;
import example.danielsierraf.agendads.dummy.DummyContent;

/**
 * Created by danielsierraf on 10/29/16.
 */

public class SimpleItemRecyclerViewAdapter
        extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

    private final List<DummyContent.DummyItem> mValues;
    private AdapterDelegate mAdapterDelegate;
    private Activity mActivity;
    private DummyContent.DummyItem mSelectedItem;

    public SimpleItemRecyclerViewAdapter(List<DummyContent.DummyItem> items, Activity activity) {
        mValues = items;
        mAdapterDelegate = (AdapterDelegate) activity;
        mActivity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        Picasso.with(mActivity).load(R.drawable.user_profile).resize(50, 50)
                .centerCrop().into(holder.imageView);
        holder.mContentView.setText(mValues.get(position).content);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public DummyContent.DummyItem getItem(){
        return mSelectedItem;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener,
            View.OnClickListener {
        private final ImageView imageView;
        private final TextView mContentView;
        private DummyContent.DummyItem mItem;

        private ViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.profile_pic);
            mContentView = (TextView) view.findViewById(R.id.contact_name);
            view.setOnLongClickListener(this);
            view.setOnClickListener(this);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }

        @Override
        public boolean onLongClick(View v) {
            //setea el item seleccionado para que luego se tome en OnCreateMenu
            mSelectedItem = mItem;
            return false;
        }

        @Override
        public void onClick(View v) {
            //notifica a la actividad que hizo click y se se pasa el item para que muestre el detalle
            mAdapterDelegate.updateView(mItem);
        }
    }
}
