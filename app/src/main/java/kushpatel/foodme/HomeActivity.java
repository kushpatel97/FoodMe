package kushpatel.foodme;

import android.content.ClipData;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class HomeActivity extends AppCompatActivity {

    //FIREBASE COMPONENTS
    public FirebaseAuth mAuth;
    public DatabaseReference mDatabase;
    public DatabaseReference uid;

    //UI COMPONENTS
    public Button search;
    public TextView userString;
    public EditText price;

    // CONSTANTS
    public static final String TAG = "HomeActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        userString = (TextView) findViewById(R.id.user_id);
        search = (Button) findViewById(R.id.search);
        price = (EditText) findViewById(R.id.price);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        uid = mDatabase.child("Users").child(mAuth.getCurrentUser().getUid());

        userString.setText(mAuth.getUid());


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map testPost = new HashMap();
                testPost.put("Price", price.getText().toString());
                uid.setValue(testPost);
                Intent goToLists = new Intent(HomeActivity.this, ItemActivity.class);
                startActivity(goToLists);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_menu_items, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.log_out:
                mAuth.signOut();
                Intent logOut = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(logOut);
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
