/**
* <P>A class representing the face of die made for the SliceNDice game.
* <P>Each face has a enum FaceType of Sword, Shield, Heal, or Broken Shield, as
* well as a integer numerical value.
*
* @author Don Murphy
* @author Nicolas Raymundo
*/
public class DiceFace {
    /**
     * Types of dice faces
     */
    public enum FaceType {
        SWORD("Sword"), SHIELD("Shield"), HEAL("Heal"), BROKEN_SHIELD("Broken Shield");
        private String value;

        private FaceType(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }
    }

    private FaceType faceType;
    private long faceValue;

    /**
     * Constructs a single DiceFace with a single sword.
     */
    public DiceFace() {
        this.faceType = FaceType.SWORD;
        this.faceValue = 1;
    }

    /**
     * Constructs a single DiceFace of the given face type and value.
     *
     * @param faceType   the type of face (Sword, Shield, Heal, or Broken Shield)
     * @param faceValue  the value of the face (for example, a sword face with value 2 is equivalent to 2 value-1 sword faces)
     */
    public DiceFace(FaceType faceType, long faceValue) {
        if (faceType == FaceType.BROKEN_SHIELD && faceValue != 1) {
            throw new IllegalArgumentException();
        }
        this.faceType = faceType;
        this.faceValue = faceValue;
    }

    /**
     * Returns the face type of this dice face.
     *
     * @return  the type of face (Sword, Shield, Heal, or Broken Shield)
     */
    public FaceType getFaceType() {
        return this.faceType;
    }
    /**
     * Returns the value of this dice face.
     *
     * @return  the value of the dice face
     */
    public long getFaceValue() {
        return this.faceValue;
    }

    /**
     * Changes the face type of this dice face. If the new face type
     * is a Broken Shield, the face's value is changed to 1.
     *
     * @param faceType   the new type of face to be applied to this dice face
     */
    public void setFaceType(FaceType faceType) {
        if (faceType == FaceType.BROKEN_SHIELD) {
            this.faceValue = 1;
        }
        this.faceType = faceType;
    }

    /**
     * Changes the value of this dice face. If the face type is already Broken
     * Shield, an IllegalArgumentException will be thrown if the new value isn't 1.
     *
     * @param faceValue   the new value of face to be applied to this dice face
     * @throws IllegalArgumentException   if this face's type is a broken shield
     *                                    and the new value isn't 1
     */
    public void setFaceValue(long faceValue) {
        if (this.faceType == FaceType.BROKEN_SHIELD && faceValue != 1) {
            throw new IllegalArgumentException();
        }
        this.faceValue = faceValue;
    }

    /**
     * Indicates whether some other object is "equal to" this one, being a
     * DiceFace with the same face type and numerical value.
     *
     * @param obj  another object that might be equal to this DiceFace
     * @return     <code>true</code> if this object is equal to the obj
     *                 argument; <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        DiceFace other = (DiceFace)obj;
        return (this.faceType == other.faceType) && (this.faceValue == other.faceValue);
    }

    /**
     * Returns a string representation of the DiceFace, including the face type
     * and numerical value.
     *
     * @return  a String representation of the DiceFace
     */
    @Override
    public String toString() {
        return this.faceType.getValue() + " " + this.faceValue;
    }

    // Advanced Java---Proceed at own risk!
    /**
     * Returns a hash code value for the DiceFace unique to the value and
     * face type combination.
     *
     * @return     a hash code value for this DiceFace
     */
    @Override
    public int hashCode() {
        final int prime = 43;
        int result = 1;
        result = prime * result + ((faceType == null) ? 0 : faceType.hashCode());
        result = prime * result + (int)faceValue;
        return result;
    }
}
