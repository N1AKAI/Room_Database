package ma.n1akai.roomdatabase.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PersonDao {

    @Insert
    void insertPerson(Person person);

    @Update
    void updatePerson(Person person);

    @Query("SELECT * FROM Person")
    List<Person> getPeople();

    @Query("DELETE FROM Person WHERE id = :id")
    void deletePerson(long id);

}
