package ma.n1akai.roomdatabase;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;

import java.util.List;

import ma.n1akai.roomdatabase.adapters.PersonAdapter;
import ma.n1akai.roomdatabase.data.MyRoomDb;
import ma.n1akai.roomdatabase.data.Person;
import ma.n1akai.roomdatabase.data.PersonDao;
import ma.n1akai.roomdatabase.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    MyRoomDb db;
    PersonAdapter personAdapter = new PersonAdapter();
    ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.rcPeople.setAdapter(personAdapter);
        binding.rcPeople.setLayoutManager(new LinearLayoutManager(this));
        binding.rcPeople.setHasFixedSize(true);

        db = Room.databaseBuilder(this, MyRoomDb.class, "my-database").allowMainThreadQueries().build();


        binding.btnAddPerson.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddUpdatePerson.class);
            startActivity(intent);
        });

        personAdapter.setOnPersonLongClickListener((v, person) -> {
            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setTitle("Delete this person")
                    .setMessage("Are you want to delete this person")
                    .setPositiveButton("Yes", (dialog1, which) -> {
                        db.getPersonDao().deletePerson(person.getId());
                        PersonDao personDao = db.getPersonDao();
                        personAdapter.setPeople(personDao.getPeople());
                        Toast.makeText(this, "Deleted Sucessfully!", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("No", (dialog12, which) -> {
                        dialog12.dismiss();
                    }).create();

            dialog.show();
        });

        personAdapter.setOnPersonClickListener((view, person) -> {
            Intent intent = new Intent(this, AddUpdatePerson.class);
            intent.putExtra("person", person);
            startActivity(intent);
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        PersonDao personDao = db.getPersonDao();
        personAdapter.setPeople(personDao.getPeople());
    }
}