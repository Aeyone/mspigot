package net.minecraft.server;

/** Sweeps a pearl's actual bounding box against a player's unexpanded box. */
final class PearlPlayerCollision {

    private PearlPlayerCollision() {}

    static Vec3D trace(AxisAlignedBB pearlBox, AxisAlignedBB playerBox, Vec3D start, Vec3D end) {
        // Expand by the pearl's extents relative to its position, not a fixed margin.
        // In particular, locY is at the bottom of the pearl, not its centre.
        AxisAlignedBB target = AxisAlignedBB.a(
                playerBox.a - (pearlBox.d - start.a),
                playerBox.b - (pearlBox.e - start.b),
                playerBox.c - (pearlBox.f - start.c),
                playerBox.d + (start.a - pearlBox.a),
                playerBox.e + (start.b - pearlBox.b),
                playerBox.f + (start.c - pearlBox.c));

        // A segment starting inside may never cross a face; it already hits at t=0.
        if (start.a >= target.a && start.a <= target.d
                && start.b >= target.b && start.b <= target.e
                && start.c >= target.c && start.c <= target.f) {
            return start;
        }

        MovingObjectPosition hit = target.a(start, end);
        return hit == null ? null : hit.pos;
    }
}
