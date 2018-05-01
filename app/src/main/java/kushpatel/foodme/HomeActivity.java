package kushpatel.foodme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {

    //FIREBASE COMPONENTS
    public FirebaseAuth mAuth;

    //UI COMPONENTS
    public Button mLogOut;
    public TextView userString;

    // CONSTANTS
    public static final String TAG = "HomeActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mLogOut = (Button) findViewById(R.id.h_m_logOut);
        userString = (TextView) findViewById(R.id.user_id);

        mAuth = FirebaseAuth.getInstance();

        userString.setText(mAuth.getUid());

        mLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent logOut = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(logOut);
            }
        });
    }
}
