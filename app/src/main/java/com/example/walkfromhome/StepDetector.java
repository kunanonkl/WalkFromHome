package com.example.walkfromhome;

public class StepDetector {

    private static int ACCEL_RING_SIZE = 50;
    private static int VEL_RING_SIZE = 10;
    private static float vel_cont = 0;

    // change this threshold according to your sensitivity preferences
    private static float STEP_THRESHOLD = 23.439f;

    private static final int STEP_DELAY_NS = 250000000;

    private int accelRingCounter = 0;
    private float[] accelRingX = new float[ACCEL_RING_SIZE];
    private float[] accelRingY = new float[ACCEL_RING_SIZE];
    private float[] accelRingZ = new float[ACCEL_RING_SIZE];
    private int velRingCounter = 0;
    private float[] velRing = new float[VEL_RING_SIZE];
    private long lastStepTimeNs = 0;
    private float oldVelocityEstimate = 0;

    private StepListener listener;

    public void registerListener(StepListener listener) {
        this.listener = listener;
    }


    public void updateAccel(long timeNs, float x, float y, float z) {
        switch (GlobalVariable.walking_information) {
            case "slow_walk":
                ACCEL_RING_SIZE = 50;
                VEL_RING_SIZE = 60;
                STEP_THRESHOLD = 23.439f;
                break;
            case "normal_walk":
                ACCEL_RING_SIZE = 50;
                VEL_RING_SIZE = 10;
                STEP_THRESHOLD = 23.439f;
                break;
            case "fast_walk":
                break;
            default:
                ACCEL_RING_SIZE = 50;
                VEL_RING_SIZE = 50;
                STEP_THRESHOLD = 23.439f;
        }
        float[] currentAccel = new float[3];
        currentAccel[0] = x;
        currentAccel[1] = y;
        currentAccel[2] = z;

        // First step is to update our guess of where the global z vector is.
        accelRingCounter++;
        accelRingX[accelRingCounter % ACCEL_RING_SIZE] = currentAccel[0];
        accelRingY[accelRingCounter % ACCEL_RING_SIZE] = currentAccel[1];
        accelRingZ[accelRingCounter % ACCEL_RING_SIZE] = currentAccel[2];

        float[] worldZ = new float[3];
        worldZ[0] = SensorFilter.sum(accelRingX) / Math.min(accelRingCounter, ACCEL_RING_SIZE);
        worldZ[1] = SensorFilter.sum(accelRingY) / Math.min(accelRingCounter, ACCEL_RING_SIZE);
        worldZ[2] = SensorFilter.sum(accelRingZ) / Math.min(accelRingCounter, ACCEL_RING_SIZE);

        float normalization_factor = SensorFilter.norm(worldZ);

        worldZ[0] = worldZ[0] / normalization_factor;
        worldZ[1] = worldZ[1] / normalization_factor;
        worldZ[2] = worldZ[2] / normalization_factor;

        float currentZ = SensorFilter.dot(worldZ, currentAccel) - normalization_factor;
        velRingCounter++;
        velRing[velRingCounter % VEL_RING_SIZE] = currentZ;

        float velocityEstimate = SensorFilter.sum(velRing);
        if(GlobalVariable.walking_information == "slow_walk"){
            vel_cont = 8.25f;
            velocityEstimate = velocityEstimate+vel_cont;
        }else if(GlobalVariable.walking_information == "normal_walk"){
            vel_cont = 0f;
            velocityEstimate = velocityEstimate+vel_cont;
        }else{
            vel_cont = 0f;
            velocityEstimate = velocityEstimate+vel_cont;
        }

        if (velocityEstimate > STEP_THRESHOLD && oldVelocityEstimate <= STEP_THRESHOLD
                && (timeNs - lastStepTimeNs > STEP_DELAY_NS)) {
            listener.step(timeNs);
            lastStepTimeNs = timeNs;
        }
        oldVelocityEstimate = velocityEstimate;
    }
}