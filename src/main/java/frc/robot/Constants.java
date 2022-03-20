// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean
 * constants. This class should not be used for any other purpose. All constants
 * should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final class DriveConstants {
        // Left Drive
        public static final int kLeftMotor1Port = 5;
        public static final int kLeftMotor2Port = 6;
        // Right Drive
        public static final int kRightMotor1Port = 7;
        public static final int kRightMotor2Port = 8;

    }

    public static final class ShooterConstants {
        // I'm lazy
        public static final int kCompressor = 11;

        public static final int kShooter1 = 15;
        public static final int kShooter2 = 16;

        public static final double kP = 6.7992E-09;
        public static final double kI = 0.0;
        public static final double kD = 0.0;

        public static final double kF = 0.000015;

        public static final double kMin = -1.0, kMax = 1.0;

    }

    public static final class TransportConstants {
        public static final int kTransport1 = 18;
    }

    public static final class IntakeConstants {
        public static final int kIntake1 = 19;
    }

    public static final class OIConstants {
        public static final int kDriverControllerPort = 0;
    }
}
