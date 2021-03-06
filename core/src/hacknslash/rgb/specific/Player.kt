package hacknslash.rgb.specific

import java.util.Random
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.g2d.TextureRegion
import hacknslash.rgb.general.GAssMan
import hacknslash.rgb.general.physics.GDim
import hacknslash.rgb.general.GInput
import hacknslash.rgb.general.physics.GVec2
import hacknslash.rgb.general.gameobjects.GActor
import hacknslash.rgb.general.gameobjects.GControllable
import hacknslash.rgb.general.gameobjects.GDrawer
import hacknslash.rgb.general.gameobjects.GMover

class Player private constructor(assMan: GAssMan, override val img: TextureRegion = assMan.square()) : GActor(dim),
        GDrawer,
        GMover,
        GControllable {

    override val color = Color()
    override val maxSpeed = 1000f
    override val pPos = GVec2()
    override val dir = GVec2()

    override val input: GInput = GInput()

    init {
        keyPressed(Input.Keys.UP,    {dir.y += 1f})
        keyPressed(Input.Keys.DOWN,  {dir.y -= 1f})
        keyPressed(Input.Keys.LEFT,  {dir.x -= 1f})
        keyPressed(Input.Keys.RIGHT, {dir.x += 1f})
        changeColor()
    }

    fun changeColor() {
        color.set(rnd.nextFloat(), rnd.nextFloat(), rnd.nextFloat(), 1f)
    }

    companion object {
        val dim = GDim(3f, 3f)
        val rnd = Random()
        fun get(assMan: GAssMan): Player {
            return Player(assMan)
        }
    }
}