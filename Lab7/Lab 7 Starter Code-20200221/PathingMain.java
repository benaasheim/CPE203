import java.util.*;

import processing.core.*;

public class PathingMain extends PApplet
{
   private List<PImage> imgs;
   private int current_image;
   private long next_time;
   private PImage background;
   private PImage obstacle;
   private PImage goal;
   private List<Point> path;
   private LinkedList<Point> positionque = new LinkedList<>();
   private static final int TILE_SIZE = 32;

   private static final int ANIMATION_TIME = 100;

   private GridValues[][] grid;
   private static final int ROWS = 15;
   private static final int COLS = 20;

   private static enum GridValues { BACKGROUND, OBSTACLE, GOAL, SEARCHED };

   private Point wPos;

   private boolean drawPath = false;

	public void settings() {
      size(640,480);
	}
	
	/* runs once to set up world */
   public void setup()
   {
      path = new LinkedList<>();
      wPos = new Point(2, 2);
      imgs = new ArrayList<>();
      imgs.add(loadImage("images/wyvern1.bmp"));
      imgs.add(loadImage("images/wyvern2.bmp"));
      imgs.add(loadImage("images/wyvern3.bmp"));

      background = loadImage("images/grass.bmp");
      obstacle = loadImage("images/vein.bmp");
      goal = loadImage("images/water.bmp");

      grid = new GridValues[ROWS][COLS];
      initialize_grid(grid);

      current_image = 0;
      next_time = System.currentTimeMillis() + ANIMATION_TIME;
   }

	/* set up a 2D grid to represent the world */
   private static void initialize_grid(GridValues[][] grid)
   {
      for (int row = 0; row < grid.length; row++)
      {
         for (int col = 0; col < grid[row].length; col++)
         {
            grid[row][col] = GridValues.BACKGROUND;
         }
      }

		//set up some obstacles
      for (int row = 2; row < 8; row++)
      {
         grid[row][row + 5] = GridValues.OBSTACLE;
      }

      for (int row = 8; row < 12; row++)
      {
         grid[row][19 - row] = GridValues.OBSTACLE;
      }

      for (int col = 1; col < 8; col++)
      {
         grid[11][col] = GridValues.OBSTACLE;
      }

      grid[13][14] = GridValues.GOAL;
   }

   private void next_image()
   {
      current_image = (current_image + 1) % imgs.size();
   }

	/* runs over and over */
   public void draw()
   {
      // A simplified action scheduling handler
      long time = System.currentTimeMillis();
      if (time >= next_time)
      {
         next_image();
         next_time = time + ANIMATION_TIME;
      }

      draw_grid();
      draw_path();

      image(imgs.get(current_image), wPos.x * TILE_SIZE, wPos.y * TILE_SIZE);
   }

   private void draw_grid()
   {
      for (int row = 0; row < grid.length; row++)
      {
         for (int col = 0; col < grid[row].length; col++)
         {
            draw_tile(row, col);
         }
      }
   }

   private void draw_path()
   {
      if (drawPath)
      {
         for (Point p : path)
         {
            fill(128, 0, 0);
            rect(p.x * TILE_SIZE + TILE_SIZE * 3 / 8,
               p.y * TILE_SIZE + TILE_SIZE * 3 / 8,
               TILE_SIZE / 4, TILE_SIZE / 4);
         }
      }
   }

   private void draw_tile(int row, int col)
   {
      switch (grid[row][col])
      {
         case BACKGROUND:
            image(background, col * TILE_SIZE, row * TILE_SIZE);
            break;
         case OBSTACLE:
            image(obstacle, col * TILE_SIZE, row * TILE_SIZE);
            break;
         case SEARCHED:
            fill(0, 128);
            rect(col * TILE_SIZE + TILE_SIZE / 4,
               row * TILE_SIZE + TILE_SIZE / 4,
               TILE_SIZE / 2, TILE_SIZE / 2);
            break;
         case GOAL:
            image(goal, col * TILE_SIZE, row * TILE_SIZE);
            break;
      }
   }

   public static void main(String args[])
   {
      PApplet.main("PathingMain");
   }

   public void keyPressed()
   {
      if (key == ' ')
      {
			//clear out prior path and re-initialize grid
//         path.clear();
//         initialize_grid(grid);

			//EXAMPLE - replace with dfs
         positionque.add(new Point(wPos.x, wPos.y));

//         wPos = tt(wPos, grid, path);
         moveOnce(wPos, grid, path);
//         ttr(wPos, grid, path);


      }
      else if (key == 'p')
      {
         drawPath ^= true;
      }
      else if (key == 'c')
      {
         path.clear();
         initialize_grid(grid);
      }
   }

