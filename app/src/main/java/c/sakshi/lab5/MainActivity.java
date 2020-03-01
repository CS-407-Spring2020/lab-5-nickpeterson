package c.sakshi.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPreferences = getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");
        if (username != "") {
            startActivity2(username);
        }
        else {
            setContentView(R.layout.activity_main);
        }
    }

    public void login(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);
        EditText text = findViewById(R.id.editText);
        String username = text.getText().toString();
        sharedPreferences.edit().putString("username", username).apply();
        startActivity2(username);
    }

    public void startActivity2(String username) {
        Intent intent = new Intent(this, Main2Activity.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }
}

