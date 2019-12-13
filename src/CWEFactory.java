//This will create the required CWE instances
public class CWEFactory {

    public CWE getCWE (CWE CWEType){
        if (CWEType == null){
            return null;
        }else if (CWEType.equals("CWE_190")){
            return new CWE_190();
        }else if (CWEType.equals("CWE_191")){
            return new CWE_191();
        }else if (CWEType.equals("CWE_682")){
            return new CWE_682();
        }
        return null;
    }
}
