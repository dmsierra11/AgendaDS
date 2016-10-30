package example.danielsierraf.agendads;

import android.view.View;

import example.danielsierraf.agendads.dummy.DummyContent;

/**
 * Created by danielsierraf on 10/29/16.
 */

public interface AdapterDelegate {
    void updateView(DummyContent.DummyItem item);
//    void onItemClick(View view, int position);
}
