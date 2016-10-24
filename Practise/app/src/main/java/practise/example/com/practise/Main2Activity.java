package practise.example.com.practise;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.jar.Attributes;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        try {
            if (getIntent().getExtras() != null) {

               // User user = (User) getIntent().getExtras().getSerializable(MainActivity.USER_KEY);


               // Toast.makeText(this, user.toString(), Toast.LENGTH_LONG).show();
                Person person = (Person) getIntent().getExtras().getParcelable(MainActivity.person_key);
                Toast.makeText(this, person.toString(),Toast.LENGTH_LONG).show();

            } else {

            }

        }catch(NullPointerException e)
        {
            Toast.makeText(this,"null pointer",Toast.LENGTH_LONG).show();
        }
    }


}
