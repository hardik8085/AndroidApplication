package practise.example.com.practise;

import android.app.ActionBar;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity  {
    final static String name_key="NAME";
    final static String age_key="AGE";
    final static String user_key="user";
    final static String USER_KEY="user1";
    final static String person_key="person";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
  /*  *//*    findViewById(R.id.ClickMe).setEnabled(false);*//*
    final EditText editText= (EditText) findViewById(R.id.editText);*/

    /*   editText.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence s, int start, int count, int after) {
               if (editText.getText().toString().matches(""))
               {
                   findViewById(R.id.ClickMe).setEnabled(false);

               }
               else
               {
                   findViewById(R.id.ClickMe).setEnabled(true);
               }
           }

           @Override
           public void onTextChanged(CharSequence s, int start, int before, int count) {

           }

           @Override
           public void afterTextChanged(Editable s) {
               if (editText.getText().toString().matches(""))
               {
                   findViewById(R.id.ClickMe).setEnabled(false);

               }
               else
               {
                   findViewById(R.id.ClickMe).setEnabled(true);
               }
           }


       });*/

               findViewById(R.id.ClickMe).setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                        Intent intent= new Intent(MainActivity.this,Main2Activity.class);

                       intent.putExtra(name_key, "hardik");
                       intent.putExtra(age_key, "22");


                       //User user1 =new User("Hardik",22);
                       Person person = new Person("Hardik","Thakkar",22);
                       person.name="Hardik";
                       person.surname="Thakkar";
                       person.age=21;
                       intent.putExtra(person_key,person);


                       startActivity(intent);
                   }
               });










       /* RelativeLayout relativeLayout = new RelativeLayout(this);
        relativeLayout.setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        setContentView(relativeLayout);

        TextView textView = new TextView(this);
        textView.setText("Hello_World!");
        textView.setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        textView.setId(100);
        relativeLayout.addView(textView);



        Button button = new Button(this);
        button.setText("Click Me");
       //ViewGroup.LayoutParams params = new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        RelativeLayout.LayoutParams buttonLayoutParam= new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.FILL_PARENT);
        buttonLayoutParam.addRule(RelativeLayout.BELOW,textView.getId());
        button.setId(101);
        button.setLayoutParams(buttonLayoutParam);
        relativeLayout.addView(button);*/









findViewById(R.id.UNCC_EDU).setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.uncc.edu"));
        startActivity(intent);
    }
});

        findViewById(R.id.call).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Intent.ACTION_CALL,Uri.parse("tel:9806363378)"));
                startActivity(intent);
            }
        });

    }
}
