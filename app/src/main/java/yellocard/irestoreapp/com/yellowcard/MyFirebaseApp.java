package yellocard.irestoreapp.com.yellowcard;

import android.app.Application;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by MALBEL on 07-08-2017.
 */

public class MyFirebaseApp extends Application {
    public String MainAct;
    @Override
    public void onCreate() {
        super.onCreate();


        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("yellowcard-1513053583067");

        dbRef.keepSynced(true);

    }
    public String getMainAct() {
        return MainAct;
    }

    public void setMainAct(String mainAct) {
        MainAct = mainAct;
    }
}