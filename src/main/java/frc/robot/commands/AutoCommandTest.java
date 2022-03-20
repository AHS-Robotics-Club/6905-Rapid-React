package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.InternalTransportSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class AutoCommandTest extends SequentialCommandGroup {

    public AutoCommandTest(DriveSubsystem driveSubsystem, ShooterSubsystem shooterSubsystem,
            InternalTransportSubsystem internalTransportSubsystem) {
        addCommands(
                // new RamseteCommand(Trajectories.trajTest(), driveSubsystem.getPose(), new
                // RamseteController(),, )
                new RunCommand(() -> driveSubsystem.drive(-0.8, 0), driveSubsystem).withTimeout(1.0),
                new ParallelDeadlineGroup(new WaitCommand(8),
                        new StartEndCommand(() -> shooterSubsystem.setMax(), () -> shooterSubsystem.stopShooter(),
                                shooterSubsystem),
                        new StartEndCommand(() -> internalTransportSubsystem.feed(),
                                () -> internalTransportSubsystem.stop(), internalTransportSubsystem)));
    }
}
