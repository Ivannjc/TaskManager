package sg.edu.rp.c346.taskmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnAddTask;
    ArrayList<Task>al;
    ArrayAdapter aa;
    DBHelper db;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAddTask = (Button) findViewById(R.id.buttonAddTask);
        lv = (ListView) findViewById(R.id.listViewTask);
        db = new DBHelper(MainActivity.this);
        al = new ArrayList<Task>();

//        al = db.getAllTask();
//        db.close();

        btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, AddActivity.class);
                startActivityForResult(i, 1234);

            }
        });

        aa = new ArrayAdapter(MainActivity.this, android.R.layout. simple_list_item_1, al);
        lv.setAdapter(aa);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Only handle when 2nd activity closed normally
        //  and data contains something
        if(resultCode == RESULT_OK && requestCode ==1234){
                // Get data passed back from 2nd activity
                DBHelper db = new DBHelper(MainActivity.this);
                al = db.getAllTask();
                aa = new ArrayAdapter(MainActivity.this, android.R.layout. simple_list_item_1, al);
                lv.setAdapter(aa);

        }
    }

}
