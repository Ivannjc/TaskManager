package sg.edu.rp.c346.taskmanager;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.icu.util.Calendar;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {
    Button btnAdd, btnCancel;
    EditText etName, etDesc, etRemind;
    DBHelper db;
    Intent i;
    int reqCode = 1234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        btnAdd = (Button) findViewById(R.id.buttonAdd);
        btnCancel = (Button) findViewById(R.id.buttonCancel);

        db = new DBHelper(AddActivity.this);
        i = getIntent();

        etDesc = (EditText) findViewById(R.id.editTextDescription);
        etName = (EditText) findViewById(R.id.editTextName);
        etRemind = (EditText) findViewById(R.id.editTextRemind);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString();
                String desc = etDesc.getText().toString();
                int remind = Integer.parseInt(etRemind.getText().toString());
                db.insertTask(name, desc);
                db.close();
                setResult(RESULT_OK, i);
                finish();

                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.SECOND, remind);

                Intent intent = new Intent(AddActivity.this,
                        NotificationReceiver.class);
                intent.putExtra("name", name);

                PendingIntent pendingIntent = PendingIntent.getBroadcast(
                        AddActivity.this, reqCode,
                        intent, PendingIntent.FLAG_CANCEL_CURRENT);

                AlarmManager am = (AlarmManager)
                        getSystemService(Activity.ALARM_SERVICE);
                am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),
                        pendingIntent);

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setResult(RESULT_OK, i);
                finish();
            }
        });
    }
}
