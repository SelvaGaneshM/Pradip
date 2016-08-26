package com.influx.pradip;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.influx.pradip.model.SlotSubModel;
import com.influx.pradip.rest.RestClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textview = (TextView) findViewById(R.id.textview);

        getData();
    }

    private void getData() {

        String first_name = "Selvanew12";
        String last_name = "Ganeshnew12";
        String email = "selvaganeshnew12@gmail.com";
        String password = "selvaganeshnew12";
        String co_parent_name = "selvaganeshnew12";
        String group = "1";

        final ProgressDialog dialog = ProgressDialog.show(this, "", "loading...");
        RestClient.GitApiInterface service = RestClient.getClient();
        Call<SlotSubModel> call = service.getcity(new User(first_name, last_name, email, password, co_parent_name, group));


        call.enqueue(new Callback<SlotSubModel>() {
                         @Override
                         public void onResponse(Call<SlotSubModel> call, Response<SlotSubModel> response) {
                             dialog.dismiss();
                             //Log.d("MainActivity", "Status Code = " + response.code());
                             //Log.w("Response:::", response.body().d.toString());
                             if (response.body() != null) {
                                 String token = response.body().getToken().toString();
                                 String user_id = response.body().getUser_id().toString();
                                 textview.setText("token :" + token + " user_id :" + user_id);
                             } else {
                                 textview.setText("Please Update Input Params");
                             }

                         }

                         @Override
                         public void onFailure(Call<SlotSubModel> call, Throwable t) {
                             dialog.dismiss();
                         }
                     }
        );

    }

    public class User {
        public final String first_name, last_name, email, password, co_parent_name, group;

        public User(final String first_name, final String last_name, final String email, final String password, final String co_parent_name, final String group) {
            this.first_name = first_name;
            this.last_name = last_name;
            this.email = email;
            this.password = password;
            this.co_parent_name = co_parent_name;
            this.group = group;
        }
    }

}
