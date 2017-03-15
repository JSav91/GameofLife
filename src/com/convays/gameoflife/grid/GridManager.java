/**
 * 
 */
package com.convays.gameoflife.grid;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.convays.gameoflife.beans.Cell;
import com.convays.gameoflife.main.GameOfLifeMain;

/**
 * Handles all the GridManager related operations
 * @author JOEL,RAVI,SHARUN
 *
 */
public class GridManager extends JFrame{

	private static final long serialVersionUID = 1L;
	public static boolean isRunning;

	/**
	 * Create the grid view
	 * @param int ,int
	 * @author JOEL
	 * 
	 */
	public void createGridiew(int rows,int cols){
		
		isRunning = false;
		// setting game panel and border layout
		JPanel pnlGame= new JPanel();
		pnlGame.setLayout(new BorderLayout(10, 10));
		
		
		// setting cell panel to contain cells
		JPanel pnlCells = new JPanel();
		pnlGame.add(pnlCells, BorderLayout.CENTER);
		
		// setting control panel
		JPanel pnlControl = new JPanel();
		JButton btnStart = new JButton("Start");;
		JButton btnStop = new JButton("Stop");
		pnlControl.add(btnStart);
		pnlControl.add(btnStop);
		pnlGame.add(pnlControl, BorderLayout.SOUTH);
		
		// start and stop button action
		btnStart.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				isRunning = true;
				
			}
			
		});
		btnStop.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				isRunning = false;
				
			}
			
		});
		
		// adding base panel to Jframe
		super.add(pnlGame);

		//Set JFame layout
		pnlCells.setLayout(new GridLayout(rows, cols ,1,1));
		GameOfLifeMain.presentCellStateArray= new Cell[rows][cols];
		GameOfLifeMain.futureCellStateArray = new Cell[rows][cols];
		//Populate the grid
		for(int x=0; x<rows; x++){ 
			for(int y=0; y<cols; y++){
				//adds cells to grid
				pnlCells.add(GameOfLifeMain.presentCellStateArray[x][y]= new Cell(rows,cols)); 
				//initialise parallel array
				GameOfLifeMain.futureCellStateArray[x][y] = new Cell(rows,cols);
			}
		}
		//TEST /cell[20][20].setBackground(Color.GREEN);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.pack();
		super.setVisible(true);
	}
	
	public void createGridiew(int rows,int cols,Cell futurecell[][])
	{
		for(int x=0; x<rows; x++){ 
			for(int y=0; y<cols; y++){
				//adds cells to grid
		GameOfLifeMain.presentCellStateArray[x][y].setState(futurecell[x][y].getState());	 
			}
		}
		super.repaint();
	}

	/**
	 * Create the grid view
	 * @param int ,int
	 * @author JOEL
	 * 
	 */
	public int countNeighbors(Cell cell[][],int xPos,int yPos,int xMax,int yMax){

		int neighborCount=0;

		if(xPos< xMax && xPos>0  && yPos< yMax && yPos>0){
			neighborCount=	cell[xPos-1][yPos-1].getState() + //North West
					cell[xPos-1][yPos].getState() + //North
					cell[xPos-1][yPos+1].getState()+ //North East
					cell[xPos][yPos-1].getState()+  // West
					cell[xPos][yPos+1].getState()+//East
					cell[xPos+1][yPos-1].getState()+//South West
					cell[xPos+1][yPos].getState()+ //South
					cell[xPos+1][yPos+1].getState();//South East
		}

		else if(xPos==0){

			if(yPos==0){// For position Cell[0][0] in the grid matrix
				neighborCount=cell[xPos][yPos+1].getState()+ //East
						cell[xPos+1][yPos].getState()+ //South
						cell[xPos+1][yPos+1].getState()+ //South East
						cell[xMax][yMax].getState();

			}
			else if(yPos==yMax){// For position Cell[0][yMax] in the grid matrix
				neighborCount=cell[xPos][yPos-1].getState()+ // West
						cell[xPos+1][yPos-1].getState()+ //South West
						cell[xPos+1][yPos].getState()+ //South
						cell[xMax][0].getState();

			}
			else {// For all other positions in the first row
				neighborCount=cell[xPos][yPos-1].getState()+// West
						cell[xPos][yPos+1].getState()+//East
						cell[xPos+1][yPos-1].getState()+ //South West
						cell[xPos+1][yPos].getState()+ //South
						cell[xPos+1][yPos+1].getState()+ //South East

						cell[xMax][yPos-1].getState()+//Bottom North West
						cell[xMax][yPos].getState()+//Bottom North 
						cell[xMax][yPos+1].getState();//Bottom North East
			}
		}
		else if(xPos ==xMax){
			if(yPos==0){// For position Cell[xMax][0] in the grid matrix
				neighborCount=cell[xPos-1][yPos].getState()+ //North
						cell[xPos-1][yPos+1].getState()+ //North East
						cell[xPos][yPos+1].getState()+ //East
						cell[0][yMax].getState();

			}
			else if(yPos==yMax){// For position Cell[xMax][yMax] in the grid matrix
				neighborCount=cell[xPos][yPos-1].getState()+ // West
						cell[xPos-1][yPos-1].getState()+ //North West
						cell[xPos-1][yPos].getState()+  //North
						cell[0][0].getState();
			}
			else {// For all other positions in the first row
				neighborCount=cell[xPos][yPos-1].getState()+ // West
						cell[xPos][yPos+1].getState()+//East
						cell[xPos-1][yPos+1].getState()+ //North East
						cell[xPos-1][yPos-1].getState()+ //North West
						cell[xPos-1][yPos].getState()+ //North						
						cell[0][yPos-1].getState()+//Top North West
						cell[0][yPos].getState()+//Top North 
						cell[0][yPos+1].getState();//Top North East
			}
		}
		else if(yPos==0){// For all other positions in the first Column
			neighborCount=cell[xPos][yPos+1].getState()+//East
					cell[xPos-1][yPos].getState()+ //North
					cell[xPos-1][yPos+1].getState()+ //North East
					cell[xPos+1][yPos].getState()+//South
					cell[xPos+1][yPos+1].getState()+ //South East	
					cell[xPos-1][yPos].getState()+//Right North West
					cell[xPos][yPos].getState()+//Right North 
					cell[xPos+1][yPos].getState();//right South East

		}
		else if(yPos ==yMax){// For all other positions in the last column 
			neighborCount=	cell[xPos][yPos-1].getState()+ // West
					cell[xPos-1][yPos-1].getState()+ //North West
					cell[xPos-1][yPos].getState()+ //North
					cell[xPos+1][yPos-1].getState()+ //South West
					cell[xPos+1][yPos].getState()+ //South
					cell[xPos-1][yPos].getState()+//Left North West
					cell[xPos][yPos].getState()+//Left North 
					cell[xPos+1][yPos].getState();//Left  South East
		}

		return neighborCount;

	}

}
