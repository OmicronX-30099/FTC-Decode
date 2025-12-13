package org.firstinspires.ftc.teamcode.BindingsLib

import kotlin.properties.Delegates

class Stage(layerName: String? = null) {
    companion object {
        val Default = Stage()
    }

    var name: String by Delegates.notNull()
    init {
        this.name = layerName ?: "Default"
    }
}