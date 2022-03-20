package frc.robot;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;

public class Trajectories {

    public static Trajectory trajTest() {
        Pose2d start = new Pose2d();
        Pose2d end = new Pose2d(-2, 0, new Rotation2d(0));

        TrajectoryConfig config = new TrajectoryConfig(3.0, 3.0);
        return TrajectoryGenerator.generateTrajectory(start, null, end, config);
    }

}
