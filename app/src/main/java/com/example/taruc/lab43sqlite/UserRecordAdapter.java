package com.example.taruc.lab43sqlite;

import android.app.Activity;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by TAR UC on 7/28/2017.
 */

public class UserRecordAdapter extends ArrayAdapter<UserRecord> {
    public UserRecordAdapter(Activity context, int resource, List<UserRecord> list) {
        super(context, resource, list);
    }

    @Override
    public View getView(int position, // position of a record. 1st one = 0
                        View convertView, // layout for each record
                        ViewGroup parent // layout of an activity
    ) {

        UserRecord userRecord = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).
                    inflate(R.layout.user_record,
                            parent,
                            false);
        }

        TextView textViewPhone, textViewName, textViewEmail;

        textViewPhone = (TextView) convertView.findViewById(R.id.textViewPhone);
        textViewName = (TextView) convertView.findViewById(R.id.textViewName);
        textViewEmail = (TextView) convertView.findViewById(R.id.textViewEmail);


        textViewPhone.setText(textViewPhone.getText() + " : " + userRecord.getPhone());
        textViewName.setText(userRecord.getName());
        textViewEmail.setText(textViewEmail.getText() + " : " + userRecord.getEmail());


        return convertView;

    }
}
