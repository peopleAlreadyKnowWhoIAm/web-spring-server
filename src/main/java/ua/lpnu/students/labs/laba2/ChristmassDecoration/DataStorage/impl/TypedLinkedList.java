package ua.lpnu.students.labs.laba2.ChristmassDecoration.DataStorage.impl;

import java.util.Collection;
import java.util.LinkedList;

import ua.lpnu.students.labs.laba2.ChristmassDecoration.DataStorage.TypedList;

public class TypedLinkedList<T extends Object> extends LinkedList<T> implements TypedList<T> {
	T type;

	public TypedLinkedList(T type, Collection<? extends T> collection) {
		super(collection);
		this.type = type;
	}

	public TypedLinkedList(T type){
		super();
		this.type = type;
	}

	@Override
	public T getType() {
		return type;
	}
	
}
