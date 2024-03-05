package algonquin.cst2335.chen0869;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**It is a login simulate page
 * @author Ting Cheng
 * @version 2.0
 */
public class MainActivity extends AppCompatActivity {

    /** This holds the text at the center of the screen */
    private TextView tv =null;
    /** This holds the input password at the center of the screen */
    private EditText et =null;
    /** This holds the button at the center of the screen */
    private Button btn = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = findViewById(R.id.my_textView);
        EditText et = findViewById(R.id.my_editText);
        Button btn = findViewById(R.id.my_button);

        btn.setOnClickListener(clk ->{
            String password =et.getText().toString();
            if (checkPasswordComplexity(password)) {
                tv.setText("Your password meets the requirements");
            } else {
                tv.setText("You shall not pass!");
            }
            checkPasswordComplexity(password);
        } );
    }

    /** This function is to check if the input password has an Upper Case letter,
     * a lower case letter, a number, and a special symbol include #$%^&*!@?.
     *
     * @param pw The string object we are checking
     * @return Returns true if the password is complex enough, otherwise false
     */
    boolean checkPasswordComplexity(String pw){
        boolean foundUpperCase, foundLowerCase, foundNumber, foundSpecial;
        foundUpperCase = foundLowerCase = foundNumber = foundSpecial = false;

        for (char c : pw.toCharArray()) {
            if (Character.isUpperCase(c)) foundUpperCase = true;
            if (Character.isLowerCase(c)) foundLowerCase = true;
            if (Character.isDigit(c)) foundNumber = true;
            if (isSpecialCharacter(c)) foundSpecial = true;
        }
        if (!foundUpperCase) {
            Toast.makeText( this, "Missing an upper case!", Toast.LENGTH_SHORT).show();// Say that they are missing an upper case letter;
            return false;
        } else if (!foundLowerCase) {
            Toast.makeText(this, "Missing an lower case!", Toast.LENGTH_SHORT).show(); // Say that they are missing a lower case letter;
            return false;
        } else if (!foundNumber) {
            Toast.makeText(this, "Missing an number!", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!foundSpecial) {
            Toast.makeText(this, "Missing an special character !", Toast.LENGTH_SHORT).show();
            return false;
        } else
            return true; //Password meets all requirements
//        return false;
    }

    /**
     *
     * @param c The String password input
     * @boolean Return true if c include any of: #$%^&*!@?, otherwise false.
     */
    private boolean isSpecialCharacter(char c) {
        switch (c) {
            case '#':
            case '$':
            case '%':
            case '^':
            case '&':
            case '*':
            case '!':
            case '@':
            case '?':
                return true;
            default:
                return false;
        }
    }

}