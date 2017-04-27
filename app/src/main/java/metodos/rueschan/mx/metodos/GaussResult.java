package metodos.rueschan.mx.metodos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class GaussResult extends AppCompatActivity {

    public static ArrayList<Matriz> steps = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gauss_result);

        for (Matriz m: steps) {
            System.out.println(m.dibujaMatriz(this));
        }
    }
}
