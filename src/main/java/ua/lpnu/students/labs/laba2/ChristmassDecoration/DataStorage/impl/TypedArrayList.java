package ua.lpnu.students.labs.laba2.ChristmassDecoration.DataStorage.impl;

import java.util.ArrayList;
import java.util.Collection;

import lombok.EqualsAndHashCode;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.DataStorage.TypedList;

@EqualsAndHashCode(callSuper = false)
public class TypedArrayList<T> extends ArrayList<T> implements TypedList<T> {

	T type;

	public TypedArrayList(T type, Collection<? extends T> collection) {
		super(collection);
		this.type = type;
	}

	public TypedArrayList(T type){
		super();
		this.type = type;
	}

	@Override
	public T getType() {
		return type;
	}
	
}
