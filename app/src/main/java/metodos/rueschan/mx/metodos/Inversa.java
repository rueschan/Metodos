package metodos.rueschan.mx.metodos;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TableLayout;

public class Inversa extends AppCompatActivity {

    private EditText tamanhoCampo;
    private int tamanho;

    private Matriz matriz;
    private Matriz multiplicable;
    private TableLayout dibujoMatriz;
    private TableLayout dibujoMultiplicable;
    private boolean hayMatriz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inversa);

        dibujoMatriz = new TableLayout(this);
        dibujoMultiplicable = new TableLayout(this);
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
                dibujoMultiplicable.setVisibility(View.INVISIBLE);
            }

            if (!hayMatriz) {
                ActionBar.LayoutParams param;

                matriz = new Matriz(tamanho);
                dibujoMatriz = matriz.dibujaMatriz(this);
                dibujoMatriz.setVisibility(View.VISIBLE);
                param = new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT);
                param.leftMargin = 40;
                param.topMargin = 260;
                param.gravity = Gravity.CENTER;

                this.addContentView(dibujoMatriz, param);

                multiplicable = new Matriz(1, tamanho);
                dibujoMultiplicable = multiplicable.dibujaMatriz(this);
                dibujoMultiplicable.setVisibility(View.VISIBLE);
                param = new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT);
                param.leftMargin = 320;
                param.topMargin = 260;
                param.gravity = Gravity.CENTER;

                this.addContentView(dibujoMultiplicable, param);

                hayMatriz = true;
            }

        } catch (Exception e) {

        }
    }

    public void resolver(View view) {
        try {
            matriz.llenarMatriz(dibujoMatriz);
            multiplicable.llenarMatriz(dibujoMultiplicable);


            // vvv DEBUG
//        System.out.println(matriz.getDatos().toString());
            // ^^^ DEBUG

            Operaciones.inversa(matriz, multiplicable);

            Intent intent = new Intent(this, GaussResult.class);
            startActivity(intent);
        } catch (NullPointerException e) {
            System.out.println("No hay matriz para resolver.");
        }
    }

//    private void inversa(Matriz matriz) {
//        Matriz inv = id(matriz);
//        Matriz nueva = join(matriz, inv);
//        System.out.println("Join: " + nueva.getDatos().toString());
//    }
//
//    private Matriz join(Matriz matriz, Matriz inv) {
//        Matriz nueva = new Matriz(matriz.getDimensiones()[0] * 2, matriz.getDimensiones()[1]);
//        nueva.llenarVacia();
//
//        for (int y = 0; y < nueva.getDimensiones()[1]; y++) {
//            for (int x = 0; x < nueva.getDimensiones()[0] / 2; x++) {
//                // Parte 1
//                nueva.getDatos().get(y).set(x, matriz.getDatos().get(y).get(x));
//                // Parte 2
//                nueva.getDatos().get(y).set(x + nueva.getDimensiones()[0] / 2,
//                        inv.getDatos().get(y).get(x));
//            }
//        }
//        return nueva;
//    }
//
//    private Matriz id(Matriz matriz) {
//        Matriz copia = matriz.copy();
//
//        for (int y = 0; y < matriz.getDimensiones()[1]; y++) {
//            for (int x = 0; x < matriz.getDimensiones()[1]; x++) {
//                if (x == y) {
//                    copia.getDatos().get(y).set(x, 1f);
//                } else {
//                    copia.getDatos().get(y).set(x, 0f);
//                }
//            }
//        }
//        return copia;
//    }
}
