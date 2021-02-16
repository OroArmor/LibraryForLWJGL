package com.oroarmor.core.glfw.event.mouse;

import com.oroarmor.core.glfw.event.mouse.button.MouseButton;

import static org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_LAST;

/**
 * This class keeps track of all the down buttons and position of the mouse
 *
 * @author OroArmor
 */
public class MouseStatus {
    /**
     * An array that keeps track of whether or not a mouse button is pushed
     */
    private static final boolean[] mouseButtonStatus = new boolean[GLFW_MOUSE_BUTTON_LAST];

    /**
     * Mouse position values
     */
    private static float mouseX, mouseY, pastMouseX, pastMouseY, deltaMouseX, deltaMouseY;

    /**
     * No instances for you
     */
    private MouseStatus() {
    }

    /**
     * @return The current delta x for the mouse
     */
    public static float getDeltaMouseX() {
        return deltaMouseX;
    }

    public static void setDeltaMouseX(float _deltaMouseX) {
        deltaMouseX = _deltaMouseX;
    }

    /**
     * @return The current delta y for the mouse
     */
    public static float getDeltaMouseY() {
        return deltaMouseY;
    }

    public static void setDeltaMouseY(float _deltaMouseY) {
        deltaMouseY = _deltaMouseY;
    }

    /**
     * @return The current x pos of the mouse
     */
    public static float getMouseX() {
        return mouseX;
    }

    public static void setMouseX(float _mouseX) {
        mouseX = _mouseX;
    }

    /**
     * @return The current y pos of the mouse
     */
    public static float getMouseY() {
        return mouseY;
    }

    public static void setMouseY(float _mouseY) {
        mouseY = _mouseY;
    }

    /**
     * @return The past mouse x position
     */
    public static float getPastMouseX() {
        return pastMouseX;
    }

    public static void setPastMouseX(float _pastMouseX) {
        pastMouseX = _pastMouseX;
    }

    /**
     * @return The past mouse y position
     */
    public static float getPastMouseY() {
        return pastMouseY;
    }

    public static void setPastMouseY(float _pastMouseY) {
        pastMouseY = _pastMouseY;
    }

    /**
     * Returns true if the button is down
     *
     * @param button The button to check
     * @return True if down
     */
    public static boolean isMouseButtonDown(MouseButton button) {
        return mouseButtonStatus[button.getMouseButtonID()];
    }

    public static void setMouseButtonDown(MouseButton button) {
        mouseButtonStatus[button.getMouseButtonID()] = true;
    }

    public static void setMouseButtonUp(MouseButton button) {
        mouseButtonStatus[button.getMouseButtonID()] = false;
    }

    /**
     * Updates the mouse values
     *
     * @param xpos New xpos
     * @param ypos New y pos
     */
    public static void updateMousePositon(float xpos, float ypos) {
        pastMouseX = mouseX;
        pastMouseY = mouseY;

        mouseX = xpos;
        mouseY = ypos;

        deltaMouseX = mouseX - pastMouseX;
        deltaMouseY = mouseY - pastMouseY;
    }
}
