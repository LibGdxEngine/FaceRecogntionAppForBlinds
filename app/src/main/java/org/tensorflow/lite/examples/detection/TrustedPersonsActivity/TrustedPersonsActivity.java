package org.tensorflow.lite.examples.detection.TrustedPersonsActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import org.tensorflow.lite.examples.detection.R;
import org.tensorflow.lite.examples.detection.models.TrustedPerson;

import java.util.ArrayList;
import java.util.List;

public class TrustedPersonsActivity extends AppCompatActivity implements TrustedPersonsAdapter.Listener {

    private static final String MAIN_SHARED_PREFERENCES = "MAIN";
    private RecyclerView recyclerView;
    private TrustedPersonsAdapter adapter;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trusted_persons);
        recyclerView = findViewById(R.id.trusted_persons_recyclerview);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        preferences = getSharedPreferences(MAIN_SHARED_PREFERENCES, Context.MODE_PRIVATE);
        String names = preferences.getString("names", null);
        String phones = preferences.getString("phones", null);
        if(names != null){
            String [] namesArray = names.split(",");
            String [] phonesArray = phones.split(",");
            List<TrustedPerson> persons = new ArrayList<>();
            for (int i = 0; i < namesArray.length; i++) {
                if(!namesArray[i].isEmpty()) {
                    persons.add(new TrustedPerson(namesArray[i], phonesArray[i]));
                }
            }
            adapter = new TrustedPersonsAdapter(persons, this);
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void onDeleteBtnClicked(String personName) {
        SharedPreferences.Editor editor = preferences.edit();
        String newNames = "";
        String names = preferences.getString("names", null);
        if(names != null){
            String[] namesArray = names.split(",");
            for (int i = 0; i < namesArray.length; i++) {
                if(!namesArray[i].equals(personName)){
                    newNames += namesArray[i];
                }
            }
            editor.putString("names", newNames);
            editor.apply();
            editor.commit();
            String [] newNamesArray = newNames.split(",");
            List<TrustedPerson>  persons = new ArrayList<>();
            for (int i = 0; i < newNamesArray.length; i++) {
                if(!namesArray[i].isEmpty()) {
                    persons.add(new TrustedPerson(namesArray[i], "" + i));
                }
            }
            adapter.setTrustedPeople(persons);
            Toast.makeText(this, personName + " Deleted", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onListEmpty() {
        ImageView v = findViewById(R.id.empty_list_icon);
        v.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        Toast.makeText(this, "No trusted people yet", Toast.LENGTH_SHORT).show();
    }
}