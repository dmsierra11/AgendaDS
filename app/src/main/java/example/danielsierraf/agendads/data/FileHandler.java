package example.danielsierraf.agendads.data;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import example.danielsierraf.agendads.AgendaDSApp;
import example.danielsierraf.agendads.Constant;

/**
 * Created by danielsierraf on 10/31/16.
 */

public class FileHandler {
    private static final String TAG = "FileHandler";
//    String FILENAME = "hello_file";
    String string = "hello world!";
    Context mContext;
    private File contacts_folder;

    public FileHandler() {
        mContext = AgendaDSApp.getInstance();
//        contacts_folder = new File(mContext.getFilesDir().getAbsolutePath(),
//                Constant.CONTACTS_FOLDER);
        contacts_folder = mContext.getFilesDir();
    }

    public void writeToFile(String old_file, Contact contact, boolean is_new_contact){
        FileOutputStream fos = null;
        try {
            string = contact.parseContactToString(contact.getId());
            String filename = Constant.CONTACTS_FOLDER+contact.getName()+contact.getPhone_number();
//            File dir =  mContext.getDir(Constant.CONTACTS_FOLDER, Context.MODE_PRIVATE);
//            File contacts_folder = new File(dir, Constant.CONTACTS_FOLDER);
//            File myFile = new File(dir, filename);
//            fos = new FileOutputStream(myFile);
            if (!is_new_contact && new File(mContext.getFilesDir(), old_file).exists())
                new File(mContext.getFilesDir(), old_file).delete();
            fos = mContext.openFileOutput(filename, Context.MODE_PRIVATE);
            fos.write(string.getBytes());
            fos.close();
            Log.d(TAG, "Contact saved: "+readFile(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Contact> readAllFiles(){
        List<Contact> contactList = new ArrayList<>();
//        if (contacts_folder.exists()){
            File[] files = contacts_folder.listFiles();
            for (int i = 0; i < files.length; i++){
                if (files[i].getName().contains(Constant.CONTACTS_FOLDER)){
                    Log.d(TAG, "file "+i+" "+files[i].getAbsolutePath());
                    contactList.add(new Contact(readFile(files[i].getName())));
                }
            }
//        }
        return contactList;
    }

    public String readFile(String filename){
//        File file = new File(contacts_folder, filename);

        FileInputStream in = null;
        String output = "";
        try {
            in = mContext.openFileInput(filename);
//            in = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(in);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            inputStreamReader.close();
            output = sb.toString();
            Log.d(TAG, "File content: "+output);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return output;
    }

    public boolean deleteAllContacts(){
        File[] files = contacts_folder.listFiles();
        for (int i = 0; i < files.length; i++){
            if (files[i].getName().contains(Constant.CONTACTS_FOLDER)){
                Log.d(TAG, "deleting "+i+" "+files[i].getAbsolutePath());
                files[i].delete();
            }
        }
        return true;
    }

    public boolean deleteFile(String filename){
        return new File(mContext.getFilesDir(), filename).delete();
    }
}
