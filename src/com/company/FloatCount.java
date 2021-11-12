package com.company;

public enum FloatCount {

    two,
    four,
    six;

    public static FloatCount getcount(int count)
    {
        switch (count) {
            case 2:
                return two;
            case 4:
                return four;
            case 6:
                return six;
        }
        return null;
    }
}
