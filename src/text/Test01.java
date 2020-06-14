
public class Test01{
    public static void main(String[] args) {
        int array[][]={{1,2,8,9},{4,7,10,13}};
        System.out.println(new Test01().Find(7,array));

    }
        public boolean Find(int target, int [][] array) {
            int cl = array.length;
            int row =array[0].length;
            //如果当前值大于数组最大值或小于数组最小值  false
            if(cl==0||row==0||array==null || target <array[0][0]||target>array[cl-1][row-1]){
                return false;
            }
            return compare(target,0,row-1,array);
        }
        //定义一个比较数值得方法
        private boolean compare(int target,int clIndex,int rowIndex,int [][]array){
            while(clIndex>array.length || rowIndex <0){
                return false;
            }
            int current=array[clIndex][rowIndex];
            if(target==current)return true;
            if(target>current){
                // if(++clIndex>array.length )return false;
                return compare(target,++clIndex,rowIndex,array);}
            if(target<current){
                //  if(--rowIndex<0) return false;
                return compare(target,clIndex,--rowIndex,array);}
            return false;
        }
    }