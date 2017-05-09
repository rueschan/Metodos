package metodos.rueschan.mx.metodos;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TableLayout;

import java.util.ArrayList;

public class Jordan extends AppCompatActivity {

    private EditText tamanhoCampo;
    private int tamanho;

    private Matriz matriz;
    private TableLayout dibujoMatriz;
    private RelativeLayout espacioMatriz;
    private boolean hayMatriz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jordan);

        dibujoMatriz = new TableLayout(this);
        tamanhoCampo = (EditText) findViewById(R.id.tamanho);
        espacioMatriz = (RelativeLayout) findViewById(R.id.matriz);
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

//                this.addContentView(dibujoMatriz, param);
                espacioMatriz.addView(dibujoMatriz);
                hayMatriz = true;

            }

        } catch (Exception e) {

        }
    }

    public void resolver(View view) {
        try {
            matriz.llenarMatriz(dibujoMatriz);

            // vvv DEBUG
//        System.out.println(matriz.getDatos().toString());
            // ^^^ DEBUG

            Operaciones.gaussJordan(matriz);

            Intent intent = new Intent(this, GaussResult.class);
            startActivity(intent);
        } catch (NullPointerException e) {
            System.out.println("No hay matriz para resolver.");
        }
    }

//    private void gaussJordan(Matriz matriz) {
//        int col = 0;
//        ArrayList<ArrayList<Float>> mat = matriz.getDatos();
//        ArrayList<Float> temp;
//
//        for (int y = 0; y < matriz.getDimensiones()[1]; y++) {
//            for (int x = 0; x < matriz.getDimensiones()[1]; x++) {
//                while (mat.get(y).get(col) == 0) {
//                    temp = mat.get(y);
//                    mat.set(y, mat.get(y + 1));
//                    mat.set(y + 1, temp);
//                }
//
//                if (mat.get(y).get(col) != 1) {
//                    ArrayList<Float> valores = mat.get(y);
//                    float valor;
//                    for (int i = valores.size() - 1; i >= 0; i--) {
//                        valor = valores.get(i) / valores.get(col);
//                        valores.set(i, valor);
//                    }
//                }
//
//                if (x != col) {
//                    float valor;
//                    ArrayList<Float> valores = mat.get(y);
//                    float pivote = mat.get(x).get(col);
//                    for (int i = valores.size() - 1; i >= 0; i--) {
//                        valor = (valores.get(i) * (-pivote) + mat.get(x).get(i));
//                        mat.get(x).set(i, valor);
//                    }
//                }
//            }
//            col++;
//        }
//        System.out.println("Matriz FINAL: " + mat.toString());
//        GaussResult.agregarTexto("El resultado de la matriz aplicando el método de Gauss-Jordan es: ");
//        GaussResult.agregarMatriz(matriz);
//    }
}
