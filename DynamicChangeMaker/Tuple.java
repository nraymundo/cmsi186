public class Tuple {
    private int[] tupleArray;

    public Tuple (int k) {
        if (k < 0) {
            throw new IllegalArgumentException("Please enter a value greater than or equal to 0.");
        } else {
            tupleArray = new int[k];
        }
    }

    public Tuple (int[] data) {
        tupleArray = new int[data.length];
        for (int i = 0; i < data.length; i++) {
            tupleArray[i] = data[i];
        }
    }

    public static Tuple generateNewTuple (int[] data, int[] newTupleArray) {
        for (int i = 0; i < data.length; i++) {
            newTupleArray[i] = data[i];
        }
        return new Tuple(newTupleArray);
    }

    public static Tuple makeTupleFromData (int[] data) {
        int[] newTupleArray = new int[data.length];
        return generateNewTuple(data, newTupleArray);
    }

    public int getElement (int i) {
        if (i < tupleArray.length && i >= 0) {
            return tupleArray[i];
        } else {
            throw new IllegalArgumentException("The i value has to be less than the length of the tuple array.");
        }
    }

    public void setElement (int i, int value) {
        if (i < tupleArray.length && i >= 0) {
            tupleArray[i] = value;
        } else {
            throw new IllegalArgumentException("The i value has to be less than the length of the tuple array.");
        }
    }

    public Tuple add (Tuple t) {
        int[] sumOfTuples = new int[tupleArray.length];
        int[] tArray = new int[t.tupleArray.length];
        for (int i = 0; i < tupleArray.length; i++) {
            tArray[i] = t.getElement(i);
        }
        if (tupleArray.length != tArray.length) {
            throw new IllegalArgumentException("Please enter 2 tuples with the same length.");
        }
        for (int i = 0; i < tupleArray.length; i++) {
            sumOfTuples[i] = tArray[i] + tupleArray[i];
        }
        return new Tuple(sumOfTuples);
    }

    public Tuple clone() {
        int[] cloneTuple = new int[tupleArray.length];
        return generateNewTuple(tupleArray, cloneTuple);
    }

    public int length() {
        return tupleArray.length;
    }

    public int sum() {
        int total = 0;
        for (int i = 0; i < tupleArray.length; i++) {
            total += tupleArray[i];
        }
        return total;
    }

    public String toString() {
        String tupleString = "[";
        for (int i = 0; i < tupleArray.length; i++) {
            if (i >= 0 && i < tupleArray.length - 1) {
                tupleString += Integer.toString(tupleArray[i]) + ",";
            } else {
                tupleString += Integer.toString(tupleArray[i]);
            }
        }
        tupleString = tupleString + "]";
        return tupleString;
    }

    public boolean equals (Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (!(obj instanceof Tuple)) {
            return false;
        }else if (this.toString().compareTo(obj.toString()) == 0) {
            return true;
        }
        return false;
    }

}
