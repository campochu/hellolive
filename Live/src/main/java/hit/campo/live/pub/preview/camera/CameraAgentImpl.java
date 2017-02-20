package hit.campo.live.pub.preview.camera;

import android.hardware.Camera;

import hit.campo.live.util.log.Logger;
import hit.campo.live.util.log.QLog;

/**
 * ckb on 2017/2/20.
 */

public class CameraAgentImpl extends CameraAgent {

    private static final Logger Log = QLog.getLogger(CameraAgentImpl.class);

    @Override
    public CameraDeviceInfo getCameraDeviceInfo() {
        return CameraDeviceInfoPreL.create();
    }

    private static class CameraDeviceInfoPreL implements CameraDeviceInfo {

        private Camera.CameraInfo[] cameraInfos;

        private int numberOfCameras;
        private int frontCameraId;
        private int backCameraId;

        public CameraDeviceInfoPreL(Camera.CameraInfo[] cameraInfos, int numberOfCameras, int frontCameraId, int backCameraId) {
            this.cameraInfos = cameraInfos;
            this.numberOfCameras = numberOfCameras;
            this.frontCameraId = frontCameraId;
            this.backCameraId = backCameraId;
        }

        public static CameraDeviceInfoPreL create() {
            int numberOfCameras;
            Camera.CameraInfo[] cameraInfos;
            try {
                numberOfCameras = Camera.getNumberOfCameras();
                cameraInfos = new Camera.CameraInfo[numberOfCameras];
                for (int i = 0; i < numberOfCameras; i++) {
                    cameraInfos[i] = new Camera.CameraInfo();
                    Camera.getCameraInfo(i, cameraInfos[i]);
                }
            } catch (RuntimeException ex) {
                Log.err("Exception while creating CameraDeviceInfo", ex);
                return null;
            }

            int frontCamera = NO_DEVICE;
            int backCamera = NO_DEVICE;
            for (int i = 0; i < numberOfCameras; i++) {
                if (Camera.CameraInfo.CAMERA_FACING_BACK == cameraInfos[i].facing) {
                    backCamera = i;
                    continue;
                }
                if (Camera.CameraInfo.CAMERA_FACING_FRONT == cameraInfos[i].facing) {
                    frontCamera = i;
                }
            }
            return new CameraDeviceInfoPreL(cameraInfos, numberOfCameras, frontCamera, backCamera);
        }

        @Override
        public OneCamera getOneCamera(int cameraId) {
            Camera.CameraInfo info = cameraInfos[cameraId];
            if (info != null) {
                return new OneCameraPreL(info);
            } else {
                return null;
            }
        }

        @Override
        public int getNumberOfCameras() {
            return numberOfCameras;
        }

        @Override
        public int getBackCameraId() {
            return backCameraId;
        }

        @Override
        public int getFrontCameraId() {
            return frontCameraId;
        }

        private static class OneCameraPreL extends OneCamera {

            private Camera.CameraInfo cameraInfo;

            public OneCameraPreL(Camera.CameraInfo cameraInfo) {
                this.cameraInfo = cameraInfo;
            }

            @Override
            public boolean isFacingFront() {
                return cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT;
            }

            @Override
            public boolean isFacingBack() {
                return cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_BACK;
            }

            @Override
            public int getSensorOrientation() {
                return cameraInfo.orientation;
            }

        }
    }

}
