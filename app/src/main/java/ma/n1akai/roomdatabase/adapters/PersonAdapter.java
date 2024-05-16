package ma.n1akai.roomdatabase.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ma.n1akai.roomdatabase.data.Person;
import ma.n1akai.roomdatabase.databinding.ListItemBinding;
import ma.n1akai.roomdatabase.listeners.OnPersonLongClickListener;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PersonViewHolder> {

    List<Person> people = new ArrayList<>();
    OnPersonLongClickListener onPersonLongClickListener;

    public void setPeople(List<Person> people) {
        this.people = people;
    }

    public void setOnPersonLongClickListener(OnPersonLongClickListener onPersonLongClickListener) {
        this.onPersonLongClickListener = onPersonLongClickListener;
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PersonViewHolder(ListItemBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {
        Person person = people.get(position);
        holder.binding.tvId.setText(person.getId() + "");
        holder.binding.tvPersonFullName.setText(person.getLastName() + " " + person.getFirstName());
        holder.binding.tvPersonEmail.setText(person.getEmail());
        holder.binding.getRoot().setOnLongClickListener(v -> {
            onPersonLongClickListener.onLongClick(v, person);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return people.size();
    }

    static class PersonViewHolder extends RecyclerView.ViewHolder {

        ListItemBinding binding;

        public PersonViewHolder(@NonNull ListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
