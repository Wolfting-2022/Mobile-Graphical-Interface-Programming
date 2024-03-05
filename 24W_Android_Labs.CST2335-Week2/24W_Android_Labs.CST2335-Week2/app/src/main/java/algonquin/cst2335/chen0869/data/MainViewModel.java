package algonquin.cst2335.chen0869.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
//    public String editString;
    // MutableLiveData is a type of LiveData that can be modified.
    //This particular instance holds a String value.
    //It's used to store and observe changes to a string, such as user input from an EditText.
    public MutableLiveData<String> editString = new MutableLiveData<>();
    //Another public MutableLiveData object named isSelected holds a Boolean value.
    //It is typically used to observe changes in a boolean state, such as the checked state
    // of a checkbox or a switch.
    public MutableLiveData<Boolean> isSelected = new MutableLiveData<>();
}
