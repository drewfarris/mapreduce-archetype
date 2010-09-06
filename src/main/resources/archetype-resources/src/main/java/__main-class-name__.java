#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import org.apache.hadoop.conf.Configured;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class ${main-class-name} extends Configured implements Tool {
	
    public int run(String [] args) throws Exception {
        Job job = new Job(getConf());
        job.setJarByClass(${main-class-name}.class);
        job.setJobName("${artifactId}");
	
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
	
        job.setMapperClass(${main-class-name}Mapper.class);
        job.setCombinerClass(${main-class-name}Reducer.class);
        job.setReducerClass(${main-class-name}Reducer.class);
	
        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);
	
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        boolean success = job.waitForCompletion(true);
        return success ? 0 : 1;
    }
	
    public static void main(String[] args) throws Exception {
        int ret = ToolRunner.run(new ${main-class-name}(), args);
        System.exit(ret);
    }
}
