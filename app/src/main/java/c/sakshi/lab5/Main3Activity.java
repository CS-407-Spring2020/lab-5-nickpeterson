package c.sakshi.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main3Activity extends AppCompatActivity {

    private int noteid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        EditText text = findViewById(R.id.editText3);
        Intent intent = getIntent();
        int noteid = intent.getIntExtra("noteid", -1);
        this.noteid = noteid;

        if (noteid != -1) {
            Note note = Main2Activity.notes.get(noteid);
            String noteContent = note.getContent();
            text.setText(noteContent);
        }
    }

    public void save(View view) {
        // Get text content
        EditText text = findViewById(R.id.editText3);
        String content = text.getText().toString();

        // Get sqlite database instance
        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("notes", Context.MODE_PRIVATE, null);
        DBHelper dbHelper = new DBHelper(sqLiteDatabase);

        // Get username from shared preferences
        SharedPreferences sharedPreferences = getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");

        // Save to database
        String title;
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String date = dateFormat.format(new Date());

        if (noteid == -1) {
            title = "NOTE_" + (Main2Activity.notes.size() + 1);
            dbHelper.saveNote(username, title, content, date);
        }
        else {
            title = "NOTE_" + (noteid + 1);
            dbHelper.updateNote(title, date, content, username);
        }

        // Go to previous activity
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }
}
