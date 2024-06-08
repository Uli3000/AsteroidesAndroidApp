package teclag.c20130027.proyecto_desarrollo_android;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class JuegoActivity extends AppCompatActivity {

    private VistaJuegoView vistaJuegoView;
    private MediaPlayer mPlayAudioFondo;
    private MediaPlayer mPlayAudioDisparo;
    private String openingDateTime;
    private final String FILENAME = "MiArchivo.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.juego_layout);
        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);

        vistaJuegoView = findViewById(R.id.vistaJuegoView);

        DatosCompartidos datosCompartidos = DatosCompartidos.getInstance();
        String tema = datosCompartidos.getTema();

        LinearLayout fondoLayout = findViewById(R.id.fondo);

        // Dependiendo del tema seleccionado en el menu carga unas fondo y unos audios u otros
        if (tema != null && tema.equals("novedoso")) {
            fondoLayout.setBackgroundResource(R.drawable.fondob);
            mPlayAudioFondo = MediaPlayer.create(this, R.raw.audio_fondo2);
            mPlayAudioFondo.setLooping(true);

            mPlayAudioDisparo = MediaPlayer.create(this, R.raw.audio_disparo2);
            vistaJuegoView.setmPlayAudioDisparo(mPlayAudioDisparo);
        } else {
            fondoLayout.setBackgroundResource(R.drawable.fondo);
            mPlayAudioFondo = MediaPlayer.create(this, R.raw.audio_fondo);
            mPlayAudioFondo.setLooping(true);

            mPlayAudioDisparo = MediaPlayer.create(this, R.raw.audio_disparo);
            vistaJuegoView.setmPlayAudioDisparo(mPlayAudioDisparo);
        }

        // A traves del Calendar acceder a la fecha y hora del momento en donde se crea el activity
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        openingDateTime = formatter.format(calendar.getTime());
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mPlayAudioFondo != null) {
            mPlayAudioFondo.start();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mPlayAudioFondo != null) {
            mPlayAudioFondo.pause();
        }
    }

    @Override
    protected void onDestroy() {
        if (mPlayAudioFondo != null) {
            mPlayAudioFondo.stop();
        }
        if (mPlayAudioDisparo != null) {
            mPlayAudioDisparo.stop();
        }

        vistaJuegoView.setCorriendo(false);
        VistaJuegoThread hilo = vistaJuegoView.getVistaJuegoThread();
        try {
            hilo.join();
        } catch (InterruptedException ex) {
            Log.e("Asteroides", ex.toString());
        }

        try {
            // Abre el archivo en modo append para no sobrescribir el contenido existente
            FileOutputStream fos = this.openFileOutput(FILENAME, Context.MODE_APPEND);
            // Escribe en el archvio la variable guardada del tiempo, y con getter de la clase
            // vistaJuegoView los disparos y aciertos
            fos.write((openingDateTime + "                "
                    + vistaJuegoView.getTotalDisparos() + "                    " +
                    + vistaJuegoView.getDisparosExitosos() + "\n").getBytes());
            fos.close();
        } catch (IOException ex) {
            Toast.makeText(this, "ERROR: " + ex, Toast.LENGTH_LONG).show();
        }

        super.onDestroy();
    }
}
