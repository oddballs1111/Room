package com.example.room;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.room.databinding.FragmentMainBinding;

public class CompleteWordFragment extends Fragment implements OnItemClickListener {
    private WordViewModel mWordViewModel;
    private FragmentMainBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(inflater, container, false);
        View layoutView = binding.getRoot();
        return layoutView;
    }

    @Override
    public void onViewCreated(View layoutView, Bundle savedInstanceState) {
        super.onViewCreated(layoutView, savedInstanceState);
        RecyclerView recyclerView = binding.recyclerview;
        final WordListAdapter adapter = new WordListAdapter(new WordListAdapter.WordDiff());
        adapter.setmOnItemClickLisetner(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.requireActivity()));

        WordViewModelFactory factory = new WordViewModelFactory(requireActivity().getApplication(), true);
        mWordViewModel = new ViewModelProvider(this.requireActivity(), factory).get(WordViewModel.class);
        mWordViewModel.getAllWords().observe(getViewLifecycleOwner(), words -> {
            // Update the cached copy of the words in the adapter.
            adapter.submitList(words);
        });

        binding.fab.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onCompleteFlagChange(Word word) {
        mWordViewModel.update(word);
    }
}
