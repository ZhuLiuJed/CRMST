package com.scse.crms.lTestUtil;

import java.lang.reflect.Method;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.ListAssert;

public class ZListAssert<T> extends ListAssert<T> {

	public ZListAssert(List<? extends T> actual) {
		super(actual);
		// TODO Auto-generated constructor stub
	}

	public static<T>  ZListAssert<T> assertThat(List<T> lists) { 
	    return new ZListAssert(lists); 
	  }
	public ZListAssert hasitem(T item) throws Exception { 
		Method getid = item.getClass().getDeclaredMethod("getId");
        isNotNull(); 
        boolean found = false; 
        for (T item2 :actual) { 
              if ( getid.invoke(item2).equals(getid.invoke(item))) {
				found = true;
			}
        } 
        Assertions 
               .assertThat(found) 
               .isTrue(); 
        return this; 

	 }
}
