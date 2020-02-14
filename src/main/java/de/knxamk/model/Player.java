package de.knxamk.model;

import de.knxamk.model.stage.Stage;
import de.knxamk.model.stage.stageComponents.StageObject;

public class Player implements StageObject
{
    public Stage currentMap = null;

    public Player(Stage m1)
    {
        this.currentMap = m1;
        this.currentMap.player = this;
    }

    public void changeMap(Stage m)
    {
        this.currentMap.player = null;
        this.currentMap = m;
    }

    @Override
    public void action()
    {
    }
}
