package sg.edu.np.mad.madpractical;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    ArrayList<User> users = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        for (int i = 1; i <= 20; i++) {

            int id = (int) Math.round(Math.random() * 1000000);
            String nameSuffix = String.valueOf((int) Math.round(Math.random() * 1000000));
            String descSuffix = String.valueOf((int) Math.round(Math.random() * 100000000));

            users.add(new User(String.format("Name%s", nameSuffix), String.format("Description %s", descSuffix), id, Math.random() > 0.5));
        }

        RecyclerView userRecyclerView = findViewById(R.id.recyclerView);
        UserAdapter mAdapter = new UserAdapter(users, this);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(
                getApplicationContext(),
                LinearLayoutManager.VERTICAL,
                false
        );

        userRecyclerView.setLayoutManager(mLayoutManager);
        userRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        User user = users.get(requestCode);
        assert data != null;
        user.followed = data.getBooleanExtra("followed", user.followed);
    }
}