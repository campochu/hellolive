package hit.campo.hellolive.pub;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.TextureView;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import hit.campo.hellolive.R;

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

        mBitRateSeekBar = (SeekBar) findViewById(R.id.seek_bar_bitrate);
        mFpsSeekBar = (SeekBar) findViewById(R.id.seek_bar_fps);

        mNetworkSpeed = (TextView) findViewById(R.id.txt_speed);
        mLaunchBtn = (Button) findViewById(R.id.btn_start);

        mTextureView = (TextureView) findViewById(R.id.textureview_pub);

    }
}
