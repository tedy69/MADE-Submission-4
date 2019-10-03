package tech.tedybearblog.moviecatalogue4.Loading;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import tech.tedybearblog.moviecatalogue4.API.MovieAPI;
import tech.tedybearblog.moviecatalogue4.API.Network;
import tech.tedybearblog.moviecatalogue4.Activity.DetailMovieActivity;
import tech.tedybearblog.moviecatalogue4.Item.Movie;
import tech.tedybearblog.moviecatalogue4.Item.MovieDetail;
import tech.tedybearblog.moviecatalogue4.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

public class LoadingMovieDetail extends AppCompatActivity {
    public static final String EXTRA_MOVIE = "string_extra";
    public Movie movie;
    private String name, description, date, photo2, photo1, year;
    private float rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading);

        movie = getIntent().getParcelableExtra(EXTRA_MOVIE);

        int movieId = movie.getId();
        name = movie.getName();
        description = movie.getDescription();
        date = movie.getDate();
        photo2 = movie.getPhoto2();
        photo1 = movie.getPhoto1();
        rating = movie.getRating();
        year = movie.getYear();

        new MovieTaskDetail().execute(MovieAPI.getURL(String.valueOf(movieId)));
    }

    public class MovieTaskDetail extends AsyncTask<URL, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(URL... urls) {
            String teks = null;
            try {
                teks = Network.getFromNetwork(urls[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return teks;
        }

        @SuppressLint("SetTextI18n")
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONObject jObject = new JSONObject(s);
                MovieDetail movieDetail = new MovieDetail(jObject);
                Intent intent = new Intent(LoadingMovieDetail.this, DetailMovieActivity.class);

                movie.setAllgenre(movieDetail.getAllGenre());
                movie.setAllproduction(movieDetail.getAllProduction());
                movie.setTagline(movieDetail.getTagline());

                intent.putExtra(DetailMovieActivity.EXTRA_MOVIE, movie);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
                finish();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
