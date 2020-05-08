package com.pierrefournier.schedule;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.List;

public class ChildrenListAdapter extends RecyclerView.Adapter<ChildrenListAdapter.childrenViewHolder> {

    private Database bdd;
    private List<String> childrenId;

    public ChildrenListAdapter(List<String> childrenId){
        bdd = new Database();
        this.childrenId = childrenId;
    }

    @NonNull
    @Override
    public childrenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.children_cell, parent, false);

        return new childrenViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull childrenViewHolder holder, int position) {
        String childID = childrenId.get(position);
        holder.display(childID);
    }

    @Override
    public int getItemCount() {
        return childrenId.size();
    }


    public class childrenViewHolder extends RecyclerView.ViewHolder{

        private final TextView name;
        private final TextView description;
        private DocumentReference currentChildRef;

        public childrenViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.ChildrenCellName);
            description = itemView.findViewById(R.id.ChildrenCellDescription);

        }

        public void display(String childId){
            bdd.getUserRef(childId)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            DocumentSnapshot document = task.getResult();
                            assert document != null;
                            if(document.exists()){
                                name.setText(document.getString("firstName") + " " + document.getString("lastName"));
                                description.setText(document.getString("email"));
                            }
                        }
                    });
        }
    }
}
