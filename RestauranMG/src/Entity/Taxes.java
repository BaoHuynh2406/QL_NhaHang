
package Entity;
public class Taxes {
    private int ID_Tax;
    private String TaxName;
    private double TaxRate;

    public Taxes(int ID_Tax, String TaxName, double TaxRate) {
        this.ID_Tax = ID_Tax;
        this.TaxName = TaxName;
        this.TaxRate = TaxRate;
    }

    public int getID_Tax() {
        return ID_Tax;
    }

    public void setID_Tax(int ID_Tax) {
        this.ID_Tax = ID_Tax;
    }

    public String getTaxName() {
        return TaxName;
    }

    public void setTaxName(String TaxName) {
        this.TaxName = TaxName;
    }

    public double getTaxRate() {
        return TaxRate;
    }

    public void setTaxRate(double TaxRate) {
        this.TaxRate = TaxRate;
    }
    @Override
    public String toString() {
        return "Tax{" +
                "idTax=" + ID_Tax +
                ", taxName='" + TaxName + '\'' +
                ", taxRate=" + TaxRate +
                '}';
    }
}
