package org.firstinspires.ftc.teamcode.BindingsLib

import androidx.core.util.Supplier

class Button(val valueSupplier: Supplier<Boolean>) {
    private var currentStatus: Boolean = false
    private var previousStatus: Boolean = false

    private var currentStage: Stage = Stage.Default
    private var allLayers = mutableListOf<Stage>()

    fun get(): Boolean {
        return this.currentStatus
    }

    private val whenPressedActions: StagedActions =  StagedActions()
    private val whenReleasedActions: StagedActions =  StagedActions()
    private val whileHeldActions: StagedActions =  StagedActions()
    private val whileReleasedActions: StagedActions =  StagedActions()

    private class StagedActions() {
        private val Actions = mutableListOf<Pair<Stage, Runnable>>()

        fun run(currentStage: String?) = Actions.forEach {
            val stage = it.first
            if (stage.name == currentStage) {
                it.second.run()
            }
        }

        fun add(stage: Stage, action: Runnable) {
            Actions.add(stage to action)
        }

        fun reset() {
            Actions.clear()
        }
    }
}