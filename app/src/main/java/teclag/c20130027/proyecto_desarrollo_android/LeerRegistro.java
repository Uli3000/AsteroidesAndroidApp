package teclag.c20130027.proyecto_desarrollo_android;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.IOException;

public class LeerRegistro extends AppCompatActivity {

    private final String FILENAME = "MiArchivo.txt";
    private EditText edtArchivoTexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ver_registro);

        edtArchivoTexto = findViewById(R.id.edtTextoMultiLinea);

        leerArchivo();
    }

    private void leerArchivo() {
        String texto = "";
        int caracter;

        try {
            FileInputStream fis = this.openFileInput(FILENAME);
            while ((caracter = fis.read()) != -1) {
                texto += (char) caracter;
            }
            fis.close();

            edtArchivoTexto.setText(texto);
        } catch (IOException ex) {
            Toast.makeText(this, "ERROR: " + ex, Toast.LENGTH_LONG).show();
        }
    }
}
