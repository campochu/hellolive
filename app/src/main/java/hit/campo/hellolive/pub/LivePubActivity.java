package hit.campo.hellolive.pub;

import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.TextureView;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;

import hit.campo.hellolive.R;
import hit.campo.live.pub.preview.camera.CameraAgent;
import hit.campo.live.pub.preview.camera.CameraAgentImpl;

/**
 * ckb on 2017/2/18.
 */

public class LivePubActivity extends AppCompatActivity {

    private TextureView mTextureView;

    private SeekBar mBitRateSeekBar;
    private SeekBar mFpsSeekBar;

    private TextView mNetworkSpeed;
    private Button mLaunchBtn;

    private int bitRate = 600;
    private int frameRate = 24;
    private int gopSize = 24;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_live_pub);

        mTextureView = (TextureView) findViewById(R.id.textureview_pub);
        mTextureView.setSurfaceTextureListener(mTextureListener);

        mBitRateSeekBar = (SeekBar) findViewById(R.id.seek_bar_bitrate);
        mFpsSeekBar = (SeekBar) findViewById(R.id.seek_bar_fps);

        mNetworkSpeed = (TextView) findViewById(R.id.txt_speed);
        mLaunchBtn = (Button) findViewById(R.id.btn_start);


    }

    CameraAgent agent = new CameraAgentImpl();
    Camera camera;

    private TextureView.SurfaceTextureListener mTextureListener = new TextureView.SurfaceTextureListener() {
        @Override
        public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i1) {
            int cameraId = agent.getCameraDeviceInfo().getFrontCameraId();
            camera = Camera.open(cameraId);
            try {
                camera.setPreviewTexture(surfaceTexture);
                camera
                        .setDisplayOrientation(
                                agent.getCameraDeviceInfo()
                                        .getOneCamera(cameraId)
                                        .getPreviewOrientation(
                                                getWindowManager()
                                                        .getDefaultDisplay()
                                                        .getRotation()
                                                , true)
                        );
                camera.startPreview();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i1) {

        }

        @Override
        public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
            if (camera != null) {
                camera.release();
            }
            return true;
        }

        @Override
        public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {

        }
    };
}
