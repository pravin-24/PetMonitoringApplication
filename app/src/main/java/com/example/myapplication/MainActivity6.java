package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
public class MainActivity6 extends AppCompatActivity {
    private TextView tv1, tv2, tv3;
    Button button7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        tv1 = findViewById(R.id.tv1);

        registerForContextMenu(tv1);

        tv2 = findViewById(R.id.tv2);

        registerForContextMenu(tv2);

        tv3 = findViewById(R.id.tv3);

        registerForContextMenu(tv3);

        button7 = findViewById(R.id.button7);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                Intent intent = new Intent(getBaseContext(), MainActivity7.class);
                startActivity(intent);
            }

        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        switch (v.getId()) {
            case R.id.tv1:
                menu.add(0, 1, 0, "Pravin Balaji P");
                menu.add(0, 2, 0, "20IT070");
                break;

            case R.id.tv2:
                menu.add(1, 1, 1, "Naveen Kumar JJ");
                menu.add(1, 2, 1, "20IT058");
                break;

            case R.id.tv3:
                menu.add(2, 1, 2, "Naga Dharsan D");
                menu.add(2, 2, 2, "20IT055");
                break;
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case 1:
                return true;

            case 2:
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }

}
