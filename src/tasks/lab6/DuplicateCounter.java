public class DuplicateCounter{
    public static void main(String[] args)  {
        if(args.length > 0){
            System.out.println(transformStr(args[0]));
        }
    }
    
    private static String transformStr(String str){
        StringBuilder sb = new StringBuilder(str);

        if(str.isEmpty()) return str;

        int numberOfDuplicates = 0;
        int index = 0;
        char previous = (char)((int)(sb.charAt(0)) + 1);//should be different from the first character

        while(index < sb.length()) {
            char current = sb.charAt(index);
            if (current == previous) {
                numberOfDuplicates++;
                sb.deleteCharAt(index);
                continue;
            }else {
                if (numberOfDuplicates > 1) {
                    sb.insert(index, numberOfDuplicates);
                    index++;
                }
                previous = current;
                numberOfDuplicates = 1;
            }
            index++;
        }
        if(numberOfDuplicates > 1) sb.append(numberOfDuplicates);

        return sb.toString();
    }
}