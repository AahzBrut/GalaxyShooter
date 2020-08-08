package galaxy.instrumentation

import com.almasb.fxgl.texture.AnimationChannel
import com.almasb.fxgl.texture.Texture
import javafx.geometry.Rectangle2D

class ControllableAnimatedTexture (
    defaultChannel: AnimationChannel,
    private var animationDriver: (Double) -> Int) : Texture(defaultChannel.image) {

    private var animationChannel: AnimationChannel = defaultChannel

    private var currentFrame : Int = 0

    override fun onUpdate(tpf: Double) {
        currentFrame = animationDriver(tpf)
        updateImage()
    }

    private fun updateImage() {
        val frameData = animationChannel.getFrameData(currentFrame)

        image = animationChannel.image
        fitWidth = frameData.width.toDouble()
        fitHeight = frameData.height.toDouble()
        viewport = Rectangle2D(frameData.x.toDouble(), frameData.y.toDouble(), frameData.width.toDouble(), frameData.height.toDouble())
    }
}