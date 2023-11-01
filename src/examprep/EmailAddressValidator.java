public class EmailAddressValidator {


    public static boolean validate(String email){
        //One @
        if(!email.contains("@") || email.indexOf("@") != email.lastIndexOf("@")){
            return false;
        }


        if(email.startsWith("@") || email.endsWith("@") || email.startsWith(".") || email.endsWith(".")){
            return false;
        }

        int atSignPos = email.indexOf("@");
        if(email.charAt(atSignPos -1) == '.' || email.charAt(atSignPos +1) == '.'){
            return false;
        }

        for(int i = 0; i < email.length()-1; i++){
            if(email.charAt(i) == '.' && email.charAt(i+1) == '.'){
                return false;
            }
        }


        return true;
    }

}
