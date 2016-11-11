import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class Model 
{
	private int[][] model;
	private ArrayList<ChangeListener> listeners;
	
	/**
	 * 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Model()
	{
		this.model = new int [9][9];
		this.listeners = new ArrayList();
		
		this.model[0][3] = 2;
		this.model[0][7] = 6;
		this.model[0][8] = 3;
		
		this.model[1][0] = 3;
		this.model[1][5] = 5;
		this.model[1][6] = 4;
		this.model[1][8] = 1;
		
		this.model[2][2] = 1;
		this.model[2][5] = 3;
		this.model[2][6] = 9;
		this.model[2][7] = 8;
		
		this.model[3][7] = 9;
		
		this.model[4][3] = 5;
		this.model[4][4] = 3;
		this.model[4][5] = 8;
		
		this.model[5][1] = 3;
		
		this.model[6][1] = 2;
		this.model[6][2] = 6;
		this.model[6][3] = 3;
		this.model[6][6] = 5;
		
		this.model[7][0] = 5;
		this.model[7][2] = 3;
		this.model[7][3] = 7;
		this.model[7][8] = 8;
		
		this.model[8][0] = 4;
		this.model[8][1] = 7;
		this.model[8][5] = 1;
	}
	
	/**
	 * 
	 * @param row
	 * @param col
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public void solve(int row, int col) throws Exception
	{
		if(row > 8)
		{
			throw new Exception("Solution has been found!");
		}
		
		Object object;
		Iterator iterator;
		
		if(this.model[row][col] != 0)
		{
			next(row, col);
		}
		else
		{
			for(int i = 1; i < 10; i++)
			{
				if((rowCheck(row, i)) && (colCheck(col, i)) && (check3x3Box(row, col, i)))
				{
					this.model[row][col] = i;
					for(object = this.listeners.iterator(); ((Iterator)object).hasNext();)
					{
						ChangeListener changeListener = (ChangeListener)((Iterator)object).next();
						changeListener.stateChanged(new ChangeEvent(this));
					}
					Thread.sleep(100L);
					next(row, col);
				}
			}
		
		
			this.model[row][col] = 0;
			
			for(iterator = this.listeners.iterator(); iterator.hasNext();)
			{
				object = (ChangeListener)iterator.next();
				((ChangeListener)object).stateChanged(new ChangeEvent(this));
			}
		}
	}
	
	/**
	 * 
	 * @param row_1
	 * @param row_2
	 * @return
	 */
	public boolean rowCheck(int row_1, int row_2)
	{
		for(int i = 0; i < 9; i++)
		{
			if(this.model[row_1][i] == row_2)
			{
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 
	 * @param col_1
	 * @param col_2
	 * @return
	 */
	public boolean colCheck(int col_1, int col_2)
	{
		for(int i = 0; i < 9; i++)
		{
			if(this.model[i][col_1] == col_2)
			{
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 
	 * @param row
	 * @param col
	 * @param value
	 * @return
	 */
	public boolean check3x3Box(int row, int col, int value)
	{
		row = row / 3 * 3;
		col = col / 3 * 3;
		
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				if(this.model[row + i][col + j] == value)
				{
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * 
	 */
	public String toString()
	{
		String string = "";
		for(int i = 0; i < 9; i++)
		{
			for(int j = 0; j < 9; j++)
			{
				string = string + this.model[i][j];
			}
		}
		return string;
	}
	
	/**
	 * 
	 * @param row
	 * @param col
	 * @return
	 */
	public int getNum(int row, int col)
	{
		return this.model[row][col];
	}
	
	/**
	 * 
	 * @param row
	 * @param col
	 * @param value
	 */
	public void setNum(int row, int col, int value)
	{
		this.model[row][col] = value;
		for(ChangeListener cL : this.listeners)
		{
			cL.stateChanged(new ChangeEvent(this));
		}
	}
	
	/**
	 * 
	 * @param changeListener
	 */
	public void addChangeListener(ChangeListener changeListener)
	{
		this.listeners.add(changeListener);
	}
	
	/**
	 * 
	 * @param row
	 * @param col
	 * @throws Exception
	 */
	public void next(int row, int col) throws Exception
	{
		if(col < 8)
		{
			solve(row, col + 1);
		}
		else
		{
			solve(row + 1, 0);
		}
	}
}
