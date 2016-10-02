public class Inverse
{
	public static void main(String[] param)
	{
		double [][] mat={{3,-2,9,4},{3,-4,-8,6},{6,8,-10,3},{2,6,2,-1}};
		gauss(mat);
		
	}

	public static void gauss(double[][] mat)
	{
		double[][] id=getId(mat.length);
		printy(mat,id);

		makeUpper(mat,id);
		makeLower(mat,id);
		reduce(mat,id);

		printy(mat,id);
	}

	public static void transform(double[] rowd, double[] rowo, double factor)
	{
		for(int i=0;i<rowd.length;i++)
		{
			rowd[i]=rowd[i]-(rowo[i]*factor);
		}
	}

	public static void makeUpper(double[][] mat,double[][] id)
	{
		while(!isUpper(mat))
		{
			for(int h=0; h<mat.length;h++)
			{
				for(int i=h; i<mat.length;i++)
				{
					if(mat[i][h]!=0)
					{
						for(int j=i+1;j<mat.length;j++)
						{
							if(mat[j][h]!=0)
							{
								double factor=mat[j][h]/mat[i][h];
								transform(mat[j],mat[i],factor);
								transform(id[j],id[i],factor);
							}
						}
					}
				}
			}
			if(zeroes(mat))
			{
				System.out.println("No Inverse");
				System.exit(0);
			}
			sort(mat,id);	
		}
	}

	public static void makeLower(double[][] mat,double[][] id)
	{
		while(!isLower(mat))
		{
			for(int h=mat.length-1;h>-1;h--)
			{
				for(int i=mat.length-1;i>-1;i--)
				{
					if(mat[i][h]!=0)
					{
						for(int j=i-1;j>-1;j--)
						{
							if(mat[j][h]!=0)
							{
								double factor=mat[j][h]/mat[i][h];
								transform(mat[j],mat[i],factor);
								transform(id[j],id[i],factor);
							}
						}
					}
				}
			}
			if(zeroes(mat))
			{
				System.out.println("No Inverse");
				System.exit(0);
			}
			sort(mat,id);
		}
	}

	public static void pause()
	{
		try
		{
			Thread.sleep(900);
		}
		catch(Exception ex)
		{
			System.out.println("Error");
		}
	}

	public static void sort(double[][] mat,double[][] id)
	{
		for(int i=0; i<mat.length-1;i++)
		{
			for(int j=0; j<mat.length-1-i;j++)
			{
				if(lead(mat[j])>lead(mat[j+1]))
				{
					double[] temp=mat[j+1];
					mat[j+1]=mat[j];
					mat[j]=temp;

					temp=id[j+1];
					id[j+1]=id[j];
					id[j]=temp;
				}
			}
		}
	}

	public static int lead(double[] row)
	{
		int lead=row.length;
		for(int i=0; i<row.length;i++)
		{
			if(row[i]!=0)
			{
				return i;
			}
		}
		return lead;
	}

	public static boolean zeroes(double[][] mat)
	{
		for(int i=0; i<mat.length;i++)
		{
			int count=0;
			for(int j=0; j<mat[i].length;j++)
			{
				if(mat[i][j]==0)
				{
					count++;
					if(count==mat[i].length)
					{
						return true;
					}
				}
			}
		}
		return false;
	}

	public static void reduce(double[][] mat,double[][] id)
	{
		for(int i=0; i<mat.length;i++)
		{
			reduceRow(mat[i],id[i]);
		}
	}

	public static void reduceRow(double[] row,double[] idrow)
	{
		for(int i=0;i<row.length;i++)
		{
			if(row[i]!=0)
			{
				double factor=row[i];
				for(int j=i;j<row.length;j++)
				{
					row[j]=row[j]/factor;
				}
				for(int j=0;j<idrow.length;j++)
				{
					idrow[j]=idrow[j]/factor;
				}
				return;
			}
		}
	}

	public static boolean isUpper(double[][] mat)
	{
		for(int i=0; i<mat.length;i++)
		{
			for(int j=0; j<i;j++)
			{
				if(mat[i][j]!=0)
				{
					return false;
				}	
			}
		}
		return true;
	}

	public static boolean isLower(double[][] mat)
	{
		for(int i=0; i<mat.length;i++)
		{
			for(int j=i+1; j<mat[i].length;j++)
			{
				if(mat[i][j]!=0)
				{
					return false;
				}	
			}
		}
		return true;
	}

	public static double[][] getId(int n)
	{
		double[][] id=new double[n][n];
		for(int i=0; i<n;i++)
		{
			for(int j=0;j<n;j++)
			{
				if(i==j)
				{
					id[i][j]=1;
				}
				else
				{
					id[i][j]=0;
				}
			}
		}
		return id;
	}

	public static boolean isId(double[][] mat)
	{
		for(int i=0;i<mat.length;i++)
		{
			for(int j=0;j<mat[i].length;j++)
			{
				if(i==j)
				{
					if(mat[i][j]!=1)
					{
						return false;
					}
				}
				else
				{
					if(mat[i][j]!=0)
					{
						return false;
					}
				}
			}
		}
		return true;
	}

	public static void printy(double[][] mat,double[][] id)
	{
		System.out.println("");
		for(int i=0; i<mat.length;i++)
		{
			for(int j=0; j<mat[i].length;j++)
			{
				System.out.print(" "+mat[i][j]);
			}
			System.out.print("|");
			for(int j=0; j<id[i].length;j++)
			{
				System.out.print(" "+id[i][j]);
			}
			System.out.println("");
		}
	}
}