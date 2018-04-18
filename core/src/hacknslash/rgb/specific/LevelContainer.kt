package hacknslash.rgb.specific

import com.badlogic.gdx.Game
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import hacknslash.rgb.general.GArr
import hacknslash.rgb.general.GAssMan
import hacknslash.rgb.general.GScreen
import hacknslash.rgb.general.InputHandler
import hacknslash.rgb.general.gameobjects.GActor
import hacknslash.rgb.general.gameobjects.GControllable
import hacknslash.rgb.general.gameobjects.GDrawer
import hacknslash.rgb.general.gameobjects.GMover
import hacknslash.rgb.general.physics.GPhysic
import hacknslash.rgb.general.GInput

class LevelContainer(game: Game, assMan: GAssMan, spriteBatch: SpriteBatch) : GScreen(game, spriteBatch, width, height),
        InputHandler,
        GControllable {

    override val input = GInput()
    val gPhysic = GPhysic()
    val player = Player.get(assMan)
    val movers: GArr<GMover> = GArr()
    val controlled: GArr<GControllable> = GArr()
    val drawers: GArr<GDrawer> = GArr()

    init {
        addActor(player)
        addActor(this)
        keyPressed(Input.Keys.E, {clear = true})
        keyPressed(Input.Keys.C, {player.changeColor()})
    }

    override fun render(delta: Float) {
        cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0f)
        super.render(delta)
        batch.begin()
        drawers.forEach { it.draw(batch) }
        batch.end()

        movers.forEach { it.act(delta) }
        controlled.forEach { it.control(this) }

        gPhysic.act(delta)
        gPhysic.debug(cam)
    }

    fun addActor(o: Any) {
        if (o is GControllable) controlled.add(o)
        if (o is GMover) movers.add(o)
        if (o is GDrawer) drawers.add(o)
    }

    companion object {
        val width = 160f
        val height = 100f
    }
}