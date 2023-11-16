
package Entity;
public class PaymentMethods {
    private int ID_Method;
    private String MethodName;

    public PaymentMethods(int ID_Method, String MethodName) {
        this.ID_Method = ID_Method;
        this.MethodName = MethodName;
    }

    public PaymentMethods() {
    }

    public int getID_Method() {
        return ID_Method;
    }

    public void setID_Method(int ID_Method) {
        this.ID_Method = ID_Method;
    }

    public String getMethodName() {
        return MethodName;
    }

    public void setMethodName(String MethodName) {
        this.MethodName = MethodName;
    }
    @Override
    public String toString() {
        return "PaymentMethod{" +
                "idMethod=" + ID_Method +
                ", methodName='" + MethodName + '\'' +
                '}';
    }
}
