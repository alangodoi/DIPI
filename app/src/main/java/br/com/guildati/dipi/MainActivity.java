package br.com.guildati.dipi;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Vibrator;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    SurfaceView sv_camera;
    TextView tv_result;
    CameraSource cameraSource;
    BarcodeDetector barcodeDetector;
    private boolean cancelled;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        cancelled = false;
        setContentView(R.layout.activity_main);

        sv_camera = (SurfaceView) findViewById(R.id.sv_cameraPreview);
        tv_result = (TextView) findViewById(R.id.tv_result);
    }

    @Override
    protected void onResume() {
        super.onResume();
        cancelled = false;
        tv_result.setText(R.string.txt_focus);
        test();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.login:
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
                return true;
            case R.id.registro:
                Intent in = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(in);
                return true;
            case R.id.config:
                Toast.makeText(MainActivity.this, "Conf", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void test() {

        barcodeDetector = new BarcodeDetector.Builder(this).setBarcodeFormats(Barcode.ALL_FORMATS).build();

        cameraSource = new CameraSource.Builder(this, barcodeDetector).setAutoFocusEnabled(true).build();

        sv_camera.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                try {
                    cameraSource.start(holder);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }
        });

        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> barcodes = detections.getDetectedItems();

                if (barcodes.size() != 0) {
                    tv_result.post(new Runnable() {
                        @Override
                        public void run() {
                            while (!cancelled) {
                                Vibrator vibrator = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                                vibrator.vibrate(1000);
                                tv_result.setText(barcodes.valueAt(0).displayValue);
                                showData(tv_result.getText().toString());
                                cancelled = true;
                            }
                        }
                    });
                }
            }
        });
    }

    public void showData(String barcode){

        if (barcode.equals("7891203063311")){
            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
            startActivity(intent);
        } else if (barcode.equals("7896066317595")){
            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
            startActivity(intent);
        } else {
            tv_result.setText(R.string.txt_product_unknown);
            cancelled = false;
            test();
        }
    }







}
