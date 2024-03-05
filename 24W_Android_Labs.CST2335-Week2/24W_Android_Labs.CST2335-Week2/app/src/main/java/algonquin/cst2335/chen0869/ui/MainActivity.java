package algonquin.cst2335.chen0869.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

// must import??
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import algonquin.cst2335.chen0869.data.MainViewModel;
import algonquin.cst2335.chen0869.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    //Variable Declarations
    //Declares a variable for view binding.
    private ActivityMainBinding variableBinding;
    //Declares a view model which manages UI-related data in a lifecycle-conscious way
    private MainViewModel model;

    @Override
    // onCreate Method:
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // This initialization sets up the view model for the activity.
        model = new ViewModelProvider(this).get(MainViewModel.class);

    // setContentView(R.layout.activity_main);

    // The inflate method is a part of View Binding that inflates the layout
    // and returns an instance of the binding class.
    // The getRoot() method on the variableBinding object returns the root view of the inflated layout
        variableBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(variableBinding.getRoot());

/*
    // Initialize widgets and ignore due to variableBinding
        TextView mytext = findViewById(R.id.textview);
        Button btn = findViewById(R.id.mybutton);
        EditText myedit = findViewById(R.id.myedittext);
 */

/*   // Set text or add listeners here
        btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String editString = myedit.getText().toString();
            mytext.setText("Your edit text has: " + editString);
        }
    });
 */

//        String editString = myedit.getText().toString();
        //UI Element Interactions:
        //Sets an OnClickListener for a button.
        //When clicked, it posts the text from an EditText to the editString LiveData in the view model.
        EditText myedit = variableBinding.myedittext;
        variableBinding.mybutton.setOnClickListener(click ->
        {
            model.editString.postValue(myedit.getText().toString());
        });

        //Observes changes in editString LiveData and updates the TextView accordingly.
        model.editString.observe(
                this, s -> variableBinding.textview.setText("Your edit text has: " + s));

        //UI State Management:
        //model.isSelected.observe(): Observes the isSelected LiveData
        //When it changes, it updates the state of CheckBox, Switch, and
        // RadioButton, and shows a Toast message.
        model.isSelected.observe(this, selected -> {
            variableBinding.mycheckbox.setChecked(selected);
            variableBinding.myswitch.setChecked(selected);
            variableBinding.myradiobutton.setChecked(selected);
            Toast.makeText(this, "The value is now: " + selected, Toast.LENGTH_SHORT).show();
        });

        //Event Listeners for UI Widgets:
        //These set listeners on CheckBox, Switch, and RadioButton, respectively.
        // When their state changes, it updates the isSelected LiveData in the view model.
        variableBinding.mycheckbox.setOnCheckedChangeListener((button, isSelected) -> {
            // The postValue() method is a function of the LiveData architecture component
            // in Android development.It is used to update the value stored in a MutableLiveData object.
            model.isSelected.postValue(isSelected);
        });
        variableBinding.myswitch.setOnCheckedChangeListener((button, isSelected) -> {
            model.isSelected.postValue(isSelected);
        });
        variableBinding.myradiobutton.setOnCheckedChangeListener((button, isSelected) -> {
            model.isSelected.postValue(isSelected);
        });

        //Sets an OnClickListener for an ImageButton,
        //displaying a Toast with the button's dimensions when clicked.
        variableBinding.myimagebutton.setOnClickListener(click ->
        {
            Toast.makeText(this, "The width = " + variableBinding.myimagebutton.getWidth()
                    + " and height = " + variableBinding.myimagebutton.getHeight(), Toast.LENGTH_SHORT).show();
        });
    }
}