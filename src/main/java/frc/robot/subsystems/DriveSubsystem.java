package frc.robot.subsystems;

import java.util.function.DoubleSupplier;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

public class DriveSubsystem extends SubsystemBase {

    private final MotorControllerGroup leftMotors = new MotorControllerGroup(
            new CANSparkMax(DriveConstants.kLeftMotor1Port, MotorType.kBrushless),
            new CANSparkMax(DriveConstants.kLeftMotor2Port, MotorType.kBrushless));

    private final MotorControllerGroup rightMotors = new MotorControllerGroup(
            new CANSparkMax(DriveConstants.kRightMotor1Port, MotorType.kBrushless),
            new CANSparkMax(DriveConstants.kRightMotor2Port, MotorType.kBrushless));

    private final DifferentialDrive diffDrive = new DifferentialDrive(leftMotors, rightMotors);

    public DriveSubsystem() {
        rightMotors.setInverted(true);
    }

    public void drive(double xSpeed, double rotation, DoubleSupplier multiplier) {
        diffDrive.arcadeDrive(xSpeed * multiplier.getAsDouble(), rotation * multiplier.getAsDouble(), true);
    }

}