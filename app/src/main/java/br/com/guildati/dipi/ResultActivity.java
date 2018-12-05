package br.com.guildati.dipi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    ImageView iv_productImage;
    TextView tv_productInformation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        iv_productImage = (ImageView) findViewById(R.id.iv_productImage);
        tv_productInformation = (TextView) findViewById(R.id.tv_productInformation);
    }
}
