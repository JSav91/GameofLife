package com.convays.gameoflife.beans;

import java.awt.Color;

import javax.swing.JPanel;

/**
 * The Cell bean
 * has a position variable and a current state
 */
public class Cell extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private int GridRow;
	private int GridColumn;
	int state;

	//Constructor
	
	//Test
	public Cell(int GridRow, int GridColumn) {

		this.GridRow = GridRow;
		this.GridColumn = GridColumn;
		if((Math.random()<0.1)?false:true){
			this.state=0;
			setBackground(Color.BLACK);
		}else {
			setBackground(Color.WHITE);
			this.state=1;

		}
			

	}

	public int getGridRow() {
		return GridRow;
	}

	public void setGridRow(int gridRow) {
		GridRow = gridRow;
	}

	public int getGridColumn() {
		return GridColumn;
	}

	public void setGridColumn(int gridColumn) {
		GridColumn = gridColumn;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
		//If State is active change the background 
		//color of cell
		if(this.state==0){
			this.setBackground(Color.BLACK);
		}
		else{
			this.setBackground(Color.WHITE);
		}
	} 
	
	
}
