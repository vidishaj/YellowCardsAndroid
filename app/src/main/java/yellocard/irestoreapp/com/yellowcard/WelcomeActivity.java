package yellocard.irestoreapp.com.yellowcard;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

public class WelcomeActivity extends PermissionsActivity implements View.OnClickListener{

    ImageView startButton,welcomeInfoBtn,welcomeReviewBtn,welcomeCameraBtn;
    CheckBox dontShowAgainChkBox;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    Typeface typeFace;
    TextView welcomeMsg,appInfo,usageInstructions,docConditions,relInfo,review,iRestoreInfoBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_layout);
        typeFace = Typeface.createFromAsset(getAssets(), "AvenirLTStd-Book.otf");
        GPSTracker gps = new GPSTracker(this);
        sharedPref = getSharedPreferences(getString(
                R.string.preference_file_key), Context.MODE_PRIVATE);
        editor = sharedPref.edit();

        LoadProblemTypeFireBase();

        startButton = (ImageView)findViewById(R.id.startButton);
        welcomeCameraBtn = (ImageView)findViewById(R.id.welcomeCamera);
        welcomeInfoBtn = (ImageView)findViewById(R.id.welcomeInfo);
        welcomeReviewBtn = (ImageView)findViewById(R.id.welcomeReview);
        dontShowAgainChkBox = (CheckBox)findViewById(R.id.dontShowAgainChkBox);

        welcomeMsg = (TextView)findViewById(R.id.welcomeMsg) ;
        appInfo = (TextView)findViewById(R.id.appInfo) ;
        usageInstructions = (TextView)findViewById(R.id.usageInstructions) ;
        docConditions = (TextView)findViewById(R.id.docConditions) ;
        relInfo = (TextView)findViewById(R.id.relInfo) ;
        review = (TextView)findViewById(R.id.review) ;
        iRestoreInfoBtn = (TextView)findViewById(R.id.iRestoreInfoBtn) ;

        welcomeMsg.setTypeface(typeFace);
        appInfo.setTypeface(typeFace);
        usageInstructions.setTypeface(typeFace);
        docConditions.setTypeface(typeFace);
        relInfo.setTypeface(typeFace);
        review.setTypeface(typeFace);
        iRestoreInfoBtn.setTypeface(typeFace);

        startButton.setOnClickListener(this);
        welcomeReviewBtn.setOnClickListener(this);
        welcomeInfoBtn.setOnClickListener(this);
        welcomeReviewBtn.setOnClickListener(this);

        dontShowAgainChkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    editor.putBoolean("dontAskAgain",isChecked);
                    editor.commit();
                }

            }
        });

        dontShowAgainChkBox.setTypeface(typeFace);



    }

    private void LoadProblemTypeFireBase()
    {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference firebaseUserDataReference = database.getReference("UIData").child("ProblemType");
        firebaseUserDataReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                String  heading = dataSnapshot.child("displayName").getValue().toString();
                editor.putString("problemTypeHeading",heading);
                try {
                    Gson gson = new Gson();
                    String problemTypeArray = gson.toJson(dataSnapshot.child("values").getValue());

                    editor.putString("problemTypeArray",problemTypeArray);
                    editor.commit();

                }catch (Exception e)
                {

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void LoadDeptTypeFireBase()
    {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference firebaseUserDataReference = database.getReference("UIData").child("Department");
        firebaseUserDataReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                String  heading = dataSnapshot.child("displayName").getValue().toString();
                editor.putString("deptTypeHeading",heading);
                try {
                    Gson gson = new Gson();
                    String deptTypeArray = gson.toJson(dataSnapshot.child("values").getValue());

                    editor.putString("deptTypeArray",deptTypeArray);
                    editor.commit();

                }catch (Exception e)
                {

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.welcomeCamera :
                break;
            case R.id.welcomeInfo :
                break;
            case R.id.welcomeReview :
                break;
            case R.id.startButton :
                Intent i = new Intent();
                i.setClass(WelcomeActivity.this, NewCardActivity.class);
                startActivity(i);
                break;
        }
    }
}
