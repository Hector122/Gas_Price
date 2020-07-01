package com.android.gaspricerd.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.gaspricerd.R;
import com.android.gaspricerd.reposiroty.ClientApi;
import com.android.gaspricerd.reposiroty.ServiceGenerator;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Fragment
 */
public class PriceFragment extends Fragment {
    ClientApi clientApi;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        clientApi = ServiceGenerator.createService(ClientApi.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_prices, container, false);

        Call<ResponseBody> call = clientApi.getCurrentGasPrice();
        Log.d("URL: " , call.request().url().toString());
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                TextView textView =  view.findViewById(R.id.txt_test);
                textView.setText(response.raw().toString());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

        return view;
    }
//    /**
     //     * Converts the contents of an InputStream to a String.
     //     */
//    private String readStream(InputStream stream) throws IOException {
//        Reader reader = new InputStreamReader(stream, StandardCharsets.UTF_8);
//        BufferedReader in = new BufferedReader(reader);
//        StringBuilder builder = new StringBuilder();
//
//        String line;
//        while ((line = in.readLine()) != null) {
//            builder.append(line);
//
//            //Find the token hash
//            if (line.contains(STATEFUL_HASH)) {
//                String[] values = line.split(VALUE_EQUAL);
//
//                Pattern pattern = Pattern.compile(REGEX);
//                Matcher matcher = pattern.matcher(values[1]);
//
//                if (matcher.find()) {
//                    mHashToken = matcher.group(1);
//                    break;
//                }
//            }
//        }
//
//        return builder.toString();
//    }

}
