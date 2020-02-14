package de.knxamk.model.stage;

import de.knxamk.model.Player;
import de.knxamk.model.stage.stageComponents.Free;
import de.knxamk.model.stage.stageComponents.StageObject;
import de.knxamk.model.stage.stageComponents.StageObjectNotPassable;
import de.knxamk.model.stage.stageComponents.Tree;
import de.knxamk.util.TwoTouple;
import de.knxamk.util.exceptions.IndexNotFittingException;

public class Stage
{
    public TwoTouple<Integer> stageDimensions;
    public StageObject[][] mapData;
    public TwoTouple<Integer> currentPos;
    public Player player;


    /**
     * Contructor for map
     * 0: width INTEGER > 2
     * 1: height INTEGER > 2
     * throws IndexNotFittingException
     */
    public Stage(int width, int height) throws IndexNotFittingException
    {

        if (width < 3 | height < 3)
        {
            throw new IndexNotFittingException(String.format("Stage with width %d and height %d could not be created!", width, height));
        }

        this.stageDimensions = new TwoTouple<>(width, height);
        this.mapData = new StageObject[width][height];
        this.currentPos = new TwoTouple<>(width / 2, height / 2);

        for (int i = 0; i < width; i++)
        {
            for (int j = 0; j < height; j++)
            {
                if (i == 0 | i == width - 1 | j == 0 | j == height - 1)
                {
                    mapData[i][j] = new Tree();
                }
                else
                {
                    mapData[i][j] = new Free();
                }
            }
        }
    }

    public void changePosition(int widthPos, int heightPos) throws IndexNotFittingException
    {
        if (!isMapBlockInBounds(widthPos, heightPos))
        {
            // TODO What does it do?
            throw new IndexNotFittingException("WHAT THE FUCK DOES THIS DO?????");
        }
        this.currentPos.change(widthPos, heightPos);
    }

    /**
     * returns true if block on map is free
     * else returns false
     */
    public boolean isMapBlockFree(int w, int h)
    {
        return !(mapData[w][h] instanceof StageObjectNotPassable);
    }

    /**
     * checks if position is in bounds of map
     * returns true if it is
     * False if not
     */
    public boolean isMapBlockInBounds(int w, int h)
    {
        return !(w < 0 | w >= stageDimensions.get(0) |
                h < 0 | h >= stageDimensions.get(1));
    }

    public StageObject specMapObject(int w, int h)
    {
        if (!this.isMapBlockInBounds(w, h))
        {
            return null;
        }
        else if (this.currentPos.get(0) == w && this.currentPos.get(1) == h)
        {
            return this.player;
        }
        else
        {
            return mapData[w][h];
        }
    }

}
