package com.cscorner.calculatrice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cscorner.calculatrice.database.DatabaseHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity2 extends AppCompatActivity {
    private DatabaseHelper login;
    private EditText usernameEditText;
    private EditText passwordEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        login = new DatabaseHelper(this);
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);

        FloatingActionButton addButton = findViewById(R.id.floatingActionButton4);
        FloatingActionButton addUserButton = findViewById(R.id.floatingActionButton5);
        FloatingActionButton removeUserButton = findViewById(R.id.floatingActionButton7);
        Button loginbutton =findViewById(R.id.loginButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addUserButton.setVisibility(View.VISIBLE);
                removeUserButton.setVisibility(View.VISIBLE);
            }
        });
        addUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
                startActivity(intent);
            }
        });

       loginbutton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String email= usernameEditText.getText().toString() ;
               String password =passwordEditText.getText().toString();


               boolean isAuthenticated = login.authenticateUser(email, password);

               if (isAuthenticated) {

                   Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                   startActivity(intent);
               } else {

                   Toast.makeText(MainActivity2.this, "Échec de l'authentification", Toast.LENGTH_SHORT).show();
               }
           }

       });
    }
}





