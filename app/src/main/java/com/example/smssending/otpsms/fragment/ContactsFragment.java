package com.example.smssending.otpsms.fragment;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import com.example.smssending.otpsms.R;
import com.example.smssending.otpsms.activity.ContactDetailActivity;
import com.example.smssending.otpsms.adapter.ContactAdapter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class ContactsFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.contacts_fragment, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.SEND_SMS},1);

        JSONArray jsonArray = getJSonData("jsondata.json");

        final ArrayList<JSONObject> listItems = getArrayListFromJSONArray(jsonArray);
        final ListView listV = (ListView) view.findViewById(R.id.listv);
        final ContactAdapter adapter = new ContactAdapter(getContext(), R.layout.contact_item, R.id.firstName, listItems);

        listV.setAdapter(adapter);
        listV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getContext(),ContactDetailActivity.class);
                try {
                    intent.putExtra("first_name",listItems.get(position).getString("first_name") );
                    intent.putExtra("last_name", listItems.get(position).getString("last_name"));
                    intent.putExtra("phone_no",listItems.get(position).getString("phone_no"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                startActivity(intent);
            }
        });
    }

    private JSONArray getJSonData(String fileName) {
        JSONArray jsonArray = null;

        try {
            InputStream is = getResources().getAssets().open(fileName);
            int size = is.available();
            byte[] data = new byte[size];
            is.read(data);
            is.close();
            String json = new String(data, "UTF-8");
            jsonArray = new JSONArray(json);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException je) {
            je.printStackTrace();
        }
        return jsonArray;

    }

    private ArrayList<JSONObject> getArrayListFromJSONArray(JSONArray jsonArray) {
        ArrayList<JSONObject> aList = new ArrayList<JSONObject>();
        try {
            if (jsonArray != null) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    aList.add(jsonArray.getJSONObject(i));
                }
            }

        } catch (JSONException je) {
            je.printStackTrace();
        }

        return aList;

    }
}
