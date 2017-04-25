package metodos.rueschan.mx.metodos;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;

import java.util.ArrayList;

// Bibliotecas

public class GaussInput extends AppCompatActivity {

    private EditText tamanhoCampo;
    private int tamanho;

    private Matriz matriz;
    private TableLayout dibujoMatriz;
    private boolean hayMatriz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gauss_input);

        dibujoMatriz = new TableLayout(this);
        tamanhoCampo = (EditText) findViewById(R.id.tamanho);
        tamanho = 0;

        hayMatriz = false;
    }

    public void crearMatriz(View view) {

        try {

            if (tamanho != Integer.parseInt(tamanhoCampo.getText().toString())) {

                tamanho = Integer.parseInt(tamanhoCampo.getText().toString());

                hayMatriz = false;
                dibujoMatriz.setVisibility(View.INVISIBLE);
            }

            if (!hayMatriz) {

                matriz = new Matriz(tamanho + 1, tamanho);
                dibujoMatriz = matriz.dibujaMatriz(this);
                dibujoMatriz.setVisibility(View.VISIBLE);
                ActionBar.LayoutParams param = new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT);
                param.leftMargin = 40;
                param.topMargin = 260;
                param.gravity = Gravity.CENTER;

                this.addContentView(dibujoMatriz, param);
                hayMatriz = true;

            }

        } catch (Exception e) {

        }
    }

    public void resolver(View view) {
        matriz.llenarMatriz(dibujoMatriz);

        // vvv DEBUG
//        System.out.println(matriz.getDatos().toString());
        // ^^^ DEBUG

        gauss(matriz);
    }

    private void gauss(Matriz a) {
        Matriz mat = a;
        float valor;
        ArrayList<Float> valores;

        if (mat.getDatos().get(0).get(0) != 1f) {
            valores = mat.getDatos().get(0);
            for (int i = valores.size() - 1; i >= 0; i--) {
                valor = valores.get(i) / mat.getDatos().get(0).get(0);
                valores.set(i, valor);
            }
        }

        // vvv DEBUG
//        System.out.println(matriz.getDatos().toString());
        // ^^^ DEBUG

        float pivote = 0;
        for (int i = 1; i < mat.getDatos().size(); i++) {
            for (int j = i; j < mat.getDatos().size(); j++) {
                valores = mat.getDatos().get(j);
                pivote = valores.get(i - 1);
//                System.out.println("Pivote: " + pivote);
                for (int k = i - 1; k < valores.size(); k++) {
                    valor = valores.get(k) - (pivote * mat.getDatos().get(i - 1).get(k));
                    valores.set(k, valor);
                    // vvv DEBUG
//                    System.out.println("Step: " + matriz.getDatos().toString());
//                    System.out.println("Valor: " + mat.getDatos().get(i - 1).get(k));
                    // ^^^ DEBUG

                }
            }
            valores = mat.getDatos().get(i);
            for (int j = valores.size() - 1; j >= 0; j--) {
                valor = valores.get(j) / mat.getDatos().get(i).get(i);
                valores.set(j, valor);
            }
        }

        // vvv DEBUG
//        System.out.println("Step: " + matriz.getDatos().toString());
        // ^^^ DEBUG


//        for (int i = 1; i < mat.getDatos().size(); i++) {
//            valores = mat.getDatos().get(i);
//            for (int j = valores.size() - 1; j >= 0; j--) {
//                valor = valores.get(j) / mat.getDatos().get(i).get(i);
//                valores.set(j, valor);
//            }
//        }

        // vvv DEBUG
        System.out.println("Final: " + matriz.getDatos().toString());
        // ^^^ DEBUG
    }

}
