package frc.robot;

import edu.wpi.first.math.util.Units;

public final class Constants {

  public static class DrivetrainConstants {
    public final int[] kMotorPorts = {15, 7, 12, 6};
    public final double kCircWheels = Units.inchesToMeters (6 * Math.PI);

    public final double kToughBox = 8.46;
  }

  public static class IndexerConstants {
    public final int[] kMotorPorts = {20, 21};
  }

  public static class ClawConstants {
    public final int kPort = 0;
  }

  public static class ArmJointConstants {
    public final int[] kMotorPorts = {2, 1};
    public final double kLimitUp = 3.2;
    public final double kLimitDown = 0.1;
    public final double kDefaultSpeed = 0.15;
  }
  
  public static class AutoConstants {
    /*
     * FP = First Position = Near the charge
     * SP = Second Position = Near the load zone
     * TP = Third Position = Out of the Zone
    */

    public final double kMChargeFP = Units.inchesToMeters(30);
    public final double kMChargeSP = Units.inchesToMeters(66);
    public final double kMChargeTP = Units.inchesToMeters(35);

    public final double kMLoadFP = 1.5;

    public final double kMOutFP = 3.5;
    public final double kMOutSP = 4.5;
  }
  
}
