package sg.edu.rp.c346.id20014518.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText etNewTodo;
    Button btnAdd,btnClearAll,btnDelete;
    ListView lvTodo;
    ArrayList<String> alTodo;
    ArrayAdapter<String> aaTodo;
    Spinner spn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNewTodo = findViewById(R.id.editNewTodo);
        btnAdd = findViewById(R.id.buttonAddItem);
        btnClearAll = findViewById(R.id.buttonClearItem);
        btnDelete = findViewById(R.id.buttonDeleteItem);
        lvTodo = findViewById(R.id.listViewTodo);
        spn = findViewById(R.id.spinner);

        alTodo = new ArrayList<>();
        aaTodo = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alTodo);

        lvTodo.setAdapter(aaTodo);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newTodo = etNewTodo.getText().toString();
                alTodo.add(newTodo);
                aaTodo.notifyDataSetChanged();
                etNewTodo.setText(null);
            }
        });

        btnClearAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alTodo.clear();
                aaTodo.notifyDataSetChanged();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etNewTodo.getText().toString().length() == 0){
                    String text = "You don't have any task to remove";
                    Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
                }
                else if (Integer.parseInt(etNewTodo.getText().toString()) > alTodo.size()-1){
                    String text = "Wrong index number";
                    Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
                }
                else{
                    int pos = Integer.parseInt(etNewTodo.getText().toString());
                    alTodo.remove(pos);
                    aaTodo.notifyDataSetChanged();
                }
            }
        });

        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case  0:
                        etNewTodo.setHint("Type a new task here");
                        btnDelete.setEnabled(false);
                        btnAdd.setEnabled(true);
                        break;
                    case 1:
                        etNewTodo.setHint("Type in the index of the task to be removed");
                        btnAdd.setEnabled(false);
                        btnDelete.setEnabled(true);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}