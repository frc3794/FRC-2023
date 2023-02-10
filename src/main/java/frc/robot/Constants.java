package frc.robot;

public final class Constants {

  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
    public static final int kSubsystemControllerPort = 1;
  }

public static class DrivetrainConstants {
    public static final int[] kMotorPorts = {0, 1, 2, 3, 4, 5, 6, 7};
    //ENCODERS
    public static final double kEncoderResolution = 1024;
    public static final int[][] kEncoderPorns = {{0,1},{2,3}};
    //SPEED
    public static final double kMaxSpeedMPS = 0;
    public static final double kMaxAccelerationMPSS = 0;

    public static final double kSensibilityPercent = 0.85;
  }
}
