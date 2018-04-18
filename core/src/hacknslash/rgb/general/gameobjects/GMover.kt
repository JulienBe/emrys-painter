package hacknslash.rgb.general.gameobjects

import hacknslash.rgb.general.physics.GVec2
import hacknslash.rgb.specific.LevelContainer

interface GMover {
    val pPos : GVec2
    val dir : GVec2
    val maxSpeed : Float

    //TODO remove this as
    fun act(delta: Float) {
        pPos.set((this as GActor).pos)
        (this as GActor).pos.add(dir.x * delta, dir.y * delta)

        if (pos.x < 0f)
            pos.x = 0f
        if (pos.y < 0f)
            pos.y = 0f
        if (pos.x > LevelContainer.width - (this as GActor).dim.width)
            pos.x = LevelContainer.width - (this as GActor).dim.width
        if (pos.y > LevelContainer.height - (this as GActor).dim.heigh)
            pos.y = LevelContainer.height - (this as GActor).dim.height
        if (dir.len2() > maxSpeed)
            dir.setLength2(maxSpeed)
    }
}