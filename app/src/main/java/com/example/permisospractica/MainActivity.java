package com.example.permisospractica;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    Switch swaudio, swcamara, swubicacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swaudio = findViewById(R.id.swaudio);
        swcamara = findViewById(R.id.swcamara);
        swubicacion = findViewById(R.id.swubicacion);

        View.OnClickListener click = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId())
                {
                    case R.id.swcamara:
                        permisos(Manifest.permission.CAMERA, 1 , swcamara);
                    case R.id.swaudio:
                        permisos(Manifest.permission.RECORD_AUDIO, 1 , swaudio);
                    case R.id.swubicacion:
                        permisos(Manifest.permission.ACCESS_FINE_LOCATION, 1 , swubicacion);

                }
            }
        };

        swaudio.setOnClickListener(click);
        swubicacion.setOnClickListener(click);
        swcamara.setOnClickListener(click);

        statuspermisos(Manifest.permission.CAMERA, swcamara);
        statuspermisos(Manifest.permission.ACCESS_FINE_LOCATION, swubicacion);
        statuspermisos(Manifest.permission.RECORD_AUDIO, swaudio);


    }




    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){

            case 1:
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this, "Permiso camara aceptado", Toast.LENGTH_SHORT).show();
                swcamara.setChecked(true);
            }
            else
            {
                Toast.makeText(this, "Permiso denegado", Toast.LENGTH_SHORT).show();
            }
            break;

             case 2:
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this, "Permiso AUDIO aceptado", Toast.LENGTH_SHORT).show();
                swaudio.setChecked(true);
            }
            else
            {
                Toast.makeText(this, "Permiso denegado", Toast.LENGTH_SHORT).show();

            }
            break;

             case 3:
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this, "Permiso UBICACION aceptado", Toast.LENGTH_SHORT).show();
                swubicacion.setChecked(true);
            }
            else
            {
                Toast.makeText(this, "Permiso denegado", Toast.LENGTH_SHORT).show();

            }
            break;

        }

    }

    public void permisos(String permiso, int codigo, Switch sw)
    {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            if (ActivityCompat.checkSelfPermission(MainActivity.this, permiso) != PackageManager.PERMISSION_GRANTED)
            {
                if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, permiso))
                {
                    String[] lp = new String[] {permiso};
                    ActivityCompat.requestPermissions(MainActivity.this,lp,codigo);
                    swcamara.setChecked(false);
                }
                else
                {
                    String[] lp = new String[] {permiso};
                    ActivityCompat.requestPermissions(MainActivity.this,lp,codigo);
                    swcamara.setChecked(true);

                }

            }
            sw.setChecked(true);
        }
    }

    public void statuspermisos(String permisos, Switch sw)
    {

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            if (ActivityCompat.checkSelfPermission(MainActivity.this, permisos) != PackageManager.PERMISSION_GRANTED)
            {
                sw.setChecked(false);
            }

            sw.setChecked(true);
        }


    }


}
