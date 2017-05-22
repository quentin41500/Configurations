package com.prolificinteractive.configurations.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.prolificinteractive.configurations.binding.configurations.ItemConfiguration;
import com.prolificinteractive.configurations.databinding.ItemTextBinding;
import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

  private final List<String> items = new ArrayList<>();

  @Override public MyViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
    return new MyViewHolder(ItemTextBinding.inflate(
        LayoutInflater.from(parent.getContext()),
        parent,
        false
    ));
  }

  @Override public void onBindViewHolder(final MyViewHolder holder, final int position) {
    final String item = items.get(position);
    holder.set(item);
  }

  @Override public int getItemCount() {
    return items.size();
  }

  void swapData(final List<String> items) {
    this.items.clear();
    this.items.addAll(items);
    notifyDataSetChanged();
  }

  class MyViewHolder extends RecyclerView.ViewHolder {
    private final ItemConfiguration config = new ItemConfiguration();

    MyViewHolder(ItemTextBinding binding) {
      super(binding.getRoot());
      binding.setConfig(config);
      binding.executePendingBindings();
    }

    void set(final String string) {
      config.setConfig(string);
    }
  }
}