	/* Replace (and rename) the below with a depth first search.
		This code provided only as an example of moving in
		in one direction for one tile - it mostly is for illustrating
		how you might test the occupancy grid and add nodes to path!
	*/
   private boolean moveOnce(Point pos, GridValues[][] grid, List<Point> path)
   {
      Point rightN = new Point(pos.x +1, pos.y );
      Point leftN = new Point(pos.x -1, pos.y );
      Point downN = new Point(pos.x , pos.y -1);
      Point upN = new Point(pos.x , pos.y +1);

      //test if this is a valid grid cell
      if (withinBounds(rightN, grid)  &&
              grid[rightN.y][rightN.x] != GridValues.OBSTACLE &&
              grid[rightN.y][rightN.x] != GridValues.SEARCHED)
      {
         //check if my right neighbor is the goal
         if (grid[rightN.y][rightN.x] == GridValues.GOAL) {
            path.add(0, rightN);
            return true;
         }
         //set this value as searched
         return topl(rightN, grid, path);


      }
		return false;
   }



   private boolean topl (Point pos, GridValues [][] grid, List<Point> path) {
System.out.println("");
      //initialize next positions
         Point rightN = new Point(pos.x + 1, pos.y);
         Point leftN = new Point(pos.x - 1, pos.y);
         Point downN = new Point(pos.x, pos.y + 1);
         Point upN = new Point(pos.x, pos.y - 1);
         boolean right = false;
         boolean left = false;
         boolean up = false;
         boolean down = false;
            // mark current position as searched

            //check if traversable
      if (!traversable(leftN, grid, path) && !traversable(upN, grid, path) && !traversable(rightN, grid, path) && !traversable(downN, grid, path)) {
         markassearched(pos, grid, path);
         path.remove(pos);
         return false;
      }
      else if (traversable(pos, grid, path) && grid[pos.y][pos.x] == GridValues.GOAL) {
         System.out.println("GOAL");
         path.add(pos);
         markassearched(pos, grid, path);
         return true;
      }
      else
         {
         markassearched(pos, grid, path);


            if (traversable(rightN, grid, path)) {
               if (topl(rightN, grid, path)) {
                  System.out.println("RIGHT");
                  path.add(pos);
                  right = true;
               }
            }
            if (right){return true;}


            if (traversable(downN, grid, path)) {
               if (topl(downN, grid, path)) {
                  System.out.println("DOWN");
                  path.add(pos);
                  down = true;
               }
            }
            if (down){return true;}


         if (traversable(leftN, grid, path)) {
            if (topl(leftN, grid, path)) {
               System.out.println("LEFT");
               path.add(pos);
               left = true;
            }
         }
            if (left){return true;}


            if (traversable(upN, grid, path)) {
               if (topl(upN, grid, path)) {
                  System.out.println("UP");
                  path.add(pos);
                  up = true;
               }
            }
            if (up){return true;}



         else {
            System.out.println("NONE");
            return false;
         }
      }
   }

   private void markassearched(Point pos, GridValues[][] grid, List<Point> path) {
      if (traversable(pos, grid, path) ) {
          grid[pos.y][pos.x] = GridValues.SEARCHED;
      }
   }

   private Point tt (Point pos, GridValues[][] grid, List<Point> path) {
      System.out.println("");
      //initialize next positions
      Point rightN = new Point(pos.x + 1, pos.y);
      Point leftN = new Point(pos.x - 1, pos.y);
      Point downN = new Point(pos.x, pos.y + 1);
      Point upN = new Point(pos.x, pos.y - 1);
      List<Point> plis = new LinkedList<>();
      plis.add(leftN);
      plis.add(upN);
      plis.add(rightN);
      plis.add(downN);

      //check if goal
      if ((withinBounds(pos, grid) && grid[pos.y][pos.x] == GridValues.GOAL)) {
         System.out.println("GOAL");

         return null;
         //check if traversable
      }

      else {
         System.out.println(positionque.size());
         grid[pos.y][pos.x] = GridValues.SEARCHED;
//add to path

         for (Point point : plis) {
            if (traversable(point, grid, path)) {
               path.add(pos);
               return point;
            }
         }
         path.remove(pos);
         positionque.removeLast();
         return positionque.removeLast() ;
      }
   }

   private boolean traversable(Point rightN, GridValues[][] grid, List<Point> path) {
      return withinBounds(rightN, grid)  &&
              grid[rightN.y][rightN.x] != GridValues.OBSTACLE &&
              grid[rightN.y][rightN.x] != GridValues.SEARCHED;
   }

   private static boolean withinBounds(Point p, GridValues[][] grid) {
      return p.y >= 0 && p.y < grid.length &&
         p.x >= 0 && p.x < grid[0].length;
   }
}
