package metodos.rueschan.mx.metodos;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void irGaussInput(View view) {
        Intent intent = new Intent(this, GaussInput.class);
        startActivity(intent);
    }
    public void irLagrangeInput(View view){
        Intent intent = new Intent(this, GaussInput.class);
        startActivity(intent);
    }
    public void irMinCuadInput(View view){
        Intent intent = new Intent(this, GaussInput.class);
        startActivity(intent);
    }
    public void irBairstrowInput(View view){
        Intent intent = new Intent(this, GaussInput.class);
        startActivity(intent);
    }
    public void irBiseccionInput(View view){
        Intent intent = new Intent(this, GaussInput.class);
        startActivity(intent);
    }

}
