public class BigInteger {
    private int[] bigIntegerArray;
    private boolean isPositive, isZero, isEven;

    public boolean checkSign(String val) {
        if (val.charAt(0) == '-') {
            isPositive = false;
        } else if (val.charAt(0) == '+') {
            isPositive = true;
        } else {
            isPositive = true;
        }
        return isPositive;
    }

    public boolean checkIfZero(String val) {
        if (val.equals("0")) {
            isZero = true;
        } else {
            isZero = false;
        }
        return isZero;
    }

    public boolean checkIfEven(BigInteger val) {
        if ((val.bigIntegerArray[0] == 0) || (val.bigIntegerArray[0] == 2) || (val.bigIntegerArray[0] == 4) || (val.bigIntegerArray[0] == 6) || (val.bigIntegerArray[0] == 8)) {
            isEven = true;
        } else {
            isEven = false;
        }
        return isEven;
    }

    public BigInteger (String val) {
        String trimmed = val.trim();
        checkSign(trimmed);
        checkIfZero(trimmed);
        String numbers = trimmed.replaceFirst("[+-]", "");
        String newString = removePrecedingZeros(numbers);
        bigIntegerArray = new int[newString.length()];
        for (int i = 0; i < newString.length(); i++) {
            if (newString.charAt(i) >= '0' && newString.charAt(i) <= '9') {
                bigIntegerArray[(newString.length() - 1) - i] = Character.getNumericValue(newString.charAt(i));
            } else {
                throw new IllegalArgumentException("Please enter numbers");
            }
        }
    }

    public static String arrayToString(int[] val) {
        String arrayAsString = "";
        for (int i = 0; i < val.length; i++)  {
            arrayAsString += Integer.toString(val[i]);
        }
        return arrayAsString;
    }

    public static String reverseArrayString(int[] val) {
        String reverseArrayString = "";
        for (int i = val.length - 1; i >= 0; i--)  {
            reverseArrayString += Integer.toString(val[i]);
        }
        return reverseArrayString;
    }

    public static int[] equalizeArray(BigInteger val, int[] newArray) {
        for (int i = 0; i < val.bigIntegerArray.length; i++) {
            newArray[i] = val.bigIntegerArray[i];
        }
        return newArray;
    }

    public static int[] reverseArithmeticArray(int[] reversedArray, int[] newVal) {
        for (int i = 0; i < reversedArray.length; i++) {
            newVal[(reversedArray.length - 1) - i] = reversedArray[i];
        }
        return newVal;
    }

