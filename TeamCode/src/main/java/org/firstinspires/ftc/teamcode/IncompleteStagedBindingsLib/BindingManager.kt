package org.firstinspires.ftc.teamcode.IncompleteStagedBindingsLib

object BindingManager {

    @get: JvmName("stage")
    @set: JvmName("stage")
    var currentStage: String = "Default"
    private val buttons = mutableListOf<Button>()

    @JvmStatic
    fun update() {
        buttons.forEach { it.update(currentStage) }
    }

    @JvmStatic
    fun add(button: Button) {
        buttons.add(button)
    }

    @JvmStatic
    fun reset() {
        buttons.forEach { it.clear() }
        currentStage = "Default"
    }
}