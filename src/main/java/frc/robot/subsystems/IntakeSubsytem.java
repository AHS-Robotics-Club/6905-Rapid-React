package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;

public class IntakeSubsytem extends SubsystemBase {

    private final CANSparkMax intakeMotor = new CANSparkMax(IntakeConstants.kIntake1, MotorType.kBrushless);

    public void intake() {
        intakeMotor.set(-0.8);
    }

    public void outtake() {
        intakeMotor.set(0.6);
    }

    public void stop() {
        intakeMotor.stopMotor();
    }
}
