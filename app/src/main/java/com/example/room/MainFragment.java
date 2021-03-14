package com.example.room;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.room.databinding.ActivityMainBinding;
import com.example.room.databinding.FragmentMainBinding;

public class MainFragment extends Fragment {
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
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.requireActivity()));

        mWordViewModel = new ViewModelProvider(this.requireActivity(),
                ViewModelProvider.AndroidViewModelFactory.getInstance(this.requireActivity().getApplication())).get(WordViewModel.class);
        mWordViewModel.getAllWords().observe(this, words -> {
            // Update the cached copy of the words in the adapter.
            adapter.submitList(words);
        });

        binding.fab.setOnClickListener( fabView -> {
            Navigation.findNavController(fabView).navigate(R.id.action_mainFragment_to_newWordFragment);
//            FragmentManager fragmentManager = requireFragmentManager();
//            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//            NewWordFragment fragment = new NewWordFragment();
//            fragmentTransaction.replace(R.id.mainFragment, fragment);
//            fragmentTransaction.addToBackStack(null);
//            fragmentTransaction.commit();
        });


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
