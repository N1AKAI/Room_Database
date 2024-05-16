package ma.n1akai.roomdatabase;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import ma.n1akai.roomdatabase.data.MyRoomDb;
import ma.n1akai.roomdatabase.data.Person;
import ma.n1akai.roomdatabase.databinding.ActivityAddUpdatePersonBinding;

public class AddUpdatePerson extends AppCompatActivity {

    ActivityAddUpdatePersonBinding binding;
    MyRoomDb db;
    Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddUpdatePersonBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().setTitle("Add Person");

        db = Room.databaseBuilder(this, MyRoomDb.class, "my-database").allowMainThreadQueries().build();

        binding.btnAddPerson.setOnClickListener(v -> {
            String firstName = binding.etFirstName.getText().toString();
            String lastName = binding.etLastName.getText().toString();
            String email = binding.etEmail.getText().toString();

            Person person = new Person(firstName, lastName, email);
            db.getPersonDao().insertPerson(person);
            finish();
        });

        Intent intent = getIntent();

        if (intent != null) {
            person = (Person) intent.getSerializableExtra("person");
            if (person != null) {
                getSupportActionBar().setTitle("Update: " + person.getLastName() + " " + person.getFirstName());
                binding.btnAddPerson.setText("Update");
                binding.etFirstName.setText(person.getFirstName());
                binding.etLastName.setText(person.getLastName());
                binding.etEmail.setText(person.getEmail());


                binding.btnAddPerson.setOnClickListener(v -> {
                    person.setFirstName(binding.etFirstName.getText().toString());
                    person.setLastName(binding.etLastName.getText().toString());
                    person.setEmail(binding.etEmail.getText().toString());
                    db.getPersonDao().updatePerson(person);
                    finish();
                });
            }
        }

    }
}