    public BigInteger sum (BigInteger val) {
        int[] reverseSum = new int[bigIntegerArray.length + 1];
        int[] sum = new int[bigIntegerArray.length + 1];
        int[] newVal = new int[this.bigIntegerArray.length];
        int[] newThis = new int[this.bigIntegerArray.length];
        if (this.equals(ZERO)) {
            return val;
        } else if (val.equals(ZERO)) {
            return this;
        }
        if (this.isPositive && val.isPositive && this.compareWith(val) >= 0) {
            reverseSum = new int[this.bigIntegerArray.length + 1];
            sum = new int[this.bigIntegerArray.length + 1];
            newThis = new int[this.bigIntegerArray.length + 1];
            newVal = new int[this.bigIntegerArray.length + 1];
            int carry = 0;
            int value = 0;
            if (this.bigIntegerArray.length > val.bigIntegerArray.length) {
                equalizeArray(val, newVal);
                equalizeArray(this, newThis);
                for (int i = 0; i < reverseSum.length; i++) {
                    value = newThis[i] + newVal[i] + carry;
                    if (value > 9) {
                        value -= 10;
                        carry = 1;
                    } else {
                        carry = 0;
                    }
                    reverseSum[i] = value;
                }
            } else {
                for (int i = 0; i < reverseSum.length; i++) {
                    equalizeArray(val, newVal);
                    equalizeArray(this, newThis);
                    value = newThis[i] + newVal[i] + carry;
                    if (this.bigIntegerArray.length == 1 && val.bigIntegerArray.length == 1 && value > 9) {
                        reverseSum[1] = 1;
                    }
                    if (value > 9) {
                        value -= 10;
                        carry = 1;
                    } else {
                        carry = 0;
                    }
                    reverseSum[i] = value;
                }
            }
            reverseArithmeticArray(reverseSum, sum);
        }
        if (this.isPositive && val.isPositive && this.compareWith(val) < 0) {
            reverseSum = new int[val.bigIntegerArray.length + 1];
            sum = new int[val.bigIntegerArray.length + 1];
            newVal = new int[val.bigIntegerArray.length];
            int carry = 0;
            int value = 0;
            if (val.bigIntegerArray.length > this.bigIntegerArray.length) {
                equalizeArray(this, newVal);
                for (int i = 0; i < val.bigIntegerArray.length; i++) {
                    value = val.bigIntegerArray[i] + newVal[i] + carry;
                    if (value > 9) {
                        value -= 10;
                        carry = 1;
                    } else {
                        carry = 0;
                    }
                    reverseSum[i] = value;
                    if (carry == 1 && i == val.bigIntegerArray.length - 1) {
                        reverseSum[val.bigIntegerArray.length] = carry;
                    }
                }
            } else {
                for (int i = 0; i < val.bigIntegerArray.length; i++) {
                    value = this.bigIntegerArray[i] + val.bigIntegerArray[i] + carry;
                    if (value > 9) {
                        value -= 10;
                        carry = 1;
                    } else {
                        carry = 0;
                    }
                    reverseSum[i] = value;
                    if (carry == 1 && i == val.bigIntegerArray.length - 1) {
                        reverseSum[val.bigIntegerArray.length] = carry;
                    }
                }
            }
            reverseArithmeticArray(reverseSum, sum);
        }
        if (!this.isPositive && !val.isPositive) {
            reverseSum = new int[val.bigIntegerArray.length + 1];
            sum = new int[val.bigIntegerArray.length + 1];
            int carry = 0;
            int value = 0;
            if (val.bigIntegerArray.length > this.bigIntegerArray.length) {
                newVal = new int[val.bigIntegerArray.length];
                equalizeArray(this, newVal);
                for (int i = 0; i < val.bigIntegerArray.length; i++) {
                    value = val.bigIntegerArray[i] + newVal[i] + carry;
                    if (value > 9) {
                        value -= 10;
                        carry = 1;
                    } else {
                        carry = 0;
                    }
                    reverseSum[i] = value;
                    if (carry == 1 && i == val.bigIntegerArray.length - 1) {
                        reverseSum[val.bigIntegerArray.length] = carry;
                    }
                }
            } else {
                for (int i = 0; i < val.bigIntegerArray.length; i++) {
                    value = this.bigIntegerArray[i] + val.bigIntegerArray[i] + carry;
                    if (value > 9) {
                        value -= 10;
                        carry = 1;
                    } else {
                        carry = 0;
                    }
                    reverseSum[i] = value;
                    if (carry == 1 && i == val.bigIntegerArray.length - 1) {
                        reverseSum[val.bigIntegerArray.length] = carry;
                    }
                }
            }
            reverseArithmeticArray(reverseSum, sum);
            return new BigInteger("-" + arrayToString(sum).replaceFirst("^0+(?!$)", ""));
        }
        if ((!this.isPositive && val.isPositive) || (this.isPositive && !val.isPositive)) {
            int carry = 0;
            int value = 0;
            if ((this.compareWith(val) == 1 || this.compareWith(val) == -1) && this.compareAbsolute(val) == 0) {
                return ZERO;
            }
            if (this.compareWith(val) == 1) {
                    val.isPositive = true;
                    return this.difference(val);
            } else if (this.compareWith(val) == -1) {
                    this.isPositive = true;
                    return val.difference(this);
            }
        }
        return new BigInteger(arrayToString(sum));
    }

