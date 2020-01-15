//This will create the required CWE instances
public class CWEFactory {

    public CWE getCWE (String CWEType){
        if (CWEType == null){
            return null;
        }else if (CWEType.equals("CWE_190")){
            return new CWE_190();
        }else if (CWEType.equals("CWE_191")){
            return new CWE_191();
        }else if (CWEType.equals("CWE_266")){
            return new CWE_266();
        }else if (CWEType.equals("CWE_272")){
            return new CWE_272();
        }else if (CWEType.equals("CWE_311")){
            return new CWE_311();
        }else if (CWEType.equals("CWE_359")){
            return new CWE_359();
        }else if (CWEType.equals("CWE_369")){
            return new CWE_369();
        }else if (CWEType.equals("CWE_375")){
            return new CWE_375();
        }else if (CWEType.equals("CWE_397")){
            return new CWE_397();
        }else if (CWEType.equals("CWE_459")){
            return new CWE_459();
        }else if (CWEType.equals("CWE_486")){
            return new CWE_486();
        }else if (CWEType.equals("CWE_492")){
            return new CWE_492();
        }else if (CWEType.equals("CWE_493")){
            return new CWE_493();
        }else if (CWEType.equals("CWE_499")){
            return new CWE_499();
        }else if (CWEType.equals("CWE_500")){
            return new CWE_500();
        }else if (CWEType.equals("CWE_502")){
            return new CWE_502();
        }else if (CWEType.equals("CWE_532")){
            return new CWE_532();
        }else if (CWEType.equals("CWE_581")){
            return new CWE_581();
        }else if (CWEType.equals("CWE_682")){
            return new CWE_682();
        }else if (CWEType.equals("CWE_766")){
            return new CWE_766();
        }
        return null;
    }
}
