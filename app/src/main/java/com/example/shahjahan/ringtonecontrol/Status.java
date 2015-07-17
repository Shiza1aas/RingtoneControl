package com.example.shahjahan.ringtonecontrol;


import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Status extends Fragment {
    //    To display all contacts
    String[] Name = new String[91];
    //    list view for list display
    ListView contactListView;



    public Status() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        get view from the xml file

        View view = inflater.inflate(R.layout.fragment_status, container, false);


        displayContacts();

        contactListView = (ListView) view.findViewById(R.id.contactList);

        ArrayAdapter<String> adapterContactList = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, Name);

        contactListView.setAdapter(adapterContactList);

        return view;
    }

    private void displayContacts() {
        int i = 0;

        ContentResolver cr = getActivity().getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);
//        redeclaration of Name string to get only count variables, otherwise nullpointer exception is thrown


        if (cur.getCount() > 0) {
            while (cur.moveToNext()) {
                String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                if (Integer.parseInt(cur.getString(
                        cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                    Cursor pCur = cr.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{id}, null);
                    while (pCur.moveToNext()) {
                        String phoneNo = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                        if ( name != null )
                        {
                            Name[i++] = "" + i + " " +name;

                        }
                    }
                    if ( i > 90 )
                        break;
                    pCur.close();
                }
            }
        }
    }


}
