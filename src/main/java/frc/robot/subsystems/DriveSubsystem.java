package frc.robot.subsystems;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

import java.util.function.DoubleSupplier;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class DriveSubsystem extends SubsystemBase {

    private final CANSparkMax leftMain = new CANSparkMax(DriveConstants.kLeftMotor1Port, MotorType.kBrushless);
    private final CANSparkMax leftFollower = new CANSparkMax(DriveConstants.kLeftMotor2Port, MotorType.kBrushless);

    private final CANSparkMax rightMain = new CANSparkMax(DriveConstants.kRightMotor1Port, MotorType.kBrushless);
    private final CANSparkMax rightFollower = new CANSparkMax(DriveConstants.kRightMotor2Port, MotorType.kBrushless);

    private final DifferentialDrive robotDrive;

    private final RelativeEncoder leftEncoder;
    private final RelativeEncoder rightEncoder;

    // 6in diameter | what is gear ratio?????
    private final double distancePerPulse = (Units.inchesToMeters(6) * Math.PI) / 8192.0;

    private final Gyro gyro = new ADXRS450_Gyro();

    private final DifferentialDriveOdometry odometry;
    private final DifferentialDriveKinematics kinematics;

    public DriveSubsystem() {
        rightMain.setInverted(true);
        setMaxOutput(0.8);

        leftFollower.follow(leftMain);
        rightFollower.follow(rightMain);

        leftEncoder = leftMain.getEncoder();
        rightEncoder = rightMain.getEncoder();

        leftEncoder.setPositionConversionFactor(distancePerPulse);
        rightEncoder.setPositionConversionFactor(distancePerPulse);

        robotDrive = new DifferentialDrive(leftMain, rightMain);

        resetEncoders();
        odometry = new DifferentialDriveOdometry(gyro.getRotation2d());

        kinematics = new DifferentialDriveKinematics(Units.inchesToMeters(20));
    }

    @Override
    public void periodic() {
        odometry.update(gyro.getRotation2d(), leftEncoder.getPosition(), rightEncoder.getPosition());
    }

    public void drive(double xSpeed, double rotation) {
        robotDrive.arcadeDrive(xSpeed, rotation, true);
    }

    public void drive(double xSpeed, double rotation, DoubleSupplier multiplier) {
        robotDrive.arcadeDrive(xSpeed * multiplier.getAsDouble(), rotation * multiplier.getAsDouble(), true);
    }

    public void driveVolts(double leftV, double rightV) {
        leftMain.setVoltage(leftV);
        rightMain.setVoltage(rightV);
        robotDrive.feed();
    }

    public void setMaxOutput(double maxOutput) {
        robotDrive.setMaxOutput(maxOutput);
    }

    public DifferentialDriveWheelSpeeds getWheelSpeeds() {
        return new DifferentialDriveWheelSpeeds(leftEncoder.getVelocity(), rightEncoder.getVelocity());
    }

    public Pose2d getPose() {
        return odometry.getPoseMeters();
    }

    public double getHeadingDeg() {
        return gyro.getRotation2d().getDegrees();
    }

    public double getTurnRate() {
        return -gyro.getRate();
    }

    public double getAverageEncoderDistance() {
        return (leftEncoder.getPosition() + rightEncoder.getPosition()) / 2.0;
    }

    public RelativeEncoder getLeftEncoder() {
        return leftEncoder;
    }

    public RelativeEncoder getRightEncoder() {
        return rightEncoder;
    }

    public DifferentialDriveKinematics getKinematics() {
        return kinematics;
    }

    public void resetGyro() {
        gyro.reset();
    }

    public void resetEncoders() {
        leftEncoder.setPosition(0.0);
        rightEncoder.setPosition(0.0);
    }

    public void resetOdom(Pose2d pose) {
        resetEncoders();
        odometry.resetPosition(pose, gyro.getRotation2d());
    }

}
