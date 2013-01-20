package eu.tjenwellens.bss.data.commands.dataToClient;

/**
 *
 * @author Tjen
 */
public class SDataFaction implements DataFaction
{
    private int id;
    private String name;

    public SDataFaction(int id, String name)
    {
        this.id = id;
        this.name = name;
    }

    @Override
    public int getId()
    {
        return id;
    }

    @Override
    public String getName()
    {
        return name;
    }
}
