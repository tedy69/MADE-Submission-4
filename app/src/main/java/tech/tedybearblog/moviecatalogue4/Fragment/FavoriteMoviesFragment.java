package tech.tedybearblog.moviecatalogue4.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import tech.tedybearblog.moviecatalogue4.Item.Movie;
import tech.tedybearblog.moviecatalogue4.R;
import tech.tedybearblog.moviecatalogue4.adapter.ListMovieAdapter;
import tech.tedybearblog.moviecatalogue4.db.FavoriteHelper;

import java.util.ArrayList;

public class FavoriteMoviesFragment extends Fragment {
    public static final String KEY_MOVIES = "movies";
    public ArrayList<Movie> listMovies = new ArrayList<>();
    public RecyclerView rvMovie;
    public ProgressBar progressBar;
    private ListMovieAdapter listMovieAdapter;
    private FavoriteHelper favoriteHelper;
    private Bundle saveState;

    public FavoriteMoviesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onStart() {
        rvMovie.setLayoutManager(new LinearLayoutManager(getContext()));
        rvMovie.setHasFixedSize(true);
        progressBar.setVisibility(View.VISIBLE);
        favoriteHelper = FavoriteHelper.getInstance(getContext());
        favoriteHelper.open();

        listMovieAdapter = new ListMovieAdapter(getContext());
        rvMovie.setAdapter(listMovieAdapter);

        if (saveState == null) {
            listMovies.clear();
            listMovies.addAll(favoriteHelper.getAllFavorites());
            if (listMovies != null) {
                listMovieAdapter.setListFavorite(listMovies);
            } else {
                Toast.makeText(getContext(), getResources().getString(R.string.empty_data), Toast.LENGTH_SHORT).show();
            }
        } else {
            ArrayList<Movie> list = saveState.getParcelableArrayList(KEY_MOVIES);
            if (list != null) {
                listMovieAdapter.setListFavorite(list);
            }
        }
        super.onStart();
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvMovie = view.findViewById(R.id.rv_movie);
        progressBar = view.findViewById(R.id.progressBar);
        if (savedInstanceState != null) {
            saveState = savedInstanceState;
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(KEY_MOVIES, listMovieAdapter.getListMovie());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        favoriteHelper.close();
    }
}
