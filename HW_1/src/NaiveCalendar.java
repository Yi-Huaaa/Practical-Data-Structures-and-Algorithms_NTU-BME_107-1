public class NaiveCalendar implements MyCalendar {

    private static final int[] DAYS_OF_MONTH = {0, 31, 28, 31, 30, 31, 30,
            31, 31, 30, 31, 30, 31};
    private static final String[] WEEK_DAY = {"SUN", "MON", "TUE", "WED",
            "THU", "FRI", "SAT"};
    private static final int FIRST_DAY_OF_YEAR_ONE = 1;

    private int year;
    private int month;
    private int dayNumOfMonth;
    private boolean leap;
    private int leapCount;
    private int firstDayOYear;
    private int firstDayOfMonth;

    public NaiveCalendar(int year, int month) { // constructor
        if (year <= 0 || month < 1 || month > 12) {
            // todo 1
            //If the 'year' is not an positive, please use RuntimeException to print "Invalid year"
            //If the 'month' is not valid, please use RuntimeException to print "Invalid month"
            if(year <= 0){
                throw new RuntimeException("Invalid year");
            }
            else {
                throw new RuntimeException("Invalid month");
            }

        }

        this.year = year;
        this.month = month;

        leap = calculateLeap();
        dayNumOfMonth = calculateDayNumOfMonth();

        leapCount = calculateLeapCount();
        firstDayOYear = calculateFirstDayOfYear();
        firstDayOfMonth = calculateFirstDayOfMonth();
    }

    @Override
    public int getYear() {
        return year;
    }

    @Override
    public int getMonth() {
        return month;
    }

    @Override
    public int getDayNumOfMonth() {
        return dayNumOfMonth;
    }

    @Override
    public boolean isLeap() {
        return leap;
    }

    private boolean calculateLeap() {
        boolean i = false;
        // todo 2
        // check if it's leap
        if(year %4 == 0 && year %100 !=0) {
            i = true;
        }
        if(year%400 == 0){
            i = true;
        }
        return i;
    }

    private int calculateLeapCount() {

        // todo 3
        leapCount = (year-1) /4 - (year-1)/100 + (year-1)/400 ;
        return leapCount;
    }

    private int calculateDayNumOfMonth() {
        int dayNum = DAYS_OF_MONTH[month];

        if (month == 2 && leap) {
            dayNum++;
        }
        /////
        //System.out.println("in the leaf count");
        return dayNum;
    }

    private int calculateFirstDayOfYear() {
        // todo 4
        //公元0001-01-01 is Monday
        //前兩個是用來計算已過完的四的倍數年分
        firstDayOYear = ((year-1)*365+leapCount+1) % 7;
        //23444444444444System.out.println("first day of the year = " + firstDayOYear);
        return firstDayOYear;
    }

    private int calculateFirstDayOfMonth() {
        // todo 5 __ okok __unsure__ logic is T
        //out for-loop: decide z num of month
        for( int i = 1; i <= 12; i++ ){
            //inside for-loop decide accumulation of z before month
            //+ DAYS_OF_MONTH[j]
            int accumulationOfDays = 0;
            for ( int j = 0; j < i; j++ ){
                accumulationOfDays = DAYS_OF_MONTH[j] + accumulationOfDays;
            }
            if(month == i){
                firstDayOfMonth = (firstDayOYear + accumulationOfDays) % 7;
                if(year %4 == 0 && year %100 !=0) {
                        DAYS_OF_MONTH[2]++;
                        if(month >=3){
                            firstDayOfMonth++;
                        }
                }
                if(year%400 == 0){

                    //
                    DAYS_OF_MONTH[2]++;
                    if(month >=3){
                        firstDayOfMonth++;
                    }
                    //
                }
                /////System.out.println("accumulationOfDays = "+accumulationOfDays);
                /////System.out.println("first Day Of Month = "+firstDayOfMonth);
            }
        }
        ///System.out.println("yaya"+firstDayOfMonth);
        return firstDayOfMonth;
    }

    @Override
    public void printCalendar() {

        /////System.out.println(firstDayOfMonth);
        /////System.out.println("doub_qts number = " + ((firstDayOfMonth)%7));
        int doub_qts = (firstDayOfMonth)%7 ;
        for (String day : WEEK_DAY) {
            System.out.print(formatEntry(day));
        }
        System.out.println();
        //first for-loop --> print "" counts
        //second for-loop --> print tje number of the days
        for (int i = 1; i <= ( doub_qts % 7); i++){
            System.out.print(formatEntry(""));
        }

        for( int d = 1 ; d <= DAYS_OF_MONTH [month]; d++){
            d = doub_qts + d;

            if( d%7 != 0){
                d = d -doub_qts;
                System.out.print(formatEntry(String.valueOf(d)));
            }
            else{
                d = d -doub_qts;
                System.out.println(formatEntry(String.valueOf(d))+"\n");
            }
        }
		/*  todo: use function formatEntry and System.out.print to print the calendar 6
			System.out.print(formatEntry("")); // get "    "
			System.out.print(formatEntry(String.valueOf(10))); // get "  10"
		*/

        System.out.println();
    }

    private static String formatEntry(String entry) {
        return String.format("%1$4s", entry);
    }
}