package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.RamseteController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import frc.robot.subsystems.DriveSubsystem;

public class FollowTrajectoryCommand extends RamseteCommand {

    // public FollowTrajectoryCommand(DriveSubsystem drive, Trajectory trajectory) {
    //     super(trajectory, drive::getPose, new RamseteController(2, 0.7), drive.getKinematics(),
    //             drive::driveVolts, drive);
    // }

    // TODO: Tune PID and stuff yeah
    public FollowTrajectoryCommand(DriveSubsystem drive, Trajectory trajectory) {
        super(trajectory, drive::getPose, new RamseteController(2, 0.7),
                new SimpleMotorFeedforward(1, 0, 0), drive.getKinematics(), drive::getWheelSpeeds,
                new PIDController(1, 0, 0), new PIDController(1, 0, 0), drive::driveVolts, drive);
    }
}
