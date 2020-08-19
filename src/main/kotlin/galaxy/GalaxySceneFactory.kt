package galaxy

import com.almasb.fxgl.app.scene.FXGLMenu
import com.almasb.fxgl.app.scene.SceneFactory
import galaxy.menu.GalaxyAppMainMenu

class GalaxySceneFactory : SceneFactory() {

	override fun newMainMenu(): FXGLMenu {
		return GalaxyAppMainMenu()
	}
}