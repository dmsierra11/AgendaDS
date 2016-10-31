package example.danielsierraf.agendads.data;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by danielsierraf on 10/31/16.
 */

public class FileHandler {
    String FILENAME = "hello_file";
    String string = "hello world!";
    Context mContext;

    public FileHandler(Context context) {
        this.mContext = context;
    }

    public void writeToFile(Contact contact){
        FileOutputStream fos = null;
        try {
            string = contact.getName() + ";phone:" + contact.getPhone_number();
            fos = mContext.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            fos.write(string.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readFile(){
        FileInputStream in = null;
        String output = "";
        try {
            in = mContext.openFileInput(FILENAME);
            InputStreamReader inputStreamReader = new InputStreamReader(in);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            inputStreamReader.close();
            output = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return output;
    }
}