    public BigInteger difference (BigInteger val) {
        int[] reverseDifference = new int[bigIntegerArray.length];
        int[] difference = new int[bigIntegerArray.length];
        int[] newVal = new int[this.bigIntegerArray.length];
        int value = 0;
        if (this.compareWith(val) == 0) {
            return ZERO;
        } else if (this.isZero || val.isZero) {
            if (this.isZero) {
                reverseDifference = new int[val.bigIntegerArray.length];
                difference = new int[val.bigIntegerArray.length];
                reverseArithmeticArray(val.bigIntegerArray, difference);
                return new BigInteger("-" + arrayToString(difference));
            } else if (val.isZero) {
                reverseDifference = new int[this.bigIntegerArray.length];
                difference = new int[this.bigIntegerArray.length];
                reverseArithmeticArray(this.bigIntegerArray, difference);
                return new BigInteger(arrayToString(difference));
            }
        } else if (this.compareWith(val) == 1) {
            if (this.isPositive && val.isPositive) {
                reverseDifference = new int[this.bigIntegerArray.length];
                difference = new int[this.bigIntegerArray.length];
                newVal = new int[this.bigIntegerArray.length];
                equalizeArray(val, newVal);
                for (int i = 0; i < this.bigIntegerArray.length; i++) {
                    if (this.bigIntegerArray[i] < newVal[i]) {
                        this.bigIntegerArray[i] += 10;
                        this.bigIntegerArray[i + 1] -= 1;
                    }
                    value = this.bigIntegerArray[i] - newVal[i];
                    reverseDifference[i] = value;
                }
                reverseArithmeticArray(reverseDifference, difference);
            } else if (((this.isPositive) && !(val.isPositive)) || (!(this.isPositive) && !(val.isPositive))) {
                val.isPositive = true;
                return this.sum(val);
            }
        } else if (this.compareWith(val) == -1) {
            if (this.isPositive && val.isPositive) {
                reverseDifference = new int[val.bigIntegerArray.length];
                difference = new int[val.bigIntegerArray.length];
                newVal = new int[val.bigIntegerArray.length];
                equalizeArray(this, newVal);
                for (int i = 0; i < val.bigIntegerArray.length; i++) {
                    if (val.bigIntegerArray[i] < newVal[i]) {
                        val.bigIntegerArray[i] += 10;
                        if (val.bigIntegerArray[i + 1] == 0) {
                            val.bigIntegerArray[i + 1] = 9;
                            val.bigIntegerArray[i + 2] -= 1;
                        } else {
                            val.bigIntegerArray[i + 1] -= 1;
                        }
                    }
                    value = val.bigIntegerArray[i] - newVal[i];
                    reverseDifference[i] = value;
                }
                reverseArithmeticArray(reverseDifference, difference);
                return new BigInteger("-" + arrayToString(difference).replaceFirst("^0+(?!$)", ""));
            } else if (!(this.isPositive) && !(val.isPositive)) {
                reverseDifference = new int[this.bigIntegerArray.length];
                difference = new int[this.bigIntegerArray.length];
                newVal = new int[this.bigIntegerArray.length];
                equalizeArray(val, newVal);
                for (int i = 0; i < this.bigIntegerArray.length; i++) {
                    if (this.bigIntegerArray[i] < newVal[i]) {
                        this.bigIntegerArray[i] += 10;
                        if (this.bigIntegerArray[i + 1] == 0) {
                            this.bigIntegerArray[i + 1] = 9;
                            this.bigIntegerArray[i + 2] -= 1;
                        } else {
                            this.bigIntegerArray[i + 1] -= 1;
                        }
                    }
                    value = this.bigIntegerArray[i] - newVal[i];
                    reverseDifference[i] = value;
                }
                reverseArithmeticArray(reverseDifference, difference);
                return new BigInteger("-" + arrayToString(difference).replaceFirst("^0+(?!$)", ""));
            } else if (!(this.isPositive) && (val.isPositive)) {
                val.isPositive = false;
                return this.sum(val);
            }
        }
        return new BigInteger(arrayToString(difference));
    }

