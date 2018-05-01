package kushpatel.foodme;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {


    //FIREBASE COMPONENTS
    public FirebaseAuth mAuth;

    //UI COMPONENTS
    public EditText tfEmail, tfPassword;
    public Button mLogIn;
    public TextView mSignUp;

    // CONSTANTS
    public static final String TAG = "LOGINACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tfEmail = (EditText) findViewById(R.id.lg_tf_email);
        tfPassword = (EditText) findViewById(R.id.lg_tf_password);
        mLogIn = (Button) findViewById(R.id.lg_m_logIn);
        mSignUp = (TextView) findViewById(R.id.lg_m_signUp);

        mAuth = FirebaseAuth.getInstance();

        //ON SIGN UP
        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToSignUp = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(goToSignUp);
            }
        });

        //ON LOG IN
        mLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = tfEmail.getText().toString().trim();
                String password = tfPassword.getText().toString().trim();

                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent goToHome = new Intent(LoginActivity.this, HomeActivity.class);
                            startActivity(goToHome);
                        }
                        else {
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
//         Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent goToHome = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(goToHome);
        }
    }

//    @Override
//    public void onResume() {
//        super.onResume();
////         Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if(currentUser != null){
//            Intent goToHome = new Intent(LoginActivity.this, HomeActivity.class);
//            startActivity(goToHome);
//        }
//    }







}
