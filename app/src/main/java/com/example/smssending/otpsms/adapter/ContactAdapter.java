package com.example.smssending.otpsms.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.smssending.otpsms.R;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class ContactAdapter extends ArrayAdapter<JSONObject>{

   int vg;
   ArrayList<JSONObject> list;
   Context context;

   public ContactAdapter(Context context, int vg, int id, ArrayList<JSONObject> list){
      super(context,vg, id,list);
      this.context=context;
      this.vg=vg;
      this.list=list;

   }

   public View getView(int position, View convertView, ViewGroup parent) {
      LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      View itemView = inflater.inflate(vg, parent, false);
      TextView txtId=(TextView)itemView.findViewById(R.id.firstName);
      TextView txtName=(TextView)itemView.findViewById(R.id.lastName);
      TextView txtSex=(TextView)itemView.findViewById(R.id.phoneNumber);

      try {
        txtId.setText(list.get(position).getString("first_name"));
        txtName.setText(list.get(position).getString("last_name"));
        txtSex.setText(list.get(position).getString("phone_no"));
      } catch (JSONException e) {

        e.printStackTrace();

      }

      return itemView;

   }

}

