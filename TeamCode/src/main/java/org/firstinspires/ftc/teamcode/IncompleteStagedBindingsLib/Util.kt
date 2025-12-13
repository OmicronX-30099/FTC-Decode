package org.firstinspires.ftc.teamcode.IncompleteStagedBindingsLib

public infix fun Pair<Runnable, Binder>.inStage(stage: String): Pair<Stage, Pair<Runnable, Binder>> {
    return Pair(Stage(stage), this)
}
public infix fun Pair<Runnable, Binder>.inStage(stage: Stage): Pair<Stage, Pair<Runnable, Binder>> {
    return Pair(stage, this)
}