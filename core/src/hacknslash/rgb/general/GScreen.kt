package hacknslash.rgb.general

import com.badlogic.gdx.Game
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera

open class GScreen(val game: Game, val batch: SpriteBatch, width: Float, height: Float) : Screen {

    val cam = OrthographicCamera(width, height)
    var clear = false

    override fun hide() {
    }

    override fun show() {
    }

    override fun render(delta: Float) {
        if (clear) {
            Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT or GL20.GL_DEPTH_BUFFER_BIT)
            clear = false
        }
        cam.update()
        batch.projectionMatrix = cam.combined
    }

    override fun pause() {
    }

    override fun resume() {
    }

    override fun resize(width: Int, height: Int) {
    }

    override fun dispose() {
    }
}