    public BigInteger product (BigInteger val) {
        BigInteger a = this;
        BigInteger b = val;
        BigInteger product = ZERO;
        if (a.equals(ONE)) {
            return val;
        } else if (b.equals(ONE)) {
            return this;
        } else if (a.equals(NEGATIVE_ONE)) {
            return new BigInteger("-" + b.toString());
        } else if (b.equals(NEGATIVE_ONE)) {
            return new BigInteger("-" + a.toString());
        }
        if (this.isZero || val.isZero) {
            return ZERO;
        } else {
            if (!(checkIfEven(b))) {
                product = product.sum(a);
            }
            while (!(b.equals(ONE))) {
                a = a.sum(a);
                b = divisionByTwo(b);
                if (!(checkIfEven(b))) {
                    product = product.sum(a);
                }
            }
        }
        if (!(this.isPositive) && !(val.isPositive) || this.isPositive && val.isPositive) {
            return (val.isPositive ? product : new BigInteger(product.toString().substring(1)));
        } else {
            return (this.isPositive ? new BigInteger("-" + product.toString()) : product);
        }
    }

    public BigInteger quotient (BigInteger val) {
        BigInteger quotient = ZERO;
        BigInteger result = ONE;
        BigInteger intermediate = new BigInteger(reverseArrayString(val.bigIntegerArray));
        BigInteger addedToResult = ZERO;
        BigInteger a = new BigInteger(reverseArrayString(this.bigIntegerArray));
        BigInteger b = new BigInteger(reverseArrayString(val.bigIntegerArray));
        if (a.equals(ZERO)) {
            return ZERO;
        } else if (b.equals(ZERO)) {
            throw new ArithmeticException("Val cannot be 0. You cannot divide by 0.");
        } else {
            if (a.compareWith(b) == -1) {
                return ZERO;
            } else {
                while ((intermediate.product(TEN)).compareWith(a) <= 0) {
                    result = result.product(TEN);
                    intermediate = intermediate.product(TEN);
                }
            }
        }
        addedToResult = result.sum((a.difference(intermediate)).quotient(b));
        if ((this.isPositive && !val.isPositive) || (!this.isPositive && val.isPositive)) {
            return new BigInteger("-" + addedToResult.toString());
        } else {
            return addedToResult;
        }
    }

    public BigInteger divisionByTwo (BigInteger val) {
        int[] newVal = new int[val.bigIntegerArray.length + 1];
        String quotient = "";
        String valString = arrayToString(val.bigIntegerArray);
        String newValString = "";
        for (int i = 0; i < valString.length(); i++) {
            newValString = valString.charAt(i) + newValString;
        }
        newValString = "0" + newValString;
        for (int i = 0; i < newValString.length() - 1; i++) {
            int value = Integer.parseInt(newValString.substring(i, i+1));
            int value2 = Integer.parseInt(newValString.substring(i+1, i+2));
            if (value % 2 == 0) {
                quotient += Integer.toString((int)(Math.floor(value2 / 2)));
            } else {
                if (value2 <= 1) {
                    quotient += "5";
                } else if (value2 <= 3) {
                    quotient += "6";
                } else if (value2 <= 5) {
                    quotient += "7";
                } else if (value2 <= 7) {
                    quotient += "8";
                } else if (value2 <= 9) {
                    quotient += "9";
                }
            }
        }
        return new BigInteger(quotient);
    }

    public BigInteger remainder (BigInteger val) {
        BigInteger remainder = ZERO;
        BigInteger a = new BigInteger(reverseArrayString(this.bigIntegerArray));
        BigInteger b = new BigInteger(reverseArrayString(val.bigIntegerArray));
        if (a.equals(ZERO)) {
            return ZERO;
        } else if (b.equals(ZERO)) {
            throw new ArithmeticException("Val cannot be 0. You cannot divide by 0.");
        } else {
            if (a.compareWith(b) == -1) {
                return this;
            }
            remainder = a.difference((a.quotient(b)).product(b));
            if((this.isPositive && !val.isPositive) || (!this.isPositive && val.isPositive)){
                return new BigInteger("-" + remainder.toString());
            } else {
                return remainder;
            }
        }
    }

    public String toString () {
        String test = "";
        if (isPositive == false) {
            test += "-";
        }
        for (int i = bigIntegerArray.length - 1; i >= 0; i--) {
            test += bigIntegerArray[i];
        }
        test = test.replaceFirst("^0+(?!$)", "");
        return test;
    }

