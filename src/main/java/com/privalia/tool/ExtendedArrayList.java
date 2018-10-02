package com.privalia.tool;

import java.util.ArrayList;
import java.util.Collections;

public class ExtendedArrayList<E> extends ArrayList<E> implements ExtendedList<E> {
    @Override
    public boolean add(E e) {
        return e != null && super.add(e);
    }


    @Override
    public ExtendedList<E> reverse() {
        Collections.reverse(this);

        return this;
    }
}
