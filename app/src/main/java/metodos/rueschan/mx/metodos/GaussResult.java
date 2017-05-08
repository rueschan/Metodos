package metodos.rueschan.mx.metodos;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
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
        Intent intent = new Intent(this, GaussInput.class);
        startActivity(intent);
    }

    private void reset() {
        LinearLayout fondo = (LinearLayout) findViewById(R.id.activity_gauss_result);
        fondo.clearDisappearingChildren();
    }

    private void dibujar(ArrayList<Object> objetos) {
        LinearLayout fondo = (LinearLayout) findViewById(R.id.activity_gauss_result);
        ActionBar.LayoutParams param;
        int y;
        for (Object objeto : objetos) {
            if (objeto instanceof Matriz) {
                Matriz matriz = (Matriz) objeto;
                y = matriz.getDimensiones()[1] * 20;

                TableLayout dibujoMatriz = matriz.dibujaResultado(this);

                dibujoMatriz.setVisibility(View.VISIBLE);
                param = new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT);
                param.leftMargin = 40;
                param.topMargin = y;
                param.gravity = Gravity.CENTER;

                fondo.addView(dibujoMatriz, param);
            } else if (objeto instanceof String) {
                String texto = (String) objeto;

                TextView tv = new TextView(this);

                tv.setText(texto);
                tv.setVisibility(View.VISIBLE);
                param = new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT);
                param.leftMargin = 20;
                param.topMargin = 60;
                param.gravity = Gravity.CENTER;

                fondo.addView(tv, param);
            }

//            TextView tv = new TextView(this);
//            tv.setVisibility(View.VISIBLE);
//            String texto;
//
//            ActionBar.LayoutParams param = new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT);
//            param.leftMargin = 20;
//            param.topMargin = y;
//            param.gravity = Gravity.CENTER;
//
//            fondo.addView(tv, param);
//            if (steps == 0) {
////                TextView tv = new TextView(this);
//                tv.setText("Matriz inicial.");
////                tv.setVisibility(View.VISIBLE);
////
////                ActionBar.LayoutParams param = new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT);
////                param.leftMargin = 20;
////                param.topMargin = y;
////                param.gravity = Gravity.CENTER;
////
////                fondo.addView(tv, param);
//            } else if (steps == matrices.get(0).getDimensiones()[0] + 1) {
//                tv.setText("Matriz final.");
//            } else if (steps%2 != 0) {
//                texto = "Se divide la fila " + fila + " entre el valor en la posición ["
//                        + fila + "] de ésta misma. (La F" + fila + " se divide entre F" + fila
//                        + "[" + fila + "])";
//                tv.setText(texto);
//                fila++;
//            } else if (steps%2 == 0) {
//                texto = "Se le resta a cada valor de la fila " + fila + " el resultado de la " +
//                        "resta del valor en esta misma posición menos la multiplicación del valor en la " +
//                        "posición " + (fila - 1) + " por el valor en esta misma posición de la fila anterior.";
//                tv.setText(texto);
//            }

//            TableLayout dibujoMatriz = m.dibujaResultado(this);
//
//            dibujoMatriz.setVisibility(View.VISIBLE);
//            param = new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT);
//            param.leftMargin = 40;
//            param.topMargin = y;
//            param.gravity = Gravity.CENTER;
//
//            fondo.addView(dibujoMatriz, param);
////            this.addContentView(dibujoMatriz, param);
//
////            y += m.getDimensiones()[1] * 60;
//            steps++;
        }
    }

    public static void agregarMatriz(Matriz m) {
        steps.add(m);
    }

    public static void agregarTexto(String s) { steps.add(s); }
}
