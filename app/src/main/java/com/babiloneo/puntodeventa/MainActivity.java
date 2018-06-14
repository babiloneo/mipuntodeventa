package com.babiloneo.puntodeventa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button ventas,productos,registrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registrar=(Button)findViewById(R.id.registro);
        funciones();
    }

    private void funciones() {
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextViewIndex = new Intent(MainActivity.this,RegistroActivity.class);
                startActivity(nextViewIndex);
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub

        if (keyCode == event.KEYCODE_BACK) {
            Intent view = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(view);
            onBackPressed();
        }
        return super.onKeyDown(keyCode, event);
    }
}
