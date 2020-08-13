package com.cx.test.thunder;

import java.util.Comparator;

public class SortByTime implements Comparator<Object>  {

	@Override
	public int compare(Object o1, Object o2) {
		ThunderValue s1 = (ThunderValue) o1;
		ThunderValue s2 = (ThunderValue) o2;
       if (s1.times[0] >= s2.times[0])
         return 1;
        return -1;
       }

}
