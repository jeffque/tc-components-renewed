package br.com.jeffque.tc.util;

public interface ReadWriteAccessor<S, T> {
	public T getAttr(S source);
	public void setAttr(S source, T value);
}
