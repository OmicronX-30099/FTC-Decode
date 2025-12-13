package org.firstinspires.ftc.teamcode.IncompleteStagedBindingsLib

object Binder {
    infix fun run(task: Runnable): Pair<Runnable, Binder> {
        return Pair(task, this)
    }
}