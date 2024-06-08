/*------------------------------------------------------------------------------------------
:*                         TECNOLOGICO NACIONAL DE MEXICO
:*                                CAMPUS LA LAGUNA
:*                     INGENIERIA EN SISTEMAS COMPUTACIONALES
:*                             DESARROLLO EN ANDROID "A"
:*
:*                   SEMESTRE: ENE-JUN/2024    HORA: 08-09 HRS
:*
:*                       Splash inicial de carga de la App
:*
:*  Autor       : Ulises Alejandro Castro Martinez     20130027
:*  Fecha       : 08/03/2024
:*  Compilador  : Android Studio Hedgehog
:*  Descripci�n : Se inicia un layout y con el postDelayed lanza el nuevo activity
:*                y finaliza el actual
:*
:*------------------------------------------------------------------------------------------*/
package teclag.c20130027.proyecto_desarrollo_android;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Hacer la transicion al MainActivity despues de 2 segs.
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent( SplashActivity.this, MainActivity.class );
                startActivity(intent);
                finish();
            }
        }, 2000);
    }
}