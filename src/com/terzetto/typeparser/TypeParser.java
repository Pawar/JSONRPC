package com.terzetto.typeparser;

public interface TypeParser<T> {
	T parse(String value);
}