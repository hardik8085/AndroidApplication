package inclass2a.com.example.inclass2b;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.Convert).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        TextView Outpu = (TextView) findViewById(R.id.Result);
        EditText Inpu = (EditText) findViewById(R.id.MeterInsert);
        String username = Inpu.getText().toString();
        try {
            if (username.matches("")) {
                Toast.makeText(this, "Enter Something!!!", Toast.LENGTH_LONG).show();
            } else {


                if (v.getId() == R.id.ToFeet) {
                    TextView Output = (TextView) findViewById(R.id.Result);
                    EditText Input = (EditText) findViewById(R.id.MeterInsert);
                    double v1 = Double.parseDouble(Input.getText().toString());
                    double v2 = v1 * 3.28;

                    Output.setText(v2 + "");
                }

                if (v.getId() == R.id.ToInches) {
                    TextView Output = (TextView) findViewById(R.id.Result);
                    EditText Input = (EditText) findViewById(R.id.MeterInsert);
                    double v1 = Double.parseDouble(Input.getText().toString());
                    double v2 = v1 * 39.3701;

                    Output.setText(v2 + "");
                }

                if (v.getId() == R.id.ToMiles) {
                    TextView Output = (TextView) findViewById(R.id.Result);
                    EditText Input = (EditText) findViewById(R.id.MeterInsert);
                    double v1 = Double.parseDouble(Input.getText().toString());
                    double v2 = v1 * 0.0006;

                    Output.setText(v2 + "");
                }

                if (v.getId() == R.id.ClearAll) {
                    TextView Output = (TextView) findViewById(R.id.Result);
                    EditText Input = (EditText) findViewById(R.id.MeterInsert);
                    Output.setText("");
                    Input.setText("");


                }
            }

        }
        catch(NumberFormatException i)
        {
            //Log.d("demo","Enter the Number");
            Toast.makeText(this,"Enter only numeric data",Toast.LENGTH_LONG).show();
        }


    }

}

