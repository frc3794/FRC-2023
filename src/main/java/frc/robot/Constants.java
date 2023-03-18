package frc.robot;

public final class Constants {

public static class DrivetrainConstants {
    public static final int[] kMotorPorts = {7, 0, 12, 3, 4, 5, 12, 13, 14, };
    //ENCODERS
    public static final double kEncoderResolution = 1024;
    //SPEED
    public static final double kSensibilityPercent = 0.85;
    public static final double kRevPerMeters = 10.75/((6 * 2.54 * Math.PI)/100.00);
}
  //MOVILE STAGE
  public static class MStageConstants {
  public static final int kForwardLimit = 2;
  public static final int kReverseLimit = 1;
  public static final double kMovileStageSpeed = .4;
  }
  //ARM JOINT
  public static class ArmJointConstants {
    public static final int kArmJointLimit = 3;
    public static final double kArmJointSpeed = .25;
  }
  //ELEVATOR
  public static class ElevatorConstants {
    public static double[] kEncoderLimits = {0, 10, 20};
    public static final double kElevatorSpeed = .5;  
  }
  
}
