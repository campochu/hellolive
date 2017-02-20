package hit.campo.live.pub.preview.camera;

/**
 * ckb on 2017/2/20.
 */

public abstract class CameraAgent {

    public abstract CameraDeviceInfo getCameraDeviceInfo();

    public interface CameraDeviceInfo {

        static final int NO_DEVICE = -1;

        OneCamera getOneCamera(int cameraId);

        int getNumberOfCameras();

        int getBackCameraId();

        int getFrontCameraId();


        public abstract class OneCamera {

            public abstract boolean isFacingFront();

            public abstract boolean isFacingBack();

            public abstract int getSensorOrientation();

            public int getPreviewOrientation(int currentDisplayOrientation, boolean mirror) {
                int result = 0;
                if (isFacingFront()) {
                    result = (getSensorOrientation() + currentDisplayOrientation) % 360;
                    if (mirror) {
                        result = (360 - result) % 360;
                    }
                } else if (isFacingBack()) {
                    result = (getSensorOrientation() - currentDisplayOrientation + 360) % 360;
                }
                return result;
            }

        }

    }

}
