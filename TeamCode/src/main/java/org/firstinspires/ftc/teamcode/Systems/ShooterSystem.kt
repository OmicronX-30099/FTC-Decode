package org.firstinspires.ftc.teamcode.Systems

import com.pedropathing.geometry.Pose
import dev.nextftc.core.commands.Command
import dev.nextftc.core.commands.delays.Delay
import dev.nextftc.core.commands.groups.SequentialGroup
import dev.nextftc.core.commands.utility.NullCommand
import dev.nextftc.core.subsystems.SubsystemGroup
import dev.nextftc.extensions.pedro.PedroComponent.Companion.follower
import org.firstinspires.ftc.teamcode.Systems.ShooterSubsystems.FlywheelSubsystem
import org.firstinspires.ftc.teamcode.Systems.ShooterSubsystems.HoodSubsystem
import org.firstinspires.ftc.teamcode.Systems.ShooterSubsystems.KickerSubsystem
import org.firstinspires.ftc.teamcode.Systems.ShooterSubsystems.TurretSubsystem
import kotlin.math.*

object ShooterSystem: SubsystemGroup() {
}