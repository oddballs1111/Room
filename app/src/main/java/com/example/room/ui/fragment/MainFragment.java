package com.example.room.ui.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.room.OnItemClickListener;
import com.example.room.OnTodoNameClickListener;
import com.example.room.R;
import com.example.room.repository.db.Word;
import com.example.room.ui.adapter.WordListAdapter;
import com.example.room.viewmodel.WordViewModel;
import com.example.room.viewmodel.factory.WordViewModelFactory;
import com.example.room.databinding.FragmentMainBinding;

public class MainFragment extends Fragment implements OnItemClickListener, OnTodoNameClickListener {
    private WordViewModel mWordViewModel;
    private FragmentMainBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
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
        adapter.setOnItemClickListener(this);
        adapter.setOnTodoNameClickListener(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.requireActivity()));

        WordViewModelFactory factory = new WordViewModelFactory(requireActivity().getApplication(), false);
        mWordViewModel = new ViewModelProvider(requireActivity(), factory).get(WordViewModel.class);
        mWordViewModel.getAllWords().observe(this, words -> {
            // Update the cached copy of the words in the adapter.
            adapter.submitList(words);
        });

        binding.fab.setOnClickListener( fabView -> {
            Navigation.findNavController(fabView).navigate(R.id.action_mainFragment_to_newWordFragment);
        });


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

    @Override
    public void onClick(View view, Word word) {
        //更新対象のwordを渡す
        MainFragmentDirections.ActionMainFragmentToNewWordFragment action =
                MainFragmentDirections.actionMainFragmentToNewWordFragment();
        action.setTodoWord(word);
        Navigation.findNavController(view).navigate(action);
    }
}
