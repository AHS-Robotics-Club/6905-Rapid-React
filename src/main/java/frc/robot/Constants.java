// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

public final class Constants {

    public static final class DriveConstants {

        public static final int kLeftMotor1Port = 5;
        public static final int kLeftMotor2Port = 7;

        public static final int kRightMotor1Port = 6;
        public static final int kRightMotor2Port = 8;

    }

    public static final class IntakeConstants {
        public static final int kIntake1Port = 19;

        public static final int kIntakeSolenoid = 40;

    }

    public static final class ShooterConstants {
        public static final int kTransportMotor = 14;

        public static final int kShooter1Port = 15;
        public static final int kShooter2Port = 16;

        public static final int kEncoder1Port = 22;
        public static final int kEncoder2Port = 23;

        // TODO: Change so many values
        public static final double kP = 1;
        public static final double kI = 0;
        public static final double kD = 0;

        public static final double kS = 0;
        public static final double kV = 0;

    }

    public static final class LiftConstants {
        public static final int solIn = 55;
        public static final int solOut = 56;
    }

    public static final class OIConstants {
        public static final int controller = 0;
    }
}
