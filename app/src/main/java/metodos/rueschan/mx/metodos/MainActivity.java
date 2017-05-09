package metodos.rueschan.mx.metodos;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static Actividades actividad = Actividades.MAIN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public static Actividades getActividad() {
        return actividad;
    }

    public void irGaussInput(View view) {
        actividad = Actividades.GAUSS;
        Intent intent = new Intent(this, GaussInput.class);
        startActivity(intent);
    }
    public void irJordan(View view) {
        actividad = Actividades.JORDAN;
        Intent intent = new Intent(this, Jordan.class);
        startActivity(intent);
    }
    public void irInversa(View view){
        actividad = Actividades.INVERSA;
        Intent intent = new Intent(this, Inversa.class);
        startActivity(intent);
    }
    public void irMinCuadInput(View view){
        Intent intent = new Intent(this, GaussInput.class);
        startActivity(intent);
    }
    public void irCramer(View view){
        Intent intent = new Intent(this, MetodoCramer.class);
        startActivity(intent);
    }
    public void irSeidel(View view){
        Intent intent = new Intent(this, MetodoSeidel.class);
        startActivity(intent);
    }

}
