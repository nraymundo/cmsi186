public class RiskyDie extends CombatDie {
    /**
     * Constructs a 8-sided die with the following faces:
     * <ul>
     * <li>3 double-Sword faces
     * <li>2 Shield faces
     * <li>1 Heal face
     * <li>2 Broken Shield faces
     * </ul>
     */
    public RiskyDie() {
        this.faces = new DiceFace[8];
        this.faces[0] = new DiceFace(DiceFace.FaceType.SWORD, 2);
        this.faces[1] = new DiceFace(DiceFace.FaceType.SWORD, 2);
        this.faces[2] = new DiceFace(DiceFace.FaceType.SWORD, 2);
        this.faces[3] = new DiceFace(DiceFace.FaceType.SHIELD, 1);
        this.faces[4] = new DiceFace(DiceFace.FaceType.SHIELD, 1);
        this.faces[5] = new DiceFace(DiceFace.FaceType.HEAL, 1);
        this.faces[6] = new DiceFace(DiceFace.FaceType.BROKEN_SHIELD, 1);
        this.faces[7] = new DiceFace(DiceFace.FaceType.BROKEN_SHIELD, 1);
        this.faceUpIndex = 0;
    }
}
