import  java.io.FileReader;
import java.io.BufferedReader;
import java.util.Arrays;

public class Mapping {

    public static void main(String[] args) throws Exception {

        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            String data = br.readLine();
            //readCount = 最上面的數字
            int readCount = Integer.parseInt(data);
            //lines = 文件總行數，自0開始數
            int lines = 0;
            //正確表示String 的表示方法： String reference = new String()
            String strs;
            String[] tmp = new  String[readCount+1];
            String[] readsArray = new String[1];

            while ((strs = br.readLine()) != null) { //readLine()依序讀取檔案內的一行文字
                lines ++ ;  //每讀一行，num就加1
            }
            //System.out.println(lines);
            //System.out.println(readCount);
            //System.out.println(lines-readCount);
            String[] tmp_seq = new String[1];
            String[] seq = new String[(999999)];//大小先亂開
            BufferedReader na = new BufferedReader(new FileReader(args[0]));
            for ( int k = 0; k <= lines; k++){

                if (k <= readCount){
                    tmp[k] = na.readLine();
                    //System.out.println(tmp[k]);
                    /*
                    tmp [k] 輸出長相：OKOK
                    4
                    CACT
                    GCCAT
                    AGCTG
                    ACTGGA
                     */
                }
                //else那邊有問題ref切ㄉ不對，以下有問題
                else{
                    tmp_seq[0] = na.readLine();
                    //System.out.println(tmp_seq[0]);
                    //tmp_seq[0]：AGCTGAGCACTGGAGTGGAGTTTTCCTGTGGAGAGGAGCCATGCCCACTGTAGAG
                }
            }
            String ref;
            String sub_ref;
            ref = Arrays.toString(tmp_seq);
            sub_ref = ref.substring(1,(ref.length()-1));
            //System.out.println(sub_ref);


            //將reference 切成string, ref 即為tmp_seq[0]

            String len ;
            //
            int[] counting = new int [readCount+1];
            //第一個for loop 用來找出seqㄉ長度，分不同次去切
            //第二ㄍfor loop 切出比對片段

            for (int i = 1; i <= readCount; i++ ){
                int tmp_len =0;
                readsArray[0] = tmp[i];
                len = Arrays.toString(readsArray);
                tmp_len = (len.length()-2);
                //System.out.println(tmp_len);

                //new comparison 自 tmp[1]~ tmp[readCount]
                for (int j = 0; (j + tmp_len) <= (sub_ref.length()); j++) {
                    String sa;
                    //System.out.println(j);
                    sa= sub_ref.substring(j, (j + tmp_len));
                    seq[j] = sa;
                    //System.out.println(seq[j]);
                    //System.out.println(j+","+(j+tmp_len));
                    //start to comparison
                    if(tmp[i].equals(seq[j])){
                        counting[i] += 1;

                    }
                }
            }
            int output1 = 0;
            int output2 = 0;

            for (int i = 0; i<=(readCount) ; i++){

                if (counting[i] > 0){
                    output1++;
                }
                if (counting[i] > 1){
                    output2++;
                }
            }

            System.out.println(output1);
            System.out.println(output2);

        }

    }
}