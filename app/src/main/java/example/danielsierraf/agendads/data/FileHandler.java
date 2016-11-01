package example.danielsierraf.agendads.data;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import example.danielsierraf.agendads.AgendaDSApp;
import example.danielsierraf.agendads.Constant;

/**
 * Created by danielsierraf on 10/31/16.
 */

public class FileHandler {
    private static final String TAG = "FileHandler";
    String FILENAME = "hello_file";
    String string = "hello world!";
    Context mContext;

    public FileHandler() {
        mContext = AgendaDSApp.getInstance();
    }

    public void writeToFile(Contact contact){
        FileOutputStream fos = null;
        try {
            string = contact.parseContactToString(contact.getId());
            if (new File(mContext.getFilesDir().getAbsolutePath()+"/"+FILENAME).exists())
                fos = mContext.openFileOutput(FILENAME, Context.MODE_APPEND);
            else
                fos = mContext.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            string += " ";
            fos.write(string.getBytes());
            fos.close();
            Log.d(TAG, "Contact saved: "+readFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToFile(String data){
        FileOutputStream fos = null;
        try {
            fos = mContext.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            fos.write(data.getBytes());
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

    public boolean updateCurrent(int position, Contact contact){
        String data = readFile();
        String[] contact_details = data.split(" ");
        ContactList.updateItem(position, contact);
        String new_output = "";
        for (int i = 0; i < contact_details.length; i++){
            String contact_string = contact_details[i];
            if (position == i)
                contact_string = contact.parseContactToString(position);
            new_output += contact_string;
        }
        deleteFile();
        writeToFile(new_output);
        return true;
    }

    public boolean deleteContact(int position){
        String data = readFile();
        String[] contact_details = data.split(" ");
        ContactList.removeItem(position);
        String new_output = "";
        for (int i = 0; i < contact_details.length; i++){
            String contact_string = contact_details[i];
            if (position != i)
                new_output += contact_string;
        }
        deleteFile();
        writeToFile(new_output);
        return true;
    }

    public boolean deleteFile(){
        return new File(mContext.getFilesDir().getAbsolutePath()+"/"+FILENAME).delete();
    }
}
