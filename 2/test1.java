class test1{
    public static void main(String[] args){
        // Количество тарелок, стартовое, среднее, назначение
        hanno(4,"X","Y","Z");
    }                                     
    public static void hanno(int n,String begin,String mid,String end){
        if(n==1){
            System.out.println(begin+" -> "+end);
        }else{
            hanno(n-1,begin,end,mid);
            System.out.println(begin+" -> "+end);
            hanno(n-1,mid,begin,end);
        }
    }
}