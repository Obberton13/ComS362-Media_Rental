package MediaRental;

public class RentalPricingStrategy {

    private double _standardRentalCharge = 0;
    private int _standardRentalLength = 0;
    private double _dailyOverdueCharge = 0;
    private String _name = "";

    public RentalPricingStrategy(double standardRentalCharge, int standardRentalLength, double dailyOverdueCharge, String name) {
        _standardRentalCharge = standardRentalCharge;
        _standardRentalLength = standardRentalLength;
        _dailyOverdueCharge = dailyOverdueCharge;
        _name = name;
    }

    public void setStandardRentalCharge(int standardRentalCharge) {
        _standardRentalCharge = standardRentalCharge;
    }

    public void setStandardRentalLength(int standardRentalLength) {
        _standardRentalLength = standardRentalLength;
    }

    public void setDailyOverdueCharge(int dailyOverdueCharge) {
        _dailyOverdueCharge = dailyOverdueCharge;
    }

    public double getStandardRentalCharge() {
        return _standardRentalCharge;
    }

    public int getStandardRentalLength() {
        return _standardRentalLength;
    }

    public double getDailyOverdueCharge() {
        return _dailyOverdueCharge;
    }

    public String getName() {
        return _name;
    }

    public double getRentalCharge(int daysRented) {
        double charge = _standardRentalCharge;
        if (daysRented > _standardRentalLength) {
            charge += (daysRented - _standardRentalLength) * _dailyOverdueCharge;
        }
        return charge;
    }
}
