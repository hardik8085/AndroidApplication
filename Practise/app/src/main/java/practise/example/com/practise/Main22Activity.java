package practise.example.com.practise;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.preference.DialogPreference;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class Main22Activity extends AppCompatActivity {
        CharSequence[] items = {"Red","Blue","Yellow","Green"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main22);
       /* AlertDialog.Builder builder = new AlertDialog.Builder(this);


        builder.setTitle("Select the color")

                .setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("demo", items[which] + "");
                    }

                })

        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d("Demo", "selected");
            }
        })
                .setCancelable(false);;







        final  AlertDialog  alert = builder.create();
*/

        final ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage("Loading");
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               progress.show();
            }
        });
        }



    }

