package teclag.c20130027.proyecto_desarrollo_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Toolbar para el menu
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Clase para compartir los datos del tema seleccionado globalmente en la aplicacion
        DatosCompartidos datosCompartidos = DatosCompartidos.getInstance();
        int itemId = item.getItemId();

        //Images Views
        ImageView imgNave = findViewById(R.id.imgViewNave);
        ImageView imgMisil = findViewById(R.id.imgViewMisil);
        ImageView imgAsteroide = findViewById(R.id.imgViewAsteroide);

        // En caso del item seleccionado del menu invoca una cosa u otra
        if(itemId == R.id.menuJugar){
            Intent intent = new Intent(MainActivity.this, JuegoActivity.class);
            startActivity(intent);
        }

        if(itemId == R.id.menuAcercaDe){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            LayoutInflater inflater = getLayoutInflater();
            View dialogLayout = inflater.inflate(R.layout.image_acercade, null);

            // Image View con mi imagen para el acerca de
            ImageView imageView = dialogLayout.findViewById(R.id.dialog_image);
            imageView.setImageResource(R.drawable.yo);

            // Texto del AcercaDe
            TextView dialogMessage = dialogLayout.findViewById(R.id.dialog_message);
            dialogMessage.setText("Proyecto Asteroides v1 \n\n" +
                    "Autor: Ulises Alejandro Castro Martinez\n" +
                    "[20130027]\n\n" +
                    "Semestre: Ene-Jun/2024");

            // AcercaDe con AlertDialog
            builder.setTitle("Acerca de...")
                    .setView(dialogLayout)
                    .setIcon(R.drawable.teclogo)
                    .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .create()
                    .show();
        }

        if(itemId == R.id.menuDefault){
            Toast.makeText(this, "Selecciono el tema Default", Toast.LENGTH_SHORT).show();
            // Asignar imagenes ilustrativas en el menu dependiendo del tema
            imgNave.setImageResource(R.drawable.nave);
            imgMisil.setImageResource(R.drawable.misil1);
            imgAsteroide.setImageResource(R.drawable.asteroide1);

            datosCompartidos.setTema("default");
        }

        if(itemId == R.id.menuNovedoso){
            Toast.makeText(this, "Selecciono el tema Novedoso", Toast.LENGTH_SHORT).show();
            imgNave.setImageResource(R.drawable.naveb);
            imgMisil.setImageResource(R.drawable.misil1b);
            imgAsteroide.setImageResource(R.drawable.asteroide1b);

            datosCompartidos.setTema("novedoso");
        }

        if(itemId == R.id.menuRegistro){
            Intent intent = new Intent(MainActivity.this, LeerRegistro.class);
            startActivity(intent);
        }

        if(itemId == R.id.menuSalir){
            finishAffinity();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = new MenuInflater(this);
        menuInflater.inflate(R.menu.options_menu, menu);

        return true;
    }
}