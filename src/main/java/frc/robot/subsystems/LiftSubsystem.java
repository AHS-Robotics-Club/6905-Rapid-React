package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.LiftConstants;

public class LiftSubsystem extends SubsystemBase {

    private final DoubleSolenoid doubleSolenoid = new DoubleSolenoid(PneumaticsModuleType.REVPH, LiftConstants.solIn,
            LiftConstants.solOut);

    public LiftSubsystem() {

        doubleSolenoid.set(Value.kForward);
    }

    public void pull() {
        doubleSolenoid.set(Value.kForward);
    }

    public void lift() {
        doubleSolenoid.set(Value.kReverse);
    }
}
