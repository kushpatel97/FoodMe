package kushpatel.foodme;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
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

public class SignUpActivity extends AppCompatActivity {

    //FIREBASE COMPONENTS
    public FirebaseAuth mAuth;

    //UI COMPONENTS
    public EditText tfEmail, tfPassword;
    public Button mSignUp;
    public TextView mLogIn;

    //CONSTANTS
    public static final String TAG = "SIGNUPACTIVITY";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        tfEmail = (EditText) findViewById(R.id.su_tf_email);
        tfPassword = (EditText) findViewById(R.id.su_tf_password);
        mSignUp = (Button) findViewById(R.id.su_m_signUp);
        mLogIn = (TextView) findViewById(R.id.su_m_logIn);

        mAuth = FirebaseAuth.getInstance();

        /**
         * Redirects the user to the log in screen
         */
        mLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToLogIn = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(goToLogIn);
            }
        });

        /**
         * Creates a new user and redirects to the log in screen
         */
        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = tfEmail.getText().toString().trim();
                String password = tfPassword.getText().toString().trim();

                if(password.length() < 6){
                    AlertDialog alertDialog = new AlertDialog.Builder(SignUpActivity.this).create();
                    alertDialog.setTitle("Password Error");
                    alertDialog.setMessage("Please enter a password that is at least 6 characters");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                    return;
                }

                if(email != null && password != null){
                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Log.d(TAG, "createUserWithEmail:success");
//                                FirebaseUser user = mAuth.getCurrentUser();
                                System.out.println("Going to Home"+ "=======================================");

                                Intent goToLogIn = new Intent(SignUpActivity.this, LoginActivity.class);
                                startActivity(goToLogIn);
                            }
                            else {
                                Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }



            }
        });
    }




}
