package com.pierrefournier.schedule;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.List;

public class ChildrenListAdapter extends RecyclerView.Adapter<ChildrenListAdapter.ChildrenViewHolder> {

    private Database bdd;
    private List<String> childrenId;
    private SharedPreferences preferences;
    private ParentMenu parentMenu;

    public ChildrenListAdapter(List<String> childrenId, SharedPreferences preferences, ParentMenu parentMenu){
        bdd = new Database();
        this.childrenId = childrenId;
        this.preferences = preferences;
        this.parentMenu = parentMenu;
    }

    @NonNull
    @Override
    public ChildrenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.children_cell, parent, false);

        return new ChildrenViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChildrenViewHolder holder, int position) {
        String childID = childrenId.get(position);
        holder.display(childID);

        holder.childrenCellLayout.setOnClickListener(v -> {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("childID", childID);
            editor.commit();

            Intent intent = new Intent(parentMenu, WeekPlanning.class);
            parentMenu.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return childrenId.size();
    }


    public class ChildrenViewHolder extends RecyclerView.ViewHolder{

        private final TextView name;
        private final TextView description;
        private final LinearLayout childrenCellLayout;

        public ChildrenViewHolder(@NonNull View itemView) {
            super(itemView);

            childrenCellLayout = itemView.findViewById(R.id.ChildrenCellLayout);
            name = itemView.findViewById(R.id.ChildrenCellName);
            description = itemView.findViewById(R.id.ChildrenCellDescription);

        }

        public void display(String childId){
            bdd.getUserRef(childId)
                    .get()
                    .addOnCompleteListener(task -> {
                        DocumentSnapshot document = task.getResult();
                        assert document != null;
                        if(document.exists()){
                            name.setText(document.getString("firstName") + " " + document.getString("lastName"));
                            description.setText(document.getString("email"));
                        }
                    });
        }
    }
}
