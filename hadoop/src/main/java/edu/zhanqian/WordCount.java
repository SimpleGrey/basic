package edu.zhanqian;


import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
/**
 * @author zhanqian
 * @Date 2020/6/11 16:20
 * @Description TODO
 */
public class WordCount {

    public static class WordMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            //接收split中的输出结果作为map的输入
            String line = value.toString();
            String[] words = line.split(" ");

            //放到list中，每个text统计加1，作为map输出
            //写到context上下文环境中，输出到下一个执行过程的shuffle
            for (String word: words) {
                context.write(new Text(word), new IntWritable(1));
            }
        }
    }


    public static class WordReducer extends Reducer<Text, IntWritable, Text, LongWritable> {
        @Override
        protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {

            long count = 0;

            //此时的输入是shuffle的输出
            for (IntWritable v : values) {
                count += v.get();
            }
            context.write(key, new LongWritable(count));
        }
    }

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();

        Job job = Job.getInstance(conf);

        job.setMapperClass(WordMapper.class);
        job.setReducerClass(WordReducer.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);

        FileInputFormat.setInputPaths(job, "D:\\test\\test.txt");
        FileOutputFormat.setOutputPath(job, new Path("D:\\test\\out" + System.currentTimeMillis()));

        //



        job.waitForCompletion(true);
    }
}
