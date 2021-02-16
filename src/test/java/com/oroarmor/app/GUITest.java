package com.oroarmor.app;

import com.oroarmor.core.game.gui.group.GUIGroup;
import com.oroarmor.core.game.gui.object.box.GUIBox;
import com.oroarmor.core.game.gui.object.box.GUIColorBox;
import com.oroarmor.core.game.gui.shader.GUIShaders;
import com.oroarmor.core.game.gui.shader.font.FontShader;
import com.oroarmor.core.game.gui.text.Font;
import com.oroarmor.core.game.gui.text.FontLoader;
import com.oroarmor.core.glfw.Display;
import com.oroarmor.core.glfw.GLFWUtil;
import com.oroarmor.core.glfw.GLFWUtil.OpenGLProfile;
import com.oroarmor.core.glfw.event.key.Key;
import com.oroarmor.core.glfw.event.key.hold.KeyHoldEvent;
import com.oroarmor.core.glfw.event.key.press.KeyPressEvent;
import com.oroarmor.core.glfw.event.key.release.KeyReleaseEvent;
import com.oroarmor.core.glfw.event.mouse.button.press.MousePressEvent;
import com.oroarmor.core.glfw.event.mouse.button.release.MouseReleaseEvent;
import com.oroarmor.core.glfw.event.mouse.over.enter.MouseEnterEvent;
import com.oroarmor.core.glfw.event.mouse.over.leave.MouseLeaveEvent;
import com.oroarmor.core.glfw.event.mouse.position.MousePositionEvent;
import com.oroarmor.core.glfw.event.mouse.scroll.MouseScrollEvent;
import com.oroarmor.core.opengl.Renderer;
import com.oroarmor.core.opengl.Texture;
import com.oroarmor.util.ResourceLoader;
import org.joml.Matrix4f;
import org.joml.Vector4f;

public class GUITest {
    static String textString = "a";

    public static void main(String[] args) {
        Display display = new Display(640, 640, "Open GL Learning") {

            @Override
            public void processKeyHeldEvent(KeyHoldEvent event) {
                // TODO Auto-generated method stub

            }

            @Override
            public void processKeyPressedEvent(KeyPressEvent event) {

                // TODO Auto-generated method stub
                if (event.key != Key.BACKSPACE) {
                    char c;

                    if (event.getEventMods().isShift()) {
                        c = event.key.getUpperChar();
                    } else {
                        c = event.key.getLowerChar();
                    }

                    textString += c == '\0' ? "" : c;

                } else if (textString.length() > 0) {
                    textString = textString.substring(0, textString.length() - 1);
                }
            }

            @Override
            public void processKeyReleasedEvent(KeyReleaseEvent event) {
                if (event.getKey() == Key.ESCAPE) {
                    close();
                }
            }

            @Override
            public void processMouseEnterEvent(MouseEnterEvent event) {
                // TODO Auto-generated method stub

            }

            @Override
            public void processMouseLeaveEvent(MouseLeaveEvent event) {
                // TODO Auto-generated method stub

            }

            @Override
            public void processMousePositionEvent(MousePositionEvent event) {
                // TODO Auto-generated method stub

            }

            @Override
            public void processMousePressEvent(MousePressEvent event) {
                // TODO Auto-generated method stub

            }

            @Override
            public void processMouseReleasedEvent(MouseReleaseEvent event) {
                // TODO Auto-generated method stub

            }

            @Override
            public void processMouseScrolledEvent(MouseScrollEvent event) {
                // TODO Auto-generated method stub

            }

        };

        display.enableTransparency();

        // Set the OpenGL version to 4.5 core
        GLFWUtil.setWindowHints(4, 5, OpenGLProfile.CORE);

        // Create a renderer
        Renderer renderer = new Renderer();

        Font testFont = FontLoader.loadFontFromData(ResourceLoader.loadFileString(GUITest.class.getClassLoader().getResourceAsStream("com/oroarmor/app/bank.fnt")),
                new Texture("com/oroarmor/app/bank.png"));

        display.setClearColor(0, 0, 0, 1);

        FontShader shader = new FontShader();
        shader.bind();
        shader.setTexture(testFont.getTexture());
        shader.setColor(new Vector4f(1, 0, 0, 1));
        shader.setObjectModel(new Matrix4f().translation(0, 0, 0));

        GUIColorBox box = new GUIColorBox(105, 105, 190, 90, new Vector4f(1, 0, 1, 1));
        GUIColorBox box2 = new GUIColorBox(105, 205, 190, 90, new Vector4f(0, 0.7f, 0.6f, 1));

        GUIBox bg = new GUIBox(100, 100, 200, 200);

        GUIGroup group = new GUIGroup(100, 100) {
        };

        group.addChildren(box, box2);

        GUIGroup ui = new GUIGroup(0, 0) {
        };

        ui.addChildren(group, bg);

        while (display.shouldNotClose()) {
            // Clear the display
            display.clear();

            GUIShaders.updateShaderView(display.getOrthoViewModel());

            shader.setColor(new Vector4f(1, 0, (float) Math.sin(System.currentTimeMillis() / 1000d) * 0.5f + 0.5f, 1));

            shader.setOrthoView(display.getOrthoViewModel());
            testFont.getTextMesh(textString, 1f, display.getWidth()).render(renderer, shader);

            ui.renderChildren(renderer);
            display.render();
        }

        // Close the display
        display.close();
    }
}
