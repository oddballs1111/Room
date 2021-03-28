package com.example.room.ui.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.room.databinding.FragmentMainBinding;
import com.example.room.ui.adapter.WordListAdapter;
import com.example.room.viewmodel.WordViewModel;
import com.example.room.viewmodel.factory.WordViewModelFactory;

abstract class BaseListFragment extends Fragment {
    protected WordViewModel mWordViewModel;
    protected FragmentMainBinding binding;

    abstract void setOnItemClickListener(WordListAdapter adapter);
    abstract void setOnTodoNameClickListener(WordListAdapter adapter);
    abstract boolean getCompleteFlag();
    abstract void setFabVisibility();
    abstract void setFabOnClickListener();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(inflater, container, false);
        View layoutView = binding.getRoot();
        return layoutView;
    }

    @SuppressLint("FragmentLiveDataObserve")
    @Override
    public void onViewCreated(View layoutView, Bundle savedInstanceState) {
        super.onViewCreated(layoutView, savedInstanceState);
        RecyclerView recyclerView = binding.recyclerview;
        final WordListAdapter adapter = new WordListAdapter(new WordListAdapter.WordDiff());
        setOnItemClickListener(adapter);
        setOnTodoNameClickListener(adapter);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.requireActivity()));

        WordViewModelFactory factory = new WordViewModelFactory(requireActivity().getApplication(), getCompleteFlag());
        mWordViewModel = new ViewModelProvider(this, factory).get(WordViewModel.class);
        mWordViewModel.getAllWords().observe(this, words -> {
            // Update the cached copy of the words in the adapter.
            adapter.submitList(words);
        });

        setFabVisibility();
        setFabOnClickListener();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
