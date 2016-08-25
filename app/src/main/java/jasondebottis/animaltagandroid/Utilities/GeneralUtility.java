package jasondebottis.animaltagandroid.Utilities;

public class GeneralUtility
{
    public static boolean IsNullOrEmpty(String inString)
    {
        if (inString.isEmpty() || inString == null)
        {
            return true;
        }
        return false;
    }


}
