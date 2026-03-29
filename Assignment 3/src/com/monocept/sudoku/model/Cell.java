package com.monocept.sudoku.model;

class Cell {

	private int value;
	private boolean fixed;

	public Cell(int value, boolean fixed) {
		this.value = value;
		this.fixed = fixed;
	}

	public int getValue() {
		return value;
	}

	public boolean isFixed() {
		return fixed;
	}

	public void setValue(int value) {
		if (fixed) {
			throw new IllegalStateException("Cannot change fixed cell.");
		}
		this.value = value;
	}

	public void clear() {
		if (fixed) {
			throw new IllegalStateException("Cannot clear fixed cell.");
		}
		this.value = 0;
	}
}
