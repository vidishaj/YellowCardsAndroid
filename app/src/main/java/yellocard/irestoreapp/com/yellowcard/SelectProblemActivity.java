package yellocard.irestoreapp.com.yellowcard;

import android.app.ActionBar;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;

import java.util.ArrayList;

import CustomUI.ProblemTypeAdapter;
import Pojo.ProblemType;

public class SelectProblemActivity extends PermissionsActivity {
    ArrayList<ProblemType> problemType_list ,problemType_listNew;
    ProblemTypeAdapter arrayAdapter;
    ListView listView;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    Typeface typeFace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_problem);
        typeFace = Typeface.createFromAsset(getAssets(), "AvenirLTStd-Book.otf");

        sharedPref = getSharedPreferences(getString(
                R.string.preference_file_key), Context.MODE_PRIVATE);
        editor = sharedPref.edit();

        problemType_list = new ArrayList<>();
        arrayAdapter = new ProblemTypeAdapter(problemType_list,SelectProblemActivity.this);
        listView = (ListView)findViewById(R.id.problemtypeList) ;

        LoadProblemTypeFireBase();
        setActionBar();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final ProblemType listItems = (ProblemType) parent.getItemAtPosition(position);
                ImageView tick = (ImageView) view.findViewById(R.id.chkMarkImage);
                if (!sharedPref.getBoolean(listItems.getDisplayName(), false)) {

                    tick.setVisibility(View.VISIBLE);

                    arrayAdapter.getItem(position).setSelected(true);
                    arrayAdapter.notifyDataSetChanged();
                    editor.putBoolean(listItems.getDisplayName(), true);
                    editor.commit();


                }else
                {
                    tick.setVisibility(View.GONE);
                    arrayAdapter.getItem(position).setSelected(false);
                    arrayAdapter.notifyDataSetChanged();
                    editor.putBoolean(listItems.getDisplayName(), false);
                    editor.commit();
                }

            }
        });

    }

    public void setActionBar()
    {
        LayoutInflater inflator = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflator.inflate(R.layout.custom_titlebar, null);
        ActionBar actionBar = getActionBar();
        TextView title = (TextView) v.findViewById(R.id.title);
        title.setText(sharedPref.getString("problemTypeHeading",""));
        title.setTypeface(typeFace);
        Button nextBtn = (Button)v.findViewById(R.id.nextBtn);
        nextBtn.setTypeface(typeFace);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowHomeEnabled (false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setCustomView(v);


    }


    private void LoadProblemTypeFireBase()
    {
        try {
                    JSONArray jsonArray = new JSONArray(sharedPref.getString("problemTypeArray",""));
                    problemType_list.clear();
                    for (int i =0;i<jsonArray.length();i++) {
                        String displayName = jsonArray.getJSONObject(i).getString("displayName");
                        String name = jsonArray.getJSONObject(i).getString("name");
                        String defalultImageURL = jsonArray.getJSONObject(i).getString("defalultImageURL");
                        String tickedImageURL = jsonArray.getJSONObject(i).getString("tickedImageURL");

                        problemType_list.add(new ProblemType(name,displayName,defalultImageURL,tickedImageURL,"",false));


                    }
                    listView.setAdapter(arrayAdapter);
                    arrayAdapter.notifyDataSetChanged();

                }catch (Exception e)
                {

                }

            }

}
