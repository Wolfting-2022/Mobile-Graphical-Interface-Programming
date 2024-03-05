package algonquin.cst2335.chen0869;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    private static String TAG = "MainActivity";
    private EditText emailEditText;
    private Button loginButton;
    private SharedPreferences prefs;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginButton = findViewById(R.id.loginButton);
        emailEditText = findViewById(R.id.emailEditText);
        prefs = getSharedPreferences("MyData", Context.MODE_PRIVATE);

        // Pre-load the email address
        String emailAddress = prefs.getString("LoginName", "");
        emailEditText.setText(emailAddress);

        loginButton.setOnClickListener(clk->{
            Intent nextPage = new Intent(MainActivity.this,SecondActivity.class);
            nextPage.putExtra("EmailAddress",emailEditText.getText().toString());
            startActivity(nextPage);

            // Save the email address
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("LoginName", emailEditText.getText().toString());
//            editor.putFloat("Hi", 4.5f);
//            editor.putInt("Age", 35);
            editor.apply();
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.w(TAG, "Now visible");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.w(TAG, "Response to user input");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.w(TAG, "No longer response");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.w(TAG, "No longer visible");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.w(TAG, "Memory freed");
    }

}