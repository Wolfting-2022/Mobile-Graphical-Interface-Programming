package algonquin.cst2335.chen0869;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import algonquin.cst2335.chen0869.databinding.ActivitySecondBinding;
public class SecondActivity extends AppCompatActivity {
//    private TextView welcomeTextView;
    private ActivitySecondBinding binding;
    private SharedPreferences prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // create ViewBinding
        binding = ActivitySecondBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        prefs = getSharedPreferences("MyData", Context.MODE_PRIVATE);//same as MainActivity prefs
        // Load saved phone number if exists
        String savedPhoneNumber = prefs.getString("PhoneNumber", "");
        binding.editTextPhone.setText(savedPhoneNumber);

//        welcomeTextView = findViewById(R.id.title);
        //part1: retrieve data from MainActivity
        Intent fromPrevious = getIntent();
        String emailAddress = fromPrevious.getStringExtra("EmailAddress");
//        welcomeTextView.setText("Welcome back " + emailAddress);
        binding.title.setText("Welcome back " + emailAddress);

        // Check if the picture file exists and load it
        File file = new File(getFilesDir(), "Picture.png");
        if (file.exists()) {
            Bitmap theImage = BitmapFactory.decodeFile(file.getAbsolutePath());
            binding.imageView.setImageBitmap(theImage);
        }

        //part2: make a phone call
        binding.upButton.setOnClickListener(v -> {
            String phoneNumber = binding.editTextPhone.getText().toString();
            Intent call = new Intent(Intent.ACTION_DIAL);
            call.setData(Uri.parse("tel:"+ phoneNumber));
            startActivity(call);
        });


        // part3:write object with 2 parameter/functions  to return a image from second Activity
        ActivityResultLauncher<Intent>cameraResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode()== Activity.RESULT_OK){
                            Intent data = result.getData();
                            Bitmap thumbnail = data.getParcelableExtra("data");

                            // Save the bitmap to file
                            try (FileOutputStream fOut = openFileOutput("Picture.png", Context.MODE_PRIVATE)) {
                                thumbnail.compress(Bitmap.CompressFormat.PNG, 100, fOut);
                            }
                            catch (IOException e) {
                                e.printStackTrace();
                            }
//                            catch(FileNotFoundException e) {e.printStackTrace();}
                            binding.imageView.setImageBitmap(thumbnail);
                        }
                    }
                });


        // part4: launch the cameraIntent
        Intent cameraIntent =  new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        binding.imageView.setOnClickListener(v -> cameraResult.launch(cameraIntent));
        binding.downButton.setOnClickListener(v -> cameraResult.launch(cameraIntent));
        cameraResult.launch(cameraIntent);
    }


    @Override
    protected void onPause() {
        super.onPause();
        // Save the current phone number
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("PhoneNumber", binding.editTextPhone.getText().toString());
        editor.apply();
    }
}