package metodos.rueschan.mx.metodos;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class GaussResult extends AppCompatActivity {

    private static ArrayList<Object> steps = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gauss_result);

        dibujar(steps);
    }

    public void regresar(View view) {
        reset();
        Intent intent;
        switch (MainActivity.getActividad()) {
            case GAUSS:
                intent = new Intent(this, GaussInput.class);
                startActivity(intent);
                break;
            case JORDAN:
                intent = new Intent(this, Jordan.class);
                startActivity(intent);
                break;
            case INVERSA:
                intent = new Intent(this, Inversa.class);
                startActivity(intent);
                break;
        }
    }

    private void reset() {
        LinearLayout fondo = (LinearLayout) findViewById(R.id.resultados);
        fondo.clearDisappearingChildren();
        resetSteps();
    }

    public static void resetSteps() {
        steps.clear();
    }

    private void dibujar(ArrayList<Object> objetos) {
        LinearLayout fondo = (LinearLayout) findViewById(R.id.resultados);
        ActionBar.LayoutParams param;
        TableLayout tabla = new TableLayout(this);
        TableRow fila;
        int y;
        for (Object objeto : objetos) {
            fila = new TableRow(this);
            if (objeto instanceof Matriz) {
                Matriz matriz = (Matriz) objeto;
                y = matriz.getDimensiones()[1] * 20;

                TableLayout dibujoMatriz = matriz.dibujaResultado(this);

                dibujoMatriz.setVisibility(View.VISIBLE);
                param = new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT);
                param.leftMargin = 40;
                param.topMargin = y;
                param.gravity = Gravity.CENTER;

                fila.setVisibility(View.VISIBLE);
                fila.addView(dibujoMatriz);
            } else if (objeto instanceof String) {
                String texto = (String) objeto;

                TextView tv = new TextView(this);

                tv.setText(texto);
                tv.setVisibility(View.VISIBLE);
                param = new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT);
                param.leftMargin = 20;
                param.topMargin = 40;
                param.gravity = Gravity.CENTER;

                fila.setVisibility(View.VISIBLE);
                fila.addView(tv);
            }
            tabla.setVisibility(View.VISIBLE);
            tabla.addView(fila);

            fila = new TableRow(this);
            TextView espacio = new TextView(this);
            espacio.setText("\n");
            fila.addView(espacio);
            tabla.addView(fila);
        }
        fondo.addView(tabla);
    }

    public static void agregarMatriz(Matriz m) {
        steps.add(m);
    }

    public static void agregarTexto(String s) { steps.add(s); }
}
