package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class ShooterSubsystem extends SubsystemBase {

    private final CANSparkMax primary = new CANSparkMax(ShooterConstants.kShooter1, MotorType.kBrushless);
    private final CANSparkMax follower = new CANSparkMax(ShooterConstants.kShooter2, MotorType.kBrushless);

    private final RelativeEncoder encoder = primary.getEncoder();

    private final SparkMaxPIDController pidfController = primary.getPIDController();

    private double rpm;

    public ShooterSubsystem() {
        rpm = 0;

        primary.setIdleMode(IdleMode.kCoast);
        follower.setIdleMode(IdleMode.kCoast);

        follower.follow(primary, true);

        pidfController.setP(ShooterConstants.kP);
        pidfController.setI(ShooterConstants.kI);
        pidfController.setD(ShooterConstants.kD);
        pidfController.setFF(ShooterConstants.kF);

        pidfController.setOutputRange(ShooterConstants.kMin, ShooterConstants.kMax);
    }

    public boolean atSetpoint() {
        return encoder.getVelocity() >= rpm - 50 && encoder.getVelocity() <= rpm + 50;
    }

    public double getVelocity() {
        return encoder.getVelocity();
    }

    public void setRPM(double targetRPM) {
        rpm = targetRPM;
    }

    public void setMax() {
        rpm = 5600;
        primary.set(0.6);
    }

    public void stopShooter() {
        rpm = 0;
        primary.stopMotor();
    }

    // 5676
    @Override
    public void periodic() {
        // pidfController.setReference(rpm, ControlType.kVelocity);
    }

}