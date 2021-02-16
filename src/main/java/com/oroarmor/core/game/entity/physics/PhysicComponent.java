package com.oroarmor.core.game.entity.physics;

import org.joml.Vector3f;

@Deprecated
public class PhysicComponent {
    private Vector3f position;
    private Vector3f velocity;
    private Vector3f acceleration;

    public PhysicComponent(Vector3f position, Vector3f velocity, Vector3f acceleration) {
        this.position = position;
        this.velocity = velocity;
        this.acceleration = acceleration;
    }

    public void addAcceleration(Vector3f add) {
        acceleration.add(add);
    }

    public Vector3f getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(Vector3f acceleration) {
        this.acceleration = acceleration;
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public Vector3f getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector3f velocity) {
        this.velocity = velocity;
    }

    public void update(float delta) {
        velocity.add(acceleration.mul(delta, new Vector3f()));
        position.add(velocity);
        acceleration.zero();
    }

}
