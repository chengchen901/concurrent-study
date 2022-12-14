package com.study.concurrent.period9;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Demo2_ExecuteTasks {

    static ArrayList<String> urls = new ArrayList<String>(){
        {
            add("http://www.baidu.com");
            add("http://www.sina.com");
            add("http://www.baidu.com");
            add("http://www.sina.com");
            add("http://www.baidu.com");
            add("http://www.sina.com");
            add("http://www.baidu.com");
            add("http://www.sina.com");
            add("http://www.baidu.com");
            add("http://www.sina.com");
            add("http://www.baidu.com");
            add("http://www.sina.com");
            add("http://www.baidu.com");
            add("http://www.sina.com");
            add("http://www.baidu.com");
            add("http://www.sina.com");
            add("http://www.baidu.com");
            add("http://www.sina.com");
            add("http://www.baidu.com");
            add("http://www.sina.com");
            add("http://www.baidu.com");
            add("http://www.sina.com");
            add("http://www.baidu.com");
            add("http://www.sina.com");
            add("http://www.baidu.com");
            add("http://www.sina.com");
            add("http://www.baidu.com");
            add("http://www.sina.com");
            add("http://www.baidu.com");
            add("http://www.sina.com");
            add("http://www.baidu.com");
            add("http://www.sina.com");
            add("http://www.baidu.com");
            add("http://www.sina.com");
            add("http://www.baidu.com");
            add("http://www.sina.com");
            add("http://www.baidu.com");
            add("http://www.sina.com");
            add("http://www.baidu.com");
            add("http://www.sina.com");
            add("http://www.baidu.com");
            add("http://www.sina.com");
            add("http://www.baidu.com");
            add("http://www.sina.com");
            add("http://www.baidu.com");
            add("http://www.sina.com");
            add("http://www.baidu.com");
            add("http://www.sina.com");
            add("http://www.baidu.com");
            add("http://www.sina.com");
            add("http://www.baidu.com");
            add("http://www.sina.com");
            add("http://www.baidu.com");
            add("http://www.sina.com");
            add("http://www.baidu.com");
            add("http://www.sina.com");
            add("http://www.baidu.com");
            add("http://www.sina.com");
            add("http://www.baidu.com");
            add("http://www.sina.com");
            add("http://www.baidu.com");
            add("http://www.sina.com");
            add("http://www.baidu.com");
            add("http://www.sina.com");
            add("http://www.baidu.com");
            add("http://www.sina.com");
            add("http://www.baidu.com");
            add("http://www.sina.com");
            add("http://www.baidu.com");
            add("http://www.sina.com");
            add("http://www.baidu.com");
            add("http://www.sina.com");
            add("http://www.baidu.com");
            add("http://www.sina.com");
            add("http://www.baidu.com");
            add("http://www.sina.com");
            add("http://www.baidu.com");
            add("http://www.sina.com");
            add("http://www.baidu.com");
            add("http://www.sina.com");
            add("http://www.baidu.com");
            add("http://www.sina.com");
            add("http://www.baidu.com");
            add("http://www.sina.com");
            add("http://www.baidu.com");
            add("http://www.sina.com");
            add("http://www.baidu.com");
            add("http://www.sina.com");
            add("http://www.baidu.com");
            add("http://www.sina.com");
        }
    };


    public static void main(String args[]) throws ExecutionException, InterruptedException {


        //??????????????????????????????????????????????????????????????????????????????????????????
        int size = urls.size();

        System.out.println(size);

        //?????????????????????
        int groupSize = 10;
        //???????????????
        int groupCount = (size -1) /groupSize +1;

        ExecutorService pool = Executors.newFixedThreadPool(3);
        List<Future> futures = new ArrayList<>();

        //????????????
        for (int groupIndex=0; groupIndex < groupCount -1; groupIndex++){
            int leftIndex = groupSize * groupIndex;
            int rightIndex = groupSize * (groupIndex + 1);

            ///System.out.println(leftIndex + ":" + rightIndex);
            Future<String> future = pool.submit(new Task(leftIndex, rightIndex));
            futures.add(future);
        }
        int leftIndex = groupSize * (groupCount -1);
        int rightIndex = size;
        //System.out.println(leftIndex + ":" + rightIndex);
        Future<String> future = pool.submit(new Task(leftIndex, rightIndex));
        futures.add(future);

        //??????????????????

        for (Future<String> item : futures){
            System.out.println(item.get());
        }



    }




    public static   String doRequest(String url){
        //??????????????????
        return "Kody ... test ... " + url + "\n";
    }


   static class Task implements Callable<String>{

        private int start;
        private int end;

        public Task(int start, int end){
            this.start = start;
            this.end = end;
        }

        @Override
        public String call() throws Exception {
            String result = "";
            for (int i=start; i<end; i++){
                result += doRequest(urls.get(i));
            }
            return result;
        }
    }

}
