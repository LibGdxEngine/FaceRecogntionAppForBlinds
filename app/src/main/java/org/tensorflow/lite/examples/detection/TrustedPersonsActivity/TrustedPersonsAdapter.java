package org.tensorflow.lite.examples.detection.TrustedPersonsActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.tensorflow.lite.examples.detection.R;
import org.tensorflow.lite.examples.detection.models.TrustedPerson;

import java.util.List;

public class TrustedPersonsAdapter extends RecyclerView.Adapter<TrustedPersonsAdapter.ViewHolder> {

    private List<TrustedPerson> trustedPeople;
    private Listener listener;

    public interface Listener {
        void onDeleteBtnClicked(String personName);
        void onListEmpty();
    }

    public TrustedPersonsAdapter(List<TrustedPerson> trustedPeople, Listener listener) {
        this.trustedPeople = trustedPeople;
        if(trustedPeople.size() == 0){
            listener.onListEmpty();
        }
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.trusted_row_item, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull  TrustedPersonsAdapter.ViewHolder viewHolder, int i) {
        TrustedPerson person = trustedPeople.get(i);
        if(person != null) {
            viewHolder.personName.setText(person.getPersonName());
            viewHolder.personPhone.setText(person.getPhoneNumber());
            viewHolder.deleteBtn.setOnClickListener(view -> listener.onDeleteBtnClicked(person.getPersonName()));
        }
    }

    @Override
    public int getItemCount() {
        return trustedPeople.size();
    }

    public void setTrustedPeople(List<TrustedPerson> people){
        this.trustedPeople = people;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView deleteBtn;
        private TextView personName, personPhone;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            deleteBtn = itemView.findViewById(R.id.deleteBtn);
            personName = itemView.findViewById(R.id.textView_name);
            personPhone = itemView.findViewById(R.id.textView_phone);
        }
    }
}
