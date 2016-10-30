package example.danielsierraf.agendads;

import example.danielsierraf.agendads.data.Contact;

/**
 * Created by danielsierraf on 10/29/16.
 */

public interface AdapterDelegate {
    void updateView(Contact item);
//    void onItemClick(View view, int position);
}
