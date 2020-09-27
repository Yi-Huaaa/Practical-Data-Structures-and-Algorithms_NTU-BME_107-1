/*
////insertion sort starts
int order[] = new int[weight.length];
int idx = 0, tmp;
for ( int i = 0; i < C; i++ )
{
    if ( weight[i] > 0 ) {
        for ( int j = 0; j <= idx; j++) {
            if (j == idx || weight[i] > weight[order[j]]) {
                //unless to here  true

                for (int k = j; k < idx; k++){
                    order[k + 1] = order[k];
                }
                order[j] = i;
                idx++;
                break;
            }
        }
    }
}
for(int i = 0; i < 3; i++)
{
    tmp = order[i];
    System.out.println(weight[tmp] + " " + String.format("%.2f", point[tmp].x) + " " + String.format("%.2f", point[tmp].y));
}
//insertion sort ends
*/



/*
int order[] = new int[weight.length];
        int b = 0;
        for ( int i = 0; i < weight.length ; i++){
            if(weight[i] > 0){
                order[b] = i;
                b++;
            }
        }
 //3 max
        if( weight[order[2]] > weight[order[1]] && weight[order[2]] > weight[order[0]] ) {
            //321
            if (  weight[order[1]] > weight[order[0]] ){
                System.out.println(weight[order[2]]+ " " + String.format("%.2f", point[order[2]].x) + " " + String.format("%.2f", point[order[2]].y));
                System.out.println(weight[order[1]]+ " " + String.format("%.2f", point[order[1]].x) + " " + String.format("%.2f", point[order[1]].y));
                System.out.println(weight[order[0]]+ " " + String.format("%.2f", point[order[0]].x) + " " + String.format("%.2f", point[order[0]].y));
            }
            //312
            else{
                System.out.println(weight[order[2]]+ " " + String.format("%.2f", point[order[2]].x) + " " + String.format("%.2f", point[order[2]].y));
                System.out.println(weight[order[0]]+ " " + String.format("%.2f", point[order[0]].x) + " " + String.format("%.2f", point[order[0]].y));
                System.out.println(weight[order[1]]+ " " + String.format("%.2f", point[order[1]].x) + " " + String.format("%.2f", point[order[1]].y));
            }
        }
        //2 max
        else if( weight[order[1]] > weight[order[0]] && weight[order[1]] > weight[order[2]] ) {
            //213
            if (  weight[order[0]] > weight[order[2]] ){
                System.out.println(weight[order[1]]+ " " + String.format("%.2f", point[order[1]].x) + " " + String.format("%.2f", point[order[1]].y));
                System.out.println(weight[order[0]]+ " " + String.format("%.2f", point[order[0]].x) + " " + String.format("%.2f", point[order[0]].y));
                System.out.println(weight[order[2]]+ " " + String.format("%.2f", point[order[2]].x) + " " + String.format("%.2f", point[order[2]].y));
            }
            //231
            else{
                System.out.println(weight[order[1]]+ " " + String.format("%.2f", point[order[1]].x) + " " + String.format("%.2f", point[order[1]].y));
                System.out.println(weight[order[2]]+ " " + String.format("%.2f", point[order[2]].x) + " " + String.format("%.2f", point[order[2]].y));
                System.out.println(weight[order[0]]+ " " + String.format("%.2f", point[order[0]].x) + " " + String.format("%.2f", point[order[0]].y));
            }
        }
        //1 max
        else if( weight[order[0]] > weight[order[1]] && weight[order[0]] > weight[order[2]]) {
            //123
            if (  weight[order[1]] > weight[order[2]] ){
                System.out.println(weight[order[0]]+ " " + String.format("%.2f", point[order[0]].x) + " " + String.format("%.2f", point[order[0]].y));
                System.out.println(weight[order[1]]+ " " + String.format("%.2f", point[order[1]].x) + " " + String.format("%.2f", point[order[1]].y));
                System.out.println(weight[order[2]]+ " " + String.format("%.2f", point[order[2]].x) + " " + String.format("%.2f", point[order[2]].y));
            }
            //132
            else{
                System.out.println(weight[order[0]]+ " " + String.format("%.2f", point[order[0]].x) + " " + String.format("%.2f", point[order[0]].y));
                System.out.println(weight[order[2]]+ " " + String.format("%.2f", point[order[2]].x) + " " + String.format("%.2f", point[order[2]].y));
                System.out.println(weight[order[1]]+ " " + String.format("%.2f", point[order[1]].x) + " " + String.format("%.2f", point[order[1]].y));
            }
        }
        else
         {
            System.out.println(weight[order[0]]+ " " + String.format("%.2f", point[order[0]].x) + " " + String.format("%.2f", point[order[0]].y));
            System.out.println(weight[order[1]]+ " " + String.format("%.2f", point[order[1]].x) + " " + String.format("%.2f", point[order[1]].y));
            System.out.println(weight[order[2]]+ " " + String.format("%.2f", point[order[2]].x) + " " + String.format("%.2f", point[order[2]].y));

        }
 */