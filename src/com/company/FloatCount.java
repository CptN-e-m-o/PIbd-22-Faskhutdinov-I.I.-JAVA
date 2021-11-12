package com.company;

public enum FloatCount {

    two,
    four,
    six;

    public static FloatCount getCount(int count)
    {
        switch (count) {
            case 0:
                return two;
            case 1:
                return four;
            case 2:
                return six;
        }
        return null;
    }
}
