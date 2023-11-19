package com.cscorner.calculatrice;



import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cscorner.calculatrice.database.DatabaseHelper;

public class MainActivity3 extends AppCompatActivity {
    private DatabaseHelper login;
    private EditText emailEditText;
    private EditText password1EditText;
    private EditText usernameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        login = new DatabaseHelper(this);
        Button ADDUSER = findViewById(R.id.ADDUSERButton);
        emailEditText = findViewById(R.id.loginEditText);
        password1EditText = findViewById(R.id.passwordEditText);
        usernameEditText = findViewById(R.id.NameEditText);
        ADDUSER.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString();
                String password1 = password1EditText.getText().toString();
                String username = usernameEditText.getText().toString();


                DatabaseHelper.InsertResult insertionResult = login.insertUser(email, password1, username, null, null);
                if (insertionResult == DatabaseHelper.InsertResult.SUCCESS) {
                    Toast.makeText(MainActivity3.this, "Utilisateur créé avec succès", Toast.LENGTH_SHORT).show();
                } else if (insertionResult == DatabaseHelper.InsertResult.DUPLICATE_USER) {
                    Toast.makeText(MainActivity3.this, "L'utilisateur existe déjà", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity3.this, "Échec de la création de l'utilisateur", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
