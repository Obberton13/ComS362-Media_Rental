package MediaRental;

public class FrequentCustomerStrategy
{
    private int fixedPoints = 0;
    private int pointsPerDay = 0;
    private String name = "";

    public FrequentCustomerStrategy(int fPoints, int ppd, String name)
    {
        fixedPoints = fPoints;
        pointsPerDay = ppd;
        this.name = name;
    }

    public void setFixedPoints(int fPoints)
    {
        fixedPoints = fPoints;
    }

    public void setPointsPerDay(int ppd)
    {
        fixedPoints = ppd;
    }

    public int getFixedPoints()
    {
        return fixedPoints;
    }

    public int getPointsPerDay()
    {
        return pointsPerDay;
    }

    public String getName()
    {
        return name;
    }

    public int getPoints(int days)
    {
        return fixedPoints + (pointsPerDay * days);
    }


}
