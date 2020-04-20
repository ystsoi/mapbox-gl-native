#pragma once

#include <mbgl/util/size.hpp>
#include "quaternion.hpp"

namespace mbgl {

class LatLng;

namespace util {

class Camera {
public:
    Camera();

    vec3 getPosition() const;
    mat4 getCameraToWorld(double zoom, bool flippedY) const;
    mat4 getWorldToCamera(double zoom, bool flippedY) const;
    mat4 getCameraToClipPerspective(double fovy, double aspectRatio, double nearZ, double farZ) const;

    vec3 forward() const;
    vec3 right() const;
    vec3 up() const;

    void lookAtPoint(const LatLng& location);

    const Quaternion& getOrientation() const { return orientation; }
    void getOrientation(double& pitch, double& bearing) const;
    void setOrientation(double pitch, double bearing);

    void setPosition(const vec3& mercatorPosition);

private:
    Quaternion orientation;
    mat4 cameraTransform; // Position (mercator) and orientation of the camera
};

} // namespace util
} // namespace mbgl