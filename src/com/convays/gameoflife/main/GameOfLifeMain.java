/**
 * 
 */
package com.convays.gameoflife.main;

import java.util.concurrent.TimeUnit;
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
	public static boolean isRunning = false;

	public static void main(String[] args) {

		//Create the initial GridManager
		int rows=50;
		int cols=50;
		int neighborCount=0;
		GridManager gridManager= new GridManager();
		//Create Gridview
		gridManager.createGridiew(rows,cols);
		
			do{
				if(GridManager.isRunning == true){
					for(int xPos=0;xPos<rows;xPos++){
						for(int yPos=0;yPos<cols;yPos++){
							neighborCount=gridManager.countNeighbors(presentCellStateArray,xPos,yPos,rows-1,cols-1);
							//System.out.println("Neighbour count for Cell["+xPos+"]["+yPos+"]= "+neighborCount);
							if(neighborCount>1 && neighborCount<4)
							{
								if(neighborCount==3)
								{
								futureCellStateArray[xPos][yPos].setState(1);
								}
								else
								{
									futureCellStateArray[xPos][yPos].setState(presentCellStateArray[xPos][yPos].getState());
								}
							}
							else
							{
								futureCellStateArray[xPos][yPos].setState(0);
							}
							//System.out.println("Neighbour count for Cell["+xPos+"]["+yPos+"]= "+futureCellStateArray[xPos][yPos].getState());
						}
					}
					gridManager.createGridiew(rows, cols, futureCellStateArray);
				}
				try {
					TimeUnit.MILLISECONDS.sleep(300);
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}while(true);
		
	}


}
