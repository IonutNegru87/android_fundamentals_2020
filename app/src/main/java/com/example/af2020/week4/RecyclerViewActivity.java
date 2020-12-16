package com.example.af2020.week4;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.af2020.R;

import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        RecyclerView rvDemo = findViewById(R.id.rv_demo);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);

        rvDemo.setLayoutManager(linearLayoutManager);

        DividerItemDecoration divider = new DividerItemDecoration(this, linearLayoutManager.getOrientation());
        rvDemo.addItemDecoration(divider);

        List<Item> dataSource = DataSource.getItems(100);

        rvDemo.setAdapter(new ItemAdapter(dataSource));
    }

    private static class ItemViewHolder extends RecyclerView.ViewHolder {

        private final TextView firstName;
        private final TextView lastName;

        ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            firstName = itemView.findViewById(R.id.tv_first_name);
            lastName = itemView.findViewById(R.id.tv_last_name);
        }

        void bind(@NonNull final Item item) {
            firstName.setText(item.getFirstName());
            lastName.setText(item.getLastName());
        }

        void updateBackground(int position) {
            if (position % 2 == 1) {
                itemView.setBackgroundColor(Color.rgb(240, 240, 240));
            } else {
                itemView.setBackgroundColor(Color.rgb(250, 255, 255));
            }
        }
    }

    private static class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {

        @NonNull
        private final List<Item> items;

        ItemAdapter(@NonNull List<Item> items) {
            this.items = items;
        }

        @NonNull
        @Override
        public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.list_item, parent, false);
            return new ItemViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
            holder.bind(items.get(position));
            holder.updateBackground(position);
        }

        @Override
        public int getItemCount() {
            return items.size();
        }
    }
}