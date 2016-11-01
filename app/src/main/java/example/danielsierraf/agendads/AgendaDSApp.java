package example.danielsierraf.agendads;

import android.app.Application;
import android.content.Context;

/**
 * Created by danielsierraf on 10/31/16.
 */

public class AgendaDSApp extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this.getApplicationContext();
    }

    public static Context getInstance() {
        return context;
    }
}
