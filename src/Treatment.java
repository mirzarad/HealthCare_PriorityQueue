import java.util.Comparator;

/**
 * @author Mirza Radoncic and Ritwik Banerjee
 */

public class Treatment {
    
    String name;
    String diseaseTreated;
    double probabilityOfSuccess;
    double pricePerUnit;

    // DEFAULT CONSTRUCTOR:
    Treatment(){
        this(null,null,0,0);
    }

    // CONSTRUCTOR WITH ALL NECESSARY ARGUMENTS:
    Treatment(String name, String diseaseTreated, double probabilityOfSuccess, double pricePerUnit){
        this.name = name;
        this.diseaseTreated = diseaseTreated;
        this.probabilityOfSuccess = probabilityOfSuccess;
        this.pricePerUnit = pricePerUnit;
    }

    // PRICE BASED COMPARATOR:
    public static class PriceBasedTreatmentComparator implements Comparator<Treatment>{
        public int compare(Treatment x, Treatment y){
            if(x.pricePerUnit > y.pricePerUnit) return -1;
            if(x.pricePerUnit < y.pricePerUnit) return 1;
            return 0;
        }
    }

    // SUCCESS BASED COMPARATOR:
    public static class SuccessBasedTreatmentComparator implements Comparator<Treatment>{
        public int compare(Treatment x, Treatment y){
            if(x.probabilityOfSuccess > y.probabilityOfSuccess) return 1;
            if(x.probabilityOfSuccess < y.probabilityOfSuccess) return -1;
            return 0;
        }
    }

    // toString METHOD:
    public String toString(){
        String str = "";
        str += "Treatment name = {'";
        str += name + "', diseaseTreated = '";
        str += diseaseTreated + "', probabilityOfSuccess = ";
        str += String.valueOf(probabilityOfSuccess) + ", pricePerUnit = ";
        str += String.valueOf(pricePerUnit) + "} ";
        return str;
    }

    // DON'T TOUCH THE HASHCODE.
    @Override
    public int hashCode() {
        int  result;
        long temp;
        result = name.hashCode();
        result = 31 * result + diseaseTreated.hashCode();
        temp = Double.doubleToLongBits(probabilityOfSuccess);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(pricePerUnit);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
