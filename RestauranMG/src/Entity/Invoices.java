
package Entity;

import java.util.Date;

public class Invoices {
    private int ID_Invoice;
    private int ID_Order;
    private int ID_Method;
    private int ID_Employee;
    private int ID_Tax;
    private Date InvoiceDate;
    private double TaxAmount;
    private double TotalAmount;
    private boolean paid;
    

    public Invoices(int ID_Invoice, int ID_Order, int ID_Method, int ID_Employee, int ID_Tax, Date InvoiceDate, double TaxAmount, double TotalAmount, boolean IsPaid) {
        this.ID_Invoice = ID_Invoice;
        this.ID_Order = ID_Order;
        this.ID_Method = ID_Method;
        this.ID_Employee = ID_Employee;
        this.ID_Tax = ID_Tax;
        this.InvoiceDate = InvoiceDate;
        this.TaxAmount = TaxAmount;
        this.TotalAmount = TotalAmount;
        this.paid = IsPaid;
    }

    public Invoices() {
    }

    public int getID_Order() {
        return ID_Order;
    }

    public void setID_Order(int ID_Order) {
        this.ID_Order = ID_Order;
    }

    public int getID_Method() {
        return ID_Method;
    }

    public void setID_Method(int ID_Method) {
        this.ID_Method = ID_Method;
    }

    public int getID_Employee() {
        return ID_Employee;
    }

    public void setID_Employee(int ID_Employee) {
        this.ID_Employee = ID_Employee;
    }

    public int getID_Tax() {
        return ID_Tax;
    }

    public void setID_Tax(int ID_Tax) {
        this.ID_Tax = ID_Tax;
    }

    
    public int getID_Invoice() {
        return ID_Invoice;
    }

    public void setID_Invoice(int ID_Invoice) {
        this.ID_Invoice = ID_Invoice;
    }

    public Date getInvoiceDate() {
        return InvoiceDate;
    }

    public void setInvoiceDate(Date InvoiceDate) {
        this.InvoiceDate = InvoiceDate;
    }

    public double getTaxAmount() {
        return TaxAmount;
    }

    public void setTaxAmount(double TaxAmount) {
        this.TaxAmount = TaxAmount;
    }

    public double getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(double TotalAmount) {
        this.TotalAmount = TotalAmount;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean IsPaid) {
        this.paid = IsPaid;
    }
    @Override
    public String toString() {
        return "Invoice{" +
                "idInvoice=" + ID_Invoice +
                ", idOrder=" + ID_Order + 
                ", idMethod=" + ID_Method + 
                ", idEmployee=" + ID_Employee + 
                ", idTax=" + ID_Tax + 
                ", invoiceDate=" + InvoiceDate +
                ", taxAmount=" + TaxAmount +
                ", totalAmount=" + TotalAmount +
                ", isPaid=" + paid +
                '}';
    }
}

