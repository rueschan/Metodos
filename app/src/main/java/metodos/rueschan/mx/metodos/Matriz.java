package metodos.rueschan.mx.metodos;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.ArrayList;

/**
 * Created by Rubén Escalante on 24/04/2017.
 */

public class Matriz {

    private ArrayList<ArrayList<Float>> datos;
    private int ancho;
    private int alto;

    private TableLayout matriz;

    public Matriz(int ancho, int alto) {
        this.ancho = ancho;
        this.alto = alto;
    }

    public Matriz(int ancho) {
        this.ancho = ancho;
        this.alto = ancho;
    }

    public Matriz(ArrayList<ArrayList<Float>> datos) {
        this.datos = datos;
        ancho = datos.get(0).size();
        alto = datos.size();
    }

    public TableLayout dibujaMatriz(Context context) {
        matriz = DibujaMatrices.dibujaMatrices(context, this);
        return matriz;
    }

    public int[] getDimensiones() {
        int[] dimensiones = {ancho, alto};
        return dimensiones;
    }

    public ArrayList<ArrayList<Float>> getDatos() {
        return datos;
    }

    public void llenarMatriz(TableLayout dibujoMatriz) {
        TableRow fila;
        EditText et;
        ArrayList<ArrayList<Float>> filas = new ArrayList<>(dibujoMatriz.getChildCount());
        ArrayList<Float> columnas;

        for (int i = 0; i < dibujoMatriz.getChildCount(); i++) {
            fila = (TableRow) dibujoMatriz.getChildAt(i);
            columnas = new ArrayList<>(fila.getChildCount());

            for (int j = 0; j < fila.getChildCount(); j++) {
                et = (EditText) fila.getChildAt(j);
                columnas.add(Float.parseFloat(et.getText().toString()));

            }
            filas.add(columnas);
        }

        datos = filas;
    }
}