    public String removePrecedingZeros (String val) {
        String newString = val;
        if (val.length() > 1 && val.charAt(0) == '0') {
            while (newString.charAt(0) == '0' && newString.length() > 1) {
                newString = newString.substring(1);
            }
        } else {
            newString = newString.substring(0);
        }
        return newString;
    }

    public int compareWith (BigInteger val) {
        if (this.isPositive != val.isPositive) {
            if (this.isPositive) {
                return 1;
            } else {
                return -1;
            }
        } else if (this.isPositive == true && val.isPositive == true) {
            if (this.bigIntegerArray.length > val.bigIntegerArray.length) {
                return 1;
            } else if (this.bigIntegerArray.length < val.bigIntegerArray.length) {
                return -1;
            } else if (this.bigIntegerArray.length == val.bigIntegerArray.length) {
                for (int i = this.bigIntegerArray.length - 1; i >= 0; i--) {
                    if (this.bigIntegerArray[i] > val.bigIntegerArray[i]) {
                        return 1;
                    } else if (this.bigIntegerArray[i] < val.bigIntegerArray[i]) {
                        return -1;
                    }
                }
                return 0;
            }
        }
        if (this.isPositive == false && val.isPositive == false) {
            if (this.bigIntegerArray.length > val.bigIntegerArray.length) {
                return -1;
            } else if (this.bigIntegerArray.length < val.bigIntegerArray.length) {
                return 1;
            } else if (this.bigIntegerArray.length == val.bigIntegerArray.length) {
                for (int i = this.bigIntegerArray.length - 1; i >= 0; i--) {
                    if (this.bigIntegerArray[i] > val.bigIntegerArray[i]) {
                        return -1;
                    } else if (this.bigIntegerArray[i] < val.bigIntegerArray[i]) {
                        return 1;
                    }
                }
                return 0;
            }
        }
        return 0;
    }

    public int compareAbsolute (BigInteger val) {
        if ((this.isPositive && val.isPositive) && (this.bigIntegerArray.length != val.bigIntegerArray.length)) {
            if (this.bigIntegerArray.length > val.bigIntegerArray.length) {
                return 1;
            } else if (this.bigIntegerArray.length < val.bigIntegerArray.length) {
                return -1;
            }
        } else if ((!this.isPositive && !val.isPositive) && (this.bigIntegerArray.length != val.bigIntegerArray.length)) {
            if (this.bigIntegerArray.length > val.bigIntegerArray.length) {
                return -1;
            } else if (this.bigIntegerArray.length < val.bigIntegerArray.length) {
                return 1;
            }
        } else if (((!this.isPositive && val.isPositive) || (this.isPositive && !val.isPositive)) && (this.bigIntegerArray.length != val.bigIntegerArray.length)) {
            if (this.bigIntegerArray.length > val.bigIntegerArray.length) {
                return 1;
            } else if (this.bigIntegerArray.length < val.bigIntegerArray.length) {
                return -1;
            }
        } else {
            for (int i = this.bigIntegerArray.length - 1; i >= 0; i--) {
                if ((bigIntegerArray[i] - val.bigIntegerArray[i]) < 0) {
                    return -1;
                } else if ((bigIntegerArray[i] - val.bigIntegerArray[i]) > 0) {
                    return 1;
                }
            }
            return 0;
        }
        return 0;
    }

    public boolean equals (Object x) {
        if (!(x instanceof BigInteger)) {
            throw new IllegalArgumentException("Object is not a big integer.");
        }
        if (this.toString().compareTo(x.toString()) == 0) {
            return true;
        }
        return false;
    }

    public static final BigInteger ZERO = new BigInteger("0"); // a classwide constant for zero
    public static final BigInteger ONE = new BigInteger("1"); // a classwide constant for one
    public static final BigInteger TEN = new BigInteger("10"); // a classwide constant for ten
    public static final BigInteger FIVE = new BigInteger("5");
    public static final BigInteger NEGATIVE_ONE = new BigInteger("-1");

    public static BigInteger valueOf (long val) {
        return new BigInteger(Long.toString(val));
    }
}
