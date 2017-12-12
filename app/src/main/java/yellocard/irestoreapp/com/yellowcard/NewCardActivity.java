package yellocard.irestoreapp.com.yellowcard;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import CustomUI.ProblemTypeAdapter;
import Pojo.ProblemType;

public class NewCardActivity extends PermissionsActivity implements View.OnClickListener{

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    Typeface typeFace;
    RelativeLayout layoutProblemType,layoutResponsibleParty,layoutPriority;
    TextView addressLabel,meterNoLabel,problemTypeLabel,selectedProblemType,respPartyLabel,respPartyType,
            priorityLabel,priorityText,descriptionLabel,customerInfoLabel,nameLabel,phoneLabel;
    EditText addressValue,meterNoValue,descriptionText,nameValue,phoneValue;
    CheckBox problemFixedChkBox,digSafeChkBox,preCompletedChkBox;
  GPSTracker gps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_card);
        typeFace = Typeface.createFromAsset(getAssets(), "AvenirLTStd-Book.otf");
        gps = new GPSTracker(this);
        sharedPref = getSharedPreferences(getString(
                R.string.preference_file_key), Context.MODE_PRIVATE);
        editor = sharedPref.edit();

        setActionBar();

        addressLabel= (TextView)findViewById(R.id.addressLabel);
        addressValue = (EditText)findViewById(R.id.addressValue) ;

        meterNoLabel= (TextView)findViewById(R.id.meterNoLabel);
        meterNoValue = (EditText)findViewById(R.id.meterNoValue) ;

        selectedProblemType= (TextView)findViewById(R.id.selectedProblemType);
        problemTypeLabel = (TextView)findViewById(R.id.problemTypeLabel) ;

        respPartyLabel= (TextView)findViewById(R.id.respPartyLabel);
        respPartyType = (TextView)findViewById(R.id.respPartyType) ;

        priorityLabel= (TextView)findViewById(R.id.priorityLabel);
        priorityText = (TextView)findViewById(R.id.priorityText) ;

        preCompletedChkBox= (CheckBox)findViewById(R.id.preCompletedChkBox) ;
        digSafeChkBox= (CheckBox)findViewById(R.id.digSafeChkBox) ;
        problemFixedChkBox= (CheckBox)findViewById(R.id.problemFixedChkBox) ;

        descriptionLabel = (TextView)findViewById(R.id.descriptionLabel) ;
        customerInfoLabel = (TextView)findViewById(R.id.customerInfoLabel) ;
        nameLabel = (TextView)findViewById(R.id.nameLabel) ;
        phoneLabel = (TextView)findViewById(R.id.phoneLabel) ;

        descriptionText = (EditText)findViewById(R.id.descriptionText) ;
        phoneValue = (EditText)findViewById(R.id.phoneValue) ;
        nameValue = (EditText)findViewById(R.id.nameValue) ;

        addressValue.setText(Global.addressString);

        addressLabel.setTypeface(typeFace);
        addressValue.setTypeface(typeFace);
        meterNoLabel.setTypeface(typeFace);
        meterNoValue.setTypeface(typeFace);
        problemTypeLabel.setTypeface(typeFace);
        selectedProblemType.setTypeface(typeFace);
        respPartyLabel.setTypeface(typeFace);
        respPartyType.setTypeface(typeFace);

        priorityText.setTypeface(typeFace);
        priorityLabel.setTypeface(typeFace);
        preCompletedChkBox.setTypeface(typeFace);
        digSafeChkBox.setTypeface(typeFace);
        problemFixedChkBox.setTypeface(typeFace);

        nameValue.setTypeface(typeFace);
        nameLabel.setTypeface(typeFace);
        phoneValue.setTypeface(typeFace);
        phoneLabel.setTypeface(typeFace);

        descriptionLabel.setTypeface(typeFace);
        descriptionText.setTypeface(typeFace);
        customerInfoLabel.setTypeface(typeFace);


        layoutProblemType = (RelativeLayout)findViewById(R.id.layoutProblemType) ;
        layoutResponsibleParty = (RelativeLayout)findViewById(R.id.layoutResponsiblePary) ;
        layoutPriority = (RelativeLayout)findViewById(R.id.layoutPriority) ;

        layoutPriority.setOnClickListener(this);
        layoutResponsibleParty.setOnClickListener(this);
        layoutProblemType.setOnClickListener(this);


    }



    public void setActionBar()
    {
        LayoutInflater inflator = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflator.inflate(R.layout.custom_titlebar, null);
        ActionBar actionBar = getActionBar();
        TextView title = (TextView) v.findViewById(R.id.title);
        title.setText("New Yellow Card");
        title.setTypeface(typeFace);
        Button nextBtn = (Button)v.findViewById(R.id.nextBtn);
        nextBtn.setText("Submit");
        nextBtn.setTypeface(typeFace);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowHomeEnabled (false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setCustomView(v);




    }

    @Override
    public void onClick(View v) {
         switch (v.getId())
         {
             case R.id.layoutProblemType:
                 Intent i = new Intent();
                 i.setClass(NewCardActivity.this, SelectProblemActivity.class);
                 startActivity(i);
                 break;
         }
    }
}
