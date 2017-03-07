/**
 * 
 */
package com.convays.gameoflife.main;

import com.convays.gameoflife.beans.Cell;
import com.convays.gameoflife.grid.GridManager;

/**
 * @author JOEL,RAVI,SHARUN
 *
 */
public class GameOfLifeMain{

	/**
	 * @param args
	 * @author JOEL
	 */

	public static Cell[][] presentCellStateArray;
	public static Cell[][] futureCellStateArray;

	public static void main(String[] args) {

		//Create the initial GridManager
		int rows=100;
		int cols=150;
		int neighborCount=0;
		GridManager gridManager= new GridManager();
		//Create Gridview
		gridManager.createGridiew(rows,cols);

		for(int xPos=0;xPos<rows;xPos++){
			for(int yPos=0;yPos<cols;yPos++){
				neighborCount=gridManager.countNeighbors(presentCellStateArray,xPos,yPos,rows-1,cols-1);
				System.out.println("Neighbour count for Cell["+xPos+"]["+yPos+"]= "+neighborCount);
			}
		}
	}


}
