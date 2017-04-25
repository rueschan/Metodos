package metodos.rueschan.mx.metodos;

import android.content.Context;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

/**
 * Created by Rubén Escalante on 24/04/2017.
 */

public class DibujaMatrices {

    public static TableLayout espacioMatriz;
    public static TableRow fila;


    public static TableLayout dibujaMatrices(Context context, Matriz matriz) {
        espacioMatriz = new TableLayout(context);
        espacioMatriz.setOrientation(LinearLayout.VERTICAL);
        fila = new TableRow(context);

        int ancho = matriz.getDimensiones()[0];
        int alto = matriz.getDimensiones()[1];

        for (int i = 0; i < alto; i++) {
            fila = new TableRow(context);
            for (int j = 0; j < ancho; j++) {
                EditText et = new EditText(context);
                et.setInputType(InputType.TYPE_CLASS_NUMBER);
                et.setVisibility(View.VISIBLE);
                et.setId((i + 1) * (j + 1));
                fila.addView(et);
                et.setText("0");
            }

            espacioMatriz.addView(fila);
        }

        return espacioMatriz;
    }

//    public TableLayout getMatriz() {
//        return espacioMatriz;
//    }
}
